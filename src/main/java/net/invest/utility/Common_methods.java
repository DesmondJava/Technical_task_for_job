package net.invest.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Created by Vadym on 1/15/2016.
 */
public class Common_methods {

    public static boolean isElementPresent(WebDriver driver, By by){
        try{
            driver.findElement(by);
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

}
