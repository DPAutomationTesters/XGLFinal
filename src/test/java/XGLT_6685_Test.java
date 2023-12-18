import base.BaseClass;
import helper.BrowserUtilities;
import helper.WaitUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MCCustomerWidgetPage;

public class XGLT_6685_Test extends BaseClass
{
    @Test(priority = 1,enabled = true,groups = {"Mission Control - Customers"})
    public void MCActivewithRevenue()
    {
        MCCustomerWidgetPage mcw=new MCCustomerWidgetPage(driver);
       // Assert.assertTrue(mcw.setMCCustomer());
        Assert.assertTrue(mcw.verifyactivewithrevcount());
        //Assert.assertTrue(mcw.verifyinactivewithrevcount());
        Assert.assertTrue(mcw.verifycreditholdcount());
        Assert.assertTrue(mcw.verifyactivewithoutrevcount());
    }
}
