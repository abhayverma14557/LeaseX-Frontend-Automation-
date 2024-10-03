package Test.Login;

import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class PassEncryptDecrypt extends BaseClass {
    //Verifying the Password Field's password Encryption and Decryption
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void verifyPassEncryptDecrypt(HashMap<String, String> input, ITestContext context) throws InterruptedException {
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        login.setPassword(input.get("password"));
        login.clickPswrdViewBtn();
        soft_Assert.assertEquals(login.getpasswordFieldState(), "text");
        login.clickPswrdViewBtn();
        soft_Assert.assertEquals(login.getpasswordFieldState(), "password");
        soft_Assert.assertAll();

    }
}
