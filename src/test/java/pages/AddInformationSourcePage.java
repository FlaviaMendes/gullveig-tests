package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddInformationSourcePage {

    private final WebDriver browser;

    public AddInformationSourcePage(WebDriver injectedBrowser){

        this.browser = injectedBrowser;
    }

    public void name(String nameToFill) {

        this.browser.findElement(By.name("name")).sendKeys((nameToFill));
    }

    public void site(String siteToFill) {
        this.browser.findElement(By.name("site")).sendKeys(siteToFill);
    }


    public void description(String descriptionToFill) {
        this.browser.findElement(By.name("description")).sendKeys(descriptionToFill);
    }

    public InformationSourcesPage clickSaveInformationSource() {
        this.browser.findElement(By.name("_save")).click();
        return new InformationSourcesPage(this.browser);
    }

    public void clearNameInformationSource() {
        this.browser.findElement(By.name("name")).clear();

    }

    public AddInformationSourcePage clickAddInformationSource() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddInformationSourcePage(this.browser);
    }

    public void clickDeleteInformationSource() {
        this.browser.findElement(By.xpath("//*[@id=\"informationsource_form\"]/div/div/p/a")).click();
    }
}
