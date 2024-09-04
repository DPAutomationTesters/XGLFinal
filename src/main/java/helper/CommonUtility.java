package helper;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

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
    public static void selectdropdownvalue(WebElement ele)
    {
        String str;
        try {
            str = ele.getText();
            CommonUtility.clickElement(ele);
            Reporter.log("Value selected is " + str);
        }
        catch(Exception et)
        {
            ExceptionHandling.handleException(et);
        }
    }
    public static void selectdropdownvalue()
    {
        String str;
        WebElement ele=driver.findElement(By.xpath("//div[@class='dropDown']/div[2]"));
        try {
            str = ele.getText();
            CommonUtility.clickElement(ele);
            Reporter.log("Value selected is " + str);
        }
        catch(Exception et)
        {
            ExceptionHandling.handleException(et);
        }
    }

    public static void selectdropdownvaluefromlist(WebElement ele)
    {
        try {

            CommonUtility.clickElement(ele);
            Reporter.log("Value selected is " + ele.getText());
        }
        catch(Exception et)
        {
            ExceptionHandling.handleException(et);
        }
    }
}
