package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Utilities.LogsUtils;
import java.util.List;
import java.util.Random;

import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.getText;

public class InventoryPage extends BasePage {

    private final By addToCartButtons = By.xpath("//button[text()='Add to cart']");
    private final By cartBage = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By productPrices = By.className("inventory_item_price");

    // Global Instance Variables (المسؤولين عن حفظ حالة التست الحالي)
    private int selectedProductCount = 0;
    private float accumulatePrice = 0.0f;

    public InventoryPage(WebDriver driver){
        super(driver);
    }

    public InventoryPage addRandomProducts(int min, int max) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        List<WebElement> prices = driver.findElements(productPrices);

        Random random = new Random();
        int numberOfProductsToAdd = random.nextInt((max - min) + 1) + min;

        // 🌟 التعديل الأول: بنعدل المتغير الأصلي للكلاس مباشرة وبدون كلمة int
        this.selectedProductCount = numberOfProductsToAdd;

        for (int i = 0; i < numberOfProductsToAdd; i++) {
            buttons.get(i).click();
            String priceText = prices.get(i).getText().replace("$", "").trim();

            // 🌟 التعديل الثاني: بنجمع السعر تراكمياً (+=) جوه متغير الكلاس الأصلي وبدون float
            this.accumulatePrice += Float.parseFloat(priceText);
        }
        return this;
    }

    public InventoryPage addAllProductsToCart(){
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        this.selectedProductCount = buttons.size();
        for (WebElement button : buttons){
            button.click();
        }
        return this;
    }

    public String getNumberOfProductsOnCartIcon(){
        return getText(driver, cartBage);
    }

    public String getNumberOfSelectedProducts(){
        return String.valueOf(selectedProductCount);
    }

    public String getTotalPriceOfSelectedProducts(){
        return "$" + accumulatePrice;
    }

    public CartPage clickOnCartIcon(){
        clickOnElement(driver, cartIcon);
        return new CartPage(driver);
    }

    public String getCurrentPageUrl(){
        String currentUrl = driver.getCurrentUrl();
        LogsUtils.info("captures current page url: " + currentUrl);
        return currentUrl;
    }
}