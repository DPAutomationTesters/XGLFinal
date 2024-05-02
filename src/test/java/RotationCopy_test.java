import base.BaseClass;
import org.testng.annotations.Test;
import pages.Rotationgroup_copy;

public class RotationCopy_test extends BaseClass
{
    @Test(priority = 1,enabled = true)
    public void CopyRotaionGroup()
    {
        Rotationgroup_copy rc= new Rotationgroup_copy(driver);
        rc.copy_group();

    }
}
