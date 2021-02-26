package customreporter;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.properties.SystemAttributesExtractor;
import com.epam.ta.reportportal.ws.model.FinishExecutionRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ;
import cucumber.api.*;
import cucumber.api.event.*;
import gherkin.pickles.PickleTag;
import io.reactivex.Maybe;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rp.com.google.common.base.Strings;
import rp.com.google.common.base.Supplier;
import rp.com.google.common.base.Suppliers;

import java.util.*;

public abstract class AbstractReporter implements ConcurrentEventListener {
    Supplier<Launch> launch;
    private static final Map<String, Supplier<Maybe<String>>> rootSuiteId = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractReporter.class);
    private final Map<String, RunningContext.FeatureContext> currentFeatureContextMap = new HashMap<>();
    private final Map<Pair<String, String>, RunningContext.ScenarioContext> currentScenarioContextMap = new HashMap<>();
    private final Map<Long, RunningContext.ScenarioContext> threadCurrentScenarioContextMap = new HashMap<>();
    private final Map<String, Date> featureEndTime = new HashMap<>();

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this.getTestRunStartedHandler());
        publisher.registerHandlerFor(TestSourceRead.class, this.getTestSourceReadHandler());
        publisher.registerHandlerFor(TestCaseStarted.class, this.getTestCaseStartedHandler());
        publisher.registerHandlerFor(TestStepStarted.class, this.getTestStepStartedHandler());
        publisher.registerHandlerFor(TestStepFinished.class, this.getTestStepFinishedHandler());
        publisher.registerHandlerFor(TestCaseFinished.class, this.getTestCaseFinishedHandler());
        publisher.registerHandlerFor(TestRunFinished.class, this.getTestRunFinishedHandler());
        publisher.registerHandlerFor(EmbedEvent.class, this.getEmbedEventHandler());
        publisher.registerHandlerFor(WriteEvent.class, this.getWriteEventHandler());
    }

    public RunningContext.ScenarioContext getCurrentScenarioContext() {
        return this.threadCurrentScenarioContextMap.get(Thread.currentThread().getId());
    }

    protected void beforeLaunch() {
        this.startLaunch();
    }

    protected ReportPortal buildReportPortal() {
        return ReportPortal.builder().build();
    }

    private void finishRootItem() {
        rootSuiteId.forEach((k,v) -> {
            Utils.finishTestItem(this.launch.get(), rootSuiteId.get(k).get());
        });
    }

    protected void afterLaunch() {
        this.finishRootItem();
        FinishExecutionRQ finishLaunchRq = new FinishExecutionRQ();
        finishLaunchRq.setEndTime(Calendar.getInstance().getTime());
        this.launch.get().finish(finishLaunchRq);
    }

    private void beforeScenario(RunningContext.FeatureContext currentFeatureContext, RunningContext.ScenarioContext currentScenarioContext, String scenarioName) {
        Maybe<String> id = Utils.startNonLeafNode(this.launch.get(), currentFeatureContext.getFeatureId(), scenarioName, currentFeatureContext.getUri() + ":" + currentScenarioContext.getLine(), currentScenarioContext.getAttributes(), this.getScenarioTestItemType());
        currentScenarioContext.setId(id);
    }

    private void afterScenario(TestCaseFinished event) {
        RunningContext.ScenarioContext currentScenarioContext = this.getCurrentScenarioContext();
        for (Map.Entry<Pair<String, String>, RunningContext.ScenarioContext> scenarioContext : this.currentScenarioContextMap.entrySet()) {
            if (scenarioContext.getValue().getLine() == currentScenarioContext.getLine()) {
                this.currentScenarioContextMap.remove(scenarioContext.getKey());
                Date endTime = Utils.finishTestItem(this.launch.get(), currentScenarioContext.getId(), event.result.getStatus().toString());
                String featureURI = (String) ((Pair) scenarioContext.getKey()).getValue();
                this.featureEndTime.put(featureURI, endTime);
                break;
            }
        }
    }

    private String buildFeatureNode(TestCase testCase) {
        RunningContext.FeatureContext featureContext = (new RunningContext.FeatureContext()).processTestSourceReadEvent(testCase);
        String featureKeyword = featureContext.getFeature().getKeyword();
        String featureName = featureContext.getFeature().getName();
        Utils.buildNodeName(featureKeyword, ": ", featureName, null);
        return featureContext.getUri();
    }

    private void startLaunch() {
        this.launch = Suppliers.memoize(new Supplier<Launch>() {
            private final Date startTime = Calendar.getInstance().getTime();
            public Launch get() {
                ReportPortal reportPortal = AbstractReporter.this.buildReportPortal();
                ListenerParameters parameters = reportPortal.getParameters();
                StartLaunchRQ rq = new StartLaunchRQ();
                rq.setName(parameters.getLaunchName());
                rq.setStartTime(this.startTime);
                rq.setMode(parameters.getLaunchRunningMode());
                rq.setAttributes(parameters.getAttributes());
                rq.getAttributes().addAll(SystemAttributesExtractor.extract("agent.properties", AbstractReporter.class.getClassLoader()));
                rq.setDescription(parameters.getDescription());
                rq.setRerun(parameters.isRerun());
                if (!Strings.isNullOrEmpty(parameters.getRerunOf())) {
                    rq.setRerunOf(parameters.getRerunOf());
                }
                if (null != parameters.getSkippedAnIssue()) {
                    ItemAttributesRQ skippedIssueAttribute = new ItemAttributesRQ();
                    skippedIssueAttribute.setKey("skippedIssue");
                    skippedIssueAttribute.setValue(parameters.getSkippedAnIssue().toString());
                    skippedIssueAttribute.setSystem(true);
                    rq.getAttributes().add(skippedIssueAttribute);
                }

                return reportPortal.newLaunch(rq);
            }
        });
    }

    protected abstract void beforeStep(TestStep var1);

    protected abstract void afterStep(Result var1);

    protected abstract void beforeHooks(HookType var1);

    protected abstract void afterHooks(Boolean var1);

    protected abstract void hookFinished(HookTestStep var1, Result var2, Boolean var3);

    protected abstract String getFeatureTestItemType();

    protected abstract String getScenarioTestItemType();

    void reportResult(Result result, String message) {
        String cukesStatus = result.getStatus().toString();
        String level = Utils.mapLevel(cukesStatus);
        String errorMessage = result.getErrorMessage();
        if (errorMessage != null) {
            Utils.sendLog(errorMessage, level, null);
        }
        if (message != null) {
            Utils.sendLog(message, level, null);
        }
    }

    private void embedding(String mimeType, byte[] data) {
        SaveLogRQ.File file = new SaveLogRQ.File();
        String embeddingName;
        try {
            embeddingName = MimeTypes.getDefaultMimeTypes().forName(mimeType).getType().getType();
        } catch (MimeTypeException var6) {
            LOGGER.warn("Mime-type not found", var6);
            embeddingName = "embedding";
        }
        file.setName(embeddingName);
        file.setContent(data);
        Utils.sendLog(embeddingName, "UNKNOWN", file);
    }

    private void write(String text) {
        Utils.sendLog(text, "INFO", null);
    }

    private boolean isBefore(TestStep step) {
        return HookType.Before == ((HookTestStep)step).getHookType();
    }

    private EventHandler<TestRunStarted> getTestRunStartedHandler() {
        return event -> AbstractReporter.this.beforeLaunch();
    }

    private EventHandler<TestSourceRead> getTestSourceReadHandler() {
        return event -> RunningContext.FeatureContext.addTestSourceReadEvent(event.uri, event);
    }

    private EventHandler<TestCaseStarted> getTestCaseStartedHandler() {
        return AbstractReporter.this::handleStartOfTestCase;
    }

    private EventHandler<TestStepStarted> getTestStepStartedHandler() {
        return AbstractReporter.this::handleTestStepStarted;
    }

    private EventHandler<TestStepFinished> getTestStepFinishedHandler() {
        return AbstractReporter.this::handleTestStepFinished;
    }

    private EventHandler<TestCaseFinished> getTestCaseFinishedHandler() {
        return AbstractReporter.this::afterScenario;
    }

    private EventHandler<TestRunFinished> getTestRunFinishedHandler() {
        return event -> {
            AbstractReporter.this.handleEndOfFeature();
            AbstractReporter.this.afterLaunch();
        };
    }

    private EventHandler<EmbedEvent> getEmbedEventHandler() {
        return event -> AbstractReporter.this.embedding(event.mimeType, event.data);
    }

    private EventHandler<WriteEvent> getWriteEventHandler() {
        return event -> AbstractReporter.this.write(event.text);
    }

    private void handleEndOfFeature() {
        this.currentFeatureContextMap.values().iterator();
        for(RunningContext.FeatureContext featureContext : this.currentFeatureContextMap.values()){
            Date featureCompletionDateTime = this.featureEndTime.get(featureContext.getUri());
            Utils.finishFeature(this.launch.get(), featureContext.getFeatureId(), featureCompletionDateTime);
        }
        this.currentFeatureContextMap.clear();
    }

    private void handleStartOfTestCase(TestCaseStarted event) {
        TestCase testCase = event.testCase;
        String featureURI = this.buildFeatureNode(testCase);
        RunningContext.FeatureContext currentFeatureContext = this.currentFeatureContextMap.get(featureURI);
        currentFeatureContext = currentFeatureContext == null ? this.createFeatureContext(testCase, featureURI) : currentFeatureContext;
        if (!currentFeatureContext.getUri().equals(testCase.getUri())) {
            throw new IllegalStateException("Scenario URI does not match Feature URI.");
        } else {
            RunningContext.ScenarioContext scenarioContext = currentFeatureContext.getScenarioContext(testCase);
            String scenarioName = Utils.buildNodeName(scenarioContext.getKeyword(), ": ", scenarioContext.getName(), scenarioContext.getOutlineIteration());
            Pair<String, String> scenarioNameFeatureURI = Pair.of(scenarioName, currentFeatureContext.getUri());
            RunningContext.ScenarioContext currentScenarioContext = this.currentScenarioContextMap.get(scenarioNameFeatureURI);
            if (currentScenarioContext == null) {
                currentScenarioContext = currentFeatureContext.getScenarioContext(testCase);
                this.currentScenarioContextMap.put(scenarioNameFeatureURI, currentScenarioContext);
                this.threadCurrentScenarioContextMap.put(Thread.currentThread().getId(), currentScenarioContext);
            }
            this.beforeScenario(currentFeatureContext, currentScenarioContext, scenarioName);
        }
    }

    private String getEpicName(TestCase testCase) {
        String epic = "Miscellaneous Features";
        List<PickleTag> tags = testCase.getTags();
        for(PickleTag tag : tags) {
            String tagName = tag.getName();
            if (tagName.contains("epic=")) {
                String snake_case_feature_name = tagName.split("=")[1];
                epic = snake_case_feature_name.replaceAll("_", " ");
            }
        }
        return epic;
    }

    private void startRootItem(TestCase testCase) {
        String epicName = getEpicName(testCase);
        if (!rootSuiteId.containsKey(epicName)) {
            rootSuiteId.put(epicName ,Suppliers.memoize(() -> {
                StartTestItemRQ rq = new StartTestItemRQ();
                rq.setName(epicName);
                rq.setStartTime(Calendar.getInstance().getTime());
                rq.setType("STORY");
                return AbstractReporter.this.launch.get().startTestItem(rq);
            }));
        }
    }

    private RunningContext.FeatureContext createFeatureContext(TestCase testCase, String featureURI) {
        startRootItem(testCase);
        RunningContext.FeatureContext currentFeatureContext = (new RunningContext.FeatureContext()).processTestSourceReadEvent(testCase);
        this.currentFeatureContextMap.put(featureURI, currentFeatureContext);
        String featureKeyword = currentFeatureContext.getFeature().getKeyword();
        String featureName = currentFeatureContext.getFeature().getName();
        StartTestItemRQ rq = new StartTestItemRQ();
        Maybe<String> root = this.rootSuiteId.get(getEpicName(testCase)).get();
        rq.setDescription(currentFeatureContext.getUri());
        rq.setName(Utils.buildNodeName(featureKeyword, ": ", featureName, null));
        rq.setAttributes(currentFeatureContext.getAttributes());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setType(this.getFeatureTestItemType());
        currentFeatureContext.setFeatureId(root == null ? this.launch.get().startTestItem(rq) : this.launch.get().startTestItem(root, rq));
        return currentFeatureContext;
    }

    private void handleTestStepStarted(TestStepStarted event) {
        TestStep testStep = event.testStep;
        if (testStep instanceof HookTestStep) {
            this.beforeHooks(((HookTestStep)testStep).getHookType());
        }
        else {
            if (this.getCurrentScenarioContext().withBackground()) {
                this.getCurrentScenarioContext().nextBackgroundStep();
            }
            this.beforeStep(testStep);
        }
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if (event.testStep instanceof HookTestStep) {
            this.hookFinished((HookTestStep)event.testStep, event.result, this.isBefore(event.testStep));
            this.afterHooks(this.isBefore(event.testStep));
        } else {
            this.afterStep(event.result);
        }
    }
}