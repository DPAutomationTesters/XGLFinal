package pages;

import base.BaseClass;
import helper.CommonUtility;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class orderReconciliationPageThird extends BaseClass
{
    @FindBy(id="header.menuReconciliation")
    public
    WebElement menuReconciliation;

    @FindBy(id="header.subMenuOrderReconciliation")
    public
    WebElement menuOrderReconciliation;

    @FindBy(xpath = "//button[@id='orderReconciliation.search']")
    WebElement searchOrderReconciliation;


    @FindBy(xpath = "//div[contains(@class,'actionHighlight')]")
    //div[@class='slick-cell l0 r0 actionHighlight active']
    WebElement ClickOnOrder;

    @FindBy(xpath = "//div[contains(@class,'checkBoxSelection')]")
    //div[@class='fa fa-square']
    WebElement ClickAdUnitCheckbox;


    public orderReconciliationPageThird(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void reconciliationUnits()
    {
        Utility.waitforPagetoLoad(driver,40);
        CommonUtility.clickElement(menuReconciliation);
        CommonUtility.clickElement(menuOrderReconciliation);
        CommonUtility.clickElement(searchOrderReconciliation);
        Utility.waitforPagetoLoad(driver,20);
        CommonUtility.clickElement(ClickOnOrder);
        Utility.waitforPagetoLoad(driver,20);
        CommonUtility.clickElement(ClickAdUnitCheckbox);
        //String Linenumber = "str";
        // String str= "//div[@class='slick-cell l1 r1']/div[contains(text(),'"+Linenumber+"')]";
        // Assert.assertTrue(driver.findElement(By.xpath(str)).isDisplayed());
        // return flag;

    }



}
