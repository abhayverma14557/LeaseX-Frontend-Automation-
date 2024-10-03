package Test.Login;

import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class FieldErrorMsgs extends BaseClass {
    //  Verify the Fields Error Message

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void verifyFieldErrMsg(HashMap<String, String> input, ITestContext context) {


        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("Browser", input.get("Browser"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));

        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));


        login.clearUsername();
        login.clearPassword();
        login.clearCompanyCode();

        soft_Assert.assertEquals(login.getErrorMessage(), "Please enter the valid Username.");
        login.setUsername(input.get("userName"));

        login.clearPassword();
        soft_Assert.assertEquals(login.getErrorMessage(), "Please enter the valid Password.");
        login.setPassword(input.get("password"));

        login.clearCompanyCode();
        soft_Assert.assertEquals(login.getErrorMessage(), "Please enter the valid Company Codeee.");
        soft_Assert.assertEquals(login.getErrorMessage(), "ua_val_bus_cpass_user_inv");
        login.clickLoginButton();
        soft_Assert.assertAll();
    }

//


}
