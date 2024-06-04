import base.BaseClass;
import org.testng.annotations.Test;
import pages.Orderlines_LineBilling;

import java.awt.*;


public class LineBilling_Orderline extends BaseClass {
    @Test(priority = 1, enabled = true)
    public void NewLineBilling_Orderline() throws AWTException {
        Orderlines_LineBilling cp = new Orderlines_LineBilling(driver);
        cp.LineBillingOL();
    }
}
