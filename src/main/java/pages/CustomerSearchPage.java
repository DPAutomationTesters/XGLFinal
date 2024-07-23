package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class CustomerSearchPage extends BaseClass
{
    public CustomerSearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="header.menuOrders")
    WebElement menuOrders;

    @FindBy(id="header.subMenuCustomers")
    WebElement menuCustomers;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue;

    @FindBy(xpath = "//div[@id='customersGrid.selectSearch']/div/div/div[1]")
    WebElement selectSearchvalue;

    @FindBy(xpath = "//div[@id='customersGrid.mineAll']/div[contains(text(),'All')]")
    WebElement All;

    @FindBy(xpath = "//div[@id='customersGrid.mineAll']/div[contains(text(),'All')]")
    WebElement mine;

    @FindBy(xpath = "//div[@id='customersGrid.selectFilter']/div[@class='iconContainer']/i")
    WebElement custgridfilter;

    @FindBy(xpath = "//div[@id='customersGrid.selectFilter']/div[last()]/div/div[contains(text(),'Standard')]")
    WebElement custCategorySelect;

    @FindBy(xpath = "//div[@id='customersGrid.selectSearch']/div/div/div[last()]/i[@class='fa fa-angle-down']")
    WebElement custSearchdropdown; // click

    @FindBy(xpath = "//div[@class='dropDown']")
    WebElement dropdownvalues;

    @FindBy(xpath = "//b[@class='loupe']")
    WebElement search_button;

    @FindBy(xpath = "//div[@class='dropDown']/div[contains(text(),'Customer Name')]")
    WebElement custnameselect;

    @FindBy(xpath = "//*[@id=\"customersGrid.selectSearch\"]/div/input[@ng-model='tempModel.value']")
    WebElement custsearchinput; // click, send key

    @FindBy(xpath = "//div[@class='textContent']/h1") //No Items to Display
    WebElement NoDataMessage;

    @FindBy(xpath = "//div[contains(@class,'actionHighlight')]")
    WebElement clickCustomer;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement breadcrumcust;
    public boolean search_customer(String customer)
    {
        boolean flag=false;
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(menuCustomers);
        Utility.waitforPagetoLoad(driver,10);
        CommonUtility.clickElement(All);
        CommonUtility.clickElement(custgridfilter);
        CommonUtility.clickElement(custCategorySelect);
        if(!selectSearchvalue.getText().equalsIgnoreCase("Customer Name"))
        {
            CommonUtility.clickElement(custSearchdropdown);
            CommonUtility.clickElement(custnameselect);
        }
        SaveProjectData spd=new SaveProjectData();
        String Customername=spd.getprojectData("Standard Cust");
        custsearchinput.clear();
        CommonUtility.sendKeys(custsearchinput,Customername);
        try {
            Robot robot = new Robot();
           // robot.mouseMove(search_button.getLocation().getX(), search_button.getLocation().getY()); // Move mouse to the dropdown
           // robot.keyPress(KeyEvent.VK_ENTER);
           // robot.keyRelease(KeyEvent.VK_ENTER);
            CommonUtility.clickElement(search_button);
        }
        catch (Exception e)
        {
            ExceptionHandling.handleException(e);
        }
        try
        {
            NoDataMessage.isDisplayed();
        }
        catch(NoSuchElementException e)
        {
            ExceptionHandling.handleException(e);
            flag=true;
        }
        try{
            String str= "//div[@class='slick-cell l3 r3']/div[contains(text(),'"+Customername+"')]";
            SoftAssert sa= new SoftAssert();
            sa.assertTrue(driver.findElement(By.xpath(str)).isDisplayed());
        }
        catch(NoSuchElementException e)
        {
            ExceptionHandling.handleException(e);
        }
        return flag;
    }

    public boolean openCustomerProfile()
    {
        Boolean flag=false;
        SaveProjectData spd=new SaveProjectData();
        String Customername=spd.getprojectData("Standard Cust");
        if(search_customer(Customername))
        {
            Reporter.log("No Such Customer present");
        }
        else
        {
            CommonUtility.clickElement(clickCustomer);
            if(breadcrumcust.getText().contains(Customername))
            {
                flag=true;
            }
        }
        return flag;
    }
}
