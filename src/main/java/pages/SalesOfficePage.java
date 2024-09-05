package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.SaveProjectData;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SalesOfficePage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuOrderSettings')]")
    WebElement orderSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Order Settings

    @FindBy(xpath = "//div[@id='OrderSettings.SalesOffices']")
    WebElement salesoffice;

    @FindBy(xpath = "//button[contains(@id,'salesOfficeGrid.add')]")
    WebElement addsalesoffice;

    @FindBy(xpath = "//input[@id='salesOfficeProfile_salesOfficeId']")
    WebElement salesofficeid;

    @FindBy(xpath = "//input[@id='salesOfficeProfile_salesOfficeName']")
    WebElement salesofficename;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement sosave;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement spbreadcrum;

    public SalesOfficePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void CreateSalesOffice()
    {
        CommonUtility.clickElement(config);
        CommonUtility.clickElement(orderSettings);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Order Settings"));
        CommonUtility.clickElement(salesoffice);
        CommonUtility.clickElement(addsalesoffice);
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.sendKeys(salesofficeid,init);
        String so_name=init+"salesperson";
        CommonUtility.sendKeys(salesofficename,so_name);
        CommonUtility.clickElement(sosave);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        if(spbreadcrum.isDisplayed())
        {
            Assert.assertTrue(spbreadcrum.getText().contains("Sales Office"));
            SaveProjectData spd=new SaveProjectData();
            spd.saveprojectData("Sales Office",init);
        }

    }

}
