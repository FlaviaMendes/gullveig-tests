package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import testscripts.CompaniesTests;

import java.io.File;

public class BrowserHelper {
    private static WebDriver openFirefox() {
        File file = new File("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());

        return new FirefoxDriver();
    }

    private static WebDriver openChrome() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    public static WebDriver openBrowser() {
        String browserEnv = System.getenv("browser");
        BrowserOptions browserChosen = BrowserOptions.valueOf(browserEnv.toUpperCase());

        switch (browserChosen) {
            case CHROME:
                return openChrome();
            case FIREFOX:
                return openFirefox();
            default:
                return openChrome();
        }
    }

}
