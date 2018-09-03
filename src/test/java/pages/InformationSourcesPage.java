package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class InformationSourcesPage {

    private final WebDriver browser;

    public InformationSourcesPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;
    }

    public String getConfirmationMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedMessage;

    }

    public AddInformationSourcePage clickAddInformationSource() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddInformationSourcePage(this.browser);
    }

    public AddInformationSourcePage clickInformationSourceByName(String name) {
        WebElement foundInformationSource = null;
        List<WebElement> createdInformationSource = this.browser.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for (WebElement informationSource : createdInformationSource){
            if(informationSource.getText().contains(name)){
                foundInformationSource = informationSource;
            }
        }
        assertNotNull("Pre-condition company not found!", foundInformationSource);
        foundInformationSource.findElement(By.xpath("th/a")).click();

        return new AddInformationSourcePage(this.browser);

    }

    public String getConfirmationEditMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return  displayedMessage;
    }

    public String getConfirmationErrorMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"informationsource_form\"]/div/p")).getText();
        return  displayedMessage;
    }

    public String getAreYouSureDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        return displayedMessage;
    }

    public void clickYesIamSure() {
        this.browser.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();
    }

    public String getConfirmationDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return  displayedMessage;
    }
}
