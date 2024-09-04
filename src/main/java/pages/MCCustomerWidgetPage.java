package pages;

import base.BaseClass;
//import dataProvider.ConfigReader;
import helper.BrowserUtilities;
import helper.CommonUtility;
import helper.JavaScriptExecutor;
import helper.WaitUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import javax.security.auth.x500.X500Principal;
import java.util.List;

public class MCCustomerWidgetPage extends BaseClass
{
    WebDriverWait wait;
    @FindBy(id = "header.missionControl")
    WebElement missioncontrol;
    @FindBy(xpath = "//div[contains(@class,'btnBig') and contains(@ng-click,'goToCustomersGrid')and contains(@ng-click,'activeWithRevenue')]")
    WebElement activewithrev;

    @FindBy(xpath = "//div[contains(@ng-click,'activeWithRevenue')]/div[@class='ng-binding']")
    WebElement activewithrevvalue;

    @FindBy(xpath = "//div[contains(@class,'btnBig') and contains(@ng-click,'goToCustomersGrid')and contains(@ng-click,'inactive')]")
    WebElement inactive;

    @FindBy(xpath = "//div[contains(@ng-click,'inactive')]/div[@class='ng-binding']")
    WebElement inactiverevenuevalue;

    @FindBy(xpath = "//div[contains(@class,'btnBig') and contains(@ng-click,'goToCustomersGrid')and contains(@ng-click,'activeWithoutRevenue')]")
    WebElement activewithoutrev;

    @FindBy(xpath = "//div[contains(@ng-click,'activeWithoutRevenue')]/div[@class='ng-binding']")
    WebElement activewithoutrevvalue;

    @FindBy(xpath = "//div[contains(@class,'btnBig') and contains(@ng-click,'goToCustomersGrid')and contains(@ng-click,'creditHold')]")
    WebElement creditHold;

    @FindBy(xpath = "//div[contains(@ng-click,'creditHold')]/div[@class='ng-binding']")
    WebElement creditholdvalue;

    @FindBy(xpath="//div[@class='grid-canvas']/div[contains(@class,'ui-widget-content slick-row')]")
    WebElement row;

    @FindBy(xpath = "//div[@class='pagesOf']/span[@class='ng-binding']")
    WebElement pageof;

    @FindBy(xpath = "//div[contains(@class,'nextPagesBtn')]/button[contains(@ng-click,'changeCurFastPages')]")
    WebElement next8pagebtn;

    @FindBy(xpath = "//div[@class='grid-canvas']")
    WebElement gridCanvas;
    public  MCCustomerWidgetPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean setMCCustomer()
    {
        Boolean flag= false;
        MCSettingsPage mc= new MCSettingsPage(driver);
        BrowserUtilities bu= new BrowserUtilities();
        CommonUtility.clickElement(missioncontrol);
            mc.openMcSettings();
            mc.resetSettings();
            mc.addwait(mc.mcsettingbtn);
            mc.openMcSettings();
            mc.setMcsettingfirstvalue("Customers");
            mc.saveMcSettings();
            bu.refreshbrowser();
            Assert.assertTrue(mc.verifymcvalueset("Customers"));
            flag=true;
        return flag;
    }

    public String getactiverevcount(WebElement ele)
    {
        WaitUtility.waitTillElementVisible(driver,30,ele);
        String str=ele.getText();
        if(str.contains(","))
        {
            String stringWithoutComma = str.replace(",", "");
            return stringWithoutComma;
        }
       else {
            return str;
        }}
    public int getrecordcountoflastpage()
    {
        CommonUtility cu=new CommonUtility();
        int totalRecords = 0;
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
            if(ht>534) {
                totalRecords = totalRecords + (ht / 30);
            } else if(ht<=534)
            {
                totalRecords = totalRecords + recordsOnPage.size();
            }
        }
        return totalRecords;
    }
    public Boolean verifyactivewithrevcount()
    {
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number = Integer.parseInt(getactiverevcount(activewithrevvalue));
        System.out.println("MC value is "+number);
        Reporter.log("MC Customer with active revenue value is "+number);
        cu.clickElement(activewithrev);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("Customers"));
        System.out.println("Customer page count for active revenue is "+getrecordcountoflastpage());
        Reporter.log("Customer page count for active revenue is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }

    public Boolean verifyinactivewithrevcount()
    {
        BrowserUtilities bu=new BrowserUtilities();
        bu.backfromBrowser();
        WaitUtility.waitTillElementVisible(driver,30,inactive);
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number = Integer.parseInt(getactiverevcount(inactiverevenuevalue));
        System.out.println("MC inactive value is "+number);
        Reporter.log("MC Customer with inactive revenue value is "+number);
        cu.clickElement(inactive);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("Customers"));
        System.out.println("Customer page count for inactive revenue is "+getrecordcountoflastpage());
        Reporter.log("Customer page count for inactive revenue is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }
    public Boolean verifyactivewithoutrevcount()
    {
        BrowserUtilities bu=new BrowserUtilities();
        bu.backfromBrowser();
         WaitUtility.waitTillElementVisible(driver,30,activewithoutrev);
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number = Integer.parseInt(getactiverevcount(activewithoutrevvalue));
        System.out.println("MC active without revenue value is "+number);
        Reporter.log("MC active without revenue value is "+number);
        cu.clickElement(activewithoutrev);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("Customers"));
        System.out.println("Customer page count for active without revenue is "+getrecordcountoflastpage());
        Reporter.log("Customer page count for active without revenue is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }
    //creditHold,creditholdvalue
    public Boolean verifycreditholdcount()
    {
        BrowserUtilities bu=new BrowserUtilities();
       bu.backfromBrowser();
       WaitUtility.waitTillElementVisible(driver,30,creditHold);
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number = Integer.parseInt(getactiverevcount(creditholdvalue));
        System.out.println("MC credit hold value is "+number);
        Reporter.log("MC credit hold value is "+number);
        cu.clickElement(creditHold);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("Customers"));
        System.out.println("Customer page count for credit hold is "+getrecordcountoflastpage());
        Reporter.log("Customer page count for credit hold is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }
}
