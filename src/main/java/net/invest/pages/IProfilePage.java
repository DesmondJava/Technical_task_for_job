package net.invest.pages;

/**
 * Created by Vadym on 1/15/2016.
 */
public interface IProfilePage {

    void chooseFileUploadAvatar(String pathToImage);
    void closeFileUploadWindow();
    void clickChoosePhotoButton();
    boolean isButtonDownloadPresent();

}
