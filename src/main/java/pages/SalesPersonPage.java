package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static helper.WaitUtility.waittillElementInteractable;
import static org.openqa.selenium.Keys.PAGE_DOWN;

public class SalesPersonPage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuOrderSettings')]")
    WebElement orderSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Order Settings

    @FindBy(xpath = "//button[contains(@id,'salesPeopleGridDrtv.add')]")
    WebElement addSalesPeople;

    @FindBy(xpath = "//input[@id='salesPeopleProfile_initials']")
    WebElement spinitial;

    @FindBy(xpath = "//input[@id='salesPeopleProfile_name']")
    WebElement spname;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_officeVid']/div[@class='iconContainer']")
    WebElement spsalesofficedropdnbutton;

    @FindBy(xpath = "//div[@id='salesOfficeProfile_companyEntityId']/div[@class='iconContainer']")
    WebElement spcompanyentitybutton;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement spSave;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement spbreadcrum;

    @FindBy(xpath = "//div[@class='dropDown']")
    WebElement dropdown;



    public SalesPersonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectdropdownvalue()
    {
        String str;
        WebElement ele=driver.findElement(By.xpath("//div[@class='dropDown']/div[1]"));
            try {
                str = ele.getText();
                CommonUtility.clickElement(ele);
                Reporter.log("Value selected is " + str);
            }
            catch(Exception et)
            {
                ExceptionHandling.handleException(et);
            }
    }


    public void setAddSalesPeople()
    {
        CommonUtility.clickElement(config);
        CommonUtility.clickElement(orderSettings);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Order Settings"));
        CommonUtility.clickElement(addSalesPeople);
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(3);
        CommonUtility.sendKeys(spinitial,init);
        String sp_name=init+"salesperson";
        CommonUtility.sendKeys(spname,sp_name);
        waittillElementInteractable(driver,20,"//div[@id='salesOfficeProfile_officeVid']/div[@class='iconContainer']");

        new Actions(driver).moveToElement(spsalesofficedropdnbutton).perform();
        CommonUtility.clickElement(spsalesofficedropdnbutton);
        selectdropdownvalue();
        new Actions(driver).moveToElement(spcompanyentitybutton).perform();
        CommonUtility.clickElement(spcompanyentitybutton);
        selectdropdownvalue();
        CommonUtility.clickElement(spSave);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if(spbreadcrum.isDisplayed())
        {
            Assert.assertTrue(spbreadcrum.getText().contains("New Salesperson"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Salesperson",init);
        }
    }
}
