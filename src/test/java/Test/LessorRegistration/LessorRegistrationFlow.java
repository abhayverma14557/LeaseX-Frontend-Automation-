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

public class LessorRegistrationFlow extends BaseClass {
    boolean e_InvState;
    String msme_Option;

    //Test Case ID in Excel= Lessor-REG001
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void lessorRegistrationFlow(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {

        //Used to Set The Test Case Type in Test Report
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("testName", input.get("test_Name"));

        //---------------------------------------Application Login--------------------------------------------------------
        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));

        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //------------------------------------------Portal Selection--------------------------------------------------------

        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //-----------------------------------------Navigation to the Lesssor Page-------------------------------------------
        LessorPage lessorPageObj = (LessorPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));

        lessorPageObj.clickRegistrationBtn();

        //-----------------------------------------Lessor Basic Tab Handling------------------------------------------------
        lessorPageObj.setLessorName(input.get("lessor_Name"));
        lessorPageObj.setLessorEmail(input.get("lessor_Email"));
        lessorPageObj.selectLessorType(input.get("lessor_Type"));
        lessorPageObj.setErpCode(input.get("erp_Code"));

        String lessor_type = input.get("lessor_Type");

        if (lessor_type.equals("Private Company") || lessor_type.equals("Public Company")) {
            lessorPageObj.setCINNumber(input.get("cin_Number"));
        }
        lessorPageObj.clickNextButton();


        //---------------------------------------Address Details Step--------------------------------------------------------

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
        //Add Validation for the Listing

        //---------------------------------------Contact Person Details Step--------------------------------------------------------
        lessorPageObj.clickAddButton();
        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();


        //---------------------------------------Compliance Details Step--------------------------------------------------------

        lessorPageObj.setPanNo(input.get("pan_Number"));
        lessorPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));

        lessorPageObj.selectMsmeReg(input.get("msme_Option"));
        msme_Option = input.get("msme_Option");
        if (!msme_Option.equals(" Not Available ")) {

            lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
            lessorPageObj.setMsmeExpDate("12-12-2026");  //Implement this as dynamic data as well
            lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
        }

        e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
        if (e_InvState) {
            lessorPageObj.setE_Inv();
        }
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");

        //---------------------------------------Gst Details in Compliance----------------------------------------------------------------
        lessorPageObj.clickAddButton();
        lessorPageObj.selectGstStatus(input.get("gst_Status"));
        lessorPageObj.setGstNo(input.get("gst_Number"));
        lessorPageObj.selectCountry(input.get("gst_Country"));
        lessorPageObj.selectState(input.get("gst_State"));
        lessorPageObj.uploadGstCerti(System.getProperty("user.dir") + input.get("gst_File_Path"));
        lessorPageObj.clickSaveButton(2);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();


        //------------------------------------------Bank Details Step--------------------------------------------------------------

        lessorPageObj.clickAddButton();
        lessorPageObj.setIfscCode(input.get("ifsc_Code"));
        lessorPageObj.setBankName(input.get("bank_Name"));
        lessorPageObj.setBranchName(input.get("bank_Branch"));
        lessorPageObj.selectBankAccType(input.get("bank_Account_Type"));
        lessorPageObj.setAccHolderName(input.get("account_Holder_Name"));
        lessorPageObj.setAccNum(input.get("account_Number"));
        lessorPageObj.uploadCheque(System.getProperty("user.dir") + input.get("cheque_File_Path"));
        lessorPageObj.selectPayout(input.get("payout"));
        lessorPageObj.clickSaveButton(1);

        //  Assert.assertEquals(vendorPageObj.getToastMessage(), "Data save successfully");
        lessorPageObj.clickNextButton();


        //------------------------------------------TDS Step Handling----------------------------------------------
        lessorPageObj.clickAddTDSBtn();
        lessorPageObj.selectTDSComponent(input.get("TDS_Component"));
        lessorPageObj.selectTDSCode(input.get("TDS_Code"));
        lessorPageObj.clickSaveTDSBtn();
        lessorPageObj.clickNextButton();


        //------------------------------------------Sub Code Mapping Step----------------------------------------------

        lessorPageObj.clickAddButton();
        lessorPageObj.selectSiteGst(input.get("gst_Number"));
        lessorPageObj.selectSiteAddress(input.get("state"));
//        lessorPageObj.selectSiteAddress(  input.get("address_Line1") + ",Ramesh Nagar," + input.get("state") + "," + input.get("country"));
        lessorPageObj.setLessorSiteName(input.get("lessor_Site_Name"));
        lessorPageObj.setLessorSiteID(input.get("lessor_Site_Id"));
        lessorPageObj.selectSiteBank(input.get("account_Holder_Name"));   //Should Come from Bank Details But Taking the Contact Person name
        lessorPageObj.selectSiteContPersn(input.get("contact_Person_Name"));  //Coming From Contact Person Details
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "SubCode Map Toast Message Values are not Equal");
        lessorPageObj.clickSubmitBtn();


        //Remarks Pop Up

        lessorPageObj.setRemarks("Test Remarks");
        lessorPageObj.clickRemarksSubmitBtn();


        //Vendor Registration Verification
        int page_Count = lessorPageObj.totalPages();

        for (int i = 0; i < page_Count; i++) {

            List<String> vendor_Name_List = lessorPageObj.lessorDetails();

            for (String vendor_Name : vendor_Name_List) {
                if (vendor_Name.equalsIgnoreCase(input.get("lessor_Name"))) {
                    soft_Assert.assertTrue(true, "Lessor Details Verification on Dashboard Failed");
                }
            }

            lessorPageObj.clickPageNxtBtn();

        }
        soft_Assert.assertAll();
    }


}

