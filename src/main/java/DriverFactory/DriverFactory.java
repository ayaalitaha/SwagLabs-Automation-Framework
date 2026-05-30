package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * DriverFactory class responsible for:
 * - Creating browser driver instances
 * - Managing WebDriver lifecycle
 * - Supporting parallel execution using ThreadLocal
 *
 * Design Pattern Used:
 * - Factory Design Pattern
 *
 * Why ThreadLocal?
 * - Ensures each test thread gets its own isolated WebDriver instance
 * - Prevents driver conflicts during parallel execution
 */
public class DriverFactory {

    /**
     * Enum representing supported browser types.
     *
     * Easy to extend later by adding:
     * - SAFARI
     * - OPERA
     * - REMOTE
     * - HEADLESS browsers
     */
    public enum BrowserType {
        CHROME, FIREFOX, EDGE
    }

    /**
     * ThreadLocal WebDriver instance.
     *
     * Each thread will have its own separate driver instance.
     * This is essential for parallel test execution.
     */
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initializes WebDriver based on browser type.
     *
     * Driver will only be created if it does not already exist
     * for the current thread.
     *
     * @param browserType Browser type to initialize
     */
    public static void setDriver(BrowserType browserType){
        if (driverThreadLocal.get() ==null){
            switch (browserType){
                case EDGE -> driverThreadLocal.set(new EdgeDriver());
                case FIREFOX -> driverThreadLocal.set(new FirefoxDriver());
                case CHROME -> driverThreadLocal.set(new ChromeDriver());
                // Return driver associated with current thread
                default -> throw new IllegalArgumentException("unsupported browser type: " + browserType);
            }
        }
    }
    /**
     * Returns the current thread's WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    /**
     * Closes browser session and removes driver instance
     * from ThreadLocal memory.
     *
     * Important:
     * - Prevents memory leaks
     * - Ensures clean execution after tests finish
     */
    public static void quitDriver(){
        // Check if driver exists before quitting
        if(driverThreadLocal.get() != null){
            // Close browser and terminate WebDriver session
            driverThreadLocal.get().quit();
            // Remove driver reference from ThreadLocal
            driverThreadLocal.remove();
        }
    }
}
