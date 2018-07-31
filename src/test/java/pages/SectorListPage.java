package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SectorListPage {
    private final WebDriver browser;

    public SectorListPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;
    }

    public AddSectorPage clickAddSector() {
        browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddSectorPage(this.browser);
    }
}
