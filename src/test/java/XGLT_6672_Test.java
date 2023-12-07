import base.BaseClass;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import org.testng.annotations.Test;
import pages.CustomerAdcopy;

public class XGLT_6672_Test extends BaseClass
{

    @Test(priority = 1, enabled = true)
    public void CreateAdcopyoflen15()
    {
       CustomerAdcopy cd=new CustomerAdcopy(driver);
       cd.createaddcopy(15);

    }

    @Test(priority = 2, enabled = false)
    public void CreateSecondAdcopyoflen15()
    {
        CustomerAdcopy cd=new CustomerAdcopy(driver);
        cd.createaddcopy(15);
    }

    @Test(priority = 3, enabled = false)
    public void CreateAdcopyoflen30()
    {
        CustomerAdcopy cd=new CustomerAdcopy(driver);
        cd.createaddcopy(30);
    }

}
