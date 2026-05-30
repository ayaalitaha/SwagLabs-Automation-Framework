package Utilities;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/**
 * DataUtils is a utility class responsible for reading
 * test data from external files such as:
 *
 * - Properties files (.properties)
 * - JSON files (.json)
 *
 * Purpose:
 * - Centralize test data management
 * - Separate test data from test logic
 * - Support data-driven testing
 */

public class DataUtils {

    private static final String PROPERTIES_PATH = "src/test/resources/environment.properties";
    private static final String JSON_PATH = "src/test/resources/loginData.json";

    /**
     * Reads value from a properties file.
     *
     *
     *
     *
     * @param key property key to retrieve
     * @return property value as String
     * @throws IOException if file reading fails
     */
    public static String getPropertyValue(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(PROPERTIES_PATH));
        return properties.getProperty(key);
    }

    /**
     * Reads data from JSON file based on field name.
     *
     * Example JSON:
     * {
     *   "username": "testUser",
     *   "password": "testPass"
     * }
     *
     *
     * @param field JSON key to retrieve value for
     * @return value as String, or empty string if error occurs
     */
    public static String getJsonData( String field) {
        try {
            FileReader reader = new FileReader(JSON_PATH);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
