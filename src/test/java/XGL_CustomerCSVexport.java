import base.BaseClass;
import helper.CommonUtility;
import helper.XGL_CountRecordOnScreen;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomerPage;

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
        XGL_CountRecordOnScreen crs= new XGL_CountRecordOnScreen();
        if(crs.getrecordcountoflastpage()>0)
        {
            CommonUtility.clickElement(cp.exportCSV);
        }
        else {
            Reporter.log("No customers present on customers page");
        }




    }
}
