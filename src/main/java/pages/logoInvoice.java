package pages;
import base.BaseClass;
import helper.CommonUtility;
import helper.Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class logoInvoice extends BaseClass
{
    @FindBy(id = "header.menuConfiguration")
    WebElement menuConfiguration;

    @FindBy(id = "header.subMenuSystemSettings")
    WebElement subMenuSystemSettings;

    @FindBy(xpath="//p[normalize-space()='Company Entity']")
    public
    WebElement companyEntity;

    @FindBy(id = "companyEntityGridDrtv.add")
    public
    WebElement addCompanyEntity;

    @FindBy(id = "companyEntityProfile_code")
    public
    WebElement companyId;

    @FindBy(id = "companyEntityProfile_company")
    public
    WebElement companyName;

    @FindBy(id = "companyEntity.cancelSave.save")
    public
    WebElement save;

    @FindBy(id ="CompanyEntity.InvoicingLogoSettingPage")
    public
    WebElement invoiceLogoSetting;

    @FindBy(id = "invoicingLogoSettings.uploadLogo")
    public
    WebElement uploadLogo;

    @FindBy(id = "invoicingLogoSettings.removeLogo")
    public
    WebElement removeLogo;


    public logoInvoice(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
 public void logoElements()
    {
        CommonUtility.clickElement(menuConfiguration);
        CommonUtility.clickElement(subMenuSystemSettings);
        CommonUtility.clickElement(companyEntity);
        CommonUtility.clickElement(addCompanyEntity);
        Utility ut1= new Utility();
        String init= ut1.randomAlphaNumeric(7);
        CommonUtility.clickElement(companyName);
        CommonUtility.sendKeys(companyName,init);
        CommonUtility.clickElement(companyId);
        companyId.sendKeys("64");
        CommonUtility.clickElement(save);
        CommonUtility.clickElement(invoiceLogoSetting);
        CommonUtility.clickElement(uploadLogo);
        uploadLogo.sendKeys("C:\\Users\\RakeshChandanbatwe\\Downloads");
        Assert.assertTrue(removeLogo.isDisplayed());
    }
}


