package customreporter;


import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import cucumber.api.PickleStepTestStep;
import cucumber.api.TestCase;
import cucumber.api.TestStep;
import cucumber.api.event.TestSourceRead;
import gherkin.AstBuilder;
import gherkin.Parser;
import gherkin.ParserException;
import gherkin.TokenMatcher;
import gherkin.ast.Background;
import gherkin.ast.Examples;
import gherkin.ast.Feature;
import gherkin.ast.GherkinDocument;
import gherkin.ast.ScenarioDefinition;
import gherkin.ast.ScenarioOutline;
import gherkin.ast.Step;
import gherkin.ast.TableRow;
import gherkin.pickles.PickleTag;
import io.reactivex.Maybe;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class RunningContext {
    private RunningContext() {
        throw new AssertionError("No instances should exist for the class!");
    }

    static class ScenarioContext {
        private static Map<String, String> outlineIterationsMap = new HashMap();
        private Maybe<String> id = null;
        private Background background;
        private ScenarioDefinition scenario;
        private Queue<Step> backgroundSteps = new ArrayDeque();
        private Map<Integer, Step> scenarioLocationMap = new HashMap();
        private Set<ItemAttributesRQ> attributes = new HashSet();
        private TestCase testCase;
        private boolean hasBackground = false;
        private String scenarioDesignation;

        ScenarioContext() {
        }

        void processScenario(ScenarioDefinition scenario) {
            this.scenario = scenario;
            Iterator var2 = scenario.getSteps().iterator();

            while(var2.hasNext()) {
                Step step = (Step)var2.next();
                this.scenarioLocationMap.put(step.getLocation().getLine(), step);
            }

        }

        void processBackground(Background background) {
            if (background != null) {
                this.background = background;
                this.hasBackground = true;
                this.backgroundSteps.addAll(background.getSteps());
                this.mapBackgroundSteps(background);
            }

        }

        public Set<ItemAttributesRQ> getAttributes() {
            return this.attributes;
        }

        void processScenarioOutline(ScenarioDefinition scenarioDefinition) {
            if (this.isScenarioOutline(scenarioDefinition) && !this.hasOutlineSteps()) {
                String outlineIdentifyer = " [" + this.scenarioDesignation.replaceAll(".*\\.feature:|\\ #.*", "") + "]";
                outlineIterationsMap.put(this.scenarioDesignation, outlineIdentifyer);
            }

        }

        void processTags(List<PickleTag> pickleTags) {
            this.attributes = Utils.extractPickleTags(pickleTags);
        }

        void mapBackgroundSteps(Background background) {
            Iterator var2 = background.getSteps().iterator();

            while(var2.hasNext()) {
                Step step = (Step)var2.next();
                this.scenarioLocationMap.put(step.getLocation().getLine(), step);
            }

        }

        String getName() {
            return this.scenario.getName();
        }

        String getKeyword() {
            return this.scenario.getKeyword();
        }

        int getLine() {
            return this.isScenarioOutline(this.scenario) ? this.testCase.getLine() : this.scenario.getLocation().getLine();
        }

        String getStepPrefix() {
            return this.hasBackground() && this.withBackground() ? this.background.getKeyword().toUpperCase() + ": " : "";
        }

        Step getStep(TestStep testStep) {
            PickleStepTestStep pickleStepTestStep = (PickleStepTestStep)testStep;
            Step step = (Step)this.scenarioLocationMap.get(pickleStepTestStep.getStepLine());
            if (step != null) {
                return step;
            } else {
                throw new IllegalStateException(String.format("Trying to get step for unknown line in feature. Scenario: %s, line: %s", this.scenario.getName(), this.getLine()));
            }
        }

        Maybe<String> getId() {
            return this.id;
        }

        void setId(Maybe<String> newId) {
            if (this.id != null) {
                throw new IllegalStateException("Attempting re-set scenario ID for unfinished scenario.");
            } else {
                this.id = newId;
            }
        }

        void setTestCase(TestCase testCase) {
            this.testCase = testCase;
            this.scenarioDesignation = testCase.getScenarioDesignation();
        }

        void nextBackgroundStep() {
            this.backgroundSteps.poll();
        }

        boolean isScenarioOutline(ScenarioDefinition scenario) {
            return scenario instanceof ScenarioOutline;
        }

        boolean withBackground() {
            return !this.backgroundSteps.isEmpty();
        }

        boolean hasBackground() {
            return this.hasBackground && this.background != null;
        }

        boolean hasOutlineSteps() {
            return outlineIterationsMap.get(this.scenarioDesignation) != null && !((String)outlineIterationsMap.get(this.scenarioDesignation)).isEmpty();
        }

        String getOutlineIteration() {
            return this.hasOutlineSteps() ? (String)outlineIterationsMap.get(this.scenarioDesignation) : null;
        }
    }

    static class FeatureContext {
        private static Map<String, TestSourceRead> pathToReadEventMap = new HashMap();
        private String currentFeatureUri;
        private Maybe<String> currentFeatureId;
        private Feature currentFeature;
        private Set<ItemAttributesRQ> attributes = new HashSet();

        FeatureContext() {
        }

        static void addTestSourceReadEvent(String path, TestSourceRead event) {
            pathToReadEventMap.put(path, event);
        }

            RunningContext.ScenarioContext getScenarioContext(TestCase testCase) {
            ScenarioDefinition scenario = this.getScenario(testCase);
            RunningContext.ScenarioContext context = new RunningContext.ScenarioContext();
            context.processScenario(scenario);
            context.setTestCase(testCase);
            context.processBackground(this.getBackground());
            context.processScenarioOutline(scenario);
            context.processTags(testCase.getTags());
            return context;
        }

            RunningContext.FeatureContext processTestSourceReadEvent(TestCase testCase) {
            TestSourceRead event = (TestSourceRead)pathToReadEventMap.get(testCase.getUri());
            this.currentFeature = this.getFeature(event.source);
            this.currentFeatureUri = event.uri;
            this.attributes = Utils.extractAttributes(this.currentFeature.getTags());
            return this;
        }

        Feature getFeature(String source) {
            Parser<GherkinDocument> parser = new Parser(new AstBuilder());
            TokenMatcher matcher = new TokenMatcher();

            GherkinDocument gherkinDocument;
            try {
                gherkinDocument = (GherkinDocument)parser.parse(source, matcher);
            } catch (ParserException var6) {
                return null;
            }

            return gherkinDocument.getFeature();
        }

        Background getBackground() {
            ScenarioDefinition background = (ScenarioDefinition)this.getFeature().getChildren().get(0);
            return background instanceof Background ? (Background)background : null;
        }

        Feature getFeature() {
            return this.currentFeature;
        }

        Set<ItemAttributesRQ> getAttributes() {
            return this.attributes;
        }

        String getUri() {
            return this.currentFeatureUri;
        }

        Maybe<String> getFeatureId() {
            return this.currentFeatureId;
        }

        void setFeatureId(Maybe<String> featureId) {
            this.currentFeatureId = featureId;
        }

        <T extends ScenarioDefinition> T getScenario(TestCase testCase) {
            List<ScenarioDefinition> featureScenarios = this.getFeature().getChildren();
            Iterator var3 = featureScenarios.iterator();

            while(true) {
                ScenarioDefinition scenario;
                do {
                    do {
                        if (!var3.hasNext()) {
                            throw new IllegalStateException("Scenario can't be null!");
                        }

                        scenario = (ScenarioDefinition)var3.next();
                    } while(scenario instanceof Background);

                    if (testCase.getLine() == scenario.getLocation().getLine() && testCase.getName().equals(scenario.getName())) {
                        return (T) scenario;
                    }
                } while(!(scenario instanceof ScenarioOutline));

                Iterator var5 = ((ScenarioOutline)scenario).getExamples().iterator();

                while(var5.hasNext()) {
                    Examples example = (Examples)var5.next();
                    Iterator var7 = example.getTableBody().iterator();

                    while(var7.hasNext()) {
                        TableRow tableRow = (TableRow)var7.next();
                        if (tableRow.getLocation().getLine() == testCase.getLine()) {
                            return (T) scenario;
                        }
                    }
                }
            }
        }
    }
}