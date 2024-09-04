import base.BaseClass;
import org.testng.annotations.Test;
import pages.CustomerAdcopy;
import pages.CustomerPage;
import pages.Customer_AdCopyGroup;

public class XGLT_6673_Test extends BaseClass
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
    @Test(priority = 3,enabled = true)
    public void createBillboardCopygroup()
    {
        CustomerAdcopy ad= new CustomerAdcopy(driver);
        ad.createaddcopy(30);
        Customer_AdCopyGroup AC=new Customer_AdCopyGroup(driver);
        AC.createaddcopytypeGroup("Billboard");
    }
    @Test(priority = 4,enabled = true)
    public void createBookendCopygroup()
    {
        CustomerAdcopy ad= new CustomerAdcopy(driver);
        ad.createaddcopy(15);
        ad.createaddcopy(15);
        Customer_AdCopyGroup AC=new Customer_AdCopyGroup(driver);
        AC.createaddcopytypeGroup("Bookend");
    }
    @Test(priority = 5,enabled = true)
    public void createPiggybackCopygroup()
    {
        CustomerAdcopy ad= new CustomerAdcopy(driver);
        ad.createaddcopy(15);
        ad.createaddcopy(15);
        Customer_AdCopyGroup AC=new Customer_AdCopyGroup(driver);
        AC.createaddcopytypeGroup("Piggyback");
    }

}
