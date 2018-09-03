package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import javax.print.DocFlavor;

public class AddCompaniesPage {

    private final WebDriver browser;

    public AddCompaniesPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;
    }

    public void name(String nameToFill) {

        this.browser.findElement(By.name("name")).sendKeys(nameToFill);
    }

    public void site(String siteToFill) {
        this.browser.findElement(By.name("site")).sendKeys(siteToFill);
    }

    public void about(String aboutToFill) {
        this.browser.findElement(By.name("about")).sendKeys(aboutToFill);
    }

    public void selectSectorByName(String sector) {
        Select selectSector = new Select(this.browser.findElement(By.name("sector")));
        selectSector.selectByVisibleText(sector);
    }


    public CompaniesListPage clickSaveCompanies() {
        this.browser.findElement(By.name("_save")).click();
        return new CompaniesListPage(this.browser);
    }

    public void clearNameCompany() {
        this.browser.findElement(By.name("name")).clear();

    }

    public void selectNewSectorByName(String sector) {
        Select selectSector = new Select(this.browser.findElement(By.name("sector")));
        selectSector.selectByVisibleText(sector);
    }

    public void clickDeleteCompany() {
        this.browser.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/p/a")).click();
    }
}
