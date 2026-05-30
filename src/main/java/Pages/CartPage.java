package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.getText;
/**
 * CartPage class represents the Shopping Cart page
 * in the application.
 *
 * Responsibilities:
 * - Retrieve cart information
 * - Validate selected products data
 * - Navigate to Checkout page
 *
 * Design Pattern Used:
 * - Page Object Model (POM)
 */
public class CartPage extends BasePage{

    /**
     * Locator for product/cart total price.
     * Locator for Checkout button.
     */
    private final By totalCartPrice = By.className("inventory_item_price");
    private final By checkoutButton = By.id("checkout");


    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */
    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Retrieves total price displayed in cart.
     *
     * Useful for:
     * - Price validation
     * - Assertion checks
     * - Comparing with expected values
     *
     * @return Total cart price as String
     */
    public String getTotalPrice(){
        return getText(driver , totalCartPrice);
    }

    /**
     * Clicks on Checkout button.
     *
     * After clicking checkout, user navigates
     * to Checkout Information Page.
     *
     * @return CheckoutPage object
     */
    public CheckoutPage clickOnCheckoutButton(){
        clickOnElement(driver , checkoutButton);
        return new CheckoutPage(driver);
    }

}
