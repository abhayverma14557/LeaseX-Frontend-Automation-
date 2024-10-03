package Test;

import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Set;

public class Demo extends BaseClass {
    private Set<Cookie> cookies;

    //Positive Case to Check the User Login
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void loginFlow(HashMap<String, String> input) throws InterruptedException {

        String case_Type = input.get("case_Type");


        //User Login Flow Automation
        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));

        PortalSelectionPage usrSelObj = login.clickLoginButton();
        cookies = login.getCookies();
        Thread.sleep(2000);
        usrSelObj.portalSelection(input.get("portal"));

        soft_Assert.assertAll();


    }


}