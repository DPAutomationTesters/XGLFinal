package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdCopy extends BaseClass
{
    @FindBy(id="header.menuOrders")
    public
    WebElement menuOrders;

    @FindBy(id="header.subMenuAdCopy")
    public
    WebElement menuAdcopy;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    public
    WebElement breadcrumvalue;

    @FindBy(xpath = "//div[@class='sepV']//button[@id='adCopyGrid.csv']")
    public
    WebElement exportCSV;

    public AdCopy(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
