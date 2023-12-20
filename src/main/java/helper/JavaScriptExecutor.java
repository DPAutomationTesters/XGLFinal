package helper;

import base.BaseClass;
import dataProvider.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutor extends BaseClass
{
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void clickElementByJS(WebElement ele)
    {
        WaitUtility.waitTillElementVisible(driver,30,ele);
        js.executeScript("arguments[0].click();",ele);

    }
    public void setElementPropertyByJS(WebElement ele, String height)
    {
        js.executeScript("arguments[0].style.height = '"+height+"';", ele);
    }
    public void setElementWidthPropertyByJS(WebElement ele, String width)
    {
        js.executeScript("arguments[0].style.width = '"+width+"';", ele);
    }
    public void sendKeysByJS(By ele,String str)
    {
        WaitUtility.waitTillElementVisible(driver,30,driver.findElement(ele));
        js.executeScript("arguments[0].setAttribute('value', '" + str +"')", ele);
    }
    public void scrolldownbyjs()
    {
        js.executeScript("window.scrollBy(0,500)", "");
    }
    public void scrolltobottombyJS()
    {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    // This method is specific to XGL pages getting scroll down, this is different from windows scroll down
    public void scrolltoXGLpagesbottom()
    {
        js.executeScript("document.querySelector('.slick-viewport').scrollTop = 3000;");
    }
    public void highlightElement(WebElement ele,String style)
    {
        try {
            js.executeScript("arguments[0].style.border='"+style+"'", ele);
            js.executeScript("arguments[0].style.border=''", ele);
        }
        catch (Exception e)
        {
            ExceptionHandling h=new ExceptionHandling(driver);
            h.handleException(e);
            Thread.currentThread().interrupt();
        }

    }


    public void setBrowserZoom(double zoomFactor) {

        // Set the browser window zoom using JavaScript
        js.executeScript("document.body.style.zoom = arguments[0];", zoomFactor);
    }
}
