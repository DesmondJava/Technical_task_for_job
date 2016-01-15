package net.invest;

import net.invest.pages.HomePage;
import net.invest.pages.LoginPage;
import net.invest.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by Vadym on 1/15/2016.
 *
 * This test check appear the button 'Download' after choosing avatar in the profile
 */
public class ButtonDownload {

    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;
    private WebDriver driver;
    private final String URL_WEB = "https://www.4invest.net/";

    @DataProvider(name = "signIn", parallel = false)
    public static Object[][] testDataLogin() {
        return new Object[][]{
                new Object[]{
                        "Desmond",
                        "testPassword",
                        "Вадим Шевченко"
                }
        };
    }

    @DataProvider(name = "PathImage", parallel = false)
    public static Object[][] testDataPathImage() {
        return new Object[][]{
                new Object[]{
                        "http://www.brunningonline.net/simon/blog/archives/South%20Park%20Avatar.jpg"
                }
        };
    }

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get(URL_WEB);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test(dataProvider = "signIn")
    public void logInOnWebSite(String login, String password, String nameAccount){
        loginPage.logIn(login, password);
        Assert.assertTrue(homePage.getNameProfile().equals(nameAccount));
    }

    @Test(dataProvider = "PathImage", dependsOnMethods = {"logInOnWebSite"})
    public void checkButtonDownloadAppearAfterChooseAvatar(String pathToImage){
        homePage.openProfilePage();
        profilePage.chooseFileUploadAvatar(pathToImage);
        Assert.assertTrue(profilePage.isButtonDownloadPresent());
    }

    @Test(dependsOnMethods = {"checkButtonDownloadAppearAfterChooseAvatar"})
    public void negativeTestJustOpenAndCloseFileUploadAndCheckButtonDownload(){
        driver.navigate().refresh();
        profilePage.clickChoosePhotoButton();
        profilePage.closeFileUploadWindow();
        Assert.assertFalse(profilePage.isButtonDownloadPresent());
    }

    @Test(dependsOnMethods = {"negativeTestJustOpenAndCloseFileUploadAndCheckButtonDownload"})
    public void negativeTestUploadNotAPicture(){
        driver.navigate().refresh();
        String pathToTxtFile = "";
        profilePage.chooseFileUploadAvatar(pathToTxtFile);
        Assert.assertFalse(profilePage.isButtonDownloadPresent());
    }

    @AfterSuite
    public void turnDown() {
        loginPage.logOut();
        this.driver.quit();
    }


}
