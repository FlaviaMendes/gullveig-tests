package testscripts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;
import java.io.File;
import java.util.UUID;



public class CompaniesTests {

    private enum Browser {
        FIREFOX, CHROME
    }

    public FirefoxDriver openFirefox() {
        File file = new File("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

        return new FirefoxDriver();
    }

    public WebDriver openChrome() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    public WebDriver openBrowser() {
        String browserEnv = System.getenv("browser");
        Browser browserChosen = Browser.valueOf(browserEnv.toUpperCase());

        switch (browserChosen) {
            case CHROME:
                return openChrome();
            case FIREFOX:
                return openFirefox();
            default:
                return openChrome();
        }
    }

    @Test
    public void CreateNewCompany() {

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

        companiesListPage = addCompaniesPage.clickSaveCompanies();

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

        companiesListPage = addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickAddCompanies();
        addCompaniesPage.name(nameCompanies);

       String site = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.site(site);

        String about = UUID.randomUUID().toString();
        addCompaniesPage.about(about);

        addCompaniesPage.selectSectorByName("Financial");

        companiesListPage = addCompaniesPage.clickSaveCompanies();

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

        companiesListPage = addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickCompanyByName(nameCompany);

        addCompaniesPage.clearNameCompany();
        String newNameCompany = UUID.randomUUID().toString().substring(1,30);
        addCompaniesPage.name(newNameCompany);

        companiesListPage = addCompaniesPage.clickSaveCompanies();

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

        companiesListPage = addCompaniesPage.clickSaveCompanies();

        companiesListPage.clickCompanyByName(nameCompany);
        addCompaniesPage.selectNewSectorByName("Financial");

        companiesListPage = addCompaniesPage.clickSaveCompanies();

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

        companiesListPage = addCompaniesPage.clickSaveCompanies();

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