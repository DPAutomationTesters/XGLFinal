import base.BaseClass;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.*;

public class XGLT_21649_Test extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CreateNewStandardCust()
    {
        CustomerPage cp=new CustomerPage(driver);
        cp.createStandardCustomer();
    }
    @Test(priority = 2,enabled = true)
    public void createStandardCopygroup()
    {
        CustomerAdcopy ad= new CustomerAdcopy(driver);
        ad.createaddcopy(30);
        Customer_AdCopyGroup AC=new Customer_AdCopyGroup(driver);
        AC.createaddcopytypeGroup("Standard");
    }

    @Test(priority=3, enabled = true)
    public void Customer_NewOrder()
    {
        Customer_Order CO =new Customer_Order(driver);
        CO.Customer_OrderAdd();
    }

    @Test(priority = 4,enabled = true)
    public void Customer_NewOrderRetailline()
    {
      Customer_Orderline col= new Customer_Orderline(driver);
       Assert.assertTrue(col.Customer_OrderlineAdd());
        OL_approve oa=new OL_approve(driver);
        oa.approveOL();

        if(oa.editapproveOL())
        {        oa.verifypriorityvalues();
                oa.verifyrevenuetypevalues();
        Reporter.log("AP/SC orderline updated successfully");}
        else {
            Reporter.log("Edit orderline failed");
            Assert.fail("Edit orderline failed");
        }


    }
}
