package Test.LesseeRegistration;

import PageObjects.DashboardPage;
import PageObjects.LesseePage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LesseeRegistrationFlow extends BaseClass {
    boolean e_InvState;
    String msme_Option;

    //Test Case ID in Excel= Lessee-REG001
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void lesseeRegistrationFlow(HashMap<String, String> input, ITestContext context) throws InterruptedException, IOException, AWTException, ParseException {

        //------------------------------Used to Set The Test Case Type and Test Scenario in Test Report------------------------------------------------------------
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("testName", input.get("test_Name"));

        //----------------------------------------User Login Flow Automation------------------------------------
        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));

        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //------------------------------------------Portal Selection---------------------------------------------------------------

        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //-------------------------Navigating to the Lessor Page and Registeration Button Click------------------------


        LesseePage lesseePageObj = (LesseePage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));
        lesseePageObj.clickRegistrationBtn();


        //-------------------------------------Lessee Basic Tab Handling----------------------------------------------------

        lesseePageObj.setLessorName(input.get("lessee_Name") != null ? input.get("lessee_Name") : "");
        lesseePageObj.setLessorEmail(input.get("lessee_Email") != null ? input.get("lessee_Email") : "");

        lesseePageObj.setErpCode(input.get("erp_Code") != null ? input.get("erp_Code") : "");
        if (input.get("lessee_Type") != null) {
            lesseePageObj.selectLessorType(input.get("lessee_Type"));

            String lessee_type = input.get("lessee_Type");

            if (lessee_type.equals("Private Company") || lessee_type.equals("Public Company")) {
                lesseePageObj.setCINNumber(input.get("cin_Number") != null ? input.get("cin_Number") : "");
            }
        }
        lesseePageObj.clickNextButton();


        List<String> actual_Basic_Msg = lesseePageObj.getBasicFieldErrorMessage();

        if (!actual_Basic_Msg.isEmpty()) {
            List<String> expected_Basic_Msgs = new ArrayList<>();
            expected_Basic_Msgs.add("Name Required");
            expected_Basic_Msgs.add("Email ID Required");
            expected_Basic_Msgs.add("Lessee Type Required");
            expected_Basic_Msgs.add("Email ID not valid");
            expected_Basic_Msgs.add("Name Only alphabets allowed");

            soft_Assert.assertTrue(expected_Basic_Msgs.containsAll(actual_Basic_Msg), "Basic Fields Error Messages Assertion Failed");


        }

        //-------------------------------------Lessee Address Tab Handling----------------------------------------------------

        if (lesseePageObj.checktheStatusofTab("Address")) {
            lesseePageObj.clickAddButton();
            if (input.get("address_Type") != null) {
                lesseePageObj.selectAddressType(input.get("address_Type"));
            }
            lesseePageObj.setAddressLine1(input.get("address_Line1") != null ? input.get("address_Line1") : "");
            lesseePageObj.setAddressLine2(input.get("address_Line2") != null ? input.get("address_Line2") : "");
            if (input.get("country") != null) {
                lesseePageObj.selectCountry(input.get("country"));
            }
            if (input.get("state") != null) {
                lesseePageObj.selectState(input.get("state"));
            }
            if (input.get("city") != null) {
                lesseePageObj.selectCity(input.get("city"));
            }
            lesseePageObj.setPincode(input.get("pincode") != null ? input.get("pincode") : "");
            lesseePageObj.clickSaveButton(1);

            List<String> actual_Adrs_Msg = lesseePageObj.getFieldsErrMsg();

            if (!actual_Adrs_Msg.isEmpty()) {
                List<String> expected_Adrs_Msg = new ArrayList<>();
                expected_Adrs_Msg.add("Address Type Required");
                expected_Adrs_Msg.add("Address Line 1 Required");
                expected_Adrs_Msg.add("Country Required");
                expected_Adrs_Msg.add("State Required");
                expected_Adrs_Msg.add("City Required");
                expected_Adrs_Msg.add("Pin Code Required");

                soft_Assert.assertTrue(expected_Adrs_Msg.containsAll(actual_Adrs_Msg), "Basic Fields Error Messages Assertion Failed");

            } else {

                //For Data Update Message changes to the "Data update successfully"
                soft_Assert.assertEquals(lesseePageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message


                lesseePageObj.clickNextButton();
            }
        }
        //Add Validation for the Listing

        //-------------------------------------Lessee Contact Person Tab Handling----------------------------------------------------
        if (lesseePageObj.checktheStatusofTab("Contact Person")) {

            lesseePageObj.clickAddButton();
            lesseePageObj.setContPersnName(input.get("contact_Person_Name") != null ? input.get("contact_Person_Name") : "");
            lesseePageObj.setMobNum(input.get("mobile_Number") != null ? input.get("mobile_Number") : "");
            lesseePageObj.setEmailAdd(input.get("contact_Email") != null ? input.get("contact_Email") : "");
            lesseePageObj.setPrimaryContPersn();
            lesseePageObj.clickSaveButton(1);

            List<String> expected_Contact_Msg = new ArrayList<String>();
            expected_Contact_Msg.add("Contact Person Name Required");
            expected_Contact_Msg.add("Mobile Number Required");
            expected_Contact_Msg.add("Email Address Required");
            List<String> actual_Contact_Msg = lesseePageObj.getFieldsErrMsg();

            if (!actual_Contact_Msg.isEmpty()) {
                soft_Assert.assertTrue(expected_Contact_Msg.containsAll(actual_Contact_Msg), "Contact Fields Error Messages Assertion Failed");
            } else {

                soft_Assert.assertEquals(lesseePageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");
                lesseePageObj.clickNextButton();
            }

        }
        //-------------------------------------Lessee Compliance Tab Handling----------------------------------------------------

        if (lesseePageObj.checktheStatusofTab("Compliance")) {
            lesseePageObj.setPanNo(input.get("pan_Number") != null ? input.get("pan_Number") : "");
            if (input.get("pan_File_Path") != null) {
                lesseePageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));
            }

            if (input.get("msme_Option") != null) {
                lesseePageObj.selectMsmeReg(input.get("msme_Option"));
            }

            msme_Option = input.get("msme_Option");
            if (!msme_Option.equals(" Not Available ")) {
                lesseePageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
                if (input.get("msme_Exp_Date") != null) {
                    lesseePageObj.setMsmeExpDate(input.get("msme_Exp_Date"));  //Implement this as dynamic data as well
                }
            }
            if (input.get("msme_File_Path") != null) {
                lesseePageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
            }
            e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
            if (e_InvState) {
                lesseePageObj.setE_Inv();
            }
            lesseePageObj.clickSaveButton(1);


            List<String> expected_Comp_Msg = new ArrayList<String>();
            expected_Comp_Msg.add("PAN Number Required");
            expected_Comp_Msg.add("MSME Registration Required");
            expected_Comp_Msg.add("MSME Registration Number Required");
            expected_Comp_Msg.add("PAN Number not valid");
            List<String> actual_Comp_Msg = lesseePageObj.getFieldsErrMsg();

            if (!actual_Comp_Msg.isEmpty()) {
                soft_Assert.assertEquals(lesseePageObj.getToastMessage("Please fill required details").get(0), "", "Compliance Details Error Toast Message Values are not Equal");
                soft_Assert.assertTrue(expected_Comp_Msg.containsAll(actual_Comp_Msg), "Compliance Fields Error Messages Assertion Failed");
            } else {
                soft_Assert.assertEquals(lesseePageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");

            }

            //-------------------------------------Lessee Compliance Gst Form Handling-------------------------------------------------------
            if(actual_Comp_Msg.isEmpty()) {
                lesseePageObj.clickAddButton();
                if (input.get("gst_Status") != null) {
                    lesseePageObj.selectGstStatus(input.get("gst_Status"));
                }

                lesseePageObj.setGstNo(input.get("gst_Number") != null ? input.get("gst_Number") : "");

                if (input.get("gst_Country") != null) {
                    lesseePageObj.selectCountry(input.get("gst_Country"));
                }
                if (input.get("gst_State") != null) {
                    lesseePageObj.selectState(input.get("gst_State"));
                }
                if (input.get("gst_File_Path") != null) {
                    lesseePageObj.uploadGstCerti(System.getProperty("user.dir") + input.get("gst_File_Path"));
                }
                lesseePageObj.clickSaveButton(2);

                List<String> expected_GST_Msg = new ArrayList<String>();
                expected_GST_Msg.add("GST Status Required");
                expected_GST_Msg.add("GST Number Required");
                expected_GST_Msg.add("Country Required");
                expected_GST_Msg.add("State Required");
                expected_GST_Msg.add("GST Number not valid");
                List<String> actual_GST_Msg = lesseePageObj.getGstFieldErr();

                if (!actual_GST_Msg.isEmpty()) {
                    soft_Assert.assertTrue(expected_GST_Msg.containsAll(actual_GST_Msg), "GST Fields Error Messages Assertion Failed");
                } else {
                    soft_Assert.assertEquals(lesseePageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");
                    lesseePageObj.clickNextButton();

                }
            }
        }

        //-------------------------------------Lessee Bank Details Tab Handling----------------------------------------------------

        if (lesseePageObj.checktheStatusofTab("Bank")) {
            lesseePageObj.clickAddButton();

            lesseePageObj.setIfscCode(input.get("ifsc_Code") != null ? input.get("ifsc_Code") : "");
            lesseePageObj.setBankName(input.get("bank_Name") != null ? input.get("bank_Name") : "");
            lesseePageObj.setBranchName(input.get("bank_Branch") != null ? input.get("bank_Branch") : "");

            if (input.get("bank_Account_Type") != null) {
                lesseePageObj.selectBankAccType(input.get("bank_Account_Type"));
            }
            lesseePageObj.setAccHolderName(input.get("account_Holder_Name") != null ? input.get("account_Holder_Name") : "");
            lesseePageObj.setAccNum(input.get("account_Number") != null ? input.get("account_Number") : "");


            if (input.get("cheque_File_Path") != null) {
                lesseePageObj.uploadCheque(System.getProperty("user.dir") + input.get("cheque_File_Path"));
            }
            if (input.get("payout") != null) {
                lesseePageObj.selectPayout(input.get("payout"));
            }
            lesseePageObj.clickSaveButton(1);

            //  Assert.assertEquals(vendorPageObj.getToastMessage(), "Data save successfully");

            List<String> actual_Bank_Msg = lesseePageObj.getFieldsErrMsg();

            if (!actual_Bank_Msg.isEmpty()) {
                List<String> expected_Bank_Msg = new ArrayList<String>();
                expected_Bank_Msg.add("Bank Key (IFSC Code) Required");
                expected_Bank_Msg.add("Bank Name Required");
                expected_Bank_Msg.add("Account Holder Name Required");
                expected_Bank_Msg.add("Bank Account No Required");
                expected_Bank_Msg.add("Payout Type Required");
                expected_Bank_Msg.add("Bank Key (IFSC Code) mf_frm_val_regx_err");
                expected_Bank_Msg.add("Bank Account No mf_frm_val_regx_err");
                expected_Bank_Msg.add("Branch Name mf_frm_val_regx_err");
                expected_Bank_Msg.add("Account Holder Name not valid");
                soft_Assert.assertTrue(expected_Bank_Msg.containsAll(actual_Bank_Msg), "Bank Fields Error Messages Assertion Failed");
            } else {
                lesseePageObj.clickNextButton();
            }
        }


        //-------------------------------------Lessee TDS Tab Handling----------------------------------------------------
        if (lesseePageObj.checktheStatusofTab("TDS")) {
            lesseePageObj.clickAddTDSBtn();
            if (input.get("TDS_Component") != null) {
                lesseePageObj.selectTDSComponent(input.get("TDS_Component"));
            }
            if (input.get("TDS_Code") != null) {
                lesseePageObj.selectTDSCode(input.get("TDS_Code"));
            }
            lesseePageObj.clickSaveTDSBtn();

            List<String> actual_TDS_Msg = lesseePageObj.getTDSFieldErrMsg();

            if (!actual_TDS_Msg.isEmpty()) {
                List<String> expected_TDS_Msg = new ArrayList<String>();
                expected_TDS_Msg.add(" Component Required");
                expected_TDS_Msg.add(" TDS Code Required");
                soft_Assert.assertTrue(expected_TDS_Msg.containsAll(actual_TDS_Msg), "TDS Fields Error Messages Assertion Failed");
            } else {
                lesseePageObj.clickNextButton();
            }
        }

        //-------------------------------------Lessee Document Tab Handling----------------------------------------------------
        if (lesseePageObj.checktheStatusofTab("Document")) {
            if (input.get("gst_Declaration_File") != null) {

                lesseePageObj.uploadGSTDocument((System.getProperty("user.dir") + input.get("gst_Declaration_File")));
            }
            if (input.get("adhar_Card_File") != null) {

                lesseePageObj.uploadAdharDocument((System.getProperty("user.dir") + input.get("adhar_Card_File")));
            }

//        List <String> actual_File_Name=lesseePageObj.getUploadedFilesName(file_Path);
//        for (String file_Name : actual_File_Name)
//        {
//            System.out.println(file_Path[actual_File_Name.indexOf(file_Name)].split("Resources\\\\")[0]);
//            soft_Assert.assertEquals(file_Name, file_Path[actual_File_Name.indexOf(file_Name)].split("Resources\\\\"));
//        }
            if (input.get("gst_Declaration_File") == null) {
                lesseePageObj.clickNextButton();
                List<String> actual_TDS_Msg = lesseePageObj.getTDSFieldErrMsg();

                if (!actual_TDS_Msg.isEmpty()) {
                    List<String> expected_TDS_Msg = new ArrayList<String>();
                    expected_TDS_Msg.add("*");
                    expected_TDS_Msg.add("File is required");
                    soft_Assert.assertTrue(expected_TDS_Msg.containsAll(actual_TDS_Msg), "Document Fields Error Messages Assertion Failed");
                }

            }

            lesseePageObj.clickNextButton();


        }

        //-------------------------------------Lessee SubCode Map Tab Handling----------------------------------------------------

        if (lesseePageObj.checktheStatusofTab("Sub Code Mapping")) {
            lesseePageObj.clickAddButton();
            if (input.get("gst_Number") != null) {
                lesseePageObj.selectSiteGst(input.get("gst_Number"));
            }

            if (input.get("state") != null) {
                lesseePageObj.selectSiteAddress(input.get("state"));
            }
            lesseePageObj.selectSiteAddress(input.get("address_Line1") + ",Ramesh Nagar," + input.get("state") + "," + input.get("country"));
            lesseePageObj.setLessorSiteName(input.get("lessee_Site_Name") != null ? input.get("lessee_Site_Name") : "");
            lesseePageObj.setLessorSiteID(input.get("lessee_Site_Id") != null ? input.get("lessee_Site_Id") : "");

            if (input.get("account_Holder_Name") != null) {
                lesseePageObj.selectSiteBank(input.get("account_Holder_Name"));   //Should Come from Bank Details But Taking the Contact Person name
            }
            if (input.get("contact_Person_Name") != null) {
                lesseePageObj.selectSiteContPersn(input.get("contact_Person_Name"));  //Coming From Contact Person Details
            }
            lesseePageObj.clickSaveButton(1);

            List<String> actual_SubCode_Msg = lesseePageObj.getFieldsErrMsg();

            if (!actual_SubCode_Msg.isEmpty()) {
                List<String> expected_SubCode_Msg = new ArrayList<String>();
                expected_SubCode_Msg.add("Site Address Required");
                expected_SubCode_Msg.add("Lessee Site Name Required");
                expected_SubCode_Msg.add("Lessee Site Code/ID Required");
                expected_SubCode_Msg.add("Site Bank Required");
                expected_SubCode_Msg.add("Site Contact Person Required");

                soft_Assert.assertTrue(expected_SubCode_Msg.containsAll(actual_SubCode_Msg), "SubCode Fields Error Messages Assertion Failed");
            } else {
                soft_Assert.assertEquals(lesseePageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "SubCode Map Toast Message Values are not Equal");
                lesseePageObj.clickSubmitBtn();


                //-------------------------------------Remarks PopUp Handling----------------------------------------------------

                lesseePageObj.setRemarks(input.get("remarks") != null ? input.get("remarks") : "");
                lesseePageObj.clickRemarksSubmitBtn();


                //-------------------------------------Lessee Registration Verification----------------------------------------------------
                int page_Count = lesseePageObj.totalPages();

                for (int i = 0; i < page_Count; i++) {

                    List<String> vendor_Name_List = lesseePageObj.lessorDetails();

                    for (String vendor_Name : vendor_Name_List) {
                        if (vendor_Name.equalsIgnoreCase(input.get("lessee_Name"))) {
                            soft_Assert.assertTrue(true, "Lessee Details Verification on Dashboard Failed");
                        }
                    }

                    lesseePageObj.clickPageNxtBtn();

                }
            }
        }
        soft_Assert.assertAll();
    }


}

