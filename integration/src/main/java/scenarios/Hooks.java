package scenarios;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import static org.fest.assertions.api.Assertions.fail;

public class Hooks {
    public static Map<String, Boolean> EVENT_LOG = new HashMap<>();
    private final Context context;

    public Hooks(Context context) {
        this.context = context;
    }

    public synchronized void pollUntilEvent(long timeoutInSeconds, String event) throws InterruptedException {
        long waitUntil = System.currentTimeMillis() + (timeoutInSeconds * 1000);
        long polling = 200;
        while (!EVENT_LOG.containsKey(event)) {
            if (waitUntil <= System.currentTimeMillis()) {
                fail("Timed out waiting for event :: " + event);
            }
            wait(polling);
        }
        if (!EVENT_LOG.get(event)) {
            fail("The event on which dependency was based has failed. Event Name :: " + event);
        }
    }

    @Before("@RemoveClient")
    public void create_db_connection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:postgresql://" + this.context.getConfigValue("Endpoint") + ":"
                + this.context.getConfigValue("Port") + "/" + this.context.getConfigValue("DB_Name");
        String username = this.context.getConfigValue("DB_User");
        String password = this.context.getConfigValue("DB_Password");
        Class.forName("org.postgresql.Driver");
        this.context.connection = DriverManager.getConnection(connectionString, username, password);
    }

    @After("@RemoveClient")
    public void close_db_connection() throws SQLException {
        this.context.connection.close();
    }

    @Before
    public void setup(Scenario scenario) throws InterruptedException {
        //Wait for dependent event to complete
        for(String s : scenario.getSourceTagNames()) {
            if (s.contains("waitingFor")) {
                String waitForEvent = s.split("=")[1];
                pollUntilEvent(1800, waitForEvent);
            }
        }
    }

    @After
    public void teardown(Scenario scenario) {
        //Record the event and notifies the waiting threads.
        for(String s : scenario.getSourceTagNames()){
            if(s.contains("event")) {
                String event = s.split("=")[1];
                EVENT_LOG.put(event, !scenario.isFailed());
            }
        }
        context.logger.info(
                "RP_MESSAGE#BASE64#{}#{}",
                ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BASE64),
                "Logging Base64 Image."
        );
        WebDriverRunner.closeWebDriver();
    }
}
