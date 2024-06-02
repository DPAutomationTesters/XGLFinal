import base.BaseClass;
import org.testng.annotations.Test;
import pages.Customer_AdCopyGroup;

public class AdCopyGroup_STD extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void Customer_AdCopyGroup()
    {
        Customer_AdCopyGroup rc= new Customer_AdCopyGroup(driver);
        rc.createaddcopyGroup();

    }

}
