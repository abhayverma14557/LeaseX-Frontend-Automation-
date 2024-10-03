package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class InvalidInputLessorBasicTab extends BaseClass {
    //  Verifying Error Message of Fields on Invalid Input in Basic Tab
//Test Case ID in Excel= Lessor_REG-003
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void invalidInputBasicTab(HashMap<String, String> input, ITestContext context) throws InterruptedException {
        //Used to Set The Test Case Type in Test Report
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));


        //User Login Flow Automation


        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));
        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //Portal Selection
        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //Navigating to the Lessor Page and Registeration Button Click
        LessorPage lessorPageObj = (LessorPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));

        lessorPageObj.clickRegistrationBtn();


        //Lessor Details Step
        lessorPageObj.setLessorName(input.get("lessor_Name"));
        soft_Assert.assertEquals(lessorPageObj.getErrMsg().get(0), "Name Only alphabets allowed", "Name Field Error Message Value are not Equal");
        lessorPageObj.clearLessorName();

        lessorPageObj.setLessorEmail(input.get("lessor_Email"));
        soft_Assert.assertEquals(lessorPageObj.getErrMsg().get(1), "Email ID not valid", "Email Field Error Message Values are not Equal");
        lessorPageObj.clearVendorEmail();
        lessorPageObj.setLessorEmail("Test@gmail.com");
        lessorPageObj.selectLessorType(input.get("lessor_Type"));
        lessorPageObj.setErpCode(input.get("erp_Code"));

        String entity_Type = input.get("lessor_Type");

        if (entity_Type.equals("Private Company") || entity_Type.equals("Public Company")) {
            lessorPageObj.setCINNumber(input.get("cin_Number"));
        }
        lessorPageObj.clickNextButton();

        soft_Assert.assertAll();
    }

}
