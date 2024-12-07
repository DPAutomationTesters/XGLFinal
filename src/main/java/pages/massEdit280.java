package pages;
import base.BaseClass;
import helper.CommonUtility;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class massEdit280 extends BaseClass
{
    @FindBy(id="header.menuOrders")
    public
    WebElement menuOrders;

    @FindBy(id="header.subMenuCustomers")
    public
    WebElement menuCustomers;

    @FindBy(id="customersGrid.add")
    public
    WebElement ClickOnPlusButton;

    @FindBy(id="customersProfile.company")
    public
    WebElement enterCustomerName;

    @FindBy(xpath="(//input[@class='defaultTextInput ng-pristine ng-untouched ng-valid ng-empty ng-valid-required'])[10]")
    public
    WebElement commodityDropdown;

    @FindBy(xpath="//span[contains(text(),'ALC_BEER')]")
    public
    WebElement selectCommodity;

    @FindBy(xpath = "//div[@id='customersProfile.salespersonVid']//input[@class='defaultTextInput ng-pristine ng-untouched ng-valid ng-empty ng-valid-required']")
    public
    WebElement salespersonDropdown;

    @FindBy(xpath = "//span[@title='GAB | Grace A. Bowen']")
    public
    WebElement selectSalesperson;

    @FindBy(id="customer.cancelSave.save")
    public
    WebElement saveNewCustomer;

    @FindBy(id="CustomerEntity.AdCopy")
    public
    WebElement ClickOnAdCopyTab;

    @FindBy(id="customerAdCopyGrid.add")
    public
    WebElement ClickOnAdCopyTab1;

    @FindBy(id="adCopyProfile.title")
    public
    WebElement clickInTitleField;

    @FindBy(id="cancelSave.save")
    public
    WebElement clickOnSave;

    @FindBy(id="breadCrumbs.crumb1")
    public
    WebElement DownArrowForBreadCrumb;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement customerBreadcrumb;// New Customer

    @FindBy(xpath = "//p[contains(text(),'Ad Copy Groups')]")
    WebElement adCopyGroupTab;

    @FindBy(id="rotationGroupsGrid.add")
    public
    WebElement plusAdCopyGroup;

    @FindBy(id="rotationGroupsEntity.title")
    public
    WebElement adCopyGroupTitle;

    @FindBy(id="cancelSave.save")
    public
    WebElement clickOnSaveButton;

    @FindBy(id="breadCrumbs.crumb1")
    public
    WebElement clickOnBreadcrumb;

    @FindBy(xpath = "//p[contains(text(),'Orders')]")
    WebElement ordersTab;

    @FindBy(id="customerOrdersGrid.add")
    public
    WebElement clickOnPlusOrder;

    @FindBy(id="newOrder_revenueType_dropDown")
    public
    WebElement ClickInRevenueTypeDropdown;

    @FindBy(xpath = "//div[@id='newOrder_revenueType_dropDown']/div[last()]")
    WebElement selectRevenueTypeFromDropdown;

    @FindBy(id="newOrder.create")
    public
    WebElement clickOnNewOrderCreate;

    @FindBy(id="CustomerOrderEntity.Orderlines")
    public
    WebElement orderLinesTab;

    @FindBy(id="orderLinesGrid.add")
    public
    WebElement clickOnPlusOrderLine;

    @FindBy(xpath = "//div[normalize-space()='Per Week']")
    public
    WebElement clickOnPerWeek;

    @FindBy(id="orderlineProfile.quantity_spotsWeek")
    public
    WebElement clickOnQuantity;

    @FindBy(xpath = "(//input[@class='defaultTextInput ng-pristine ng-untouched ng-valid ng-empty ng-valid-required'])[1]")
    public
    WebElement clickOnNetworkDropdown;

    @FindBy(xpath = "//span[@title='AEN | * Arts & Entertainment']")
    public
    WebElement selectNetwork;

    @FindBy(xpath = "(//input[@class='defaultTextInput ng-pristine ng-untouched ng-valid ng-empty ng-valid-required'])[1]")
    public
    WebElement  clickOnRetailDropdown;

    @FindBy(xpath = "//span[contains(text(),'BXLY | Bexley')]")
    public
    WebElement selectRetail;

    @FindBy(xpath = "//button[@id='adCopyGroup_missing']")
    public
    WebElement selectAdCopyGroup;

    @FindBy(xpath = "//button[@value='ok']")
    public
    WebElement clickOnYes;

    @FindBy(xpath = "//button[@id='orderline.cancelSave.save']")
    public
    WebElement clickOnSaveOrderLine;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb2']")
    public
    WebElement Breadcrumb;

    @FindBy(xpath = "//div[@class='slick-cell l0 r0']//i[@class='fa fa-square']")
    public
    WebElement CopyOrderLine;

    @FindBy(xpath = "//i[@class='copy']")
    public
    WebElement CopyIcon;

    @FindBy(xpath = "//button[@id='orderline.cancelSave.save']")
    public
    WebElement SaveOL;

    @FindBy(xpath = "//span[@class='slick-column-name']//i[@class='fa fa-square']")
    public
    WebElement selectAll;

    @FindBy(xpath = "//i[@ng-click='d.actionButtons.massedit.action()']")
    public
    WebElement modifySelected;

    @FindBy(xpath = "//i[@ng-click=\"d.actionButtons.edit('lineBilling')\"]")
    public
    WebElement clickToEdit;

    @FindBy(xpath = "//td[@class=\"bottomPadding\"]//div[@id=\"lineBilling\"]")
    public
    WebElement selectLineBilling;

    @FindBy(xpath = "//button[@id=\"cancelSaveOk.Modify\"]")
    public
    WebElement clickOnModifyButton;

    @FindBy(xpath = "(//button[normalize-space()='Yes'])[1]")
    public
    WebElement clickOnYess;

    @FindBy(xpath = "(//button[normalize-space()='Ok'])[1]")
    public
    WebElement success;

    public massEdit280(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void orderOrderlines()
    {
        Utility.waitforPagetoLoad(driver,40);
        CommonUtility.clickElement(menuOrders);
        CommonUtility.clickElement(menuCustomers);
        CommonUtility.clickElement(ClickOnPlusButton);
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.clickElement(enterCustomerName);
        CommonUtility.sendKeys(enterCustomerName,init);
        CommonUtility.clickElement(commodityDropdown);
        CommonUtility.clickElement(selectCommodity);
        CommonUtility.clickElement(salespersonDropdown);
        CommonUtility.clickElement(selectSalesperson);
        CommonUtility.clickElement(saveNewCustomer);
        CommonUtility.clickElement(ClickOnAdCopyTab);
        CommonUtility.clickElement(ClickOnAdCopyTab1);
        CommonUtility.clickElement(clickInTitleField);
        String adCopyTitle=ut.randomAlphaNumeric(2);
        CommonUtility.sendKeys(clickInTitleField,adCopyTitle);
        CommonUtility.clickElement(clickOnSave);
        CommonUtility.clickElement(DownArrowForBreadCrumb);
        CommonUtility.clickElement(customerBreadcrumb);
        CommonUtility.clickElement(adCopyGroupTab);
        CommonUtility.clickElement(plusAdCopyGroup);
        CommonUtility.clickElement(adCopyGroupTitle);
        CommonUtility.sendKeys(adCopyGroupTitle,init);
        CommonUtility.clickElement(clickOnSaveButton);
        CommonUtility.clickElement(clickOnBreadcrumb);
        CommonUtility.clickElement(ordersTab);
        CommonUtility.clickElement(clickOnPlusOrder);
        CommonUtility.clickElement(ClickInRevenueTypeDropdown);
        CommonUtility.clickElement(selectRevenueTypeFromDropdown);
        CommonUtility.clickElement(clickOnNewOrderCreate);
        CommonUtility.clickElement(orderLinesTab);
        CommonUtility.clickElement(clickOnPlusOrderLine);
        CommonUtility.clickElement(clickOnPerWeek);
        CommonUtility.clickElement(clickOnQuantity);
        clickOnQuantity.sendKeys("20");
        CommonUtility.clickElement(clickOnNetworkDropdown);
        CommonUtility.clickElement(selectNetwork);
        CommonUtility.clickElement(clickOnRetailDropdown);
        CommonUtility.clickElement(selectRetail);
        CommonUtility.clickElement(selectAdCopyGroup);
        CommonUtility.clickElement(clickOnYes);
        CommonUtility.clickElement(clickOnSaveOrderLine);
        CommonUtility.clickElement(Breadcrumb);
        CommonUtility.clickElement(CopyOrderLine);
        CommonUtility.clickElement(CopyIcon);
        CommonUtility.clickElement(SaveOL);
        CommonUtility.clickElement(Breadcrumb);
        CommonUtility.clickElement(selectAll);
        CommonUtility.clickElement(modifySelected);
        CommonUtility.clickElement(clickToEdit);
        CommonUtility.clickElement(selectLineBilling);
        CommonUtility.clickElement(clickOnModifyButton);
        CommonUtility.clickElement(clickOnYess);
        Assert.assertTrue(success.isDisplayed());
    }
}