package testscripts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;

public class ValuationTests {

    public WebDriver openBrowser(){

        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();

    }

    @Test
    public void CreateNewValuationFillingDate(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationListPage valuationListPage = mainMenuPage.openValuationPage();
        AddValuationPage addValuationPage = valuationListPage.clickAddValuation();

        String measure = "Price";
        addValuationPage.selectMeasureValuationByName(measure);

        String stock = "ED (Consolidated Edison, Inc.)";
        addValuationPage.selectStockValuationByName(stock);

        String source = "Seeking Alpha - https://seekingalpha.com";
        addValuationPage.selectSourceValuationByName(source);

        String value = "0.5";
        addValuationPage.value(value);

        addValuationPage.clearDateValuation();
        String date = "2018-08-22";
        addValuationPage.date(date);

        valuationListPage = addValuationPage.clickSaveValuation();

        String expectedTicker = stock.substring(0, stock.indexOf(' '));
        String expectedSourceName = source.split(" - ")[0];
        String displayedMessage = valuationListPage.getConfirmationMessage();
        String expectedMessage = "The valuation \"" + measure + " - " + expectedTicker + " - " + date + " - " + value + " (" + expectedSourceName + ")\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();


    }

    @Test
    public void CreateNewValuationChooseDate(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationListPage valuationListPage = mainMenuPage.openValuationPage();
        AddValuationPage addValuationPage = valuationListPage.clickAddValuation();

        String measure = "Price";
        addValuationPage.selectMeasureValuationByName(measure);

        String stock = "ED (Consolidated Edison, Inc.)";
        addValuationPage.selectStockValuationByName(stock);

        String source = "Seeking Alpha - https://seekingalpha.com";
        addValuationPage.selectSourceValuationByName(source);

        String value = "0.5";
        addValuationPage.value(value);

        addValuationPage.clickCalender();
        addValuationPage.clickNextMonth();

        String day = "16";
        addValuationPage.clickToChoseDay(day);

        String chosenDate = addValuationPage.getDate();

        valuationListPage = addValuationPage.clickSaveValuation();

        String expectedTicker = stock.substring(0, stock.indexOf(' '));
        String expectedSourceName = source.split(" - ")[0];
        String displayedMessage = valuationListPage.getConfirmationMessage();
        String expectedMessage = "The valuation \"" + measure + " - " + expectedTicker + " - " + chosenDate +  " - " + value + " (" + expectedSourceName + ")\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();


    }
}
