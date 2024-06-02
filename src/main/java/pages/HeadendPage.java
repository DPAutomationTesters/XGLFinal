package pages;

import base.BaseClass;
import helper.CommonUtility;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeadendPage extends BaseClass
{
    public HeadendPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='header.menuConfiguration']")
    WebElement configuration;

    @FindBy(xpath = "//div[@id='header.subMenuScheduleSettings.Dayparts']")
    WebElement subMenuScheduleSettings;

    @FindBy(xpath = "//div[@id='ScheduleSettings.Headends']")
    WebElement Headends;

    @FindBy(xpath = "//div[@class='ng-isolate-scope searchSelect ng-not-empty ng-valid']//i[@class='fa fa-angle-down']")
    WebElement DropdownSearch_Headend;

    @FindBy(xpath = "//div[contains(text(),'ID')]")
    WebElement ID_Search;

    @FindBy (xpath = "//*[contains(concat( \" \", @class, \" \" ), concat( \" \", \"ng-touched\", \" \" ))]")
    WebElement Search_box;

    @FindBy (xpath = "//b[@class='loupe']")
    WebElement search_button;

    @FindBy(xpath = "//h1[normalize-space()='No Items to Display']") //No Items to Display
    WebElement NoDataMessage;

    @FindBy(xpath = "//button[@id='headendGrid.add']")// + button
    WebElement add_headend;

    @FindBy(xpath = "//input[@id='headend.headend']")// headend id
    WebElement Headend_id;

    @FindBy(xpath = "//input[@id='headend.description']")//headend description
    WebElement Headend_desc;

    @FindBy(xpath = "//div[@class='ng-isolate-scope defaultCheckboxInput']")//Active checkbox checked
    WebElement Active_checkbox;

    @FindBy(xpath = "//i[@class='fa fa-angle-down']")
    WebElement Equipment_Model;
    @FindBy(xpath = "//div[normalize-space()='Sky Connect']")
    WebElement EquipmentModel_Select;
    @FindBy(xpath = "//div[@id='headend.msoId']")
    WebElement MSO_ID;

    @FindBy(xpath = "//span[@title='01 | MVPD General MSO']")
    WebElement MSO_ID_Select;

    @FindBy(xpath = "//div[@id='headends.companyEntity']")
    WebElement CompanyEntity;

    @FindBy(xpath = "//span[@title='11 | Test Entity']")
    WebElement CompanyEntity_Select;

    @FindBy(xpath = "//input[@id='headend.locationGlId']")
    WebElement locationGlId;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement headend_save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement headend_label;

    public boolean search_Headend(String Headend)
    {
        boolean flag=true;
        CommonUtility.clickElement(configuration);
        CommonUtility.clickElement(subMenuScheduleSettings);
        CommonUtility.clickElement(Headends);
        CommonUtility.clickElement(DropdownSearch_Headend);
        CommonUtility.clickElement(ID_Search);
        Search_box.clear();
        CommonUtility.clickElement(Search_box);
        CommonUtility.sendKeys(Search_box,Headend);
        CommonUtility.clickElement(search_button);
        try
        {
            NoDataMessage.isDisplayed();
        }
        catch(NoSuchElementException e)
        {
            flag=false;
        }
        return flag;
    }

    public boolean create_Headend(String Headend)
    {


        String desc=Headend+"Automation Headend";
        CommonUtility.clickElement(configuration);
        CommonUtility.clickElement(subMenuScheduleSettings);
        CommonUtility.clickElement(Headends);
        CommonUtility.clickElement(add_headend);
        CommonUtility.sendKeys(Headend_id, Headend);
        CommonUtility.sendKeys(Headend_desc,desc);
        CommonUtility.clickElement(Active_checkbox);
        CommonUtility.clickElement(Equipment_Model);
        CommonUtility.clickElement(EquipmentModel_Select);
        CommonUtility.clickElement(MSO_ID);
        CommonUtility.clickElement(MSO_ID_Select);
        CommonUtility.clickElement(CompanyEntity);
        CommonUtility.clickElement(CompanyEntity_Select);
        CommonUtility.sendKeys(locationGlId, "GL01");
        CommonUtility.clickElement(headend_save);
        Boolean result=headend_label.getText().contains("Headend");
        return result;
    }

}