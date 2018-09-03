package testscripts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.UUID;

public class ValuationMeasuresTests {

    public WebDriver openBrowser() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();

    }

    @Test
    public void CreateNewValidationMeasures() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationMeasuresListPage valuationMeasuresListPage = mainMenuPage.openValuationMeasuresPage();
        AddValuationMeasuresPage addValuationMeasuresPage = valuationMeasuresListPage.clickAddValuationMeasures();

        String nameValuation = UUID.randomUUID().toString().substring(1, 30);
        addValuationMeasuresPage.name(nameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        String displayedMessage = valuationMeasuresListPage.getConfirmationMessage();
        String expectedMessage = "The valuation measure \"" + nameValuation + "\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DoNotAllowSameValuationMeasureTwice() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationMeasuresListPage valuationMeasuresListPage = mainMenuPage.openValuationMeasuresPage();
        AddValuationMeasuresPage addValuationMeasuresPage = valuationMeasuresListPage.clickAddValuationMeasures();

        String nameValuation = UUID.randomUUID().toString().substring(1, 30);
        addValuationMeasuresPage.name(nameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        valuationMeasuresListPage.clickAddValuationMeasures();

        addValuationMeasuresPage.name(nameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        String displayedErrorMessage = valuationMeasuresListPage.getConfirmationErrorMessage();
        String expectedErrorMessage = "Please correct the error below.";
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();
    }

    @Test
    public void EditValuationMeasures() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationMeasuresListPage valuationMeasuresListPage = mainMenuPage.openValuationMeasuresPage();
        AddValuationMeasuresPage addValuationMeasuresPage = valuationMeasuresListPage.clickAddValuationMeasures();

        String nameValuation = UUID.randomUUID().toString().substring(1, 30);
        addValuationMeasuresPage.name(nameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        valuationMeasuresListPage.clickValuationMeasuresByName(nameValuation);

        addValuationMeasuresPage.clearNameValuation();
        String newNameValuation = UUID.randomUUID().toString().substring(1, 30);
        addValuationMeasuresPage.name(newNameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        String displayedMessage = valuationMeasuresListPage.getConfirmationEditMessage();
        String expectedMessage = "The valuation measure \"" + newNameValuation + "\" was changed successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();
    }

    @Test
    public void DeletetValuationMeasures() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        ValuationMeasuresListPage valuationMeasuresListPage = mainMenuPage.openValuationMeasuresPage();
        AddValuationMeasuresPage addValuationMeasuresPage = valuationMeasuresListPage.clickAddValuationMeasures();

        String nameValuation = UUID.randomUUID().toString().substring(1, 30);
        addValuationMeasuresPage.name(nameValuation);

        valuationMeasuresListPage = addValuationMeasuresPage.clickSaveValuationMeasures();

        valuationMeasuresListPage.clickValuationMeasuresByName(nameValuation);
        addValuationMeasuresPage.clickDeleteValuation();

        String displayedAreYouSureDeleteMessage = valuationMeasuresListPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = "Are you sure?";
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        valuationMeasuresListPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = valuationMeasuresListPage.getConfirmationDeleteMessage();
        String expectedConfirmationDeleteMessage = "The valuation measure \"" + nameValuation + "\" was deleted successfully.";
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();


    }
}


