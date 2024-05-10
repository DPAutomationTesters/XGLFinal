import base.BaseClass;
import dataProvider.ConfigReader;
import helper.CommonUtility;
import helper.ExceptionHandling;
import helper.JavaScriptExecutor;
import helper.XGL_CountRecordOnScreen;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomerPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Logger;



public class XGL_CustomerCSVexport extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CustomerExportCSV()
    {
        CustomerPage cp= new CustomerPage(driver);
        cp.menuOrders.click();
        cp.menuCustomers.click();
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(cp.breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        String relativeDownloadDir = "\\Downloads";
        String downloadDir = ConfigReader.getPropertyvalue("Downloadfolder")+relativeDownloadDir;
        System.out.println("Folder name is "+downloadDir);
        File dir = new File(downloadDir);
        int initialFileCount = dir.listFiles().length;
        XGL_CountRecordOnScreen crs= new XGL_CountRecordOnScreen();
        JavaScriptExecutor jse= new JavaScriptExecutor();
        jse.scrolltobottombyJS();
        if(crs.getrecordcountoflastpage()>0)
        {
            CommonUtility.clickElement(cp.exportCSV);
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            }
            catch(Exception e)
            {
                ExceptionHandling.handleException(e);
            }
            File[] files = dir.listFiles();
            long newestFileTime = 0;
            File newestFile = null;
            for (File file : files) {
                if (file.isFile() && file.lastModified() > newestFileTime) {
                    newestFileTime = file.lastModified();
                    newestFile = file;
                }
            }

            String fileName = newestFile.getName();

            // Print the file name
            System.out.println("System-generated CSV file name: " + fileName);
        }
        else {
            Reporter.log("No customers present on customers page");
        }
    }
}
