package testscripts;

import helpers.BrowserHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StocksTests {

    @Test
    public void CreateNewStocks(){

        WebDriver driver = BrowserHelper.openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        StocksListPage stocksListPage = mainMenuPage.openStocksPage();
        AddStockPage addStockPage = stocksListPage.clickAddStock();

        String nameTicker = UUID.randomUUID().toString().substring(1, 30);
        addStockPage.name(nameTicker);

        String company = "Target Corp.";
        addStockPage.selectCompanyByName(company);

        stocksListPage = addStockPage.clickSaveStocks();

        String displayedMessage = stocksListPage.getConfirmationMessge();
        String expectedMessage = "The stock \"" + nameTicker + " (" + company + ")\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DoNotAllowSameStockTwice() {
        WebDriver driver = BrowserHelper.openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        StocksListPage stocksListPage = mainMenuPage.openStocksPage();
        AddStockPage addStockPage = stocksListPage.clickAddStock();

        String nameTicker = UUID.randomUUID().toString().substring(1, 30);
        addStockPage.name(nameTicker);

        addStockPage.selectCompanyByName("The Clorox Co.");

        stocksListPage = addStockPage.clickSaveStocks();

        stocksListPage.clickAddStock();
        addStockPage.name(nameTicker);

        addStockPage.selectCompanyByName("The Clorox Co.");

        stocksListPage = addStockPage.clickSaveStocks();

        String displayedErrorMessage = stocksListPage.getConfirmationErrorMessage();
        String expectedErrorMessage = "Please correct the error below.";
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();

    }


    @Test
    public void EditTickerStock(){
        WebDriver driver = BrowserHelper.openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        StocksListPage stocksListPage = mainMenuPage.openStocksPage();
        AddStockPage addStockPage = stocksListPage.clickAddStock();

        String nameTicker = UUID.randomUUID().toString().substring(1,30);
        addStockPage.name(nameTicker);

        String company = "The Clorox Co.";
        addStockPage.selectCompanyByName(company);

        stocksListPage = addStockPage.clickSaveStocks();

        stocksListPage.clickStockByName(nameTicker);

        addStockPage.clearTicker();
        String newTicker = UUID.randomUUID().toString().substring(1,30);
        addStockPage.name(newTicker);

        stocksListPage = addStockPage.clickSaveStocks();

        String displayedMessage = stocksListPage.getConfirmationEditMessage();
        String expectedMessage = "The stock \"" + newTicker + " (" + company + ")\" was changed successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void EditCompanyStock(){
        WebDriver driver = BrowserHelper.openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        StocksListPage stocksListPage = mainMenuPage.openStocksPage();
        AddStockPage addStockPage = stocksListPage.clickAddStock();

        String nameTicker = UUID.randomUUID().toString().substring(1,30);
        addStockPage.name(nameTicker);

        String company = "The Clorox Co.";
        addStockPage.selectCompanyByName(company);

        stocksListPage = addStockPage.clickSaveStocks();

        stocksListPage.clickStockByName(nameTicker);

        String newCompany = "The Coca-Cola Co.";
        addStockPage.selectNewCompanyByName(newCompany);

        stocksListPage = addStockPage.clickSaveStocks();

        String displayedEditMessage = stocksListPage.getConfirmationEditMessage();
        String expectedEditMessage = "The stock \"" + nameTicker + " (" + newCompany + ")\" was changed successfully.";
        Assert.assertEquals(expectedEditMessage, displayedEditMessage);

        driver.quit();


    }

    @Test
    public void DeleteStock(){
        WebDriver driver = BrowserHelper.openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        StocksListPage stocksListPage = mainMenuPage.openStocksPage();
        AddStockPage addStockPage = stocksListPage.clickAddStock();

        String nameTicker = UUID.randomUUID().toString().substring(1,30);
        addStockPage.name(nameTicker);

        String company = "The Clorox Co.";
        addStockPage.selectCompanyByName(company);

        stocksListPage = addStockPage.clickSaveStocks();

        stocksListPage.clickStockByName(nameTicker);
        addStockPage.clickDeleteStocks();

        String displayedAreYouSureDeleteMessage = stocksListPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = "Are you sure?";
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        stocksListPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = stocksListPage.getConfirmationDeleteMessage();
        String  expectedConfirmationDeleteMessage = "The stock \"" + nameTicker + " (" + company + ")\" was deleted successfully.";
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();
    }


}
