package base;

import models.User;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import pages.RegisterUserPage;
import utils.CredentialManager;
import utils.TestDataFactory;
import utils.configReader;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        String browser = configReader.getBrowser();
        driver = DriverFactory.initializationDriver(browser);

        driver.get(configReader.getBaseUrl());
    }

    protected void ensureuserExist() throws IOException {
        LoginPage loginPage = new LoginPage(driver);
        String userName = CredentialManager.getUserName();
        String password = CredentialManager.getPassword();

        boolean isLoggedIn = loginPage.LoginAs(userName, password);
        if (!isLoggedIn) {
            // re-check after acquiring lock — another thread may have registered already
            userName = CredentialManager.getUserName();
            password = CredentialManager.getPassword();
            isLoggedIn = loginPage.LoginAs(userName, password);
            if (!isLoggedIn) {
                System.out.println("Login failed. Registering new User....");
                RegisterUserPage registerpage = loginPage.registerUserPage();
                User user = TestDataFactory.generateUser();
                registerpage.SignUp(user);
                userName = user.username;
                password = user.password;
                CredentialManager.saveCredentials(userName, password);
                System.out.println("New User Created: " + userName);
            }
        }
    }

        public String getScreenShot (String testCaseName, WebDriver driver) throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
            FileUtils.copyFile(source, file);
            return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        }


//        @AfterMethod(alwaysRun = true)
//        public void tearDown () {
//            driver.quit();
//        }

}
