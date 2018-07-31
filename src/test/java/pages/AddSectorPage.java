package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddSectorPage {
    private final WebDriver browser;

    public AddSectorPage(WebDriver browser) {
        this.browser = browser;
    }

    public void name(String nameToFill) {
        browser.findElement(By.name("name")).sendKeys(nameToFill);
    }
}
