package pages;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.time.Duration;

public class CustomerLookup extends WaitUtils {

    WebDriver driver;

    public CustomerLookup(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="firstName")
    WebElement firstName;
    @FindBy(id="lastName")
    WebElement lastName;
    @FindBy(id="address.street")
    WebElement address;
    @FindBy(id="address.city")
    WebElement city;
    @FindBy(id="address.state")
    WebElement state;
    @FindBy(id="address.zipCode")
    WebElement zip;
    @FindBy(id="ssn")
    WebElement ssn;

    @FindBy(css ="input[value='Find My Login Info']")
    WebElement findMyLoginInfo;


    public boolean FindMyLoginInfo(User user){
        //waitForElementToAppear(firstName);
        firstName.sendKeys(user.firstname);
        lastName.sendKeys(user.lastname);
        address.sendKeys(user.address);
        city.sendKeys(user.city);
        state.sendKeys(user.state);
        zip.sendKeys(user.zipCode);
        ssn.sendKeys(user.ssn);
        waitForElementToAppear(findMyLoginInfo);
        findMyLoginInfo.click();

        // wait for result page to load
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(d -> d.getPageSource().contains("login information")
                        || d.getPageSource().contains("could not be found"));

        return driver.getPageSource().contains("Your login information was located successfully");

    }
}
