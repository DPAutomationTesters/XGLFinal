package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class MCFinanceWidgetPage extends BaseClass
{
    @FindBy(xpath = "//div[@ng-model='finance.data.billingPeriodId']/div[last()]/i")
    public WebElement mcFinanceBilldropdwn;

    @FindBy(xpath = "//div[@ng-model='finance.data.billingPeriodId']/div[1]")
    public WebElement mcFinaceBilldefaultvalue;

    @FindAll(@FindBy(xpath = "//div[@class='dropDown']/div"))
    public List<WebElement> mcFinaceBilldropdownoptions;

    @FindBy(xpath = "//div[normalize-space()='All Orders']")
    public WebElement allorderradiobtn;

    @FindBy(xpath = "//div[normalize-space()='Monthly Billing Cycle']")
    public WebElement monthlybillcycleradiobtn;

    @FindBy(xpath = "//div[normalize-space()='End of Flight Billing Cycle']")
    public WebElement EndofFlightradiobtn;

    @FindBy(xpath = "//div[normalize-space()='Calendar Billing Cycle']")
    public WebElement CalenderBillcycleradiobtn;

    @FindBy(xpath = "//div[normalize-space()='Shared Billing Id']")
    public WebElement SharedBillidradiobtn;

    @FindBy(xpath = "//div[normalize-space()='No Agency']")
    public WebElement NoAgencyradiobtn;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('open')\"]")
    public WebElement Open;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('open')\"]/div[last()]")
    public WebElement openValue;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('prepared')\"]")
    public WebElement Prepared;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('prepared')\"]/div[last()]")
    public WebElement Preparedvalue;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('submitted')\"]")
    public WebElement Submitted;

    @FindBy(xpath = "//div[@ng-click=\"finance.openBillingSubmission('submitted')\"]/div[last()]")
    public WebElement SubmittedValue;

    @FindBy(xpath="//div[@class='grid-canvas']/div[contains(@class,'ui-widget-content slick-row')]")
    WebElement row;
    @FindBy(xpath = "//div[@class='grid-canvas']")
    WebElement gridCanvas;

    @FindBy(xpath = "//div[contains(@class,'nextPagesBtn')]/button[contains(@ng-click,'changeCurFastPages')]")
    WebElement next8pagebtn;

    @FindBy(xpath = "//div[@class='pagesOf']/span[@class='ng-binding']")
    WebElement pageof;


    public MCFinanceWidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getmccount(WebElement ele)
    {
        WaitUtility.waitTillElementVisible(driver,30,ele);
        return Integer.parseInt(ele.getText());
    }
    public int getrecordcountoflastpage()
    {
        try{
        if(!row.isDisplayed())
        {
            return 0;
        }}catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            return 0;
        }
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
            if(ht>=1500) {
                totalRecords = totalRecords + (ht / 30);
            } else
            {
                totalRecords = totalRecords + recordsOnPage.size();
            }
        }
        return totalRecords;
    }
    public void selectBillingPeriod()
    {
        String defaultBillperiod=mcFinaceBilldefaultvalue.getText();
        if(!defaultBillperiod.equalsIgnoreCase(ConfigReader.getPropertyvalue("DefaultBillingPeriod"))) {
            CommonUtility.clickElement(mcFinanceBilldropdwn);
            for (WebElement e : mcFinaceBilldropdownoptions) {
                if (e.getText().equalsIgnoreCase(ConfigReader.getPropertyvalue("DefaultBillingPeriod"))) {
                    e.click();
                }
            }
        }
    }

    public Boolean verifyOpenCount()
    {
        BrowserUtilities bu=new BrowserUtilities();
        bu.refreshbrowser();
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number=getmccount(openValue);
        System.out.println("MC value for open is "+number);
        Reporter.log("MC value for open is "+number);
        cu.clickElement(Open);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("BillingSubmission"));

        System.out.println("Billing submission count for open invoices is "+getrecordcountoflastpage());
        Reporter.log("Billing submission count for open invoices is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }

    public Boolean verifyPreparedCount()
    {
        BrowserUtilities bu=new BrowserUtilities();
        bu.backfromBrowser();
        WaitUtility.waitTillElementVisible(driver,30,Prepared);
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number=getmccount(Preparedvalue);
        System.out.println("MC value for prepared is "+number);
        Reporter.log("MC value for prepared is "+number);
        cu.clickElement(Prepared);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("BillingSubmission"));

        System.out.println("Billing submission count for prepared invoices is "+getrecordcountoflastpage());
        Reporter.log("Billing submission count for prepared invoices is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }

    public Boolean verifySubmittedCount()
    {
        BrowserUtilities bu=new BrowserUtilities();
        bu.backfromBrowser();
        WaitUtility.waitTillElementVisible(driver,30,Submitted);
        Boolean flag=false;
        CommonUtility cu=new CommonUtility();
        int number=getmccount(SubmittedValue);
        System.out.println("MC value for submitted is "+number);
        Reporter.log("MC value for submitted is "+number);
        cu.clickElement(Submitted);
        WaitUtility.waitTillElementVisible(driver,30,row);
        Assert.assertTrue(driver.getCurrentUrl().contains("BillingSubmission"));

        System.out.println("Billing submission count for submitted invoices is "+getrecordcountoflastpage());
        Reporter.log("Billing submission count for submitted invoices is "+getrecordcountoflastpage());
        if(number==getrecordcountoflastpage())
        {
            flag=true;
        }
        return flag;
    }
}
