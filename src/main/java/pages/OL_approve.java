package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.List;

public class OL_approve extends BaseClass
{
    @FindBy(xpath = "//button[text()='Approve']")
    WebElement approvebtn;

    @FindBy(xpath = "//*[@id=\"breadCrumbs.crumb3\"]//div[1][contains(text(),'Order Line')]")
    By Orderline;

    @FindBy(xpath = "//button[text()='Edit']")
    WebElement edit;

    @FindBy(xpath = "//*[@id=\"breadCrumbs.crumb3\"]//div[1][contains(text(),'Order Line')]")
    WebElement Orderline1;

    @FindBy(xpath = "//input[@id='scheduling_priority_textbox']")
    WebElement priority;

    @FindBy(xpath = "//div[@id='scheduling_revenueType_dropdown']//i")
    WebElement revenuetypei;
    @FindBy(xpath = "//i[@class='fa fa-angle-up']")
    WebElement dropup;

    @FindBy(xpath = "//button[@id='orderline.cancelSave.save']")
    WebElement saveOl;


    public OL_approve(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void approveOL()
    {
        CommonUtility.clickElement(approvebtn);
        WaitUtility.waitforelementtext(Orderline1,"Approved",60);
        Assert.assertTrue(Orderline1.getText().contains("Approved"));
    }

    public boolean editapproveOL() {

        boolean flag = false;
        CommonUtility.clickElement(edit);
           CommonUtility.clickElement(priority);
            priority.clear();
            String numberAsString = String.valueOf(100);
            priority.sendKeys(numberAsString);
            CommonUtility.clickElement(revenuetypei);
            Actions ac = new Actions(driver);
            /*int i = 30;
            try{
                JavaScriptExecutor js=new JavaScriptExecutor();
                js.clickElementByJS(revenuetypei);
                while (!dropup.isDisplayed()){
                if (i > 0) {
                    i--;
                    ac.moveToElement(revenuetypei).click(revenuetypei);
                    ac.perform();
                } else {
                    break;
                }}}
            catch (Exception e1)
            {
                ExceptionHandling.handleException(e1);
            }*/
            String revtype = ConfigReader.getPropertyvalue("Revenuetype");
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"scheduling_revenueType_dropdown\"]/div[3]/div/div"));
            for (WebElement ele : elements) {
                if (ele.getText().contains(revtype)) {
                    ele.click();
                    break;
                }
            }

            int counter = 30;
            try{
            while(saveOl.isDisplayed())
            {                if (counter > 0) {
                    counter--;
                    ac.moveToElement(saveOl).click(saveOl);
                    ac.perform();
                } else {
                    break;
                }
            }}
            catch(Exception e2)
            {
                ExceptionHandling.handleException(e2);
            }
            flag=true;

        return flag;
    }
    
    public void verifypriorityvalues()
    {
        String arr= "";
        String str[]=driver.getCurrentUrl().split("Orderlines/");
        String str2[]=str[1].split("/");
        String ulid=str2[0];
        DataBaseUtility db=new DataBaseUtility();
        String query="select ulpriority from x_order_line where ulid="+ulid;
        System.out.println(query);
        db.connectToSqlDB(ConfigReader.getPropertyvalue("jdbcUrl"),ConfigReader.getPropertyvalue("DBUname"),ConfigReader.getPropertyvalue("DBPwd"));
        try {
            arr= db.executeSQLdbQueryCount(ConfigReader.getPropertyvalue("jdbcUrl"), ConfigReader.getPropertyvalue("DBUname"), ConfigReader.getPropertyvalue("DBPwd"), query);
        }
        catch(SQLException e)
        {
            ExceptionHandling.handleException(e);
        }
        System.out.println("Priority is DN is "+arr);
        Assert.assertEquals(arr,"100");
    }

    public void verifyrevenuetypevalues()
    {
        String arr= "";
        String str[]=driver.getCurrentUrl().split("Orderlines/");
        String str2[]=str[1].split("/");
        String ulid=str2[0];
        DataBaseUtility db=new DataBaseUtility();
        String query="select SZTYPECODE from x_revenue_type where ULID=(select ULREVENUETYPEVID from x_order_line where ulid="+ulid+")";
        System.out.println(query);
        db.connectToSqlDB(ConfigReader.getPropertyvalue("jdbcUrl"),ConfigReader.getPropertyvalue("DBUname"),ConfigReader.getPropertyvalue("DBPwd"));
        try {
            arr= db.executeSQLdbQueryCount(ConfigReader.getPropertyvalue("jdbcUrl"), ConfigReader.getPropertyvalue("DBUname"), ConfigReader.getPropertyvalue("DBPwd"), query);
        }
        catch(SQLException e)
        {
            ExceptionHandling.handleException(e);
        }
        System.out.println("Revenue type is "+arr);
        if(arr.contains(ConfigReader.getPropertyvalue("Revenuetype")))
        {
            Assert.assertTrue(true);
        }
        else {
            Assert.fail("Revenue type did not update");
        }
    }
}
