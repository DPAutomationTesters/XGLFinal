package pages;

import base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends BaseClass
{
    @FindBy(id="header.menuOrders")
    public
    WebElement menuOrders;

    @FindBy(id="header.subMenuCustomers")
    public
    WebElement menuCustomers;

    @FindBy(xpath="//div[@id='header.subMenuOrders']")
    public WebElement submenuOrders;

    @FindBy(xpath = "//div[contains(@id,'breadCrumbs')]")
    public
    WebElement breadcrumvalue;

    @FindBy(xpath = "//div[@class='sepV']//button[@id='orderGrid.csv']")
    public
    WebElement exportCSV;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
