package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainMenuPage {

    private final WebDriver browser;

    public MainMenuPage(WebDriver injectedBrowser) {

        this.browser = injectedBrowser;
    }

    public SectorListPage openSectorPage() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[3]/th/a")).click();
        return new SectorListPage(this.browser);
    }

    public CompaniesListPage openCompaniesPage() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[1]/th/a")).click();
        return new CompaniesListPage(this.browser);
    }

    public StocksListPage openStocksPage() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div/table/tbody/tr[4]/th/a")).click();
        return new StocksListPage(this.browser);
    }

    public InformationSourcesPage openInformationSource() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[2]/th/a")).click();
        return new InformationSourcesPage(this.browser);
    }

    public ValuationMeasuresListPage openValuationMeasuresPage() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div/table/tbody/tr[6]/th/a")).click();
        return new ValuationMeasuresListPage(this.browser);
    }

    public ValuationListPage openValuationPage() {
        this.browser.findElement(By.xpath("//*[@id=\"content-main\"]/div[2]/table/tbody/tr[7]/th/a")).click();
        return new ValuationListPage(this.browser);
    }
}
