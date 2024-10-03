package Test.Login;

import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class InvalidCredVerification extends BaseClass {


    // Test Data ID LOGIN-003
    // Test Case to verify error message when invalid credentials are given
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void verifyInvalidCredentials(HashMap<String, String> input, ITestContext context) throws InterruptedException {
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        //User Login Flow Automation
        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));
        soft_Assert.assertTrue(login.loginButtonStatus());
        login.clickLoginButton();
        soft_Assert.assertEquals(login.getErrorMessage(), "ua_val_bus_cpass_user_inv");
        soft_Assert.assertAll();

    }

}
