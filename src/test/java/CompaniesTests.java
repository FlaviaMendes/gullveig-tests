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

public class CompaniesTests {

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
        String nameCompany = UUID.randomUUID().toString();// generate a code and transform into string
        driver.findElement(By.name("name")).sendKeys(nameCompany);

        String siteCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("site")).sendKeys(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("about")).sendKeys(aboutCompany);

        Select sector = new Select(driver.findElement(By.name("sector")));
        sector.selectByVisibleText("Financial");

        driver.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/input[1]")).click();

        //Confirmation Page
        String expectedMessage = "The company \"" + nameCompany + "\" was added successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        assertEquals(expectedMessage, displayedMessage);


        //Logout
        logoutPage(driver);

    }

    @Test
    public void EditNameCompany(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //List Page
        openCompanies(driver);

        //Add Page
        openAddCompany(driver);

        //Company Page
        String nameCompany = UUID.randomUUID().toString();// generate a code and transform into string
        driver.findElement(By.name("name")).sendKeys(nameCompany);

        String siteCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("site")).sendKeys(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("about")).sendKeys(aboutCompany);

        Select sector = new Select(driver.findElement(By.name("sector")));
        sector.selectByVisibleText("Financial");

        driver.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/input[1]")).click();

        //Edit Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        driver.findElement(By.name("site")).clear();
        driver.findElement(By.name("site")).sendKeys("www.amazon.com");

        driver.findElement(By.name("_save")).click();

        // Find created company
        WebElement foundStock = null;
        List<WebElement> createdCompany = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement stock : createdCompany) {
            if (stock.getText().contains(nameCompany)){
                foundStock = stock;
            }
        }
        assertNotNull("Pre-condition stock not found!", foundStock);
        foundStock.findElement(By.xpath("th/a")).click();

        //Edit Page
        String newCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(newCompany);

        driver.findElement(By.name("_save")).click();

        //Confirmation Page
        String expectedMessage = "The company \"" + newCompany + "\" was changed successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        assertEquals(expectedMessage, displayedMessage);

        logoutPage(driver);

    }

    @Test
    public void EditSectorCompany(){

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        //List Page
        openCompanies(driver);

        //Add Page
        openAddCompany(driver);

        //Company Page
        String nameCompany = UUID.randomUUID().toString();// generate a code and transform into string
        driver.findElement(By.name("name")).sendKeys(nameCompany);

        String siteCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("site")).sendKeys(siteCompany);

        String aboutCompany = UUID.randomUUID().toString();
        driver.findElement(By.name("about")).sendKeys(aboutCompany);

        Select sector = new Select(driver.findElement(By.name("sector")));
        sector.selectByVisibleText("Financial");

        driver.findElement(By.xpath("//*[@id=\"company_form\"]/div/div/input[1]")).click();

        // Find created company
        WebElement foundStock = null;
        List<WebElement> createdCompany = driver.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for(WebElement stock : createdCompany) {
            if (stock.getText().contains(nameCompany)){
                foundStock = stock;
            }
        }
        assertNotNull("Pre-condition stock not found!", foundStock);
        foundStock.findElement(By.xpath("th/a")).click();

        //Edit Page
        Select newSector = new Select(driver.findElement(By.name("sector")));
        newSector.selectByVisibleText("Services");

        driver.findElement(By.name("_save")).click();

        //Confirmation Page
        String expectedMessage = "The company \"" + newSector + "\" was changed successfully.";
        String displayedMessage = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        assertEquals(expectedMessage, displayedMessage);

        logoutPage(driver);

    }

   /* @Test // mudar o teste :Cannot delete company, outra q pode ser cncelada
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
    }*/
}
