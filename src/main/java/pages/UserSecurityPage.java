package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import java.util.List;

public class UserSecurityPage extends BaseClass
{
    public UserSecurityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   @FindBy(xpath = "//button[@id='userSecurityGrid.add']")
    WebElement add_user;

    @FindBy(xpath="//input[@name='fullName']")
    WebElement user_name;

    @FindBy(xpath = "//input[@name='login']")
    WebElement login_id;

    @FindBy(xpath = "//i[@id='userProfilePage_ntAuthenticatePassword_validation']")
    WebElement user_password;

    @FindBy(xpath = "//div[@id='userEntityProfile.companyEntityList']")
    WebElement user_company_entity;

    @FindBy(xpath = "//div[@id='userEntityProfile.companyEntityList']//div[contains(@class,'auto-complete-list-drop-down')]/div")
    List<WebElement> user_ce_options;

    boolean select_CE()
    {
        boolean flag=false;
        user_company_entity.click();
        if(user_ce_options.size()>1) {
            for (WebElement e : user_ce_options) {
                e.click(); flag=true;
                Reporter.log("User created with comapny entity "+e.getText());break;
            }
        }
        return flag;
    }
}
