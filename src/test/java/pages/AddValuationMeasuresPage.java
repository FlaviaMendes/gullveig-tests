package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddValuationMeasuresPage {

    private final WebDriver browser;

    public AddValuationMeasuresPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;

    }

    public void name(String nameToFill) {

        this.browser.findElement(By.name("name")).sendKeys(nameToFill);

    }

    public ValuationMeasuresListPage clickSaveValuationMeasures() {
        this.browser.findElement(By.name("_save")).click();
        return new ValuationMeasuresListPage(this.browser);
   }

    public void clearNameValuation() {
        this.browser.findElement(By.name("name")).clear();
    }

    public void clickDeleteValuation() {

        this.browser.findElement(By.xpath("//*[@id=\"valuationmeasure_form\"]/div/div/p/a")).click();
    }
}
