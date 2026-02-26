package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.OpenAccountPage;
import pages.accountInfoPage;
import utils.CredentialManager;

import java.io.IOException;

public class AccountServicesTest extends BaseTest {
    private String accountNumber;
    @Test(retryAnalyzer=base.Retry.class)
    public void ExistingAccountInfo() throws IOException {
        ensureuserExist();
        DashBoardPage dashBoardPage= new DashBoardPage(driver);
        accountInfoPage accountinfo=  dashBoardPage.accountsOverview();
        accountinfo.getAccountDetails();
        accountNumber= accountinfo.getAccountNumber();
        this.accountNumber=accountNumber;
        System.out.println("AccountNo= "+accountNumber);

        String url=driver.getCurrentUrl();
        String expAccNo=url.split("id=")[1];
        Assert.assertEquals(expAccNo,accountNumber);

    }
    @Test(retryAnalyzer=base.Retry.class)
    public void OpenAccount() throws IOException {
        ensureuserExist();
        DashBoardPage dashBoardPage= new DashBoardPage(driver);
        accountInfoPage accountInfo= dashBoardPage.accountsOverview();
        accountInfo.getAccountDetails();
        String accountNumber= accountInfo.getAccountNumber();
        OpenAccountPage openAccount= dashBoardPage.openNewAccount();
        openAccount.AccountType("SAVINGS", accountNumber);
        String newAccount=openAccount.AccountOpened();
        Assert.assertEquals(newAccount,"Congratulations, your account is now open.");
        String newAccountNumber= openAccount.newAccountDetails();
        System.out.println(newAccountNumber);
        //Verify New AccountCreated
        accountInfoPage acctinfo= dashBoardPage.accountsOverview();
        boolean matched= acctinfo.AccountNames(newAccountNumber);
        Assert.assertTrue(matched);

    }

}
