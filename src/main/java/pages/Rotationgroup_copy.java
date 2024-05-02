package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Rotationgroup_copy extends BaseClass
{

    public Rotationgroup_copy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@id='header.menuOrders']")
    WebElement orders_menu;

    @FindBy(xpath = "//div[contains(@id,'subMenuRotationGroups')]")
    WebElement rotation_groups;

    @FindBy(xpath = "//div[@class='dropDown']/div[2]")
    WebElement Cust_id_search;

    @FindBy(xpath = "//div[@id='rotationGroupsGrid.selectSearch']//i[@class='fa fa-angle-down'][1]")
    WebElement search_rotation_dropdown;

    @FindBy(xpath = "//div[@id='rotationGroupsGrid.selectSearch']//input[@ng-model='tempModel.value']")
    WebElement search_editbox;

    @FindBy(xpath = "//b[@class='loupe']")
    WebElement search_button;

    @FindBy(xpath = "//div[contains(@class,'ui-widget-content')][1]")
    WebElement first_row;

    @FindBy(xpath = "//div[contains(@class,'ui-widget-content')][1]//div[@class='actionMenuButton']/i")
    WebElement action;

    @FindBy(xpath = "//div[@class='subMenuAction'][2]")
    WebElement copy;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb0']")
    WebElement back;


    public void copy_group()
    {
        CommonUtility.clickElement(orders_menu);
        CommonUtility.clickElement(rotation_groups);
        for(int i=5;i<=980;i++) {
            CommonUtility.clickElement(action);
            CommonUtility.clickElement(copy);
            WaitUtility.waitStatic(5);
            CommonUtility.clickElement(save);
            WaitUtility.waitStatic(5);
            CommonUtility.clickElement(back);
        }
    }
}
