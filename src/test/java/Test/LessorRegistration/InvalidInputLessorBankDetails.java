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

public class InvalidInputLessorBankDetails extends BaseClass {
    //Test Case ID in Excel= Lessor_REG016
//    Bank Details Field Verify on the Invalid Input
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void invalidInputBankDetails(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {
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

        //Navigating to the Lessor Page and Registration Button Click
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
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();


        //Compliance Details Step

        lessorPageObj.setPanNo(input.get("pan_Number"));
        System.out.println(input.get("pan_File_Path"));
        lessorPageObj.uploadPanFile(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\sample-pan-card-front.jpg");

        lessorPageObj.selectMsmeReg(input.get("msme_Option"));

        msme_Option = input.get("msme_Option");
        if (!msme_Option.equals(" Not Available ")) {

            lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
            lessorPageObj.setMsmeExpDate(input.get("msme_Exp_Date"));  //Implement this as dynamic data as well

        }

        lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\sample-pan-card-front.jpg");
        e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
        if (e_InvState) {
            lessorPageObj.setE_Inv();
        }
        lessorPageObj.clickSaveButton(1);

        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");

        //Gst Details in Compliance
        lessorPageObj.clickAddButton();
        lessorPageObj.selectGstStatus(input.get("gst_Status"));
        lessorPageObj.setGstNo(input.get("gst_Number"));
        lessorPageObj.selectCountry(input.get("gst_Country"));
        lessorPageObj.selectState(input.get("gst_State"));
        lessorPageObj.uploadGstCerti(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\sample-pan-card-front.jpg");
        lessorPageObj.clickSaveButton(2);

        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();


        //Bank Details Step

        lessorPageObj.clickAddButton();

        lessorPageObj.setIfscCode(input.get("ifsc_Code"));
        lessorPageObj.setBankName(input.get("bank_Name"));
        lessorPageObj.setBranchName(input.get("bank_Branch"));
        lessorPageObj.selectBankAccType(input.get("bank_Account_Type"));
        lessorPageObj.setAccHolderName(input.get("account_Holder_Name"));
        lessorPageObj.setAccNum(input.get("account_Number"));
        lessorPageObj.uploadCheque(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\sample-pan-card-front.jpg");
        lessorPageObj.selectPayout(input.get("payout"));
        lessorPageObj.clickSaveButton(1);

        String[] expect_Msgs = {"Account Holder Name not valid"};
        List<String> msges = lessorPageObj.getBankFieldErrMsg();

        for (String actual_Msg : msges) {
            soft_Assert.assertEquals(actual_Msg, expect_Msgs[msges.indexOf(actual_Msg)], "Bank Details Field Error Message Values are not Equal");

        }
        lessorPageObj.clearAccHolderName();
        lessorPageObj.setAccHolderName("Test Name");
        lessorPageObj.clickSaveButton(1);

        if (input.get("account_Number").equals("^[a-zA-Z0-9]+$")) {
            soft_Assert.assertEquals(lessorPageObj.getToastMessage("Opps something went wrong.\n" +
                    "account_no : Only alphanumeric values are allowed.").get(0), "Opps something went wrong.\n" +
                    "account_no : Only alphanumeric values are allowed.", "Contact Details Account Number Field's Toast Message Values are not Equal");
        } else {
            soft_Assert.assertEquals(lessorPageObj.getToastMessage("Opps something went wrong.\n" +
                    "ifsc_code : Enter valid IFSC code.").get(0), "Opps something went wrong.\n" +
                    "ifsc_code : Enter valid IFSC code.", "Bank Details IFSC Field's Toast Message Values are not Equal");
        }

        //  soft_Assert.assertEquals(lessorPageObj.getBankAccErrMsg(), "Bank Account No Required");
        soft_Assert.assertAll();

    }

}
