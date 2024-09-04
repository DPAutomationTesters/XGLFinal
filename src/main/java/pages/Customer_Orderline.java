package pages;

import base.BaseClass;
import dataProvider.ConfigReader;
import helper.CommonUtility;
import helper.ExceptionHandling;
import helper.JavaScriptExecutor;
import helper.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class Customer_Orderline extends BaseClass
{
    @FindBy(id = "CustomerOrderEntity.Orderlines")
    WebElement Orderlinetab;

    @FindBy(id="orderLinesGrid.add")
    WebElement AddOL;

    @FindBy(xpath = "//*[@id=\"quantity_quantityTypeOptions\"]/div[text()='Entire Range']")
    WebElement entryStrategy;

    @FindBy(xpath = "//*[@id=\"quantity_quantityTypeOptions\"]/div[text()='Per Week']")
    WebElement perweek;

    @FindBy(xpath = "//div[@id='quantity_spotsWeek']/input")
    WebElement qty;

    @FindBy(xpath = "//div[@id='adCopyProfile.adCopyCustomer']/input")
    WebElement Adcopygroup;

    @FindBy(xpath = "//*[@id=\"adCopyProfile.adCopyCustomer\"]/div[2]/div")
    WebElement Adgroupselect;

    @FindBy(xpath = "//div[@id='location_network_dropdown']//div[@class='iconContainer']/i")
    WebElement networkidropdown;

    @FindBy(xpath = "//div[@id='location_regRet_dropdown']/div[@class='iconContainer']/i")
    WebElement regionidropdown;

    @FindBy(xpath = "//div[@id='location_regRet_label']")
    WebElement regionlable;

    @FindBy(xpath = "//input[@id='locationGridRns_totalRate_textbox']")
    WebElement totalrate;

    @FindBy(xpath = "//button[@id='orderline.cancelSave.save']")
    WebElement saveOl;

    @FindBy(xpath = "//*[@id=\"breadCrumbs.crumb3\"]//div[1][contains(text(),'Order Line')]")
    WebElement Orderline;

    @FindBy(xpath = "//i[@class='fa fa-angle-up']")
    WebElement dropup;

    public Customer_Orderline(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean Customer_OrderlineAdd()
    {
        boolean flag=false;
        CommonUtility.clickElement(Orderlinetab);
        CommonUtility.clickElement(AddOL);
        if(driver.getCurrentUrl().contains("Orderlines/0/Profile"))
        {
            CommonUtility.clickElement(perweek);
            CommonUtility.clickElement(qty);
            qty.clear();
            CommonUtility.clickElement(qty);
            qty.sendKeys("1");
            CommonUtility.clickElement(Adcopygroup);
            List<WebElement> elements=driver.findElements(By.xpath("//*[@id='adCopyProfile.adCopyCustomer']/div[2]/div"));
            elements.get(0).click();
            List<WebElement> elements1=driver.findElements(By.xpath("//div[@id='location_radiobuttons']/div"));
            elements1.get(0).click();
            CommonUtility.clickElement(regionlable);
            //Select network from dowpdown
           // WaitUtility.waittillElementInteractable(driver,2,"//div[@id='location_network_dropdown']/div[2]/i");
           // CommonUtility.clickElement(networkidropdown);
            Actions ac = new Actions(driver);
            int i=30;
            do{
                if(i>0)
                { i--;
            ac.moveToElement(networkidropdown).click(networkidropdown);
            ac.perform();}
                else {
                    break;
                }
            }while(!dropup.isDisplayed());
            String net= ConfigReader.getPropertyvalue("Net");
            //String str="//div[@class='dropDown']/div[contains(text(),'"+net+"')]";
            List<WebElement> element2= driver.findElements(By.xpath("//div[@class='dropDown']/div"));
            for (WebElement ele:element2)
            {
                if(ele.getText().contains(net))
                {
                    ele.click();break;
                }
            }

            //select region from dowpdown
            int j=30;
            do{
                if(j>0)
                {
                    j--;
                ac.moveToElement(regionidropdown).click(regionidropdown);
                ac.perform();}
                else {
                    break;
                }
            }while(!dropup.isDisplayed());
            String reg= ConfigReader.getPropertyvalue("Reg");
            //String str1="//div[@class='dropDown']/div[contains(text(),'"+reg+"')]";
            List<WebElement> element3= driver.findElements(By.xpath("//div[@class='dropDown']/div"));
            for (WebElement ele:element3)
            {
                if(ele.getText().contains(reg))
                {
                    ele.click();break;
                }
            }

            //set rate
            CommonUtility.clickElement(totalrate);
            totalrate.clear();
            String numberAsString = String.valueOf(100);
            totalrate.sendKeys(numberAsString);
            CommonUtility.clickElement(saveOl);
            int counter=30;
            try {
                do {
                    if (counter > 0) {
                        counter--;
                        ac.moveToElement(saveOl).click(saveOl);
                        ac.perform();
                    } else {
                        break;
                    }
                } while (saveOl.isEnabled());
            }
            catch(Exception e)
            {
                ExceptionHandling.handleException(e);
            }
            Assert.assertTrue(Orderline.getText().contains("Proposed"));
            String arr2[]=Orderline.getText().split(":");
            String arr3[]=arr2[0].split(" ");
            Reporter.log("Orderline is created with number "+arr3[2]);
            System.out.println("Orderline is created with number "+arr3[2]);
            flag=true;
        }
        else
        {
            Reporter.log("Orderline profile page did not open");
            flag=false;
        }
        return flag;
    }
}
