package listners;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebEventListener extends BaseClass implements WebDriverListener
{
    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("Clicked on: {}",element.toString());
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        //logger.info("Found Element By {by.toString()}");
    }
    private String getElementDescription(WebElement element) {
        String tagName = element.getTagName();
        String text = element.getText().isEmpty() ? "No text" : element.getText();
        String id = element.getAttribute("id").isEmpty() ? "No id" : element.getAttribute("id");
        String name =element.getAttribute("name").isEmpty() ? "No name" : element.getAttribute("name");
        String className = element.getAttribute("class").isEmpty() ? "No class" : element.getAttribute("class");

        return String.format("tag: %s, text: %s, id: %s, name: %s, class: %s", tagName, text, id, name, className);
    }
}
