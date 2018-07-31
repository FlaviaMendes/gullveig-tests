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

import static org.junit.Assert.assertNotNull;

public class SectorsTests {

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

        String nameSector = UUID.randomUUID().toString().substring(1,30); // substring
        driver.findElement(By.name("name")).sendKeys(nameSector); // fill in the name field

        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();

        //Confirm message
        String expectedMessage = "The sector \"" + nameSector + "\" was added successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessage,displayedMessage);

        //Back to home
        driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/a[1]")).click();

        //Logout
        logoutPage(driver);
    }

    @Test
    public void DoNotAllowSameSectorTwice(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        //Login Page
        executeLogin(driver);

        //Menu Page
        openSectors(driver);

        //Add Sector Existing
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();

        driver.findElement(By.name("name")).sendKeys("Services");
        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();

        //Msg Error
        String expectedMessageError = "Sector with this Name already exists.";
        String displayedMessageError = driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/fieldset/div/ul/li")).getText();

        Assert.assertEquals(expectedMessageError, displayedMessageError);

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

       // Create new sector (Pre-condition - Add Sector Page)
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();

        String nameSector = UUID.randomUUID().toString().substring(1,30);
        driver.findElement(By.name("name")).sendKeys(nameSector);

        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();

        //Find created sector
        WebElement foundSector = null;
        List<WebElement> createdSector = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement sector : createdSector){
            if(sector.getText().contains(nameSector)){
                foundSector = sector;
            }
        }
        assertNotNull("Pre-condition sector not found!", foundSector);
        foundSector.findElement(By.xpath("th/a")).click();

        //Edit Page
        String newSector = UUID.randomUUID().toString().substring(1,30);
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(newSector);

        driver.findElement(By.name("_save")).click();

       //Confirmation Page
        String expectedMessage = "The sector \"" + newSector + "\" was changed successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        //Logout
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

        // Create new sector (Pre-condition - Add Sector Page)
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();

        String nameSector = UUID.randomUUID().toString().substring(1,30);
        driver.findElement(By.name("name")).sendKeys(nameSector);

        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/input[1]")).click();

        //Find created sector
        WebElement foundSector = null;
        List<WebElement> createdSector = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement sector : createdSector){
            if(sector.getText().contains(nameSector)){
                foundSector = sector;
            }
        }
        assertNotNull("Pre-condition stock not found!", foundSector);
        foundSector.findElement(By.xpath("th/a")).click();

        //Delete Page
        driver.findElement(By.xpath("//*[@id=\"sector_form\"]/div/div/p/a")).click();

        //Confirmation Page
        String expectedMessage = "Are you sure?";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();

        //Confirmation Delete
        String expectedMessageDelete = "The sector \"" + nameSector + "\" was deleted successfully.";
        String displayedMessageDelete = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageDelete, displayedMessageDelete);

        //Logout
        logoutPage(driver);
    }
}
