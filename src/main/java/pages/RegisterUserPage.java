package pages;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterUserPage {
    WebDriver driver;
    public RegisterUserPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="customer.firstName")
    WebElement firstName;
    @FindBy(id="customer.lastName")
    WebElement lastName;
    @FindBy(id="customer.address.street")
    WebElement address;
    @FindBy(id="customer.address.city")
    WebElement city;
    @FindBy(id="customer.address.state")
    WebElement state;
    @FindBy(id="customer.address.zipCode")
    WebElement zip;
    @FindBy(id="customer.phoneNumber")
    WebElement number;
    @FindBy(id="customer.ssn")
    WebElement ssn;
    @FindBy(id="customer.username")
    WebElement userName;
    @FindBy(id="customer.password")
    WebElement pwd;
    @FindBy(id="repeatedPassword")
    WebElement confirmPwd;
    @FindBy(css = "input[value='Register']")
    WebElement register;

    public DashBoardPage SignUp(User user){
        firstName.sendKeys(user.firstname);
        lastName.sendKeys(user.lastname);
        address.sendKeys(user.address);
        city.sendKeys(user.city);
        state.sendKeys(user.state);
        zip.sendKeys(user.zipCode);
        number.sendKeys(user.phone);
        ssn.sendKeys(user.ssn);
        userName.sendKeys(user.username);
        pwd.sendKeys(user.password);
        confirmPwd.sendKeys(user.password);

        register.click();
        return new DashBoardPage(driver);
    }
}
