package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static Utilities.Utility.getText;
/**
 * FinishingOrderPage class represents
 * the final confirmation page displayed
 * after successful order completion.
 *
 * Responsibilities:
 * - Retrieve confirmation messages
 * - Validate successful checkout process
 *
 * Design Pattern Used:
 * - Page Object Model (POM)
 */
public class FinishingOrderPage extends BasePage {

    private final By thankHeader = By.className("complete-header");

    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */
    public FinishingOrderPage(WebDriver driver) {
        super(driver);
    }
    /**
     * Retrieves thank you / confirmation message
     * displayed after successful order completion.
     *
     * Useful for:
     * - Assertion validations
     * - Verifying successful checkout flow
     * - End-to-end test completion checks
     *
     * @return Confirmation message text
     */
    public String getThanksMessageText(){
        return getText(driver, thankHeader);
    }
}
