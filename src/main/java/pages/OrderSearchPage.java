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

import java.awt.*;
import java.awt.event.KeyEvent;

public class OrderSearchPage extends BaseClass
{
    public
    OrderSearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="header.menuOrders")
    WebElement menuOrders;

    @FindBy(xpath = "//div[@id='header.subMenuOrders']")
    WebElement OrdersTab;

    @FindBy(xpath = "//div[@class=\"advancedFilters advanced-filters-container ng-isolate-scope\" and @id=\"orderGrid.advanced\"]//i[@ng-click=\"expandButtonPressed(); advancedFiltersClick($event)\"]")
    WebElement AdvFiltersClick;

    @FindBy(xpath = "//input[@ng-model='tempModel.value']")
    WebElement EnterOrderNumber;

    @FindBy(xpath = "//button[contains(text(),'Search')]")
    WebElement SearchButton;

    @FindBy(xpath = "//div[contains(@class,'slick-cell l0 r0 actionHighlight')]")
    WebElement ClickOrder;


    @FindBy(xpath = "//div[@class='textContent']/h1") //No Items to Display
    WebElement NoDataMessage;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement breadcrumOrder;

    @FindBy (xpath = "//div[@ng-repeat=\"item in buttonsArr\" and @class=\"defaultRadioInputs ng-binding ng-scope selected\" and @ng-click=\"item.disabled || click()\"]\n")
    WebElement ALL;


    public boolean Search_Order(String Order)
    {
        boolean flag=false;
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(OrdersTab);
        Utility.waitforPagetoLoad(driver,10);
        CommonUtility.clickElement(ALL);
        CommonUtility.clickElement(AdvFiltersClick);
        WaitUtility.waitTillElementtobeClickable(driver, 30, EnterOrderNumber);
        CommonUtility.clickElement(EnterOrderNumber);
        SaveProjectData spd = new SaveProjectData();
        String OrderNumber=spd.getprojectData("Order");
        System.out.println("Order number retrieved from Excel: " + OrderNumber);
        WaitUtility.waitTillElementtobeClickable(driver, 30, EnterOrderNumber);
        EnterOrderNumber.clear();
        CommonUtility.sendKeys(EnterOrderNumber,OrderNumber);
        WaitUtility.waitTillElementtobeClickable(driver, 30, EnterOrderNumber);
        try {
            Robot robot = new Robot();
            //robot.mouseMove(search_button.getLocation().getX(), search_button.getLocation().getY()); // Move mouse to the dropdown
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

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
            flag=true;
        }
        String str= "//div[contains(text(),'"+OrderNumber+"')]";
        Assert.assertTrue(driver.findElement(By.xpath(str)).isDisplayed());
        return flag;
    }

    public boolean
    openOrderProfile()
    {
        Boolean flag=false;
        SaveProjectData spd=new SaveProjectData();
        String OrderNumber=spd.getprojectData("order");
        if(Search_Order(OrderNumber))
        {
            Reporter.log("No Such Order present");
        }
        else
        {
            CommonUtility.clickElement(ClickOrder);
            if(breadcrumOrder.getText().contains(OrderNumber))
            {
                flag=true;
            }
        }
        return flag;
    }
}
