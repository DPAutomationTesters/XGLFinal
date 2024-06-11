package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerPage extends BaseClass
{
    @FindBy(id="header.menuOrders")
    public
    WebElement menuOrders;

    @FindBy(id="header.subMenuCustomers")
    public
    WebElement menuCustomers;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    public
    WebElement breadcrumvalue;

    @FindBy(xpath = "//div[@class='sepV']//button[@id='customersGridDrtv.csv']")
    public
    WebElement exportCSV;

    @FindBy(xpath = "//button[@id='customersGrid.add']")
    WebElement addCustomers;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[@class='iconContainer']")
    WebElement typedropdwn;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[1]")
    WebElement typeValue;

    @FindBy(xpath = "//div[@id='customersProfile.type']/div[last()]/div/div")
    WebElement custtypevalue;//Standard,Agency,Rep

    @FindBy(xpath = "//div[@id='customersProfile.checkBoxCreditHold']")
    WebElement creditholdchckbx;

    @FindBy(xpath = "//div[@id='customersProfile.active']")
    WebElement inactivechckbox;

    @FindBy(xpath = "//input[@id='customersProfile.custNumber']")
    WebElement custid;

    @FindBy(xpath = "//input[@id='customersProfile.company']")
    WebElement custname;

    @FindBy(xpath = "//div[@id='customersProfile.salespersonVid']")
    WebElement salespersondrpdwn;

    @FindBy(xpath = "//div[@id='customersProfile.salespersonVid']/div[last()]/div/div")
    WebElement spdropdownvalues;

    @FindBy(xpath = "//div[@id='customersProfile.secondaryCommodityVid']")
    WebElement commoditydrpdown;

    @FindBy(xpath = "//div[@id='customersProfile.secondaryCommodityVid']/div[last()]/div/div")
    WebElement commodityvalues;

    @FindBy(xpath = "//button[@id='customer.cancelSave.save']")
    WebElement savecustbtn;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement custbreadcrum;// New Customer

    @FindBy(xpath = "//*[@id='customersProfile.salespersonVid']/div[2]/div[1]")
    WebElement dropdownvalue;

    @FindBy(xpath = "//div[@name='salesPersonVid']/div[@class='iconContainer']/i")
    WebElement salesofficeibutton;

    @FindBy(xpath = "//div[@id='customersProfile.secondaryCommodityVid']/div[@class='iconContainer']/i")
    WebElement commodityibutton;
    @FindBy(xpath = "//*[@id='customersProfile.secondaryCommodityVid']/div[2]/div[2]")
    WebElement commoditydropdownvalue;
    public CustomerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean selectCustType(String type)
    {
        Boolean flag=false;
        Actions ac=new Actions(driver);
        WaitUtility.waitforPageload(10);
         JavaScriptExecutor js=new JavaScriptExecutor();

        js.clickElementByJS(typedropdwn);
        try{
        while(!custtypevalue.isDisplayed()) {
           ac.click(typedropdwn);driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }}
        catch(Exception e)
        {
            ExceptionHandling.handleException(e);
        }
        if(custtypevalue.isDisplayed()) {
            if (type.equalsIgnoreCase("Agency"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Agency')]"));
                //WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[1]"));
                js.clickElementByJS(ele1);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                flag = true;
            }
            else if (type.equalsIgnoreCase("Standard"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Standard')]"));
                js.clickElementByJS(ele1);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                flag = true;
            }
            else if (type.equalsIgnoreCase("Rep"))
            {
                WebElement ele1=driver.findElement(By.xpath("//div[@class='dropDown']/div[contains(text(),'Rep')]"));
                js.clickElementByJS(ele1);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                flag = true;
            }
        }
        return flag;
    }
    public boolean selectSalesperson()
    {
        Boolean flag=false;
        List<WebElement> ele=driver.findElements(By.xpath("//div[@id='customersProfile.salespersonVid']/div[last()]/div/div"));
        for (WebElement e:ele)
        {
               e.click();
                flag=true;
                break;
        }
        return flag;
    }
    public boolean selectcommodity()
    {
        Boolean flag=false;
        List<WebElement> ele=driver.findElements(By.xpath("//div[@id='customersProfile.secondaryCommodityVid']/div[last()]/div/div"));
        for (WebElement e:ele)
        {
            e.click();
            flag=true;
            break;
        }
        return flag;
    }
    public void createStandardCustomer()
    {
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(menuCustomers);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        CommonUtility.clickElement(addCustomers);
        //driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        sa.assertTrue(selectCustType("Standard"));
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.clickElement(custname);
        CommonUtility.sendKeys(custname,init);

        try{
            if(salesofficeibutton.isDisplayed())
            {
                CommonUtility.clickElement(salesofficeibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(salespersondrpdwn);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(dropdownvalue);
            WaitUtility.waitStatic(100);
        }

        try{
            if(commodityibutton.isDisplayed())
            {
                CommonUtility.clickElement(commodityibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(commoditydrpdown);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(commoditydropdownvalue);
            WaitUtility.waitStatic(100);
        }

        CommonUtility.clickElement(savecustbtn);
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Standard Cust",init);
        }
    }
    public void createAgencyCustomer()
    {
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(menuCustomers);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        CommonUtility.clickElement(addCustomers);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        sa.assertTrue(selectCustType("Agency"));
        Assert.assertEquals(typeValue.getText(),"Agency");
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.clickElement(custname);
        CommonUtility.sendKeys(custname,init);
        System.out.println("Customer name is "+init);
        try{
            if(salesofficeibutton.isDisplayed())
            {
                CommonUtility.clickElement(salesofficeibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(salespersondrpdwn);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(dropdownvalue);
            WaitUtility.waitStatic(100);
        }

        try{
            if(commodityibutton.isDisplayed())
            {
                CommonUtility.clickElement(commodityibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(commoditydrpdown);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(commoditydropdownvalue);
            WaitUtility.waitStatic(100);
        }
        CommonUtility.clickElement(savecustbtn);
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Agency Cust",init);
        }
    }
    public void createRepCustomer()
    {
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(menuCustomers);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customers"));
        CommonUtility.clickElement(addCustomers);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        sa.assertTrue(selectCustType("Rep"));
        Assert.assertEquals(typeValue.getText(),"Rep");
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.clickElement(custname);
        CommonUtility.sendKeys(custname,init);
        try{
            if(salesofficeibutton.isDisplayed())
            {
                CommonUtility.clickElement(salesofficeibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(salespersondrpdwn);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(dropdownvalue);
            WaitUtility.waitStatic(100);
        }

        try{
            if(commodityibutton.isDisplayed())
            {
                CommonUtility.clickElement(commodityibutton);
                WaitUtility.waitStatic(500);
                CommonUtility.selectdropdownvalue();
                WaitUtility.waitStatic(100);
            }
        }catch(Exception e)
        {
            ExceptionHandling.handleException(e);
            CommonUtility.clickElement(commoditydrpdown);
            WaitUtility.waitStatic(500);//
            CommonUtility.selectdropdownvalue(commoditydropdownvalue);
            WaitUtility.waitStatic(100);
        }
        CommonUtility.clickElement(savecustbtn);
        if(custbreadcrum.isDisplayed())
        {
            Assert.assertTrue(custbreadcrum.getText().contains("New Customer"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Rep Cust",init);
        }

    }






}
