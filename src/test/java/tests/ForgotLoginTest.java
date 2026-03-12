package tests;

import base.BaseTest;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CustomerLookup;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.RegisterUserPage;
import utils.TestDataFactory;

public class ForgotLoginTest extends BaseTest {

    @Test(retryAnalyzer = base.Retry.class)
    public void Lookupuser(){
        // Step 1: Register a new user first
        LoginPage loginPage = new LoginPage(driver);
        RegisterUserPage registerPage = loginPage.registerUserPage();
        User user = utils.TestDataFactory.generateUser();
        registerPage.SignUp(user);

        //Logout First
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.logout();

        // Step 3: Now look up that same user with their registered details
        LoginPage loginPage2 = new LoginPage(driver);
        CustomerLookup customerLookup = loginPage2.ForgotInfo();
        customerLookup.FindMyLoginInfo(user);

        // Step 4: Verify success
        Assert.assertTrue(customerLookup.FindMyLoginInfo(user));


    }

}
