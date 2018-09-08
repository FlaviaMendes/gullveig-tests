package testscripts;

import helpers.BrowserHelper;
import helpers.UserHelper;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.util.UUID;

public class InformationSourcesListTest {

    @Test
    public void CreateNewInformationSource() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        InformationSourcesPage informationSourcesPage = mainMenuPage.openInformationSource();
        AddInformationSourcePage addInformationSource = informationSourcesPage.clickAddInformationSource();

        String nameInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.name(nameInformationSource);

        String siteInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.site(siteInformationSource);

        String descriptionInformationSource = UUID.randomUUID().toString();
        addInformationSource.description(descriptionInformationSource);

        informationSourcesPage = addInformationSource.clickSaveInformationSource();

        String displayedMessage = informationSourcesPage.getConfirmationMessage();
        String expectedMessage = "The information source \"" + nameInformationSource + " - " + siteInformationSource + "\" was added successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DoNotAllowSameInformationSourceTwice() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        InformationSourcesPage informationSourcesPage = mainMenuPage.openInformationSource();
        AddInformationSourcePage addInformationSourcePage = informationSourcesPage.clickAddInformationSource();

        String nameInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSourcePage.name(nameInformationSource);

        String siteInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSourcePage.site(siteInformationSource);

        String descriptionInformationSource = UUID.randomUUID().toString();
        addInformationSourcePage.description(descriptionInformationSource);

        informationSourcesPage = addInformationSourcePage.clickSaveInformationSource();

        addInformationSourcePage.clickAddInformationSource();

        addInformationSourcePage.name(nameInformationSource);
        String newSiteInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSourcePage.site(newSiteInformationSource);

        String newDescriptionInformationSource = UUID.randomUUID().toString();
        addInformationSourcePage.description(newDescriptionInformationSource);

        informationSourcesPage = addInformationSourcePage.clickSaveInformationSource();

        String displayedErrorMessage = informationSourcesPage.getConfirmationErrorMessage();
        String expectedErrorMessage = "Please correct the error below.";
        Assert.assertEquals(expectedErrorMessage, displayedErrorMessage);

        driver.quit();

    }


    @Test
    public void EditInformationSource() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        InformationSourcesPage informationSourcesPage = mainMenuPage.openInformationSource();
        AddInformationSourcePage addInformationSource = informationSourcesPage.clickAddInformationSource();

        String nameInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.name(nameInformationSource);

        String siteInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.site(siteInformationSource);

        String descriptionInformationSource = UUID.randomUUID().toString();
        addInformationSource.description(descriptionInformationSource);

        informationSourcesPage = addInformationSource.clickSaveInformationSource();

        informationSourcesPage.clickInformationSourceByName(nameInformationSource);

        addInformationSource.clearNameInformationSource();
        String newNameInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.name(newNameInformationSource);

        informationSourcesPage = addInformationSource.clickSaveInformationSource();

        String displayedMessage = informationSourcesPage.getConfirmationEditMessage();
        String expectedMessage = "The information source \"" + newNameInformationSource + " - " + siteInformationSource + "\" was changed successfully.";
        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.quit();

    }

    @Test
    public void DeleteInformationSource() {

        WebDriver driver = BrowserHelper.openBrowser();

        MainMenuPage mainMenuPage = UserHelper.executeLogin(driver, "admin", "1234qwer");

        InformationSourcesPage informationSourcesPage = mainMenuPage.openInformationSource();
        AddInformationSourcePage addInformationSource = informationSourcesPage.clickAddInformationSource();

        String nameInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.name(nameInformationSource);

        String siteInformationSource = UUID.randomUUID().toString().substring(1, 30);
        addInformationSource.site(siteInformationSource);

        String descriptionInformationSource = UUID.randomUUID().toString();
        addInformationSource.description(descriptionInformationSource);

        informationSourcesPage = addInformationSource.clickSaveInformationSource();

        informationSourcesPage.clickInformationSourceByName(nameInformationSource);

        addInformationSource.clickDeleteInformationSource();

        String displayedAreYouSureDeleteMessage = informationSourcesPage.getAreYouSureDeleteMessage();
        String expectedAreYouSureDeleteMessage = "Are you sure?";
        Assert.assertEquals(expectedAreYouSureDeleteMessage, displayedAreYouSureDeleteMessage);

        informationSourcesPage.clickYesIamSure();

        String displayedConfirmationDeleteMessage = informationSourcesPage.getConfirmationDeleteMessage();
        String expectedConfirmationDeleteMessage = "The information source \"" + nameInformationSource + " - " + siteInformationSource + "\" was deleted successfully.";
        Assert.assertEquals(expectedConfirmationDeleteMessage, displayedConfirmationDeleteMessage);

        driver.quit();
    }
}
