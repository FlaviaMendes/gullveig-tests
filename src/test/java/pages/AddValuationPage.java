package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AddValuationPage {

    private final WebDriver browser;

    public AddValuationPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;


    }

    public void selectMeasureValuationByName(String measure) {
        Select selectMeasure = new Select(this.browser.findElement(By.name("measure")));
        selectMeasure.selectByVisibleText(measure);
    }

    public void selectStockValuationByName(String stock) {
        Select selectStock = new Select(this.browser.findElement(By.name("stock")));
        selectStock.selectByVisibleText(stock);
    }

    public void selectSourceValuationByName(String source) {
        Select selectSource = new Select(this.browser.findElement(By.name("source")));
        selectSource.selectByVisibleText(source);
    }

    public void value(String valueToFill) {
        this.browser.findElement(By.name("value")).sendKeys(valueToFill);
    }

    public void clearDateValuation() {
        this.browser.findElement(By.name("date")).clear();
    }

    public void date(String dateToFill) {
        this.browser.findElement(By.name("date")).sendKeys(dateToFill);
    }

    public ValuationListPage clickSaveValuation() {
        this.browser.findElement(By.name("_save")).click();
        return new ValuationListPage(this.browser);
    }

    public void clickCalender() {
        this.browser.findElement(By.xpath("//*[@id=\"calendarlink0\"]/span")).click();
    }

    public void clickToChoseDay() {
        this.browser.findElement(By.xpath("//*[@id=\"calendarin0\"]/table/tbody/tr[5]/td[5]/a")).click();
    }

    public void clickToChoseDay(int dia) {
        // TODO implmentar o click pelo dia passado na parametro
    }

    public void clickNextMonth() {
        this.browser.findElement(By.xpath("//*[@id=\"calendarbox0\"]/div[1]/a[2]")).click();
    }
}
