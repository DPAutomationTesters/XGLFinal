import base.BaseClass;
import org.testng.annotations.Test;
import pages.ScheduleViewerPage;


import dataProvider.ConfigReader;
import helper.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import pages.CustomerPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;

public class SV_Page extends BaseClass
{
    @Test(priority = 1,enabled = true)

    public void SV_Page()
    {
        ScheduleViewerPage sv = new ScheduleViewerPage(driver);
        sv.scheduling.click();
        sv.subMenuScheduleViewer.click();
    }
}
