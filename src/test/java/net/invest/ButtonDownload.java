package net.invest;

import net.invest.pages.HomePage;
import net.invest.pages.LoginPage;
import net.invest.pages.ProfilePage;
import net.invest.utility.Common_methods;
import net.invest.utility.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

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

    @DataProvider(name = "pathFile", parallel = false)
    public static Object[][] testDataPathFile() {
        return new Object[][] {
                {"resources/image1.jpg", true},
                {"resources/testFile.txt", false}};
    }

    @BeforeSuite
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.get(Constant.URL_WEB);
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

    @Test(dependsOnMethods = {"logInOnWebSite"})
    public void openProfilePage(){
        homePage.openProfilePage();
        Assert.assertTrue(Common_methods.isElementPresent(driver, ProfilePage.CHOOSE_PHOTO_BUTTON));
    }

    @Test(dataProvider = "pathFile", dependsOnMethods = {"openProfilePage"})
    public void checkButtonDownloadAppearAfterChooseFile(String pathToFileInProject, boolean expectedResult){
        driver.navigate().refresh();
        String absolutePathToFile = new File(pathToFileInProject).getAbsoluteFile().toString();
        profilePage.chooseFileUploadAvatar(absolutePathToFile);
        Assert.assertEquals(profilePage.isButtonDownloadPresent(), expectedResult);
    }

    @Test
    public void negativeTestJustOpenAndCloseFileUploadAndCheckButtonDownload(){
        driver.navigate().refresh();
        profilePage.clickChoosePhotoButton();
        profilePage.closeFileUploadWindow();
        Assert.assertFalse(profilePage.isButtonDownloadPresent());
    }

    @AfterSuite
    public void turnDown() {
        loginPage.logOut();
        this.driver.quit();
    }


}
