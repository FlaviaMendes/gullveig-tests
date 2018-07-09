import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class CompaniesTests {

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

    public void openCompanies(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[1]/th/a")).click();
    }

    public void openAddCompany(WebDriver driver){
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
    }

    public void logoutPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"user-tools\"]/a[3]")).click();

        driver.quit();
    }

    @Test
    public void CreateNewCompanies(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //List Page
        openCompanies(driver);

        //Add Page
        openAddCompany(driver);

        //Company Page
        driver.findElement(By.name("name")).sendKeys("Amazon.com, Inc."); // preenche campo name
        driver.findElement(By.name("site")).sendKeys("//www.amazon.com"); // preenche campo email
        driver.findElement(By.name("about")).sendKeys("Amazon.com, Inc. engages in the provision of online retail shopping services. "); //preenche campo about
        driver.findElement(By.name("sector")).click(); // abri tb sector
        driver.findElement(By.xpath("//*[@id=\"id_sector\"]/option[2]")).click(); // seleciona sector

        driver.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/input[1]")).click(); // click save

        //Logout
        logoutPage(driver);

    }

    @Test
    public void EditCompany(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //List Page
        openCompanies(driver);

        //Edit Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        driver.findElement(By.name("site")).clear();
        driver.findElement(By.name("site")).sendKeys("www.amazon.com");

        driver.findElement(By.name("_save")).click();

        logoutPage(driver);

    }

    @Test
    public void DeleteCompany(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //List Page
        openCompanies(driver);

        //Delete Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        driver.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/p/a")).click();

        String expectedMessage = "Are you sure?";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessage, displayedMessage);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();

        logoutPage(driver);
    }
}
