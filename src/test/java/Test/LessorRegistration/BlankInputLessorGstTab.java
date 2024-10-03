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

public class BlankInputLessorGstTab extends BaseClass {

    boolean e_InvState;
    String msme_Option;

    //Test Case ID in Excel= Lessor_REG-012
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankInputGstTab(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {


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
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message
        lessorPageObj.clickNextButton();

        //Contact Person Details Step


        lessorPageObj.clickAddButton();
        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Person Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();


        //Compliance Details Step

        lessorPageObj.setPanNo(input.get("pan_Number"));
        lessorPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));

        lessorPageObj.selectMsmeReg(input.get("msme_Option"));

        msme_Option = input.get("msme_Option");
        if (!msme_Option.equals(" Not Available ")) {
            lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
            lessorPageObj.setMsmeExpDate(input.get("msme_Exp_Date"));  //Implement this as dynamic data as well

        }
        lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
        e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
        if (e_InvState) {
            lessorPageObj.setE_Inv();
        }
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");


        //Gst Details in Compliance
        lessorPageObj.clickAddButton();
        lessorPageObj.clickSaveButton(2);
        String[] expected_Err_Msg = {"Country Required", "State Required"};

        List<String> actual_Err_Msg = lessorPageObj.getGstStatusErr();
        for (String msg : actual_Err_Msg) {
            soft_Assert.assertEquals(msg, expected_Err_Msg[actual_Err_Msg.indexOf(msg)], "GST Details Fields Error Message Values are not Equal");  //Verifying the Error Messages
        }
        lessorPageObj.selectGstStatus(input.get("gst_Status"));
        lessorPageObj.setGstNo(input.get("gst_Number"));
        lessorPageObj.selectCountry(input.get("country"));
        lessorPageObj.selectState(input.get("state"));
        lessorPageObj.uploadGstCerti(System.getProperty("user.dir") + input.get("gst_File_Path"));  //In Test Data Fill the File Path Starting from the src
        soft_Assert.assertTrue(lessorPageObj.getGstStatusErr().isEmpty());
        lessorPageObj.clickSaveButton(2);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");


        soft_Assert.assertAll();
    }

}
