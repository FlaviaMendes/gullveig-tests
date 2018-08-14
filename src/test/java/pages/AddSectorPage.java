package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddSectorPage {
    private final WebDriver browser;

    public AddSectorPage(WebDriver browser) {
        this.browser = browser;
    }

    public void name(String nameToFill) {
        this.browser.findElement(By.name("name")).sendKeys(nameToFill);
    }

    public SectorListPage clickSave() {
        this.browser.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();
        return new SectorListPage(browser);

    }


    public void clearNameSector() {
        this.browser.findElement(By.name("name")).clear();

    }

    public AddSectorPage clickDeleteSector(String nameSector) {
        this.browser.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/p")).click();
        return new AddSectorPage(this.browser);

    }

    public ConfirmationDeletePage clickDelete() {
        this.browser.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();
        return new ConfirmationDeletePage(this.browser);

    }
}
