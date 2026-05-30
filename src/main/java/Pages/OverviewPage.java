package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.getText;
/**
 * OverviewPage class represents the final order overview page
 * before completing the purchase.
 *
 * Responsibilities:
 * - Retrieve product prices
 * - Calculate expected total price
 * - Retrieve displayed total amount
 * - Complete checkout process
 *
 * Design Pattern Used:
 * - Page Object Model (POM)
 */

public class OverviewPage extends BasePage{

    private final By itemPrices = By.className("inventory_item_price");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    /**
     * Constructor used to initialize WebDriver
     * for every page object.
     *
     * @param driver WebDriver instance passed from test classes
     */
    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Calculates expected total order price.
     *
     * Workflow:
     * - Retrieve all item prices
     * - Sum subtotal values
     * - Retrieve tax amount
     * - Add tax to subtotal
     *
     * Useful for validating displayed total amount.
     *
     * @return Calculated total price including tax
     */
    public Float getCalculatedTotalPrice(){
        List<WebElement> prices = driver.findElements(itemPrices);
        float subTotal = 0.0f;
        for (WebElement price : prices){
            subTotal += Float.parseFloat(price.getText().replace("$","").trim());

        }
        float tax = Float.parseFloat(getText(driver , taxLabel).replaceAll("[^0-9.]", ""));
        return subTotal +tax;
    }

    /**
     * Retrieves displayed final total amount
     * from overview page.
     *
     * Example:
     * "Total: $32.39" -> 32.39
     *
     * @return Displayed total amount
     */
    public Float getTotal(){
        return Float.parseFloat(getText(driver, taxLabel).replaceAll("[^0-9.]", ""));
    }

    /**
     * Clicks on Finish button
     * to complete order process.
     *
     * After successful completion,
     * user navigates to Finishing Order Page.
     *
     * @return FinishingOrderPage object
     */
    public FinishingOrderPage clickOnFinishButton(){
        clickOnElement(driver ,finishButton);
        return new FinishingOrderPage(driver);
    }

}
