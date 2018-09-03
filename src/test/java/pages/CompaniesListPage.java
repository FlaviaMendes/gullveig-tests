package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CompaniesListPage {

    private final WebDriver browser;

    public CompaniesListPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;
    }

    public AddCompaniesPage clickAddCompanies() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/ul/li/a")).click();
        return new AddCompaniesPage(this.browser);
    }

    public String getConfirmationMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul")).getText();
        return displayedMessage;
    }

    public String getConfirmationErrorMessage() {
        String displayedErrorMessage = this.browser.findElement(By.xpath("//*[@id=\"company_form\"]/div/p")).getText();
        return displayedErrorMessage;
    }

    public AddCompaniesPage clickCompanyByName(String nameCompany) {
        WebElement foundCompany = null;
        List<WebElement> createdCompany = this.browser.findElements(By.xpath("//*[@id=\"result_list\"]/tbody/tr"));
        for (WebElement company : createdCompany){
            if(company.getText().contains(nameCompany)){
                foundCompany = company;
            }
        }
        assertNotNull("Pre-condition company not found!", foundCompany);
        foundCompany.findElement(By.xpath("th/a")).click();

        return new AddCompaniesPage(this.browser);

    }

    public String getConfirmationEditMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return  displayedMessage;
    }

    public String getConfirmationEditSectorMessage() {
        String displayedMessage = this.browser.findElement((By.xpath("//*[@id=\"container\"]/ul/li"))).getText();
        return displayedMessage;
    }

    public String getAreYouSureDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
        return displayedMessage;
    }

    public void clickYesIamSure() {
        this.browser.findElement(By.xpath("//*[@id=\"content\"]/form/div/input[2]")).click();
    }

    public String getConfirmationDeleteMessage() {
        String displayedMessage = this.browser.findElement(By.xpath("//*[@id=\"container\"]/ul/li")).getText();
        return  displayedMessage;
    }

}
