package Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LogsUtils is a wrapper utility class for Log4j2 logging framework.
 *
 * Purpose:
 * - Provide centralized logging methods
 * - Standardize log usage across the framework
 * - Avoid direct dependency on LogManager in multiple classes
 *
 * Benefits:
 * - Clean and consistent logging calls
 * - Easier maintenance and configuration changes
 * - Centralized logging control
 *
 * Logging levels supported:
 * - INFO
 * - WARN
 * - ERROR
 * - DEBUG
 * - FATAL
 */
public class LogsUtils {


    /**
     * Log4j2 Logger instance.
     *
     * Declared as static to ensure:
     * - Single shared logger per class
     * - Efficient memory usage
     */
    private static final Logger Log = LogManager.getLogger(LogsUtils.class.getName());

    /**
     * Logs informational messages.
     *
     * Used for general execution flow tracking.
     *
     * @param message Information message to log
     */
    public static void info(String message){
        Log.info(message);
    }

    /**
     * Logs warning messages.
     *
     * Used for non-critical issues or unexpected behavior.
     *
     * @param message Warning message to log
     */
    public static void warn(String message){
        Log.warn(message);
    }

    /**
     * Logs error messages.
     *
     * Used when a test step or application behavior fails.
     *
     * @param message Error message to log
     */
    public static void error(String message){
        Log.error(message);
    }

    /**
     * Logs debug messages.
     *
     * Used for detailed troubleshooting information
     * during development or test execution.
     *
     * @param message Debug message to log
     */
    public static void debug(String message){
        Log.debug(message);
    }

    /**
     * Logs fatal messages.
     *
     * Used for severe failures that may stop execution.
     *
     * @param message Fatal message to log
     */
    public static void fatal(String message){
        Log.fatal(message);
    }
}

