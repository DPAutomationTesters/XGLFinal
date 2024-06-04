package pages;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Orderline extends BaseClass {

    @FindBy(xpath = "//div[@class='option selected opened']//p[@class='ng-binding' and text()='Order Lines']\n")
    WebElement ClickOrderlineTab;


}