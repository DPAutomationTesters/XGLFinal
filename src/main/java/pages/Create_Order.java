package pages;
import base.BaseClass;
import org.testng.annotations.Test;


public class Create_Order extends BaseClass
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
        int argumentValue = 123; // Provided an appropriate integer value
        ad.createaddcopy(argumentValue);
    }

    @Test(priority = 3,enabled = true)
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