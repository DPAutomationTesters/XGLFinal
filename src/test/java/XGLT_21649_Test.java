import base.BaseClass;
import org.testng.annotations.Test;
import pages.CustomerAdcopy;
import pages.CustomerPage;
import pages.Customer_AdCopyGroup;
import pages.Customer_Order;

public class XGLT_21649_Test extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CreateNewStandardCust()
    {
        CustomerPage cp=new CustomerPage(driver);
        cp.createStandardCustomer();
    }
    @Test(priority = 2,enabled = true)
    public void createNewaddcopy()
    {
        CustomerAdcopy ad= new CustomerAdcopy(driver);
        ad.createaddcopy(30);
    }

    @Test(priority = 3,enabled = false)
    public void createNewcopygroup()
    {
        Customer_AdCopyGroup AC=new Customer_AdCopyGroup(driver);
        AC.createaddcopyGroup();
    }

    @Test(priority=4, enabled = false)
    public void Customer_NewOrder()
    {
        Customer_Order CO =new Customer_Order(driver);
        CO.Customer_OrderAdd();
    }
}
