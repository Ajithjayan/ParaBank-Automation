package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.OpenAccountPage;
import pages.TransferFundsPage;
import pages.accountInfoPage;

import java.io.IOException;
import java.util.List;

public class TransferFundsTest extends BaseTest {

    @Test(retryAnalyzer = base.Retry.class)
    public void  FundTransfer() throws IOException {
        ensureuserExist();
        DashBoardPage dashBoardPage= new DashBoardPage(driver);
        accountInfoPage info = dashBoardPage.accountsOverview();
        List<String> accounts =info.NumberOfAccounts();
        System.out.println(accounts.size());
        if(accounts.size()<=2){
            String baseAccount = accounts.get(0);
            OpenAccountPage openAccountPage= dashBoardPage.openNewAccount();
            openAccountPage.AccountType("SAVINGS",baseAccount);
            info=dashBoardPage.accountsOverview();
            accounts=info.NumberOfAccounts();
            System.out.println(accounts=info.NumberOfAccounts());
        }

        String accountNumber1= accounts.get(0);
        String accountNumber2= accounts.get(1);
        System.out.println(accountNumber1);
        System.out.println(accountNumber2);
        TransferFundsPage transferFund= dashBoardPage.transferFunds();
        String fundAmount= "10000";
        transferFund.TransferAmount(fundAmount, accountNumber1, accountNumber2);

        String actualTransferAmount= transferFund.AmountTransferred();
        Assert.assertEquals(actualTransferAmount,fundAmount);

        String fromAccount= transferFund.FundTransferredFrom();
        Assert.assertEquals(fromAccount,accountNumber1);

        String toAccount = transferFund.FundTransferredTo();
        Assert.assertEquals(toAccount,accountNumber2);

    }
}
