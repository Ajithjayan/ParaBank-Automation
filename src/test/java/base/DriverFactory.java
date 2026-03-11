package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.configReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory{
    private static ThreadLocal<WebDriver> driver= new ThreadLocal<>();
    public static WebDriver initializationDriver(String browser) throws IOException {
       boolean headless= configReader.isHeadless();


        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless){
                options.addArguments("--headless=new"); // "new" mode is stable in Chrome 112+
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080"); // needed since maximize() won't work headless
            }
            driver.set(new ChromeDriver(options));
        }
        else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();
            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--window-size=1920,1080");
            }
            driver.set(new EdgeDriver(options));

        }
        if (!headless) {
            driver.get().manage().window().maximize(); // only maximize in non-headless
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver.get();

    }
    public static WebDriver getDriver(){
        return driver.get();
    }

}
