package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.sendData;

/**
 * LoginPage class represents the Login Page
 * of the application using Page Object Model (POM).
 *
 * Responsibilities:
 * - Locate login page elements
 * - Perform actions on login page
 * - Provide reusable business workflows
 *
 * Design Principles Used:
 * - Encapsulation
 * - Fluent Interface Method Chaining
 * - Single Responsibility Principle (SRP)
 */
public class LoginPage extends BasePage{

    /**
     * Locator for username input field.
     * Locator for password input field.
     * Locator for login button.
     */
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters username into username field.
     *
     * Returns current page object to support
     * method chaining (Fluent Design).
     *
     * @param username Username value
     * @return LoginPage instance
     */
    public LoginPage enterUsername(String username){
        sendData(driver , usernameField , username);
        return this;
    }

    /**
     * Enters password into password field.
     *
     * @param password Password value
     * @return LoginPage instance
     */
    public LoginPage enterPassword(String password){
        sendData(driver ,passwordField, password);
        return this;
    }

    /**
     * Clicks on login button.
     *
     * After successful login, user navigates
     * to Inventory Page.
     *
     * @return InventoryPage object
     */
    public InventoryPage clickOnLoginButton(){
        clickOnElement(driver, loginButton);
        return new InventoryPage(driver);
    }

    /**
     * Atomic Workflow Method.
     *
     * Combines complete login steps into
     * one reusable business action.
     *
     * Benefits:
     * - Cleaner test scripts
     * - Better readability
     * - Reduced duplicated steps
     *
     * Example Usage:
     * loginPage.loginWithValidCredentials("user","pass");
     *
     * @param username Valid username
     * @param password Valid password
     * @return InventoryPage object
     */
    public InventoryPage loginWithValidCredentials(String username , String password){
        return enterUsername(username)
                .enterPassword(password).
                clickOnLoginButton();
    }

}

