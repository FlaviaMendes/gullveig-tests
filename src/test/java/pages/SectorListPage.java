package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class SectorListPage {
    private final WebDriver browser;

    public SectorListPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;
    }

    public AddSectorPage clickAddSector() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddSectorPage(this.browser);
    }

    public String getConfirmationMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedMessage;
    }

    public String getConfirmationErrorMessage() {
        return this.browser.findElement(By.xpath("//*[@id=\"sector_form\"]/div/fieldset/div/ul/li")).getText();
    }


    public EditSectorsPage clickSectorByName(String nameSector) {
        WebElement foundSector = null;
        List<WebElement> createdSector = this.browser.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement sector : createdSector){
            if(sector.getText().contains(nameSector)){
                foundSector = sector;
            }
        }
        assertNotNull("Pre-condition sector not found!", foundSector);
        foundSector.findElement(By.xpath("th/a")).click();

        return new EditSectorsPage(this.browser);
    }


    public String getConfirmationEditMessage() {
        return this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

    }

    public String getAreYouSureDeleteMessage() {
        return this.browser.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
    }

    public SectorListPage clickYesIamSure() {
        this.browser.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();
        return new SectorListPage(this.browser);

    }

    public String getConfirmationDeleteMessage() {
        return this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
    }


}
