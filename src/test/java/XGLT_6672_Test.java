import base.BaseClass;
import org.testng.annotations.Test;
import pages.CustomerAdcopy;

public class XGLT_6672_Test extends BaseClass
{

    @Test(priority = 1, enabled = true)
    public void CreateAdcopy()
    {
        CustomerAdcopy cd=new CustomerAdcopy(driver);
        cd.createaddcopy();
    }

}
