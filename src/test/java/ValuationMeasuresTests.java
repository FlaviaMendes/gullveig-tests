import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class ValuationMeasuresTests {

    public WebDriver openBrowser() {
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

    public void openValidationMeasures(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[6]/th/a")).click();
    }

    @Test
    public void CreateNewValidationMeasures(){


    }


}


