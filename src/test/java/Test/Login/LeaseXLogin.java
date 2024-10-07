package Test.Login;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.IRetry;
import TestComponents.TestData;
import jdk.dynalink.linker.LinkerServices;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeaseXLogin extends BaseClass {


    //Positive Case to Check the User Login

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void loginFlow(HashMap<String, String> input, ITestContext context) throws InterruptedException {

        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testName", input.get("test_Name"));

        //User Login Flow Automation
        login.setUsername(input.get("userName")!=null? input.get("userName") :"");
        login.setPassword(input.get("password")!=null? input.get("password") :"");
        login.setCompanyCode(input.get("company_Code")!=null? input.get("company_Code") :"");
        if(login.loginButtonStatus()) {
            login.clickLoginButton();
        }
        List<String> actual_Err_Msg=login.getErrorMessage();
        List<String> expected_Err_Msg=new ArrayList<String>();
        expected_Err_Msg.add("ua_val_bus_cpass_user_inv");
        expected_Err_Msg.add("ua_val_bus_pass_un_match");
        expected_Err_Msg.add("Please enter the valid Company Code.");
        expected_Err_Msg.add("Please enter the valid Password.");
        expected_Err_Msg.add("Please enter the valid Username.");

        if (!actual_Err_Msg.isEmpty())
        {
            soft_Assert.assertTrue(expected_Err_Msg.containsAll(actual_Err_Msg),"Login Error Message Validation Failed and Actual Messages=" +actual_Err_Msg);
        }


        soft_Assert.assertAll();


    }


}
