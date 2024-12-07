import base.BaseClass;
import org.testng.annotations.*;
import pages.massEdit2539;
public class XGLT_6696_Test_2539 extends BaseClass
{
    @Test(priority = 1, enabled=true)
    public void loadAdUnits()
    {
        massEdit2539 cp= new massEdit2539(driver);
        cp.orderOrderlines();
    }
}