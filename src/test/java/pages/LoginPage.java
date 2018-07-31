package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver browser; // pesquisar sobre field nas classes

    public LoginPage(WebDriver injectedBrowser) { // pesquisar sobre métodos contrutores
        this.browser = injectedBrowser;
    }

    public void open() {
        this.browser.get("http://127.0.0.1:8000/admin/");
    }

    public void username(String usernameToFill) { // pesquisar sobre parametros nos metodos
        WebElement element = browser.findElement(By.name("username"));
        element.clear();
        element.sendKeys(usernameToFill);
    }

    public void password(String passwordToFill) {
        WebElement element = browser.findElement(By.name("password"));
        element.clear();
        element.sendKeys(passwordToFill);
    }

    public MainMenuPage executeLogin() { // pesquisar sobre retorno dos metodos
        this.browser.findElement(By.xpath("//*[@id=\"login-form\"]/div[3]/input")).click();
        return new MainMenuPage(this.browser);
    }
}
