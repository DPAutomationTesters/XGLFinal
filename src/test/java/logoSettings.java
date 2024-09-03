import base.BaseClass;
import org.testng.annotations.*;
import pages.logoInvoice;
public class logoSettings extends BaseClass
{
    @Test(priority = 1, enabled=true)
    public void logo()
    {
        logoInvoice cp= new logoInvoice(driver);
        cp.logoElements();
    }
}
