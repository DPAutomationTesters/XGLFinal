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
import java.util.Arrays;
import java.util.Comparator;
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
        System.out.println("Download file path is "+downloadDir);

        XGL_CountRecordOnScreen crs= new XGL_CountRecordOnScreen();
        JavaScriptExecutor jse= new JavaScriptExecutor();
        jse.scrolltobottombyJS();
        if(crs.getrecordcountoflastpage()>0)
        {
            CommonUtility.clickElement(cp.exportCSV);
            WaitUtility.waitStatic(20);

            // Find the latest downloaded file of a specific type (e.g., .pdf)
            FileUtility fu=new FileUtility();

            System.out.println("Latest downloaded file: " + fu.getvisbilefilecount(downloadDir));

            XGL_CountRecordOnScreen xcs=new XGL_CountRecordOnScreen();
            int pagecount=xcs.getrecordcountoflastpage();
            System.out.println("Page count is "+pagecount);
            CSVUtilities cu=new CSVUtilities();
            int CSVcount=cu.countCSVRecords(fu.getvisbilefilecount(downloadDir))-1;
            System.out.println("File count is "+CSVcount);
            Assert.assertEquals(pagecount,CSVcount);
        }
        else {
            Reporter.log("No customers present on customers page");
        }
    }
}
