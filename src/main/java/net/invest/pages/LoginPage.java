package net.invest.pages;

import net.invest.utility.Common_methods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vadym on 1/15/2016.
 */
public class LoginPage extends HomePage implements ILoginPage{

    public static final By LOGIN_FIELD = By.id("textfield-1941-inputEl");
    public static final By PASSWORD_FIELD = By.id("textfield-1942-inputEl");
    public static final By LOGIN_BUTTON = By.id("button-1946-btnEl");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * Sign in on the website.
     *
     * @param email    for login
     * @param password for login.
     */
    @Override
    public void logIn(String email, String password) {
        // Check if we already login in website => logout
        if(Common_methods.isElementPresent(driver, USER_RIGHT_UP)){
            driver.findElement(USER_RIGHT_UP).click();
            driver.findElement(SIGN_OUT_IN_MENU).click();
            driver.findElement(LOGIN_LINK_AFTER_LOG_OUT).click();
        } else {
            driver.findElement(LOGIN_LINK).click();
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(LOGIN_FIELD).clear();
        driver.findElement(LOGIN_FIELD).sendKeys(email);
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }

    /**
     * Log out on the website.
     */
//    @Override
    public void logOut() {
        if(Common_methods.isElementPresent(driver, USER_RIGHT_UP)){
            driver.findElement(USER_RIGHT_UP).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(SIGN_OUT_IN_MENU).click();
        }
    }

}
