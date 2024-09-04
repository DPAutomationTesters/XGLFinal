package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Customer_AdCopyGroup extends BaseClass {



    @FindBy(xpath = "//p[contains(text(),'Ad Copy Groups')]")
    WebElement adcopyGrouptab;

    @FindBy(xpath = "//button[@id='rotationGroupsGrid.add']")
    WebElement addAdcopyGroup;

    @FindBy(xpath = "//div[@id='rotationGroupsEntity.type']//i")
    WebElement grouptypebutton;

    @FindBy(xpath = "//div[@class='dropDown']/div[contains(text(),'Piggyback')]")
    WebElement piggyback;

    @FindBy(xpath = "//div[@class='dropDown']/div[contains(text(),'Billboard')]")
    WebElement billboard;

    @FindBy(xpath = "//div[@class='dropDown']/div[contains(text(),'Bookend')]")
    WebElement bookend;
    @FindBy(xpath = "//input[@id='rotationGroupsEntity.title']")
    WebElement GroupDescription;

    @FindBy(xpath = "//*[@id='rotationGroupsEntity.length']")
    WebElement Grouplength;

    @FindBy(xpath ="//i[@class='add']")
    WebElement AddCopyItem;

    @FindBy(xpath = "//button[@id='cancelSave.save']")
    WebElement Save;

    @FindBy(xpath = "//div[@name='rotationId']/input")
    WebElement SelectAdCopy;

    @FindBy(xpath = "//div[@name='rotationId']//div[contains(@class,'auto-complete-list-drop-down')]/div")
    By SelectAdcopydropdown;
    @FindBy(xpath = "//div[@name='firstAdCopy']/input")
    WebElement pbfirstcopy;

    @FindBy(xpath = "//div[@name='secondAdCopy']/input")
    WebElement pbsecondcopy;

    @FindBy(xpath = "//div[@name='topAdCopy']/input")
    WebElement betopcopy;


    @FindBy(xpath = "//div[@name='bottomAdCopy']/input")
    WebElement bebottomcopy;

    @FindBy(xpath = "//div[@name='rotationId']/input")
    WebElement bbselectAdcopy;

    @FindBy(xpath = "//button[@id='cancelSaveOk.OK']")
    WebElement SaveCopyItem;

    @FindBy(xpath= "//button[@id='cancelSave.save']")
    WebElement SaveGroup;

    @FindBy (xpath = "//div[@id='breadCrumbs.crumb1']")
    WebElement breadcrumvalue;

    public Customer_AdCopyGroup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void createaddcopyGroup() {
        CustomerSearchPage csp = new CustomerSearchPage(driver);
        if (csp.openCustomerProfile()) {
            CommonUtility.clickElement(adcopyGrouptab);
            WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
            Utility ut = new Utility();
            String init = ut.randomAlphaNumeric(2);
            CommonUtility.clickElement(GroupDescription);
            CommonUtility.sendKeys(GroupDescription,init);
            try{
            CommonUtility.clickElement(Grouplength);
            String strLen=String.valueOf(30);
            char[] digit=strLen.toCharArray();
            try {
                Robot robot=new Robot();
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                CommonUtility.sendKeys(Grouplength, String.valueOf(digit[1]));
                CommonUtility.sendKeys(Grouplength,String.valueOf(digit[0]));
            }
            catch (AWTException e)
            {
                ExceptionHandling.handleException(e);
            }}
            catch (Exception e)
            {
                ExceptionHandling.handleException(e);
            }
            CommonUtility.clickElement(AddCopyItem);
            CommonUtility.clickElement(SelectAdCopy);

            //dropdown.selectByIndex(0);
            List<WebElement> elements = driver.findElements(By.xpath("//div[@name='rotationId']//div[contains(@class,'auto-complete-list-drop-down')]/div"));
            elements.get(0).click();
            try{
                while(SaveCopyItem.isDisplayed())
                {
                  CommonUtility.clickElement(SaveCopyItem);}}
            catch(Exception e){
                ExceptionHandling.handleException(e);
            }
            CommonUtility.clickElement(SaveGroup);
            SoftAssert sa=new SoftAssert();
            sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Customer"));
            if(breadcrumvalue.getText().equalsIgnoreCase("Customer"))
            {
                Reporter.log("Standard copy group created with group description "+init);
            }

        }

        }
        public void createPiggybackGroup()
        {
            CustomerSearchPage csp = new CustomerSearchPage(driver);
            if (csp.openCustomerProfile()) {
                CommonUtility.clickElement(adcopyGrouptab);
                WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
                WaitUtility.waitTillElementtobeClickable(driver, 30, grouptypebutton);
                WaitUtility.waitTillElementVisible(driver,30,piggyback);
                CommonUtility.clickElement(piggyback);
                Utility ut = new Utility();
                String init = ut.randomAlphaNumeric(2) +" Piggy";
                CommonUtility.clickElement(GroupDescription);
                CommonUtility.sendKeys(GroupDescription,init);

                CommonUtility.clickElement(AddCopyItem);
                CommonUtility.clickElement(pbfirstcopy);
                List<WebElement> elements= driver.findElements(By.xpath("//div[@name='firstAdCopy']/div[contains(@class,'auto-complete-list-drop-down')]/div"));
                elements.get(0).click();

                CommonUtility.clickElement(pbsecondcopy);
                List<WebElement> elements1=driver.findElements(By.xpath("//div[@name='secondAdCopy']/div[contains(@class,'auto-complete-list-drop-down')]/div"));
                elements1.get(0).click();

                CommonUtility.clickElement(SaveCopyItem);
                CommonUtility.clickElement(SaveGroup);
                SoftAssert sa=new SoftAssert();
                sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Ad Copy Group"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                if(breadcrumvalue.isDisplayed())
                {
                    SaveProjectData spd=new SaveProjectData();
                    spd.saveprojectData("Piggybackgroup",init);
                }

        }}

        public void createBillboardGroup()
        {
            CustomerSearchPage csp = new CustomerSearchPage(driver);
            if (csp.openCustomerProfile()) {
                CommonUtility.clickElement(adcopyGrouptab);
                WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
                WaitUtility.waitTillElementtobeClickable(driver, 30, grouptypebutton);
                WaitUtility.waitTillElementVisible(driver,30,billboard);
                CommonUtility.clickElement(billboard);
                Utility ut = new Utility();
                String init = ut.randomAlphaNumeric(2);
                GroupDescription.sendKeys(init);
                CommonUtility.clickElement(AddCopyItem);
                CommonUtility.clickElement(bbselectAdcopy);
                List<WebElement> elements = driver.findElements(By.xpath("//div[@name='rotationId']//div[contains(@class,'auto-complete-list-drop-down')]/div"));
                elements.get(0).click();
                CommonUtility.clickElement(SaveCopyItem);
                CommonUtility.clickElement(SaveGroup);
                SoftAssert sa=new SoftAssert();
                sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Ad Copy Group"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                if(breadcrumvalue.isDisplayed())
                {
                    SaveProjectData spd=new SaveProjectData();
                    spd.saveprojectData("Billboardgroup",init);
                }
        }}
    public void createBookendGroup()
    {
        CustomerSearchPage csp = new CustomerSearchPage(driver);
        if (csp.openCustomerProfile()) {
            CommonUtility.clickElement(adcopyGrouptab);
            WaitUtility.waitTillElementtobeClickable(driver, 30, addAdcopyGroup);
            WaitUtility.waitTillElementtobeClickable(driver, 30, grouptypebutton);
            WaitUtility.waitTillElementVisible(driver,30,bookend);
            CommonUtility.clickElement(bookend);
            Utility ut = new Utility();
            String init = ut.randomAlphaNumeric(2) +" bookend";
            CommonUtility.clickElement(GroupDescription);
            CommonUtility.sendKeys(GroupDescription,init);
            CommonUtility.clickElement(AddCopyItem);
            CommonUtility.clickElement(betopcopy);
            ////div[@id='editBookend.topAdCopy']/div[contains(@class,'auto-complete-list-drop-down')]/div
            List<WebElement> elements= driver.findElements(By.xpath("//div[@id='editBookend.topAdCopy']/div[contains(@class,'auto-complete-list-drop-down')]/div"));
            elements.get(0).click();

            CommonUtility.clickElement(bebottomcopy);
            List<WebElement> elements1=driver.findElements(By.xpath("//div[@id='editBookend.bottomAdCopy']/div[contains(@class,'auto-complete-list-drop-down')]/div"));
            elements1.get(0).click();
            CommonUtility.clickElement(SaveCopyItem);
            CommonUtility.clickElement(SaveGroup);
            SoftAssert sa=new SoftAssert();
            sa.assertTrue(breadcrumvalue.getText().equalsIgnoreCase("Ad Copy Group"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            if(breadcrumvalue.isDisplayed())
            {
                SaveProjectData spd=new SaveProjectData();
                spd.saveprojectData("Bookendgroup",init);
            }

        }}
    public void createaddcopytypeGroup(String type)
    {
        if(type=="Standard")
        {
            createaddcopyGroup();
        } else if (type=="Piggyback")
        {
            createPiggybackGroup();
        } else if (type=="Bookend")
        {
            createBookendGroup();
        } else if (type=="Billboard")
        {
            createBillboardGroup();
        }

    }
    }

