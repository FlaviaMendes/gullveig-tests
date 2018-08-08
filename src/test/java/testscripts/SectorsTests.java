package testscripts;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class SectorsTests {

    public WebDriver openBrowser() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    public void executeLogin(WebDriver driver) {
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("1234qwer");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).click();
    }

    public void openSectors(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[3]/th/a")).click();
    }

    public void logoutPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"user-tools\"]/a[3]")).click();

        driver.quit();
    }

    @Test
    public void CreateNewSectors() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        String displayedMessage = sectorListPage.getConfirmationMessage();
        String expectedMessage = "The sector \"" + nameSector + "\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DoNotAllowSameSectorTwice() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);
        sectorListPage = addSectorPage.clickSave();

        sectorListPage.clickAddSector();
        addSectorPage.name(nameSector);
        sectorListPage = addSectorPage.clickSave();

        String displayedErrorMessage = sectorListPage.getConfirmationErrorMessage();
        String expectedErrorMessage = driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/fieldset/div/ul/li")).getText();
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();

    }

    @Test
    public void EditSectors() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        EditSectorsPage editSectorsPage = sectorListPage.clickSectorByName(nameSector);

        String newSector = UUID.randomUUID().toString().substring(1, 30);
        editSectorsPage.newName(nameSector);

        sectorListPage = editSectorsPage.clickSave();

        String displayedEditMessage = sectorListPage.getConfirmationEditMessage();
        String expectedEditMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        Assert.assertEquals(expectedEditMessage, displayedEditMessage);

        driver.quit();

    }


    @Test
    public void DeleteSectors() {

        WebDriver driver = openBrowser();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.username("admin");
        loginPage.password("1234qwer");
        MainMenuPage mainMenuPage = loginPage.executeLogin();

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        sectorListPage.clickSectorByName(nameSector);
        sectorListPage.clickDeleteSector(nameSector);

        String displayedAreYouSureDeleteMessage = sectorListPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        sectorListPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = sectorListPage.getConfirmationDeleteMessage();
        String expectedConfirmationDeleteMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();

    }
}
