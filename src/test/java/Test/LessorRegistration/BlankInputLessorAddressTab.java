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

public class BlankInputLessorAddressTab extends BaseClass {
    //    //Verify Saving of Address Details on Blank Fields and Their Error Messages are disappearing after text input
    //Test Case ID in Excel= Lessor-REG004
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankInputAddressTab(HashMap<String, String> input, ITestContext context) throws InterruptedException {

        //Used to Set The Test Case Type in Test Report
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));


        //User Login Flow Automation

        String[] expected_Err_Msg = {"Address Type Required", "Address Line 1 Required", "Country Required", "State Required", "City Required", "Pin Code Required"};
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
        lessorPageObj.setLessorEmail(input.get("lessor_Email"));
        lessorPageObj.selectLessorType(input.get("lessor_Type"));
        lessorPageObj.setErpCode(input.get("erp_Code"));

        String erp_Code = input.get("lessor_Type");


        if (erp_Code.equals("Private Company") || erp_Code.equals("Public Company")) {
            lessorPageObj.setCINNumber(input.get("cin_Number"));
        }
        lessorPageObj.clickNextButton();


        //Address Details Step

        lessorPageObj.clickAddButton();
        lessorPageObj.clickSaveButton(1);
        List<String> err_Msg_List = lessorPageObj.getFormFieldErrorMsg();

        for (String str : err_Msg_List) {
            soft_Assert.assertEquals(str, expected_Err_Msg[err_Msg_List.indexOf(str)], "Address Details Field Error Message Values are not Equal");
        }
        lessorPageObj.selectAddressType(input.get("address_Type"));
        lessorPageObj.setAddressLine1(input.get("address_Line1"));
        lessorPageObj.setAddressLine2(input.get("address_Line2"));
        lessorPageObj.selectCountry(input.get("country"));
        lessorPageObj.selectState(input.get("state"));
        lessorPageObj.selectCity(input.get("city"));
        lessorPageObj.setPincode(input.get("pincode"));


        List<String> new_err_Msg_List = lessorPageObj.getFormFieldErrorMsg();
        soft_Assert.assertTrue(new_err_Msg_List.isEmpty());

        lessorPageObj.clickSaveButton(1);
        //For Data Update Message changes to the "Data update successfully"
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Value are not Equal"); //Assertion For Successfull Address save verifying from the toast message
        lessorPageObj.clickNextButton();
        soft_Assert.assertAll();
    }


}
