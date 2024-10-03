package Test.Login;

import TestComponents.BaseClass;
import TestComponents.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class LoginWithoutCompanyCode extends BaseClass {

    private static final Logger log = LoggerFactory.getLogger(LoginWithoutCompanyCode.class);

    //    // Test Data ID LOGIN-002
    //  Test Case Where User haven't set Company Code, and then we are verifying the login button status
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void loginWithoutCompanyCode(HashMap<String, String> input, ITestContext context) throws InterruptedException {

        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        //User Login Flow Automation
        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        soft_Assert.assertFalse(login.loginButtonStatus());
        soft_Assert.assertAll();

    }

}
