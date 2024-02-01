import base.BaseClass;
import helper.BrowserUtilities;
import helper.WaitUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MCFinanceWidgetPage;
import pages.MCSettingsPage;

public class XGLT_6687_Test  extends BaseClass
{
    @Test(priority = 1,enabled = true,groups = {"Mission Control - Customers"})
    public void setFinanceWidget()
    {
        MCSettingsPage mc= new MCSettingsPage(driver);
        mc.openMcSettings();
        mc.resetSettings();
        mc.openMcSettings();
        mc.setMcsettingfirstvalue("Finance");
        mc.saveMcSettings();
        BrowserUtilities bu= new BrowserUtilities();
        bu.refreshbrowser();
        Assert.assertTrue(mc.verifymcvalueset("Finance"));
    }

    @Test(priority = 2,groups = {"Mission Control - Customers"})
    public void OpenRecord()
    {
        MCFinanceWidgetPage mc= new MCFinanceWidgetPage(driver);
        Assert.assertTrue(mc.verifyOpenCount());
    }

    @Test(priority = 3,enabled = true,groups = {"Mission Control - Customers"},dependsOnMethods = "setFinanceWidget")
    public void PreparedRecord()
    {
        MCFinanceWidgetPage mc= new MCFinanceWidgetPage(driver);
        Assert.assertTrue(mc.verifyPreparedCount());
    }

    @Test(priority = 4,enabled = true,groups = {"Mission Control - Customers"},dependsOnMethods = "setFinanceWidget")
    public void SubmittedRecord()
    {
        MCFinanceWidgetPage mc= new MCFinanceWidgetPage(driver);
        Assert.assertTrue(mc.verifySubmittedCount());
    }
}
