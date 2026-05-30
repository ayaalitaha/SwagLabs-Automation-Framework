package Tests;
import DriverFactory.DriverFactory;
import DriverFactory.DriverFactory.BrowserType;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
/**
 * BaseTest class acts as the parent class
 * for all test classes in the framework.
 *
 * Responsibilities:
 * - Browser initialization
 * - Test environment setup
 * - Driver lifecycle management
 * - Common test configurations
 *
 * Framework Design:
 * - Supports cross-browser execution
 * - Supports parallel execution
 * - Centralized test setup/teardown
 */

public class BaseTest {

    /**
     * Setup method executed before each test method.
     *
     * Responsibilities:
     * - Read target browser configuration
     * - Initialize WebDriver
     * - Open application URL
     *
     * Execution Priority:
     * alwaysRun = true ensures execution
     * even if groups/dependencies fail.
     *
     * @throws IOException if configuration file reading fails
     */
    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException{

        /**
         * Read browser from Maven command line if provided.
         * Example:
         * mvn test -Dbrowser=chrome
         */
        String browserProperty = System.getProperty("Browser");
        /**
         * If no browser passed from command line,
         * fallback to environment.properties value.
         */
        String browserName = (browserProperty != null) ? browserProperty : DataUtils.getPropertyValue("Browser");
        // Log selected browser for reporting/debugging
        LogsUtils.info("Target Browser: " + browserName);
        /**
         * Convert browser name String
         * into BrowserType Enum.
         */
        BrowserType browserType = BrowserType.valueOf(browserName.toUpperCase().trim());
        // Initialize WebDriver using DriverFactory
        DriverFactory.setDriver(browserType);
        /**
         * Retrieve application base URL
         * from configuration file.
         */
        String baseUrl = DataUtils.getPropertyValue("BASE_URL");
        // Navigate to application URL
        DriverFactory.getDriver().get(baseUrl);
    }

    /**
     * TearDown method executed after each test method.
     *
     * Responsibilities:
     * - Close browser session
     * - Remove driver instance safely
     * - Prevent memory leaks
     *
     * alwaysRun = true ensures cleanup
     * executes even if test fails.
     */
    @AfterClass(alwaysRun = true)
    public void tearDown(){
        DriverFactory.quitDriver();
        LogsUtils.info("driver context session terminated safely.");
    }
}


