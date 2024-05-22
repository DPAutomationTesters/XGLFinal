package helper;

import base.BaseClass;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DownloadPopup extends BaseClass
{
    public static void handleDownloadPopup() {
        try {
            // Attempt to use Robot to handle the popup
            Robot robot = new Robot();
            robot.delay(2000); // Adjust the delay based on your needs
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e)
        {
            ExceptionHandling.handleException(e);
            System.out.println("No download popup detected.");
        }
    }

}
