package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class LoginPage extends WaitUtils {
    WebDriver driver;
    WebDriverWait wait;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        this.wait= new WebDriverWait(driver, Duration.ofSeconds(15));
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

    //Forgot Info
    @FindBy(partialLinkText ="Forgot login info?")
    WebElement forgotInfo;

    public Boolean LoginAs(String email, String pwd){
        wait.until(ExpectedConditions.visibilityOf(userName));
        userName.clear();
        userName.sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(userName));
        password.clear();
        password.sendKeys(pwd);

        wait.until(ExpectedConditions.visibilityOf(userName));
        logIn.click();

        return isLoginsuccessful();
        }

    public boolean isLoginsuccessful() {
        try {
            wait.until(d -> d.getPageSource().contains("Accounts Overview")
                    || d.getPageSource().contains("Error")
                    || d.getPageSource().contains("Log In"));
        } catch (Exception e) {
            return false;
        }
        return driver.getPageSource().contains("Accounts Overview");
    }
    public boolean inValidCredentials(){
        wait.until(ExpectedConditions.visibilityOf(errortext));
        return errortext.isDisplayed();

    }
    public RegisterUserPage registerUserPage(){
        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLink.click();
        return new RegisterUserPage(driver);
    }
    public DashBoardPage goToDashB0ard(){
        return new DashBoardPage(driver);
    }

    public CustomerLookup ForgotInfo(){
        forgotInfo.click();
        return new CustomerLookup(driver);
    }
}
