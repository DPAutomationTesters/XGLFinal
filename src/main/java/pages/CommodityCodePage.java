package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.SaveProjectData;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

import static helper.WaitUtility.waittillElementInteractable;

public class CommodityCodePage extends BaseClass
{
    @FindBy(id="header.menuConfiguration")
    WebElement config;

    @FindBy(xpath="//div[contains(@id,'subMenuOrderSettings')]")
    WebElement orderSettings;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    WebElement breadcrumvalue; //Order Settings

    @FindBy(xpath = "//div[@id='OrderSettings.Commodities']")
    WebElement commoditycode;

    @FindBy(xpath = "//button[contains(@id,'commodityGrid.add')]")
    WebElement addcommoditycode;

    @FindBy(xpath = "//input[@id='commodityProfile_code']")
    WebElement commoditycodeid;

    @FindBy(xpath = "//input[@id='commodityProfile_description']")
    WebElement commoditycodedesc;

    @FindBy(xpath = "//button[@id='secondaryCommodity.add']")
    WebElement addseccommodity;

    @FindBy(xpath = "//div[@class='modal-content']")
    WebElement seccommditypopup;

    @FindBy(xpath = "//input[@id='editedSecondaryCommodity_code']")
    WebElement Seccode;

    @FindBy(xpath = "//input[@id='editedSecondaryCommodity_description']")
    WebElement Secdesc;

    @FindBy(xpath = "//div[@id='editedSecondaryCommodity_overrideSeparation']")
    WebElement separationchckbx;

    @FindBy(xpath = "//button[@id='cancelSaveOk.OK']")
    WebElement ok;

    @FindBy(xpath = "//div[contains(@class,'slick-row')]")
    WebElement seccommditydataline;

    @FindBy(xpath = "//div[last()]/div[contains(@id,'breadCrumbs')]")
    WebElement cobreadcrum;

    @FindBy(xpath = "//button[contains(@id,'save')]")
    WebElement coSave;

    public CommodityCodePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Addcommoditycode()
    {
        CommonUtility.clickElement(config);
        CommonUtility.clickElement(orderSettings);
        SoftAssert sa=new SoftAssert();
        sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Order Settings"));
        CommonUtility.clickElement(commoditycode);
        CommonUtility.clickElement(addcommoditycode);
        Utility ut= new Utility();
        String init=ut.randomAlphaNumeric(2);
        CommonUtility.sendKeys(commoditycodeid,init);
        String sp_name=init+"commodity code";
        CommonUtility.sendKeys(commoditycodedesc,sp_name);
        CommonUtility.clickElement(addseccommodity);
        waittillElementInteractable(driver,20,"//div[@class='modal-content']");
        new Actions(driver).moveToElement(Seccode).perform();
         init=ut.randomAlphaNumeric(2);
        CommonUtility.sendKeys(Seccode,init);
        sp_name=init+"sec code";
        CommonUtility.sendKeys(Secdesc,sp_name);
        CommonUtility.clickElement(separationchckbx);
        CommonUtility.clickElement(ok);
        if(coSave.isDisplayed())
        {
            CommonUtility.clickElement(coSave);
        }
        Assert.assertTrue(cobreadcrum.getText().contains("New Commodity"));
        SaveProjectData sp=new SaveProjectData();
        sp.saveprojectData("Commodity code",init);
    }
}
