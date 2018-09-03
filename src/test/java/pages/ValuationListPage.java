package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ValuationListPage {

    private final WebDriver browser;

    public ValuationListPage (WebDriver injectedBrowser){

        this.browser = injectedBrowser;

    }

    public AddValuationPage clickAddValuation() {

        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddValuationPage(this.browser);

    }

    public String getConfirmationMessage() {
        String displayedErrorMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return displayedErrorMessage;
    }
}
