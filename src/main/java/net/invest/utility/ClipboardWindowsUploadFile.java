package net.invest.utility;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by Vadym on 1/15/2016.
 */
public class ClipboardWindowsUploadFile {

    public static void uploadFileByPath(String path) {
        StringSelection stringSelection = new StringSelection(path);
        // add our path to img in clipboard
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
            Thread.sleep(2000);
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER); // press enter again
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER); // press enter again
            robot.delay(1000);
            Thread.sleep(5000);
        } catch (AWTException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
