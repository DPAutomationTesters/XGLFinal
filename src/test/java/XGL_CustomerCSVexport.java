import base.BaseClass;
import dataProvider.ConfigReader;
import helper.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomerPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
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
        //Download file
        String downloadDir = ConfigReader.getPropertyvalue("Downloadfolder");

        XGL_CountRecordOnScreen crs= new XGL_CountRecordOnScreen();
        JavaScriptExecutor jse= new JavaScriptExecutor();
        jse.scrolltobottombyJS();
        if(crs.getrecordcountoflastpage()>0)
        {
            CommonUtility.clickElement(cp.exportCSV);
            String currentTime = new SimpleDateFormat("HHmmss").format(new Date());
            String expected= "Customers_"+DateUtility.getDateinformat()+"_"+currentTime+".csv";
            System.out.println("Expected file name "+expected);

            // Find the latest downloaded file of a specific type (e.g., .pdf)
            FileUtility fu=new FileUtility();

            //String filename=fu.getvisbilefilecount(downloadDir);
            //wait till file is not downloaded

            if(fu.waitforfiletodownload(downloadDir,expected))
            {
                Reporter.log("CSV is 100% downloaded");
                XGL_CountRecordOnScreen xcs = new XGL_CountRecordOnScreen();
                int pagecount = xcs.getrecordcountoflastpage();
                System.out.println("Page count is " + pagecount);
                CSVUtilities cu = new CSVUtilities();
               // int CSVcount = cu.countCSVRecords(fu.getvisbilefilecount(downloadDir)) - 1;
                int CSVcount =cu.countCSVRecords(downloadDir+"\\"+expected)-1;
                System.out.println("File count is " + CSVcount);
                Assert.assertEquals(pagecount, CSVcount);
                Reporter.log("Page count is " + pagecount);
                Reporter.log("File count is " + CSVcount);
            }
            else if(!fu.waitforfiletodownload(downloadDir,expected))
            {
                Reporter.log("CSV not downloaded");
            }
        }
        else {
            Reporter.log("No customers present on customers page");
        }
    }
}
