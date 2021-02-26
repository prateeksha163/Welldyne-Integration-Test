package scenarios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Context {

    private final HashMap<String, String> configuration = new HashMap<>();
    public Logger logger = LoggerFactory.getLogger(Context.class);
    public Connection connection;
    public List<String> channels = new ArrayList<>();

    public Context() {
        Properties properties = new Properties();
        try {
            properties.load(Context.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            //Do nothing properties file is probably missing from resources.
            //We shall print a warning and move ahead and not stall the test execution.
            System.out.println("Warning :: Properties file is missing in resources: 'config.properties'");
        }
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            configuration.put(key, value);
        }
    }

    public String getConfigValue(String key) {
        return configuration.get(key);
    }
}