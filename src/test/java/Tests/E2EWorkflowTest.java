package Tests;
import Pages.*;
import Utilities.DataUtils;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.ITestResultListenerClass;

import static DriverFactory.DriverFactory.getDriver;
/**
 * E2EWorkflowTest represents a complete
 * End-to-End user purchase journey.
 *
 * Covered Business Flow:
 * 1- User Login
 * 2- Add Products to Cart
 * 3- Navigate to Cart
 * 4- Start Checkout
 * 5- Fill User Information
 * 6- Finish Order
 * 7- Validate Success Message
 *
 * Test Design:
 * - Uses Page Object Model (POM)
 * - Uses Fluent Design Pattern
 * - Uses Dynamic Test Data
 * - Validates critical business workflow
 */
@Listeners(ITestResultListenerClass.class)
public class E2EWorkflowTest extends BaseTest{

    private final String USERNAME = DataUtils.getJsonData("username");
    private final String PASSWORD = DataUtils.getJsonData("password");
    private final String FIRSTNAME = DataUtils.getJsonData("fName") ;
    private final String LASTNAME = DataUtils.getJsonData("lName");
    /**
     * Dynamic ZIP code generated randomly
     * using JavaFaker library.
     *
     * Purpose:
     * - Avoid duplicate/static test data
     * - Simulate realistic user inputs
     */
    private final String ZIPCODE = new Faker().number().digits(5); // Dynamic data using JavaFaker


   @Test
   public void completePurchaseFlow() {

       /**
        * Step 1:
        * Login using valid credentials.
        *
        * Expected Result:
        * User successfully redirected to Inventory Page.
        */
       InventoryPage inventoryPage = new LoginPage(getDriver())
               .enterUsername(USERNAME)
               .enterPassword(PASSWORD)
               .clickOnLoginButton();

       /**
        * Step 2:
        * Add random number of products to cart.
        *
        * Business Logic:
        * Random range between 2 and 4 products.
        */
       inventoryPage.addRandomProducts(2,4);

       /**
        * Step 3:
        * Navigate to shopping cart page.
        */
       CartPage cartPage = inventoryPage.clickOnCartIcon();

       /**
        * Step 4:
        * Start checkout process.
        */
       CheckoutPage checkoutPage = cartPage.clickOnCheckoutButton();

       /**
        * Step 5:
        * Fill checkout form with customer information.
        *
        * Expected Result:
        * User redirected to Overview Page.
        */
       OverviewPage overviewPage = checkoutPage.fillFormAndContiue(FIRSTNAME, LASTNAME, ZIPCODE);

       /**
        * Step 6:
        * Complete order placement process.
        */
       FinishingOrderPage finishingOrderPage = overviewPage.clickOnFinishButton();

       /**
        * Step 7:
        * Validate successful order completion message.
        *
        * Expected Result:
        * Confirmation message should match expected text.
        */
       Assert.assertEquals(finishingOrderPage.getThanksMessageText(), "Thank you for your order!"
       );
   }
}

