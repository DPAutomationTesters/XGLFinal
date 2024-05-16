package pages;

import base.BaseClass;
import helper.CommonUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Customer_Order extends BaseClass {

    @FindBy(xpath = "//button[@id='customerOrdersGrid.add']")
    WebElement AddNewOrder;

    @FindBy(xpath = "//body/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[4]/div[1]/input[1]")
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

    public Customer_Order(WebDriver driver) {
        BaseClass.driver = driver;
    }

      /* @FindBy(xpath = "//p[contains(text(),'Order Lines')]")
       WebElement ClickOnOrderlineTab;

       @FindBy(xpath = "//*[@id=\"orderLinesGrid.add\"]/i[@class=\"add\"]")
       WebElement AddNewOrderLine;*/


     public void Customer_OrderAdd()
        {

            CommonUtility.clickElement(AddNewOrder);
            CommonUtility.clickElement(AddRevenueType);
            CommonUtility.clickElement(SelectRevenuetype);
            CommonUtility.clickElement(addorder);
            CommonUtility.clickElement(SearchRetailModeTxt);
            CommonUtility.clickElement(SaveOrderSetting);

        }

    }


