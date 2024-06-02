package helper;

import base.BaseClass;
import org.openqa.selenium.WebElement;

public class CommonUtility extends BaseClass
{
    public static void clickElement(WebElement element)
    {
        WaitUtility.waitTillElementVisible(driver,30,element);

        element.click();
    }
    public static void sendKeys(WebElement element,String str)
    {
        WaitUtility.waitTillElementtobeClickable(driver,30,element);
        element.sendKeys(str);
    }
}
