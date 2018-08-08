package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditSectorsPage {

    private final WebDriver browser;

    public EditSectorsPage(WebDriver injectedBrowser) {
        this.browser = injectedBrowser;
    }


    public void newName(String nameToFill) {
        this.browser.findElement(By.name("name")).sendKeys(nameToFill);
    }

    public SectorListPage clickSave() {
        this.browser.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();
        return new SectorListPage(this.browser);

    }
}
