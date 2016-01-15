package net.invest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vadym on 1/15/2016.
 */
public class ProfilePage extends HomePage {

    public static final By DOWNLOAD_AVATAR_BUTTON = By.id("button-2318");
    public static final By CHOOSE_PHOTO_BUTTON = By.id("fileuploadfield-2315-button");

    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void chooseFileUploadAvatar(String pathToImage) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickChoosePhotoButton();
        // add our path img to clipboard
        StringSelection stringSelection = new StringSelection(pathToImage);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        try {
            Robot robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);  // press ctrl + 'V' (sent path to input form)
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER); // press enter
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER); // press enter again
            robot.delay(1000);
            // Sometimes uploading so slow, around 15 sec.
            Thread.sleep(25000);
        }catch (AWTException | InterruptedException e){
            e.printStackTrace();
        }
    }

    public void closeFileUploadWindow(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void clickChoosePhotoButton(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(CHOOSE_PHOTO_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean isButtonDownloadPresent(){
        String cssClassesInDownloadButton = driver.findElement(DOWNLOAD_AVATAR_BUTTON).getAttribute("style");
        String classButtonDisabled = "display: none;";
        if(cssClassesInDownloadButton.contains(classButtonDisabled)){
            return false;
        }else {
            return true;
        }
    }

}
