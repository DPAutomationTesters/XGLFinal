package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.SaveProjectData;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRolePage extends BaseClass
{
    public UserRolePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@id='header.menuConfiguration']")
    WebElement configuration;

    @FindBy(xpath = "//div[@id='header.subMenuUserSettings.UserRoles']")
    WebElement subMenuUserSettings;

    @FindBy(xpath = "//div[@id='UserSettings.UserRoles']")
    WebElement userrolestab;

    @FindBy(xpath = "//div[@id='UserSettings.UserSecurity']")
    WebElement usersecuritytab;

    @FindBy(xpath = "//i[@class='fa fa-angle-down']")
    WebElement search_role;

    @FindBy(xpath = "//div[@class='dropDown']/div[1]")
    WebElement code_search;

    @FindBy(xpath = "//*[contains(@class, 'ng-pristine') and @ng-model='tempModel.value']")
    //ng-valid ng-dirty ng-valid-max-length-vldr ng-touched ng-empty ng-valid-parse
    WebElement search_box;

    @FindBy(xpath = "//b[@class='loupe']")
    WebElement search_button;

    @FindBy(xpath = "//div[@class='textContent']/h1") //No Items to Display
    WebElement NoDataMessage;

    @FindBy(xpath = "//button[@id='userRolesGridDrtv.add']")
    WebElement add_roles;

    @FindBy(xpath = "//input[@name='userRoleCode']")
    WebElement user_role_code;

    @FindBy(xpath = "//input[@name='description']")
    WebElement user_role_desc;

    @FindBy(xpath = "//div[@id='userRolesGridDrtv.selectFilter']//i[@class='fa fa-angle-down']")
    WebElement userrolegrid_filter;

    @FindBy(xpath = "//div[@class='dropDown']/div[contains(text(),\"Ungranted\")]")
    WebElement ungranter_dropdown;

    @FindBy(xpath = "//div[contains(@class,\"selectAll\")]//div[@class='checkBoxSelection']/i")
    WebElement Select_all_chckbox;

    @FindBy(xpath = "//button[@id='userRolesGridDrtv.Grant']")
    WebElement userPermission_grant;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement user_role_save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement user_label;

    public boolean search_role(String role)
    {
        boolean flag=true;
        CommonUtility.clickElement(configuration);
        CommonUtility.clickElement(subMenuUserSettings);
        CommonUtility.clickElement(userrolestab);
        CommonUtility.clickElement(search_role);
        CommonUtility.clickElement(code_search);
        search_box.clear();
        CommonUtility.clickElement(search_box);
        CommonUtility.sendKeys(search_box,role);
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

    public boolean create_user_role(String rolenew)
    {
        String desc = rolenew + "Automation network";
        CommonUtility.clickElement(add_roles);
        CommonUtility.sendKeys(user_role_code,rolenew);
        CommonUtility.sendKeys(user_role_desc,desc);
        //Grant permission
        CommonUtility.clickElement(userrolegrid_filter);
        CommonUtility.clickElement(ungranter_dropdown);
        CommonUtility.clickElement(Select_all_chckbox);
        CommonUtility.clickElement(userPermission_grant);
        //save
        CommonUtility.clickElement(user_role_save);
        Boolean result=user_label.getText().contains("User Role ");
        if(result=true)
        {
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("User role",rolenew);
        }
        return result;
    }
}
