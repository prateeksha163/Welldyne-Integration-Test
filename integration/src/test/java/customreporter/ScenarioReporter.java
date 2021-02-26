package customreporter;

import cucumber.api.HookTestStep;
import cucumber.api.HookType;
import cucumber.api.Result;
import cucumber.api.TestStep;
import gherkin.ast.Step;

public class ScenarioReporter extends AbstractReporter {

    protected void beforeStep(TestStep testStep) {
        RunningContext.ScenarioContext currentScenarioContext = this.getCurrentScenarioContext();
        Step step = currentScenarioContext.getStep(testStep);
        String decoratedStepName = "STEP ::: " + Utils.buildNodeName(currentScenarioContext.getStepPrefix(), step.getKeyword(), Utils.getStepName(testStep), "");
        String multilineArg = Utils.buildMultilineArgument(testStep);
        Utils.sendLog(decoratedStepName + multilineArg, "INFO", null);
    }

    protected void afterStep(Result result) {
        if (!result.is(Result.Type.PASSED)) {
            this.reportResult(result, "STEP ::: " + result.getStatus().toString().toUpperCase());
        }
    }

    protected void beforeHooks(HookType hookType) {}

    protected void afterHooks(Boolean isBefore) {}

    protected void hookFinished(HookTestStep step, Result result, Boolean isBefore) {
        if (result.is(Result.Type.FAILED)) {
            String hook = (isBefore) ? "BEFORE" : "AFTER";
            this.reportResult(result, "HOOK ::: " + hook);
        }
    }

    protected String getFeatureTestItemType() {
        return "SCENARIO";
    }

    protected String getScenarioTestItemType() {
        return "STEP";
    }
}