package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.SaveProjectData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Customer_Order extends BaseClass {

    @FindBy(xpath = "//div/div/p[text()='Orders']")
    WebElement OrderTab;

    @FindBy(xpath = "//button[@id='customerOrdersGrid.add']")
    WebElement AddNewOrder;

    @FindBy(xpath = "//body/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[3]/div[4]/div[1]/input[1]")
    WebElement AddRevenueType;

    @FindBy(xpath = "//span[contains(text(),'| AGENCY')]")
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
    WebElement Orderbreadcrum;



      /* @FindBy(xpath = "//p[contains(text(),'Order Lines')]")
       WebElement ClickOnOrderlineTab;

       @FindBy(xpath = "//*[@id=\"orderLinesGrid.add\"]/i[@class=\"add\"]")
       WebElement AddNewOrderLine;*/


    public Customer_Order(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
        public void Customer_OrderAdd ()
        {
            CustomerSearchPage csp = new CustomerSearchPage(driver);
            if (csp.openCustomerProfile())
            {
//                WaitUtility.waitTillElementtobeClickable(driver, 30, OrderTab);
//                System.out.println("height is" + OrderTab.getSize().getHeight());
//                System.out.println("Width is" + OrderTab.getSize().getWidth());
//                JavaScriptExecutor JE = new JavaScriptExecutor();
//                JE.clickElementByJS(OrderTab);
                CommonUtility.clickElement(OrderTab);
                CommonUtility.clickElement(AddNewOrder);
                CommonUtility.clickElement(AddRevenueType);
                CommonUtility.clickElement(SelectRevenuetype);
                CommonUtility.clickElement(addorder);
                CommonUtility.clickElement(SearchRetailModeTxt);
                CommonUtility.clickElement(SaveOrderSetting);
                if(Orderbreadcrum.isDisplayed())
                {
                    WebElement Breadcrumb = driver.findElement(By.xpath("//div[@id='breadCrumbs.crumb2']"));
                    Assert.assertTrue(Breadcrumb.getText().contains("Order"));
                    String init = String.valueOf(Breadcrumb.getText().contains("Order"));
                    System.out.println(Breadcrumb.getText());
                    String[] arr = Breadcrumb.getText().split(":");
                    int len= arr.length;
                    System.out.println(arr[len-1]);;
                    SaveProjectData spd=new SaveProjectData();
                    spd.saveprojectData("Order", String.valueOf(arr[len-1]));

                }
            }
        }
   }
