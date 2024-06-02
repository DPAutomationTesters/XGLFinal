package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.ExceptionHandling;
import helper.Utility;
import helper.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Customer_AdCopyGroup extends BaseClass {

    public Customer_AdCopyGroup(WebDriver driver)
    {
        BaseClass.driver =driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[contains(text(),'Ad Copy Groups')]")
    WebElement adcopyGrouptab;

    @FindBy(xpath = "//button[@id='rotationGroupsGrid.add']")
    WebElement addAdcopyGroup;

    @FindBy(xpath = "//input[@id='rotationGroupsEntity.title']")
    WebElement GroupDescription;

    @FindBy(xpath = "//input[@id='rotationGroupsEntity.length']")
    WebElement Grouplength;

    @FindBy(xpath = "//i[@class='add']")
    WebElement AddCopyItem;

    @FindBy(xpath = "//input[@class='defaultTextInput ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched']")
    WebElement SelectAdCopy;

    @FindBy(xpath = "//button[@id='cancelSaveOk.OK']")
    WebElement SaveCopyItem;

    @FindBy(xpath= "//button[@id='cancelSave.save']")
    WebElement SaveGroup;

    @FindBy (xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement breadcrumvalue;

    public void
    createaddcopyGroup() {
        CustomerSearchPage csp = new CustomerSearchPage(driver);
        if (csp.openCustomerProfile())
        {
            CommonUtility.clickElement(adcopyGrouptab);
            WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
            CommonUtility.clickElement(GroupDescription);
            Utility ut = new Utility();
            String init = ut.randomAlphaNumeric(2);
            WaitUtility.waitTillElementtobeClickable(driver, 30, GroupDescription);
            GroupDescription.sendKeys(init);
            CommonUtility.clickElement(Grouplength);
            String strLen = String.valueOf(30);
            char[] digit = strLen.toCharArray();
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                CommonUtility.sendKeys(Grouplength, String.valueOf(digit[0]));
                CommonUtility.sendKeys(Grouplength, String.valueOf(digit[1]));
            } catch (AWTException e) {
                ExceptionHandling.handleException(e);
            }
            CommonUtility.clickElement(AddCopyItem);
            CommonUtility.clickElement(SelectAdCopy);
            Select dropdown = new Select(SelectAdCopy);
            dropdown.selectByIndex(0);
            CommonUtility.clickElement(SaveCopyItem);
            CommonUtility.clickElement(SaveGroup);
            SoftAssert sa=new SoftAssert();
            sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customer"));
            }

        }
    }


