import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CompaniesTests {

    public WebDriver openBrowser() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    @Test
    public void CreateNewCompanies() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        CompaniesListPage companiesListPage = mainMenuPage.openCompaniesPage();
        AddCompaniesPage addCompaniesPage = companiesListPage.clickAddCompanies();

        String nameCompanies = UUID.randomUUID().toString().substring(1, 30);
        addCompaniesPage.name(nameCompanies);

        String siteCompanies = UUID.randomUUID().toString().substring(1, 30);
        addCompaniesPage.site(siteCompanies);

        String aboutCompanies = UUID.randomUUID().toString();
        addCompaniesPage.about(aboutCompanies);

        addCompaniesPage.selectSectorByName("Financial");

        addCompaniesPage.clickSaveCompanies();

        String displayedMessage = companiesListPage.getConfirmationMessage();
        String expectedMessage = "The company \"" + nameCompanies + "\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DoNotAllowSameCompanyTwice(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        CompaniesListPage companiesListPage = mainMenuPage.openCompaniesPage();
        AddCompaniesPage addCompaniesPage = companiesListPage.clickAddCompanies();

        String nameCompanies = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(nameCompanies);

        String siteCompanies = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(siteCompanies);

        String aboutCompanies = UUID.randomUUID().toString();
        addCompaniesPage.about(aboutCompanies);

        addCompaniesPage.selectSectorByName("Financial");

        addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickAddCompanies();
        addCompaniesPage.name(nameCompanies);

        String site = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(site);

        String about = UUID.randomUUID().toString();
        addCompaniesPage.about(about);

        addCompaniesPage.selectSectorByName("Financial");

        addCompaniesPage.clickSaveCompanies();

        String displayedErrorMessage = companiesListPage.getConfirmationErrorMessage();
        String expectedErrorMessage = "Please correct the error below.";
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();

    }

    @Test
    public void EditCompanies(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        CompaniesListPage companiesListPage = mainMenuPage.openCompaniesPage();
        AddCompaniesPage addCompaniesPage = companiesListPage.clickAddCompanies();

        String nameCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(nameCompany);

        String siteCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        addCompaniesPage.about(aboutCompany);

        addCompaniesPage.selectSectorByName("Test");

        addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickCompanyByName(nameCompany);

        addCompaniesPage.clearNameCompany();
        String newNameCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(newNameCompany);

        addCompaniesPage.site(siteCompany);
        addCompaniesPage.about(aboutCompany);
        addCompaniesPage.selectSectorByName("Test");

        addCompaniesPage.clickSaveCompanies();

        String displayedMessage = companiesListPage.getConfirmationEditMessage();
        String expectedMessage = "The company \"" + newNameCompany + "\" was changed successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();


    }

    @Test
    public void EditSector(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        CompaniesListPage companiesListPage = mainMenuPage.openCompaniesPage();
        AddCompaniesPage addCompaniesPage = companiesListPage.clickAddCompanies();

        String nameCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(nameCompany);

        String siteCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        addCompaniesPage.about(aboutCompany);

        addCompaniesPage.selectSectorByName("Test");

        addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickCompanyByName(nameCompany);
        addCompaniesPage.selectNewSectorByName("Financial");

        addCompaniesPage.clickSaveCompanies();

        String displayedEditSectorMessage = companiesListPage.getConfirmationEditSectorMessage();
        String expectedEditSectorMessage = "The company \"" + nameCompany + "\" was changed successfully.";
        Assert.assertEquals(expectedEditSectorMessage, displayedEditSectorMessage);

        driver.quit();
    }

    @Test
    public void DeleteCompanies(){

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        CompaniesListPage companiesListPage = mainMenuPage.openCompaniesPage();
        AddCompaniesPage addCompaniesPage = companiesListPage.clickAddCompanies();

        String nameCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(nameCompany);

        String siteCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        addCompaniesPage.about(aboutCompany);

        addCompaniesPage.selectSectorByName("Test");

        addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickCompanyByName(nameCompany);
        addCompaniesPage.clickDeleteCompany();

        String displayedAreYouSureDeleteMessage = companiesListPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = "Are you sure?";
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        companiesListPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = companiesListPage.getConfirmationDeleteMessage();
        String expectedConfirmationDeleteMessage = "The company \"" + nameCompany + "\" was deleted successfully.";
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();
    }


}