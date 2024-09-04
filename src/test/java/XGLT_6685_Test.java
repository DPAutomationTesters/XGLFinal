import base.BaseClass;
import helper.BrowserUtilities;
import helper.WaitUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CustomerPage;
import pages.MCCustomerWidgetPage;

public class XGLT_6685_Test extends BaseClass
{

    @Test(priority = 1,enabled = true,groups = {"Mission Control - Customers"})
    public void MCActivewithRevenue()
    {
        MCCustomerWidgetPage mcw=new MCCustomerWidgetPage(driver);
        CustomerPage cp=new CustomerPage(driver);
        cp.resetUserbasedpersistnanceCP();
        Assert.assertTrue(mcw.setMCCustomer());
        Assert.assertTrue(mcw.verifyactivewithrevcount());
    }
    @Test(priority = 2,enabled = false,groups = {"Mission Control - Customers"})
    public void MCinactivewithrev()
    {
        MCCustomerWidgetPage mcw=new MCCustomerWidgetPage(driver);
        Assert.assertTrue(mcw.verifyinactivewithrevcount());
    }
    @Test(priority = 3,groups = {"Mission Control - Customers"},enabled = false)
    public void MCCredithold()
    {
        MCCustomerWidgetPage mcw=new MCCustomerWidgetPage(driver);
        Assert.assertTrue(mcw.verifycreditholdcount());
    }

    @Test(priority = 3,enabled = false,groups = {"Mission Control - Customers"})
    public void MCactivewithoutrev()
    {
        MCCustomerWidgetPage mcw=new MCCustomerWidgetPage(driver);
        Assert.assertTrue(mcw.verifyactivewithoutrevcount());
    }

}
