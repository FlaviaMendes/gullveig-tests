package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.channels.SelectionKey;

public class AddStockPage {

    private final WebDriver browser;

    public AddStockPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;
    }


    public void name(String nameToFill) {
        this.browser.findElement(By.name("ticker")).sendKeys(nameToFill);
    }

    public void selectCompanyByName(String company) {
        Select selectCompany = new Select(this.browser.findElement(By.name("company")));
        selectCompany.selectByVisibleText(company);
    }

    public StocksListPage clickSaveStocks() {
        this.browser.findElement(By.name("_save")).click();
        return new StocksListPage(this.browser);
    }

    public void clearTicker() {
        this.browser.findElement(By.name("ticker")).clear();
    }

    public void selectNewCompanyByName(String company) {
        Select selectCompany = new Select(this.browser.findElement(By.name("company")));
        selectCompany.selectByVisibleText(company);
    }

    public void clickDeleteStocks() {
        this.browser.findElement(By.xpath("//*[@id=\"stock_form\"]/div/div/p/a")).click();
    }
}
