package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;

public class DashBoardPage extends WaitUtils {
    WebDriver driver;
    public DashBoardPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="[id='showOverview'] h1")
    WebElement accountDetails;

    @FindBy(css="a[href='overview.htm']")
    WebElement accountOverview;

    //Open New Account:
    @FindBy(css="a[href='openaccount.htm']")
    WebElement openNewAccount;

    //Transfer Funds
    @FindBy(css="a[href='transfer.htm']")
    WebElement transferFundsbtn;

    public String getAccountDetailsText(){
        return accountDetails.getText();
    }

    public  Boolean isAccountOverviewDisplayed(){
        return accountDetails.isDisplayed();
    }

    public OpenAccountPage openNewAccount(){
        waitForElementToAppear(openNewAccount);
        openNewAccount.click();
        return new OpenAccountPage(driver);
    }

    public accountInfoPage accountsOverview(){
        waitForElementToAppear(accountOverview);
        accountOverview.click();
        return new accountInfoPage(driver);
    }

    public TransferFundsPage transferFunds(){
        transferFundsbtn.click();
        return new TransferFundsPage(driver);
    }
}
