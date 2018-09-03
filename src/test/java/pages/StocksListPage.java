package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class StocksListPage {

    private final WebDriver browser;

    public StocksListPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;

    }

    public AddStockPage clickAddStock() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddStockPage(this.browser);
    }

    public String getConfirmationMessge() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedMessage;
    }

    public String getConfirmationErrorMessage() {
        String displayedErrorMessage = this.browser.findElement(By.xpath("//*[@id=\"stock_form\"]/div/p")).getText();
        return displayedErrorMessage;
    }

    public AddStockPage clickStockByName(String nameTicker) {
        WebElement foundStock = null;
        List<WebElement> createdStock = this.browser.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for (WebElement stock : createdStock) {
            if (stock.getText().contains(nameTicker)) {
                foundStock = stock;
            }
        }
            assertNotNull("Pre-condition company not found!", foundStock);
            foundStock.findElement(By.xpath("th/a")).click();

            return new AddStockPage(this.browser);

    }



    public String getConfirmationEditMessage() {
        String displayedErrorMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedErrorMessage;
    }

    public String getAreYouSureDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        return displayedMessage;
    }

    public void clickYesIamSure() {
        this.browser.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();
    }

    public String getConfirmationDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return  displayedMessage;
    }
}
