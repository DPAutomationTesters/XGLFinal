package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.SaveProjectData;
import helper.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class Customer_Order extends BaseClass {

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement Customerbreadcrum;

    @FindBy(xpath = "//p[text()='Orders']")
    WebElement orderstab;

    @FindBy(xpath = "//button[@id='customerOrdersGrid.add']")
    WebElement AddNewOrder;

    @FindBy(xpath = "//*[@id=\"newOrder_revenueType_dropDown\"]/input")
    WebElement AddRevenueType;

    @FindBy(xpath = "//span[contains(text(),'| Digital')]")
    WebElement SelectRevenuetype;

    @FindBy(xpath = "//button[@id='newOrder.create']")
    WebElement addorder;


    @FindBy(xpath = "//div[contains(text(),'Individual Retail')]")
    WebElement SearchRetailModeTxt;

    @FindBy(xpath = "//div[contains(text(),'Threshold')]")
    WebElement SearchThresholdModeTxt;

    @FindBy(xpath = "//button[@id='order.cancelSave.save']")
    WebElement SaveOrderSetting;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb2']")
    WebElement orderbreadcrum;

    @FindBy(xpath = "//button[@id='order.cancelSave.edit']")
    WebElement btnedit;

    @FindBy(xpath = "//div[@id='orderlinesSettingsGridDrtv.changeBillingMode']")
    WebElement ordersettingbillmode;

    public Customer_Order(WebDriver driver) {
        BaseClass.driver = driver;
        PageFactory.initElements(driver, this);
    }



     public void Customer_OrderAdd()
        {
            CustomerSearchPage csp = new CustomerSearchPage(driver);
            if (csp.openCustomerProfile()) {
                CommonUtility.clickElement(orderstab);
                CommonUtility.clickElement(AddNewOrder);
                CommonUtility.clickElement(AddRevenueType);
                List<WebElement> elements=driver.findElements(By.xpath("//*[@id=\"newOrder_revenueType_dropDown\"]/div[2]/div"));
                elements.get(1).click();
                CommonUtility.clickElement(addorder);
                CommonUtility.clickElement(SearchRetailModeTxt);
                CommonUtility.clickElement(ordersettingbillmode);
                CommonUtility.clickElement(SaveOrderSetting);
                WaitUtility.waitTillElementVisible(driver,30,btnedit);
                String arr[]=orderbreadcrum.getText().split(": ");
                Reporter.log("Order created is "+arr[1]);
                System.out.println("Order created is "+arr[1]);

                if(orderbreadcrum.isDisplayed())
                {
                    Assert.assertTrue(orderbreadcrum.getText().contains("Order"));
                    SaveProjectData spd=new SaveProjectData();
                    spd.saveprojectData("Order",arr[1]);
                }
            }
        }

    }


