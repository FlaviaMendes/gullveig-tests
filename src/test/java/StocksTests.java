import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class StocksTests {

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

    public void openStocks(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[4]/th/a")).click();
    }

    public void openAddStocks(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
    }

    public void logoutPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"user-tools\"]/a[3]")).click();

        driver.quit();
    }

    @Test
    public void CreateNewStock(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //Open Stock Page
        openStocks(driver);

       //Add Stock Page
        openAddStocks(driver);

        //Stock Page
        driver.findElement(By.name("ticker")).sendKeys("AMZN");
        driver.findElement(By.name("company")).click();
        driver.findElement(By.xpath("//*[@id=\"id_company\"]/option[2]")).click();
        driver.findElement(By.name("_save")).click();

        //Confirmation Page
        String expectedMessage = "The stock \"AMZN (Amazon.com, Inc.)\" was added successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        logoutPage(driver);

    }

    @Test
    public void EditStocks(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //Open Stock Page
        openStocks(driver);

        //Edit Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();
        driver.findElement(By.name("ticker")).clear();
        driver.findElement(By.name("ticker")).sendKeys("AMZN $1,710.63");
        driver.findElement(By.name("_save")).click();

        //Logout
        logoutPage(driver);

    }

    @Test
    public void DeleteStock(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //Open Stock Page
        openStocks(driver);

        //Delete Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();
        driver.findElement(By.xpath("//*[@id=\"stock_form\"]/div/div/p/a")).click();

        //Confirmation Page
        String expectedMessage = "Are you sure?";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();

        //Confirmation Delete
        String expectedMessageDelete = "The stock \"AMZN (Amazon.com, Inc.)\" was deleted successfully.";
        String displayedMessageDelete = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageDelete, displayedMessageDelete);

    }
}
