package tests;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.RegisterUserPage;
import pages.accountInfoPage;
import utils.CredentialManager;
import utils.TestDataFactory;
import utils.configReader;

import java.io.IOException;

public class LoginTest extends BaseTest {
//    String username="Testadmin";
//    String pwd="Testadmin@1";
//    LoginPage login= new LoginPage(driver);
//    @Test
//    public void validLoginTest(){
//        LoginPage login= new LoginPage(driver);
//        DashBoardPage dashBoardPage=login.LoginAs(configReader.getUsername(),configReader.getPassword());
//        Assert.assertTrue(dashBoardPage.isAccountOverviewDisplayed());
//    }
//
//    @Test
//    public void invalidLoginTest(){
//        LoginPage login= new LoginPage(driver);
//        login.LoginAs("wrongUser","wrongPwd");
//        Assert.assertTrue(login.inValidCredentials());
//    }

    @Test(retryAnalyzer = base.Retry.class)
    public void loginOrRegister() throws IOException {
        ensureuserExist();
        LoginPage loginPage= new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginsuccessful());

    }
}
