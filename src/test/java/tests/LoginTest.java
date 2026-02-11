package tests;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    String username="ajithj1009";
    String pwd="1234ajith@1";
    @Test
    public void validLoginTest(){
        LoginPage login= new LoginPage(driver);
        login.LoginAs(username,pwd);
    }

}
