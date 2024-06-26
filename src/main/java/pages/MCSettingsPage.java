package pages;

import ORParcer.RespositoryParser;
import base.BaseClass;
import dataProvider.ConfigReader;
import helper.CommonUtility;
import helper.JavaScriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class MCSettingsPage extends BaseClass
{
    WebDriverWait wait;
    @FindBy(xpath = "//button[@class='btnSmall dark']")
    public WebElement mcsettingbtn;

    @FindBy(xpath = "//div[@class='modal-content'")
    public WebElement mcsettingPopup;

    @FindBy(xpath = "//div[@id='missionLayout.firstQuadrant']/div[2]/i")
    WebElement mcsettingfirstbtn;

    @FindBy(xpath = "//div[@id='missionLayout.secondQuadrant']/div[2]/i")
    WebElement  mcsettingsecondbtn;

    @FindBy(xpath = "//div[@id='missionLayout.thirdQuadrant']/div[2]/i")
    WebElement mcsettingthirdbtn;

    @FindBy(xpath = "//div[@id='missionLayout.fourthQuadrant']/div[2]/i")
    WebElement mcsettingforthbtn;

    @FindBy(xpath = "//div[@id='missionLayout.firstQuadrant']/div[1]")
    WebElement mcsettingfirstvalue;

    @FindBy(xpath = "//div[@id='missionLayout.secondQuadrant']/div[1]")
    WebElement mcsettingsecondvalue;

    @FindBy(xpath = "//div[@id='missionLayout.thirdQuadrant']/div[1]")
    WebElement mcsettingthirdvalue;

    @FindBy(xpath = "//div[@id='missionLayout.fourthQuadrant']/div[1]")
    WebElement mcsettingfourthvalue;

    @FindBy(id="cancelSaveOk.Save")
    WebElement mcsettingsave;

    @FindBy(id="cancelSaveOk.Reset")
    WebElement mcsettingreset;

    @FindBy(xpath = "//div[@class='dropDown']/div")
    WebElement mcdropdownoptions;

    @FindBy(xpath = "//h2[contains(@class,'ng-binding')]")
    WebElement mcheadersQuadrants;

    @FindBy(xpath = "//div[@id='header.missionControl']")
    public
    WebElement missioncontrolHeader;

    public MCSettingsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public MCSettingsPage(WebDriver driver, RespositoryParser parser)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);


       /* mcsettingPopup=driver.findElement(parser.getobjectLocator("mcsettingPopup"));
        mcsettingfirstbtn=driver.findElement(parser.getobjectLocator("mcsettingfirstbtn"));
        mcsettingsecondbtn=driver.findElement(parser.getobjectLocator("mcsettingsecondbtn"));
        mcsettingthirdbtn=driver.findElement(parser.getobjectLocator("mcsettingthirdbtn"));
        mcsettingforthbtn=driver.findElement(parser.getobjectLocator("mcsettingforthbtn"));

        mcsettingfirstvalue=driver.findElement(parser.getobjectLocator("mcsettingfirstvalue"));
        mcsettingsecondvalue=driver.findElement(parser.getobjectLocator("mcsettingsecondvalue"));
        mcsettingthirdvalue=driver.findElement(parser.getobjectLocator("mcsettingthirdvalue"));
        mcsettingfourthvalue=driver.findElement(parser.getobjectLocator("mcsettingfourthvalue"));

        mcsettingsave=driver.findElement(parser.getobjectLocator("mcsettingsave"));
        mcsettingreset=driver.findElement(parser.getobjectLocator("mcsettingreset"));
        mcdropdownoptions=driver.findElement(parser.getobjectLocator("mcdropdownoptions"));*/
    }
    public void openMcSettings()
    {
        JavaScriptExecutor js= new JavaScriptExecutor();
        js.clickElementByJS(mcsettingbtn);
        js.highlightElement(mcsettingbtn, ConfigReader.getPropertyvalue("Style"));
        js.clickElementByJS(mcsettingbtn);
    }
    public void resetSettings()
    {
        CommonUtility.clickElement(mcsettingreset);
    }
    public void saveMcSettings()
    {
        CommonUtility.clickElement(mcsettingsave);
    }
    public void setMcsettingfirstvalue(String widgetValue)
    {
        CommonUtility.clickElement(mcsettingfirstbtn);
           List<WebElement> option=driver.findElements(By.xpath("//div[@class='dropDown']/div"));
            for (WebElement ele :option)
            {
               if(ele.getText().equalsIgnoreCase(widgetValue))
                {
                    CommonUtility.clickElement(ele);
                    break;
                }
            }
        CommonUtility.clickElement(mcsettingsecondbtn);
        List<WebElement> option1=driver.findElements(By.xpath("//div[@class='dropDown']/div"));
        for (WebElement ele :option1)
        {
            if(ele.getText().equalsIgnoreCase("None"))
            {
                CommonUtility.clickElement(ele);
                break;
            }
        }
        CommonUtility.clickElement(mcsettingthirdbtn);
        List<WebElement> option2=driver.findElements(By.xpath("//div[@class='dropDown']/div"));
        for (WebElement ele :option2)
        {
            if(ele.getText().equalsIgnoreCase("None"))
            {
                CommonUtility.clickElement(ele);
                break;
            }
        }
        CommonUtility.clickElement(mcsettingforthbtn);
        List<WebElement> option3=driver.findElements(By.xpath("//div[@class='dropDown']/div"));
        for (WebElement ele :option3)
        {
            if(ele.getText().equalsIgnoreCase("None"))
            {
                CommonUtility.clickElement(ele);
                break;
            }
        }
    }

    public boolean verifymcvalueset(String mcValue)
    {
        Boolean flag=false;
        WebElement ele = null;
        //String str=ConfigReader.getPropertyvalue("missioncontrolvalue");
        if(mcValue.equalsIgnoreCase("Ad Copy Handling")|| mcValue.equalsIgnoreCase("Ad Copy Status")
        || mcValue.equalsIgnoreCase("Customers") || mcValue.equalsIgnoreCase("Network Instance Status")
        || mcValue.equalsIgnoreCase("Users"))
        {
             ele=driver.findElement(By.xpath("//div[contains(@ng-if,'firstQuadrantWidget.value')]/div/h2"));
        } 
        else if (mcValue.equalsIgnoreCase("Data Governance") || mcValue.equalsIgnoreCase("Finance") ||
                mcValue.equalsIgnoreCase("Programming/Allocation Jobs") || mcValue.equalsIgnoreCase("Reconciliation")
                || mcValue.equalsIgnoreCase("Scheduling Information"))
        {
             ele=driver.findElement(By.xpath("//div[contains(@ng-if,'firstQuadrantWidget.value')]/div/div/h2"));
        } else if (mcValue.equalsIgnoreCase("Order Lines Overview"))
        {
            ele=driver.findElement(By.xpath("//div[contains(@ng-if,'firstQuadrantWidget.value')]/div/div/div/div/div/h2"));
        } 

        if(ele.getText().equalsIgnoreCase(mcValue))
        {
                flag=true;
        }
        return flag;

    }
    public boolean verifyforthmcvalueset(String mcValue)
    {
        Boolean flag=false;
        WebElement ele = null;
        String str=ConfigReader.getPropertyvalue("missioncontrolvalue");
        if(str.equalsIgnoreCase("Ad Copy Handling")|| str.equalsIgnoreCase("Ad Copy Status")
                || str.equalsIgnoreCase("Customers") || str.equalsIgnoreCase("Network Instance Status")
                || str.equalsIgnoreCase("Users"))
        {
            ele=driver.findElement(By.xpath("//div[contains(@ng-if,'fourthQuadrantWidget.value')]/div/h2"));
        }
        else if (str.equalsIgnoreCase("Data Governance") || str.equalsIgnoreCase("Finance") ||
                str.equalsIgnoreCase("Programming/Allocation Jobs") || str.equalsIgnoreCase("Reconciliation")
                || str.equalsIgnoreCase("Scheduling Information"))
        {
            ele=driver.findElement(By.xpath("//div[contains(@ng-if,'fourthQuadrantWidget.value')]/div/div/h2"));
        } else if (str.equalsIgnoreCase("Order Lines Overview"))
        {
            ele=driver.findElement(By.xpath("//div[contains(@ng-if,'fourthQuadrantWidget.value')]/div/div/div/div/div/h2"));
        }

        if(ele.getText().equalsIgnoreCase(mcValue))
        {
            flag=true;
        }
        return flag;

    }

    public void addwait(WebElement ele)
    {
        Duration timeout = Duration.ofSeconds(50);
        // Convert the Duration to milliseconds
        long timeoutMilliseconds = timeout.toMillis();
        wait= new WebDriverWait(driver,timeout);
        wait.until(WebDriver->ExpectedConditions.visibilityOf(ele));
    }
}

