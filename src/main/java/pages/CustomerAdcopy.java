package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class CustomerAdcopy extends BaseClass
{
    public CustomerAdcopy(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()='Ad Copy']")
    WebElement adcopytab;

    @FindBy(xpath = "//button[@id='customerAdCopyGrid.add']")
    WebElement addAdcopy;

    @FindBy(xpath = "//input[@id='adCopyProfile.id']")
    WebElement AdCopyId;

    @FindBy(xpath = "//input[@id='adCopyProfile.title']")
    WebElement adcopyTitle;

    @FindBy(xpath = "//div[not(contains(@class,'ng-hide')) and contains(@class,'has-validation')]/input[@id='adCopyProfile.length']")
    WebElement length;

    @FindBy(xpath = "//div[@id='adCopyProfile.adCopyEncoded']")
    WebElement encodedchckbox;

    @FindBy(xpath = "//div[@id='adCopyProfile.receivedFlag']")
    WebElement recievedchckbox;

    @FindBy(xpath = "//div[@ng-model='data.dateReceived']/div[contains(@class,'calendarIcon')]")
    WebElement recieveddate;

    @FindBy(xpath = "//div[@ng-model='data.dateReceived']/input")
    WebElement inputRecieveddate;

    @FindBy(xpath = "//*[@id='adCopyProfile.adCopyCommodity']/input")
    WebElement adcopycommodity;

    @FindBy(xpath = "//*[@id='adCopyProfile.adCopyCommodity']/div[2]/div[2]")
    WebElement adcopycommvalue;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement Save;

    @FindBy(xpath = "//div[@id='breadCrumbs.crumb2']")
    WebElement adcopybreadcrum;

    @FindBy(xpath = "//button[@id='cancelSave.edit']")
    WebElement editbtn;

    @FindBy(xpath = "//div[@class='errorSubmitFrom']")
    WebElement errorsubmission;

    public void createaddcopy()
    {
        CustomerSearchPage csp=new CustomerSearchPage(driver);
        if(csp.openCustomerProfile())
        {
            CommonUtility.clickElement(adcopytab);
            WaitUtility.waitTillElementtobeClickable(driver,30,addAdcopy);
            Utility ut= new Utility();
            if(AdCopyId.getAttribute("placeholder").equalsIgnoreCase("Generated ID"))
            {
                AdCopyId.sendKeys(ut.randomAlphaNumeric(3));
            }
            String init=ut.randomAlphaNumeric(2);
            adcopyTitle.sendKeys(init);
            CommonUtility.clickElement(recievedchckbox);
            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = dateObj.format(formatter);
            CommonUtility.sendKeys(inputRecieveddate,date);
            CommonUtility.clickElement(encodedchckbox);
            WaitUtility.waittillElementisNotEmpty(driver,30,adcopycommodity);
            CommonUtility.clickElement(Save);
            try{
                while(errorsubmission.isDisplayed())
                {
                    JavaScriptExecutor js = new JavaScriptExecutor();
                    js.clickElementByJS(Save);
                    if(!errorsubmission.isDisplayed())
                    {
                        break;
                    }
                }
            }
            catch(Exception e)
            {
                ExceptionHandling.handleException(e);
            }
            if(adcopybreadcrum.isDisplayed())
            {
                Assert.assertTrue(editbtn.isDisplayed());
                SaveProjectData spd=new SaveProjectData();
                spd.saveprojectData("Ad copy",init);
            }

        }

    }
}
