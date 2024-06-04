package pages;

import base.BaseClass;
import helper.CommonUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Orderlines_LineBilling extends BaseClass {

    @FindBy(xpath = "//p[contains(text(),'Order Lines')]")
    WebElement ClickOrderlineTab;
    @FindBy(xpath = "//*[@class='add']")
    WebElement AddOrderline;

    @FindBy(xpath = "//div[contains(text(),'Entire Range')]")
    WebElement QuantityStrategy;

    @FindBy(xpath = "//*[@class='defaultTextInput short ng-pristine ng-valid ng-not-empty ng-valid-required ng-touched']\n")
    WebElement Quantity;

    @FindBy(xpath = "//*[@class='fa fa-angle-up']")
    WebElement AdCopyGrouptype;

    @FindBy(xpath = "//input[@class='defaultTextInput ng-pristine ng-empty ng-invalid ng-invalid-required ng-touched']")
    WebElement AdCopyGroupSelect;

    @FindBy(xpath = "//div[contains(text(),'Line Billing')]")
    WebElement LineBilling;

    @FindBy(xpath = "//div[@class='defaultRadioInputs ng-binding ng-scope selected' and text()='Retail']")
    WebElement BillingModeRetail;

    @FindBy(xpath = "//div[@class='defaultRadioInputs ng-binding ng-scope selected' and text()='Network']")
    WebElement Networkbutton;

    @FindBy(xpath = "//tbody/tr[1]/td[2]/div[2]/div[3]/div[1]/div[2]/i[1]")
    WebElement NetworkDropdown;
    @FindBy(xpath = "//div[contains(text(),'AEN')]")
    WebElement NetworkSelect;

    @FindBy(xpath = "//div[@name='regionRetail']//div[@class='iconContainer']")
    WebElement RetailRegionDropdown;

    @FindBy(xpath = "//input[@id='locationGridRns_totalRate_textbox']")
    WebElement TotalRate;

    @FindBy(xpath = "//div[@id='scheduling_inventoryType_dropdown']//div[@class='iconContainer']")
    WebElement InventoryDropdown;

    @FindBy(xpath = "//button[@id='orderline.cancelSave.save']")
    WebElement SaveOL;

    @FindBy(xpath = "//button[contains(text(),'Approve')]")
    WebElement ApproveOL;

    @FindBy(xpath = "//div[contains(text(),'Order Line 1: Approved')]")
    WebElement ApprovedStatus;

    @FindBy(xpath = "//div/div/p[text()='Orders']")
    WebElement OrderTab;

    @FindBy(xpath = "//input[@ng-model='tempModel.value']")
    WebElement OrderSearchInput;

    @FindBy(xpath = "//div[@class='textContent']/h1") //No Items to Display
    WebElement NoDataMessage;

    public Orderlines_LineBilling(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public boolean selectAdCopyGroupType(String str) {
        Boolean flag = false;
        List<WebElement> ele = driver.findElements(By.xpath("//body/tr[1]/td[2]/div[1]/div[3]/div[1]/div[4]"));
        for (WebElement e : ele) {
            e.click();
            flag = true;
            break;
        }

        return flag;
    }

    public boolean AdCopySelected() {
        Boolean flag = false;
        List<WebElement> ele = driver.findElements(By.xpath("//span[contains(text(),'R00')]"));
        for (WebElement e : ele) {
            e.click();
            flag = true;
            break;
        }
        return flag;
    }

    public boolean RetailRegionselection() {
        Boolean flag = false;
        List<WebElement> ele = driver.findElements(By.xpath("//div[@name='regionRetail']//div[@class='dropDown']//div[@class='ng-binding ng-scope'][1]"));
        for (WebElement e : ele) {
            e.click();
            flag = true;
            break;
        }
        return flag;
    }


    public boolean InventorySelect() {
        Boolean flag = false;
        List<WebElement> ele = driver.findElements(By.xpath("//div[@id='scheduling_inventoryType_dropdown']//div[contains(@class, 'dropDown')]//div[contains(@class, 'ng-binding ng-scope') and contains(text(), 'DEFAULT')]"));
        for (WebElement e : ele) {
            e.click();
            flag = true;
            break;
        }
        return flag;
    }

    public void LineBillingOL() {
        OrderSearchPage csp = new OrderSearchPage(driver);
        if (csp.openOrderProfile())
        {
                CommonUtility.clickElement(ClickOrderlineTab);
                CommonUtility.clickElement(AddOrderline);
                CommonUtility.clickElement(QuantityStrategy);
                CommonUtility.clickElement(Quantity);
                CommonUtility.sendKeys(Quantity, "5");
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                CommonUtility.clickElement(AdCopyGrouptype);
                SoftAssert sa = new SoftAssert();
                sa.assertTrue(selectAdCopyGroupType("Standard"));
                CommonUtility.clickElement(AdCopyGroupSelect);
                sa.assertTrue(AdCopySelected());
                CommonUtility.clickElement(LineBilling);
                CommonUtility.clickElement(BillingModeRetail);
                CommonUtility.clickElement(Networkbutton);
                CommonUtility.clickElement(NetworkDropdown);
                CommonUtility.clickElement(NetworkSelect);
                CommonUtility.clickElement(RetailRegionDropdown);
                sa.assertTrue(RetailRegionselection());
                CommonUtility.clickElement(TotalRate);
                CommonUtility.sendKeys(TotalRate, "100");
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                CommonUtility.clickElement(InventoryDropdown);
                sa.assertTrue(InventorySelect());
                CommonUtility.clickElement(SaveOL);
                CommonUtility.clickElement(ApproveOL);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            }
    }
}





