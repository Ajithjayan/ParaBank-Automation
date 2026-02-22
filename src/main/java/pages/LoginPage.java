package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="input[name='username']")
    WebElement userName;

    @FindBy(css="input[name='password']")
    WebElement password;

    @FindBy(css="input[value='Log In']")
    WebElement logIn;

    @FindBy(css=".error")
    WebElement errortext;

    @FindBy(linkText = "Register")
    WebElement registerLink;
//    public DashBoardPage LoginAs(String email, String pwd){
//        userName.sendKeys(email);
//        password.sendKeys(pwd);
//        logIn.click();
//        return new DashBoardPage(driver);
//    }

    public Boolean LoginAs(String email, String pwd){
        userName.clear();
        userName.sendKeys(email);
        password.clear();
        password.sendKeys(pwd);
        logIn.click();

        return isLoginsuccessful();
        }

    public boolean isLoginsuccessful() {
        return driver.getPageSource().contains("Accounts Overview");
    }
    public boolean inValidCredentials(){
        return errortext.isDisplayed();

    }
    public RegisterUserPage registerUserPage(){
        registerLink.click();
        return new RegisterUserPage(driver);
    }
    public DashBoardPage goToDashB0ard(){
        return new DashBoardPage(driver);
    }
}
