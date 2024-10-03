package Test.Login;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.IRetry;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LeaseXLogin extends BaseClass {


    //Positive Case to Check the User Login

    @Test(dataProvider = "getData", dataProviderClass = TestData.class, retryAnalyzer = IRetry.class)
    public void loginFlow(HashMap<String, String> input, ITestContext context) throws InterruptedException {

        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("case_Type", input.get("case_Type"));
        //User Login Flow Automation
        login.setUsername(input.get("userName")!=null? input.get("userName") :"");
        login.setPassword(input.get("password")!=null? input.get("password") :"");
        login.setCompanyCode(input.get("company_Code")!=null? input.get("company_Code") :"");


        PortalSelectionPage usrSelObj = login.clickLoginButton();
        usrSelObj.portalSelection(input.get("portal"));


        soft_Assert.assertAll();


    }


}
