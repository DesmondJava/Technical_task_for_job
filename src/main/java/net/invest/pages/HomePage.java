package net.invest.pages;

import net.invest.utility.Common_methods;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Vadym on 1/15/2016.
 */
public class HomePage {

    public static final By LOGIN_LINK = By.xpath("//nav[@class='menuHeader']//span[.='Log in']");
    public static final By LOGIN_LINK_AFTER_LOG_OUT = By.id("button-1024-btnInnerEl");
    public static final By SIGNUP_LINK = By.xpath("//nav[@class='menuHeader']//span[.='Sign Up']");
    public static final By USER_RIGHT_UP = By.id("button-1013-btnInnerEl");
    public static final By PROFILE_LINK_IN_MENU = By.xpath("//span[@id='menuitem-1015-textEl']/span");
    public static final By SIGN_OUT_IN_MENU = By.xpath("//span[@id='menuitem-1021-textEl']/span");

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openProfilePage(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(Common_methods.isElementPresent(driver, USER_RIGHT_UP)){
            driver.findElement(USER_RIGHT_UP).click();
            driver.findElement(PROFILE_LINK_IN_MENU).click();

        } else{
            throw new NoSuchElementException("You need sign up before find this element!");
        }
    }

    public String getNameProfile() throws NoSuchElementException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if(Common_methods.isElementPresent(driver, USER_RIGHT_UP)){
            return driver.findElement(USER_RIGHT_UP).getText();
        } else{
            throw new NoSuchElementException("You need sign up before find this element!");
        }
    }


}
