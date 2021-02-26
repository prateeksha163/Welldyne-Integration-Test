package customreporter;

import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.ta.reportportal.ws.model.FinishTestItemRQ;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.epam.ta.reportportal.ws.model.attribute.ItemAttributesRQ;
import com.epam.ta.reportportal.ws.model.log.SaveLogRQ;
import cucumber.api.HookTestStep;
import cucumber.api.PickleStepTestStep;
import cucumber.api.TestStep;
import gherkin.ast.Tag;
import gherkin.pickles.*;
import io.reactivex.Maybe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rp.com.google.common.base.Function;
import java.util.*;

public class Utils {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.epam.reportportal.cucumber.Utils.class);

    private Utils() {
        throw new AssertionError("No instances should exist for the class!");
    }

    static void finishFeature(Launch rp, Maybe<String> itemId, Date dateTime) {
        if (itemId == null) {
            LOGGER.error("BUG: Trying to finish unspecified test item.");
        } else {
            FinishTestItemRQ rq = new FinishTestItemRQ();
            rq.setEndTime(dateTime);
            rp.finishTestItem(itemId, rq);
        }
    }

    static void finishTestItem(Launch rp, Maybe<String> itemId) {
        finishTestItem(rp, itemId, null);
    }

    static Date finishTestItem(Launch rp, Maybe<String> itemId, String status) {
        if (itemId == null) {
            LOGGER.error("BUG: Trying to finish unspecified test item.");
            return null;
        } else {
            FinishTestItemRQ rq = new FinishTestItemRQ();
            Date endTime = Calendar.getInstance().getTime();
            rq.setEndTime(endTime);
            rq.setStatus(status);
            rp.finishTestItem(itemId, rq);
            return endTime;
        }
    }

    static Maybe<String> startNonLeafNode(Launch rp, Maybe<String> rootItemId, String name, String description, Set<ItemAttributesRQ> attributes, String type) {
        StartTestItemRQ rq = new StartTestItemRQ();
        rq.setDescription(description);
        rq.setName(name);
        rq.setAttributes(attributes);
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setType(type);
        return rp.startTestItem(rootItemId, rq);
    }

    static void sendLog(final String message, final String level, final SaveLogRQ.File file) {
        ReportPortal.emitLog((Function<String, SaveLogRQ>) itemUuid -> {
            SaveLogRQ rq = new SaveLogRQ();
            rq.setMessage(message);
            rq.setItemUuid(itemUuid);
            rq.setLevel(level);
            rq.setLogTime(Calendar.getInstance().getTime());
            if (file != null) {
                rq.setFile(file);
            }
            return rq;
        });
    }

    static Set<ItemAttributesRQ> extractPickleTags(List<PickleTag> tags) {
        HashSet<ItemAttributesRQ> attributes = new HashSet<>();
        for (PickleTag tag : tags) {
            attributes.add(new ItemAttributesRQ(null, tag.getName()));
        }
        return attributes;
    }

    public static Set<ItemAttributesRQ> extractAttributes(List<Tag> tags) {
        Set<ItemAttributesRQ> attributes = new HashSet<>();
        for (Tag tag : tags) {
            attributes.add(new ItemAttributesRQ(null, tag.getName()));
        }
        return attributes;
    }

    static String mapLevel(String cukeStatus) {
        String mapped;
        if (cukeStatus.equalsIgnoreCase("passed")) {
            mapped = "INFO";
        } else if (cukeStatus.equalsIgnoreCase("skipped")) {
            mapped = "WARN";
        } else {
            mapped = "ERROR";
        }
        return mapped;
    }

    static String buildNodeName(String prefix, String infix, String argument, String suffix) {
        StringBuilder infixBuilder = new StringBuilder(infix);
        for(int i=0; i<6 - infix.length(); i++) {
            infixBuilder.append(" ");
        }
        return buildName(prefix, infixBuilder.append(": ").toString(), argument, suffix);
    }

    private static String buildName(String prefix, String infix, String argument, String suffix) {
        return (prefix == null ? "" : prefix) + infix + argument + (suffix == null ? "" : suffix);
    }

    private static int getRowWithMaxWidth(List<PickleRow> pickleRows) {
        int width = 0;
        for(PickleRow row : pickleRows) {
            for(PickleCell cell : row.getCells()) {
                if (cell.getValue().length() > width) {
                    width = cell.getValue().length();
                }
            }
        }
        return width;
    }

    private static StringBuilder getTransformedTable(List<PickleRow> pickleRows, StringBuilder builder){
        builder.append("\r\n");
        int width = getRowWithMaxWidth(pickleRows);
        for(PickleRow row : pickleRows) {
            builder.append("               ").append("|").append(" ");
            for(PickleCell cell : row.getCells()) {
                builder.append(cell.getValue());
                for(int i=0; i < width - cell.getValue().length(); i++) {
                    builder.append(" ");
                }
                builder.append(" ").append("| ");
            }
            builder.append("\r\n");
        }
        return builder;
    }

    static String buildMultilineArgument(TestStep step) {
        List<PickleRow> table = null;
        String dockString = "";
        StringBuilder builder = new StringBuilder();
        PickleStepTestStep pickleStep = (PickleStepTestStep)step;
        if (!pickleStep.getStepArgument().isEmpty()) {
            Argument argument = pickleStep.getStepArgument().get(0);
            if (argument instanceof PickleString) {
                dockString = ((PickleString)argument).getContent();
            } else if (argument instanceof PickleTable) {
                table = ((PickleTable)argument).getRows();
            }
        }

        if (table != null) {
            builder.append(getTransformedTable(table, builder));
        }
        if (!dockString.isEmpty()) {
            builder.append("\n\"\"\"\n").append(dockString).append("\n\"\"\"\n");
        }
        return builder.toString();
    }

    static String getStepName(TestStep step) {
        return step instanceof HookTestStep ? "Hook: " + ((HookTestStep)step).getHookType().toString() : ((PickleStepTestStep)step).getPickleStep().getText();
    }
}

