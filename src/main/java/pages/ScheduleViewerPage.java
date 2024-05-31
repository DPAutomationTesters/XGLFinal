package pages;

import base.BaseClass;
import helper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ScheduleViewerPage extends BaseClass
{
    @FindBy(xpath = "//div[text()='Scheduling']")
    public
    WebElement scheduling;

    @FindBy(xpath = "//div[@id='header.subMenuScheduleViewer']")
    public
    WebElement subMenuScheduleViewer;

    public ScheduleViewerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
