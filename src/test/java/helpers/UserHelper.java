package helpers;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainMenuPage;

public class UserHelper {

    public static MainMenuPage executeLogin(WebDriver browser, String username, String password){

        LoginPage loginPage = new LoginPage(browser);
        loginPage.open();

        loginPage.username(username);
        loginPage.password(password);
        return loginPage.executeLogin();

    }
}
