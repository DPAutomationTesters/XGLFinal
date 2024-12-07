import base.BaseClass;
import org.testng.annotations.*;
import pages.massEdit280;
public class XGLT_6696_Test_280 extends BaseClass
{
    @Test(priority = 1, enabled=true)
    public void loadAdUnits()
    {
        massEdit280 cp= new massEdit280(driver);
        cp.orderOrderlines();
    }
}
