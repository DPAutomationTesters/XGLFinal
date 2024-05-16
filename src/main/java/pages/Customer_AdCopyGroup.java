package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.ExceptionHandling;
import helper.Utility;
import helper.WaitUtility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Customer_AdCopyGroup extends BaseClass {

    @FindBy(xpath = "//p[contains(text(),'Ad Copy Groups')]")
    WebElement adcopyGrouptab;

    @FindBy(xpath = "//button[@id='rotationGroupsGrid.add']")
    WebElement addAdcopyGroup;

    @FindBy(xpath = "//input[@id='rotationGroupsEntity.title']")
    WebElement GroupDescription;

    @FindBy(xpath = "//*[@id='rotationGroupsEntity.length']")
    WebElement Grouplength;

    @FindBy(xpath ="//i[@class='add']")
    WebElement AddCopyItem;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement Save;

    public void createaddcopyGroup() {
        CustomerSearchPage csp = new CustomerSearchPage(driver);
        if (csp.openCustomerProfile()) {
            CommonUtility.clickElement(adcopyGrouptab);
            WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
            Utility ut = new Utility();
            String init = ut.randomAlphaNumeric(2);
            GroupDescription.sendKeys(init);
            CommonUtility.clickElement(Grouplength);
            String strLen=String.valueOf(30);
            char[] digit=strLen.toCharArray();
            try {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                CommonUtility.sendKeys(Grouplength, String.valueOf(digit[1]));
                CommonUtility.sendKeys(Grouplength,String.valueOf(digit[0]));
            }
            catch (AWTException e)
            {
                ExceptionHandling.handleException(e);
            }
            CommonUtility.clickElement(AddCopyItem);

        }

        }
    }

