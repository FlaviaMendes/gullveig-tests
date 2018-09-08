package testscripts;

import helpers.BrowserHelper;
import helpers.UserHelper;
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

    @Test
    public void CreateNewSectors() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

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

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);
        sectorListPage = addSectorPage.clickSave();

        sectorListPage.clickAddSector();
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        String displayedErrorMessage = sectorListPage.getConfirmationErrorMessage();
        String expectedErrorMessage = "Sector with this Name already exists.";
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();

    }

    @Test
    public void EditSectors() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        sectorListPage.clickSectorByName(nameSector);

        addSectorPage.clearNameSector();
        String newNameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(newNameSector);

        sectorListPage = addSectorPage.clickSave();

        String displayedEditMessage = sectorListPage.getConfirmationEditMessage();
        String expectedEditMessage =  "The sector \"" + newNameSector + "\" was changed successfully.";
        Assert.assertEquals(expectedEditMessage, displayedEditMessage);

        driver.quit();

    }


    @Test
    public void DeleteSectors() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        SectorListPage sectorListPage = mainMenuPage.openSectorPage();
        AddSectorPage addSectorPage = sectorListPage.clickAddSector();

        String nameSector = UUID.randomUUID().toString().substring(1, 30);
        addSectorPage.name(nameSector);

        sectorListPage = addSectorPage.clickSave();

        sectorListPage.clickSectorByName(nameSector);
        addSectorPage.clickDeleteSector(nameSector);

        String displayedAreYouSureDeleteMessage = sectorListPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = "Are you sure?";
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        sectorListPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = sectorListPage.getConfirmationDeleteMessage();
        String expectedConfirmationDeleteMessage = "The sector \"" + nameSector + "\" was deleted successfully.";
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();

    }
}
