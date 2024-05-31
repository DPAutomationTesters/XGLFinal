package browserfactory;

import base.BaseClass;
import dataProvider.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import listners.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserFactory extends BaseClass
{

    public static WebDriver getBrowserInstance() {
        return driver;
    }

    public static WebDriver startBrowser(String browserName, String applicationURL)
    {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        //Create instance of ChromeOptions Class
        ChromeOptions options  = new ChromeOptions();
        //Using the accept insecure cert method with true as parameter to accept the untrusted certificate
        options.setAcceptInsecureCerts(true);
       /* options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");*/
        //options.addArguments("download.default_directory=" + ConfigReader.getPropertyvalue("Downloadfolder"));

        if (browserName.contains("Chrome")) {
           WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver","D:\\XGLFinal\\chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
        } else if (browserName.contains("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", ConfigReader.getPropertyvalue("GeckoexePath"));
            driver = new FirefoxDriver();
        }
        //Event Listener initialization
        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;
        //end of event listener initialization

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get(applicationURL);
        driver.navigate().refresh();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        return driver;
    }
}
