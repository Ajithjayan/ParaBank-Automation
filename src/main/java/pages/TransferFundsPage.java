package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class TransferFundsPage extends WaitUtils {
    WebDriver driver;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "amount")
    WebElement amount;

    @FindBy(id = "fromAccountId")
    WebElement fromAccount;

    @FindBy(id = "toAccountId")
    WebElement toAccount;

    @FindBy(css = "input[type='submit']")
    WebElement transfer;
    //After Transfer -Amount
    @FindBy(css = "p span[id='amountResult']")
    WebElement fundTransferAmount;

    @FindBy(css = "p span[id='fromAccountIdResult']")
    WebElement fromaccountAfterTransfer;

    @FindBy(css = "p span[id='toAccountIdResult']")
    WebElement toaccountAfterTransfer;


    public void TransferAmount(String amt, String acc1, String acc2) {
        amount.sendKeys(amt);
        Select fromDropDown = new Select(fromAccount);
        fromDropDown.selectByVisibleText(acc1);

        Select toDropdown = new Select(toAccount);
        toDropdown.selectByVisibleText(acc2);

        transfer.click();

    }

    public String AmountTransferred() {
        waitForElementToAppear(fundTransferAmount);
        String money = fundTransferAmount.getText();
        money= money.replace("$","")
                .replace(".00","");
        return money;
    }

    public String FundTransferredFrom() {

        return fromaccountAfterTransfer.getText();

    }

    public String FundTransferredTo() {
        return toaccountAfterTransfer.getText();
    }
}