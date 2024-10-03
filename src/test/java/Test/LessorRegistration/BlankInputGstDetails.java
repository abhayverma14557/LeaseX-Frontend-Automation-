package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class BlankInputGstDetails extends BaseClass {

    //Test Case ID in Excel= VEND_REG009
    //Gst Details Fields Verify on Blank Field Input
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankInputGstDetails(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {
        boolean e_InvState;
        String msme_Option;

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
        LessorPage lessorPageObj = (LessorPage) dashPageObj.sideMenuOptionSelection("Vendor");

        lessorPageObj.clickRegistrationBtn();

        //Vendor Details Step
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
        lessorPageObj.selectAddressType(input.get("address_Type"));
        lessorPageObj.setAddressLine1(input.get("address_Line1"));
        lessorPageObj.setAddressLine2(input.get("address_Line2"));
        lessorPageObj.selectCountry(input.get("country"));
        lessorPageObj.selectState(input.get("state"));
        lessorPageObj.selectCity(input.get("city"));
        lessorPageObj.setPincode(input.get("pincode"));
        lessorPageObj.clickSaveButton(1);
        //For Data Update Message changes to the "Data update successfully"
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Msg Values are not Matching"); //Assertion For Successfull Address save verifying from the toast message
        lessorPageObj.clickNextButton();
        //Add Validation for the Listing

        //Contact Person Details Step


        lessorPageObj.clickAddButton();
        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Msg Values are not Matching");
        lessorPageObj.clickNextButton();


        //Compliance Details Step

        lessorPageObj.setPanNo(input.get("pan_Number"));
        lessorPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));

        lessorPageObj.selectMsmeReg(input.get("msme_Option"));

        msme_Option = input.get("msme_Option");
        if (!msme_Option.equals(" Not Available ")) {
            lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
            lessorPageObj.setMsmeExpDate("12-12-2025");  //Implement this as dynamic data as well

        }
        lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
        e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
        if (e_InvState) {
            lessorPageObj.setE_Inv();
        }
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Msg Values are not Matching");


        //Gst Details in Compliance
        lessorPageObj.clickAddButton();
        lessorPageObj.clickSaveButton(2);

        soft_Assert.assertEquals(lessorPageObj.getGstStatusErr(), "GST Status Required");
        String[] expected_Err = {"GST Number Required", "Country Required", "State Required"};
        List<String> recievedErrMsg = lessorPageObj.getGstFieldErr();
        int i = 0;
        for (String actual_ErrMsg : recievedErrMsg) {
            soft_Assert.assertEquals(actual_ErrMsg, expected_Err[recievedErrMsg.indexOf(actual_ErrMsg)], "GST Text Fields Error Msgs are not Equal ");
            i++;
        }
        soft_Assert.assertAll();
    }


}
