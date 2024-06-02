import base.BaseClass;
import helper.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HeadendPage;
import java.util.concurrent.TimeUnit;


public class Create_Headend extends BaseClass {

    private String Headend;

    @Test(priority = 2)
    public void search_Headend() {
        Boolean b = true;
        Utility ut = new Utility();
       Headend = ut.randomAlphaNumeric(2);
        HeadendPage np = new HeadendPage(driver);
        b = np.search_Headend("HD012");
        Assert.assertTrue(b);
        if (b) {
            System.out.println("Can proceed to create a new Headend " + Headend);
            this.Headend = Headend;
        } else {
            System.out.println("Need a new Headend");
        }
    }

   @Test(priority = 1,enabled = true)
    public void AddNewHeadend()
    {
        HeadendPage HeadP=new HeadendPage(driver);
        Boolean result=HeadP.create_Headend("HD012");
        Assert.assertTrue(result);
        System.out.println("Headend "+Headend+" is created");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
}
