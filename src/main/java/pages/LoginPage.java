package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public DashBoardPage LoginAs(String email, String pwd){
        userName.sendKeys(email);
        password.sendKeys(pwd);
        logIn.click();
        return new DashBoardPage();
    }

}
