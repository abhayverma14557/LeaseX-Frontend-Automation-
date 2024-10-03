package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class BlankInputLessorBasicTab extends BaseClass {
    //Verify Error Toast Messages on Blank Field Submission in Basic Tab

    //Test Case ID in Excel= Lessor-REG002

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankFieldInputBasicTab(HashMap<String, String> input, ITestContext context) throws InterruptedException {

        //Used to Set The Test Case Type in Test Report
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));

        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));
        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //Portal Selection

        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //Navigating to the Lessor Page and Register Button Click
        LessorPage lessorPageObj = (LessorPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));

        lessorPageObj.clickRegistrationBtn();
        lessorPageObj.clickNextButton();
        String[] expected_Err_Msg = {"Name Required", "Email ID Required", "", "Lessor Type Required"};
        List<String> err_Messages = lessorPageObj.getErrMsg();
        System.out.println(err_Messages);

        for (String actual_Err_Msg : err_Messages) {

            soft_Assert.assertEquals(actual_Err_Msg, expected_Err_Msg[err_Messages.indexOf(actual_Err_Msg)], "Basis Tab Error Messages Values are not Equal");
        }
        lessorPageObj.setLessorName(input.get("lessor_Name"));
        lessorPageObj.setLessorEmail(input.get("lessor_Email"));
        lessorPageObj.selectLessorType(input.get("lessor_Type"));
        soft_Assert.assertAll();
    }

}
