import base.BaseClass;
import org.testng.annotations.*;
import pages.orderReconciliationPageThird;

public class reconciliationAdUnits extends BaseClass
{

    @Test(priority = 1, enabled=true)
    public void loadAdUnits()
    {
        orderReconciliationPageThird cp= new orderReconciliationPageThird(driver);
        cp.reconciliationUnits();
    }
}


