import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.GeckoDriverService;


import java.io.File;
import java.io.IOException;

public class PrincipalPageTests {

    public WebDriver openBrowserChrome() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    public void executeLogin(WebDriver driver) {
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("AdminFlavia");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).click();
    }

   /* public  openBrowserFirefox() {
        File file = new File("geckodriver.exe");
        System.setProperty("webdriver.gecko.drive", file.getAbsolutePath());

        return new GeckoDriverService();
    }

    @Test
    public void OpenBrowserChrome() {

        WebDriver driver = openBrowserChrome();
        driver.get("http://127.0.0.1:8000/admin/");
    }

    @Test
    public void OpenBrowserFirefox() {

        WebDriver driver = openBrowserFirefox();
        driver.get("http://127.0.0.1:8000/admin/");
    }*/


}
