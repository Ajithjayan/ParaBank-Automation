package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import utils.configReader;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    @BeforeTest(alwaysRun = true)
    public void setup() throws IOException {
        String browser= configReader.getBrowser();
        driver=DriverFactory.initializationDriver(browser);

        driver.get(configReader.getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
