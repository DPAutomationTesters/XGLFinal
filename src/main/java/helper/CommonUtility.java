package helper;

import base.BaseClass;
import org.openqa.selenium.WebElement;

public class CommonUtility extends BaseClass
{
    public static void clickElement(WebElement element)
    {
        WaitUtility.waitTillElementVisible(driver,5,element);

        element.click();
    }
    public static void sendKeys(WebElement element,String str)
    {
        WaitUtility.waitTillElementtobeClickable(driver,10,element);
        element.sendKeys(str);
    }
}
