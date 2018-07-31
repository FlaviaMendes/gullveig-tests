import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StocksTests {

    public WebDriver openBrowser() {
        File file = new File("chromedriver.exe");
        System.setProperty("webdriver.chrome.drive", file.getAbsolutePath());

        return new ChromeDriver();
    }

    public void executeLogin(WebDriver driver) {
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("1234qwer");
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
        String ticker = UUID.randomUUID().toString();
        driver.findElement(By.name("ticker")).sendKeys(ticker); // fill in the name field

        Select company = new Select(driver.findElement(By.name("company")));
        company.selectByVisibleText("Target Corp.");

        driver.findElement(By.name("_save")).click(); // save new stock

        //Confirmation Page
        String expectedMessage = "The stock \"" + ticker + "\" was added successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        assertEquals(expectedMessage, displayedMessage);

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

        //Add Stock Page
        openAddStocks(driver);

        // Create new stock (Pre-condition)
        String ticker = UUID.randomUUID().toString();
        driver.findElement(By.name("ticker")).sendKeys(ticker);

        String companyName = "Target Corp.";
        Select company = new Select(driver.findElement(By.name("company")));
        company.selectByVisibleText(companyName);

        driver.findElement(By.name("_save")).click();

        // Find created stock
        WebElement foundStock = null;
        List<WebElement> createdStocks = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement stock : createdStocks) {
            if (stock.getText().contains(ticker)){
                foundStock = stock;
            }
        }
        assertNotNull("Pre-condition stock not found!", foundStock);
        foundStock.findElement(By.xpath("th/a")).click();

        //Edit Page
        String newTicker = UUID.randomUUID().toString();
        driver.findElement(By.name("ticker")).clear();
        driver.findElement(By.name("ticker")).sendKeys(newTicker);

        driver.findElement(By.name("_save")).click();

        //Confirmation Page
        String expectedMessage = "The stock \"" + newTicker + " (" + companyName + ")\" was changed successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        assertEquals(expectedMessage, displayedMessage);

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

        //Add Stock Page
        openAddStocks(driver);

        // Create new stock (Pre-condition)
        String ticker = UUID.randomUUID().toString();
        driver.findElement(By.name("ticker")).sendKeys(ticker);

        String companyName = "Target Corp.";
        Select company = new Select(driver.findElement(By.name("company")));
        company.selectByVisibleText(companyName);

        driver.findElement(By.name("_save")).click();

        // Find created stock
        WebElement foundStock = null;
        List<WebElement> createdStocks = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement stock : createdStocks) {
            if (stock.getText().contains(ticker)){
                foundStock = stock;
            }
        }
        assertNotNull("Pre-condition stock not found!", foundStock);
        foundStock.findElement(By.xpath("th/a")).click();

        //Delete Page
        driver.findElement(By.xpath("//*[@id=\"stock_form\"]/div/div/p/a")).click();

        //Confirmation Page
        String expectedMessage = "Are you sure?";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();

        //Confirmation Delete "The stock \"" + newTicker + " (" + companyName + ")\"
        String expectedMessageDelete = "The stock \"" + ticker + " (" + companyName + ")\" was deleted successfully.";
        String displayedMessageDelete = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageDelete, displayedMessageDelete);

    }
}
