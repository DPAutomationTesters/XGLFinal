package helper;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class XGL_CountRecordOnScreen extends BaseClass
{
    WebElement row=driver.findElement(By.xpath("//div[@class='grid-canvas']/div[contains(@class,'ui-widget-content slick-row')]"));

     WebElement next8pagebtn=driver.findElement(By.xpath("//div[contains(@class,'nextPagesBtn')]/button[contains(@ng-click,'changeCurFastPages')]"));

     WebElement pageof=driver.findElement(By.xpath("//div[@class='pagesOf']/span[@class='ng-binding']"));

    WebElement gridCanvas= driver.findElement(By.xpath("//div[@class='grid-canvas']"));

    public int getrecordcountoflastpage()
    {
        CommonUtility cu=new CommonUtility();
        int totalRecords = 0;
        WaitUtility.waitTillElementtobeClickable(driver,20,next8pagebtn);
        while (next8pagebtn.isEnabled())
        {
            next8pagebtn.click();
            if(driver.findElements(By.xpath("//div[@class='grid-canvas']/div[contains(@class,'ui-widget-content slick-row')]")).size()==0)
            {
                return totalRecords;
            }
        }
        if(!next8pagebtn.isEnabled())
        {
            JavaScriptExecutor js=new JavaScriptExecutor();
            js.scrolltobottombyJS();
            String arr[]=pageof.getText().split("of ");
            int i=Integer.parseInt(arr[arr.length-1]);
            //Evaluate last page, click on last page , get counts till last page
            System.out.println("last page in current selection is "+i);
            if(i>1)
            {
                totalRecords=totalRecords+((i-1)*100);
                //Get counts on the last and get the final record count
                String xpath2 = "//span[contains(@class,'fastPageNumber ng-binding ng-scope') and text()='" + i + "']";
                WebElement lastpage = driver.findElement(By.xpath(xpath2));
                cu.clickElement(lastpage);
                WaitUtility.waitTillElementVisible(driver,30,row);
                js.scrolltobottombyJS();
                js.scrolltoXGLpagesbottom();
            }
            else if (i==1)
            {
                //do nothing
            }
            int ht=gridCanvas.getSize().getHeight();
            System.out.println("Height is "+ht);
            List<WebElement> recordsOnPage = driver.findElements(By.xpath("//div[@class='grid-canvas']/div[contains(@class,'ui-widget-content slick-row')]"));
            if(ht>516) {
                totalRecords = totalRecords + (ht / 30);
            } else if(ht<=516)
            {
                totalRecords = totalRecords + recordsOnPage.size();
            }
        }
        return totalRecords;
    }
}
