package pages;

import base.BaseClass;
import helper.*;
import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static helper.WaitUtility.waittillElementInteractable;
import static org.openqa.selenium.Keys.PAGE_DOWN;

public class SalesPersonPage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuOrderSettings')]")
    WebElement orderSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Order Settings

    @FindBy(xpath = "//button[contains(@id,'salesPeopleGridDrtv.add')]")
    WebElement addSalesPeople;

    @FindBy(xpath = "//input[@id='salesPeopleProfile_initials']")
    WebElement spinitial;

    @FindBy(xpath = "//input[@id='salesPeopleProfile_name']")
    WebElement spname;

    @FindBy(xpath = "//input[contains(@id,'phone')]")
    WebElement spphone;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_officeVid']")
    WebElement spsalesofficedropdnbutton;

    @FindBy(xpath = "//div[@name='officeVid']/div[@class='iconContainer']/i")
    WebElement spsalesofficeibutton;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_companyEntityId']")
    WebElement spcompanyentitybutton;

    @FindBy(xpath = "//div[@name='companyEntity']/div[@class='iconContainer']/i")
    WebElement spcompanyentityibutton;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement spSave;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement spbreadcrum;

    @FindBy(xpath = "//div[@class='dropDown']")
    WebElement dropdown;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_officeVid']/div[contains(@class,'auto-complete-list-drop-down')]/div/span")
    WebElement salesofficedrpdwn;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_companyEntityId']/div[contains(@class,'auto-complete-list-drop-down')]/div")
    WebElement companyentitydropdwn;

    @FindBy(xpath = "//div[@name='officeVid']//div[contains(@class,'option ng-scope')][1]")
    WebElement dropdownvalue;

    @FindBy(xpath = "//div[@name='companyEntity']//div[contains(@class,'option ng-scope')]")
    WebElement CEdropdownvalue;
    public SalesPersonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectdropdownvalue(WebElement ele)
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
    public void selectdropdownvalue()
    {
        String str;
        WebElement ele=driver.findElement(By.xpath("//div[@class='dropDown']/div[1]"));
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


    public void setAddSalesPeople()
    {
        CommonUtility.clickElement(config);
        CommonUtility.clickElement(orderSettings);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Order Settings"));
        CommonUtility.clickElement(addSalesPeople);
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(3);
        CommonUtility.sendKeys(spinitial,init);
        String sp_name=init+" salesperson";
        CommonUtility.sendKeys(spname,sp_name);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", spsalesofficedropdnbutton);
        try{
            if(spsalesofficeibutton.isDisplayed())
            {
                CommonUtility.clickElement(spsalesofficeibutton);
                WaitUtility.waitStatic(500);
                selectdropdownvalue();WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(spsalesofficedropdnbutton);
            WaitUtility.waitStatic(500);//
            selectdropdownvalue(dropdownvalue);
            WaitUtility.waitStatic(100);
        }

        try{
            if(spcompanyentityibutton.isDisplayed())
            {
                CommonUtility.clickElement(spcompanyentityibutton);
                WaitUtility.waitStatic(500);
                selectdropdownvalue();WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(spcompanyentitybutton);
            WaitUtility.waitStatic(500);
            selectdropdownvalue(CEdropdownvalue);
            WaitUtility.waitStatic(100);
        }

        CommonUtility.clickElement(spSave);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(spbreadcrum.isDisplayed())
        {
            Assert.assertTrue(spbreadcrum.getText().contains("New Salesperson"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Salesperson",init);
        }
    }
}
