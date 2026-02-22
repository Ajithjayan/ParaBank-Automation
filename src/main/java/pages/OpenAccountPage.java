package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

public class OpenAccountPage extends WaitUtils {
    WebDriver driver;
    public OpenAccountPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    //Type of Account DropDown
    @FindBy(id="type")
    WebElement accountType;

    @FindBy(id="fromAccountId")
    WebElement existingAccount;

    @FindBy(css = "input[value='Open New Account']")
    WebElement openNewAccount;

    @FindBy(xpath="//p[contains(text(),'Congratulations')]")
    WebElement accountOpenedtext;

    @FindBy(id = "newAccountId")
    WebElement newAccountId;
    public void AccountType(String acctype,String no){
        waitForElementToAppear(accountType);
        Select select= new Select(accountType);
        select.selectByVisibleText(acctype);
        existingAccountFundTransfer(no);
    }

    private void existingAccountFundTransfer(String no) {
        waitForElementToAppear(existingAccount);
        Select select= new Select(existingAccount);
        select.selectByVisibleText(no);
        OpenNewAccount();
    }

    private void OpenNewAccount() {
        waitForElementToAppear(openNewAccount);
        openNewAccount.click();
    }
    public String AccountOpened(){
        waitForElementToAppear(accountOpenedtext);
        return accountOpenedtext.getText();
    }
    public String newAccountDetails(){
       return newAccountId.getText();
    }
}
