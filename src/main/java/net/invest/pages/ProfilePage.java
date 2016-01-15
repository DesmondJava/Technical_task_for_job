package net.invest.pages;

import net.invest.utility.ClipboardWindowsUploadFile;
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

    /**
     * This method open window upload file, choose file and press enter
     *
     * @param pathToImage This is path which file we will upload in the window upload file.
     */
    public void chooseFileUploadAvatar(String pathToImage) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        clickChoosePhotoButton();
        ClipboardWindowsUploadFile.uploadFileByPath(pathToImage);
    }

    public void closeFileUploadWindow(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            robot.delay(1000);
            Thread.sleep(5000);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Click the link 'Choose photo' in the profile user
     */
    public void clickChoosePhotoButton(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(CHOOSE_PHOTO_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * This method is checking attribute in the button 'Download'
     *
     * @return true if button present on the profile page or false if not
     */
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
