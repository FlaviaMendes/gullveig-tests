package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ValuationMeasuresListPage {

    private final WebDriver browser;

    public ValuationMeasuresListPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;

    }

    public AddValuationMeasuresPage clickAddValuationMeasures() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddValuationMeasuresPage(this.browser);
    }

    public String getConfirmationMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedMessage;
    }

    public String getConfirmationErrorMessage() {
        String displayedErrorMessage = this.browser.findElement(By.xpath("//*[@id=\"valuationmeasure_form\"]/div/p")).getText();
        return displayedErrorMessage;
    }

    public AddValuationMeasuresPage clickValuationMeasuresByName(String nameValuation) {

        WebElement foundValuation = null;
        List<WebElement> createdValuation = this.browser.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for (WebElement valuation : createdValuation) {
            if (valuation.getText().contains(nameValuation)) {
                foundValuation = valuation;
            }
        }
        assertNotNull("Pre-condition company not found!", foundValuation);
        foundValuation.findElement(By.xpath("th/a")).click();

        return new AddValuationMeasuresPage(this.browser);


    }

    public String getConfirmationEditMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedMessage;
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
