package base;

import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;
import pages.RegisterUserPage;
import utils.CredentialManager;
import utils.TestDataFactory;
import utils.configReader;

import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void setup() throws IOException {
        String browser= configReader.getBrowser();
        driver=DriverFactory.initializationDriver(browser);

        driver.get(configReader.getBaseUrl());
        System.out.println("Browser: " + browser);
        System.out.println("URL: " + configReader.getBaseUrl());
    }

    protected void ensureuserExist() throws IOException {
        LoginPage loginPage= new LoginPage(driver);
        String userName= CredentialManager.getUserName();
        String password=CredentialManager.getPassword();

        boolean isLoggedIn= loginPage.LoginAs(userName,password);
        System.out.println(isLoggedIn);
        System.out.println(userName);
        System.out.println(password);
        if(!isLoggedIn){
            System.out.println("Login failed. Registering new User....");
            RegisterUserPage registerpage=loginPage.registerUserPage();
            User user= TestDataFactory.generateUser();
            registerpage.SignUp(user);
            userName=user.username;
            password=user.password;
            CredentialManager.saveCredentials(userName,password);
            System.out.println("New User Created: "+userName);
            System.out.println(password);
        }
    }

//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown(){
//        driver.quit();
//    }

}
