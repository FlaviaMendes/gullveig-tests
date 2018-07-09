import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class SectorsTests {

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

    public void openSectors(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[3]/th/a")).click();
    }

    public void logoutPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"user-tools\"]/a[3]")).click();

        driver.quit();
    }

    @Test
    public void CreateNewSectors(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        // Menu Page
        openSectors(driver); // click sectors

        // Add Sector Page
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();

        driver.findElement(By.name("name")).sendKeys("Technology"); // preenche campo name
        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();

        //BACK TO HOME
        driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/a[1]")).click();

        //Logout
        logoutPage(driver);
    }

    @Test
    public void EditSectors(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        // Menu Page
        openSectors(driver); // click sectors

        //Edit Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Tech");

        logoutPage(driver);
    }

    @Test
    public void DeleteSectors(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        // Menu Page
        openSectors(driver); // click sectors

        //Delete Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/p/a")).click();

        //Confirmation Page
        String expectedMessage = "Are you sure?";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();

        //Confirmation Delete
        String expectedMessageDelete = "The sector \"Technology\" was deleted successfully.";
        String displayedMessageDelete = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageDelete, displayedMessageDelete);

    }





}
