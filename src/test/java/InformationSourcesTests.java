import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.UUID;

public class InformationSourcesTests {

    @Test
    public void CreateNewInformationSource() throws InterruptedException {

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        // Menu Page
        openInformationSources(driver); // click information sources

        // Item List page
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click(); // click add information source

        // Add information
        String nameInformationSource = UUID.randomUUID().toString();
        driver.findElement(By.name("name")).sendKeys(nameInformationSource); // preenche campo name

        String siteInformationSource = UUID.randomUUID().toString();
        driver.findElement(By.name("site")).sendKeys(siteInformationSource);// preenche campo site

        String descriptionInformationSource = UUID.randomUUID().toString();
        driver.findElement(By.name("description")).sendKeys(descriptionInformationSource);// preenche campo about

        driver.findElement(By.xpath("//*[@id=\"informationsource_form\"]/div/div/input[1]")).click();

       // Thread.sleep(2000); // espera 2 segundos para confirmar msg

       // String expectedMessage = "The information source \"Seeking Alpha - https://seekingalpha.com\" was added successfully."; // msg esperada
        //String displayedMessage = driver.findElement(By.xpath(" //*[@id=\"container\"]/ul/li")).getText();

       // Assert.assertEquals(expectedMessage, displayedMessage);//compara msg esperad com msg exibida

        // Logout Page
        //logoutPage(driver);

        //Log In Again Page
        // driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]/a")).click();

    }

    @Test
    public void DeleteInformationSource() throws InterruptedException {

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        // Login Page
        executeLogin(driver);

        // Menu Page
        openInformationSources(driver); // click information sources

        // Item List page
        driver.findElement(By.name("action")).click(); //  action
        driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[1]/label/select/option[2]")).click(); //escolher action
        driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[1]/button")).click(); // click go

        String expectedMessageAction = "Items must be selected in order to perform actions on them. No items have been changed.";
        String displayedMessageAction = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageAction, displayedMessageAction);

        driver.findElement(By.name("_selected_action")).click();

        driver.findElement(By.name("action")).click(); //  action
        driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[1]/label/select/option[2]")).click(); //escolher action
        driver.findElement(By.xpath("//*[@id=\"changelist-form\"]/div[1]/button")).click(); // click go

        //Delete Page
        String expectMessageAreYouSure = "Are you sure?"; // cnfirmar se está na pagania de delete
        String displayedMessageAreYouSure = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectMessageAreYouSure, displayedMessageAreYouSure);

        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[4]")).click();

        String expectMessageSucessDelete = "Successfully deleted 1 information source.";
        String displayedMessageSucessDelete = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectMessageSucessDelete, displayedMessageSucessDelete);

        // Logout Page
        logoutPage(driver);
    }

    @Test
    public void EditInformationSource() throws InterruptedException{

        WebDriver driver = openBrowser();
        driver.get("http://127.0.0.1:8000/admin/");

        executeLogin(driver);

        openInformationSources(driver);

        // Edit Page
        driver.findElement(By.xpath("//*[@id=\"result_list\"]/tbody/tr[1]/th/a")).click();

        String expectedMessageEdit = "Change information source";
        String displayedMessageEdit = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(expectedMessageEdit, displayedMessageEdit);

        driver.findElement(By.name("name")).clear();

        driver.findElement(By.name("name")).sendKeys("Seeking Alpha Invest");
        driver.findElement(By.name("_save")).click();

        String expectedMessageSuccessEdit = "The information source \"Seeking Alpha Invest - https://seekingalpha.com\" was changed successfully.";
        String displayedMessageSuccessEdit = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();

        Assert.assertEquals(expectedMessageSuccessEdit, displayedMessageSuccessEdit);

    }

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

    public void openInformationSources(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[2]/th/a")).click();
    }

    public void logoutPage(WebDriver driver) {
        driver.findElement(By.xpath("//*[@id=\"user-tools\"]/a[3]")).click();

        driver.quit();
    }
}