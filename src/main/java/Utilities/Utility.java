package Utilities;
import io.restassured.http.Cookies;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;
/**
 * Utility class that contains reusable helper methods
 * for common Selenium WebDriver actions.
 *
 * This class follows the utility/helper pattern:
 * - All methods are static
 * - No object creation is required
 * - Used across the framework to reduce code duplication
 */
public class Utility {


    /**
     * Clicks on a web element after waiting until
     * the element becomes clickable.
     *
     * @param driver  WebDriver instance
     * @param locator Locator of the target element
     */
    public static void clickOnElement(WebDriver driver, By locator){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Sends text to an input field after waiting
     * until the element becomes visible.
     *
     * @param driver  WebDriver instance
     * @param locator Locator of the input field
     * @param text    Text to be entered
     */
    public static void sendData(WebDriver driver, By locator, String text){
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }


    /**
     * Retrieves the visible text from a web element.
     *
     * @param driver  WebDriver instance
     * @param locator Locator of the target element
     * @return Text displayed by the element
     */
    public static String getText(WebDriver driver, By locator){
        return new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    /**
     * Verifies that the current browser URL
     * matches the expected URL.
     *
     * @param driver      WebDriver instance
     * @param expectedUrl Expected page URL
     * @return true if URLs match, otherwise false
     */
    public static boolean VerifyURL(WebDriver driver, String expectedUrl){
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlToBe(expectedUrl));
    }


    /**
     * Retrieves all cookies from the current browser session.
     *
     * Useful for:
     * - Session handling
     * - Login persistence
     * - Session restoration
     *
     * @param driver WebDriver instance
     * @return Set of browser cookies
     */
    public static Set<Cookie> getAllCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    /**
     * Restores browser session cookies.
     *
     * Commonly used to:
     * - Reuse authenticated sessions
     * - Avoid repeated logins
     * - Speed up test execution
     *
     * @param driver  WebDriver instance
     * @param cookies Set of cookies to restore
     */
    public static void restoreSession(WebDriver driver, Set<Cookie> cookies){
        if(cookies !=null){
            for (Cookie cookie : cookies){
                driver.manage().addCookie(cookie);
            }
        }
    }

    /**
     * Generates a unique timestamp value.
     *
     * Useful for:
     * - Creating unique test data
     * - Naming files/screenshots
     * - Avoiding duplicated values
     *
     * @return Current timestamp in milliseconds as String
     */
    public static String getTimeStamp(){
        // Return current system time in milliseconds
        return String.valueOf(System.currentTimeMillis());
    }

}
