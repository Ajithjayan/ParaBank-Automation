package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

public class accountInfoPage extends WaitUtils {
    WebDriver driver;
    WebDriverWait wait;
    String accountnumber;
    public accountInfoPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="accountId")
    WebElement accountNumber;

    @FindBy(css="a[href*='activity.htm?id=']")
    WebElement accountDetails;

    @FindBy(css="tbody tr td:nth-child(1)")
    List<WebElement> accountNames;

    //By accountNumber= By.id("accountId");
    public void getAccountDetails(){
        waitForElementToAppear(accountDetails);
        accountDetails.click();
    }
    public String getAccountNumber(){
        waitForElementToAppear(accountNumber);
        String url = driver.getCurrentUrl();
        String accno= url.split("id=")[1];
        return accno;
    }

    public Boolean AccountNames(String newAccountNo){
       Boolean matchedNo= accountNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(newAccountNo));
       return matchedNo;
    }

    public List<String> NumberOfAccounts(){

        List<String> acc= new ArrayList<>();
        for(WebElement element:accountNames){
            acc.add(element.getText());
        }
        return acc;
    }
}
