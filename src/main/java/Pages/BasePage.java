package Pages;
import org.openqa.selenium.WebDriver;
/**
 * BasePage class acts as the parent class
 * for all Page Object classes in the framework.
 *
 * Purpose:
 * - Store shared WebDriver instance
 * - Provide common functionality for all pages
 * - Reduce code duplication
 *
 * Design Pattern Used:
 * - Page Object Model (POM)
 *
 * All page classes should extend this class.
 */

public class BasePage {
    /**
     * Protected WebDriver instance.
     *
     * Protected access modifier allows:
     * - Direct access inside child page classes
     * - Better reusability across the framework
     */

    protected WebDriver driver;

    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */

    public BasePage(WebDriver driver){
        this.driver = driver;
    }
}
