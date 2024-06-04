import base.BaseClass;
import helper.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NetworkPage;
import pages.UserRolePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class XGLT_6756_Test extends BaseClass
{
    private String user_role;

    @Test(priority = 1,enabled = true,groups={"User settings"})
    public void search_user_role()
    {
        Boolean b=true;
        Utility ut= new Utility();
        user_role=ut.randomAlphaNumeric(2);
        UserRolePage np= new UserRolePage(driver);
        b=np.search_role(user_role);
        Assert.assertTrue(b);
        if(b)
        {
            System.out.println("Can proceed to create a new user role "+user_role);
        }
        else
        {
            System.out.println("Need a new user role");
        }
    }
    @Test(priority = 2,dependsOnMethods = "search_user_role",groups = {"User settings"})
    public void create_user_role()
    {
        UserRolePage np= new UserRolePage(driver);
        Boolean result=np.create_user_role(user_role);
        Assert.assertTrue(result);
        System.out.println("User role "+user_role+" is created");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

}
