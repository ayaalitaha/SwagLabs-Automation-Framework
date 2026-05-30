package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.sendData;
/**
 * CheckoutPage class represents the Checkout Information page
 * where user enters shipping/payment information.
 *
 * Responsibilities:
 * - Fill checkout form fields
 * - Navigate to overview page
 * - Provide reusable checkout workflows
 *
 * Design Pattern Used:
 * - Page Object Model (POM)
 */

public class CheckoutPage extends BasePage {

    /**
     * Locator for First Name input field.
     * Locator for Last Name input field.
     * Locator for Postal/Zip Code input field.
     * Locator for Continue button.
     */
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Fills checkout information form.
     *
     * Fields included:
     * - First Name
     * - Last Name
     * - Postal Code
     *
     * @param fname    User first name
     * @param lname    User last name
     * @param zipCode  Postal/Zip code
     * @return CheckoutPage instance
     */
    private CheckoutPage fillingInformationForm(String fname ,String lname , String zipCode){
        sendData(driver, firstNameField , fname);
        sendData(driver, lastNameField , lname);
        sendData(driver, postalCodeField , zipCode);
        return this;
    }

    /**
     * Clicks on Continue button.
     *
     * After clicking continue, user navigates
     * to Overview Page.
     *
     * @return OverviewPage object
     */
    public OverviewPage clickOnContinueButton(){
        clickOnElement(driver, continueButton);
        return new OverviewPage(driver);
    }

    /**
     * Atomic Workflow Method.
     *
     * Combines:
     * - Filling checkout form
     * - Clicking continue button
     *
     * Benefits:
     * - Cleaner test cases
     * - Better readability
     * - Reusable business workflow
     *
     * @param fname    User first name
     * @param lname    User last name
     * @param zipCode  Postal/Zip code
     * @return OverviewPage object
     */
    public OverviewPage fillFormAndContiue(String fname, String lname , String zipCode){
        return fillingInformationForm(fname , lname,zipCode).clickOnContinueButton();
    }
}
