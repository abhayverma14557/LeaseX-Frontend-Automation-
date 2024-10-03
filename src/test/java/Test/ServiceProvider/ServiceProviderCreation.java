package Test.ServiceProvider;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import PageObjects.ServiceProviderPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceProviderCreation extends BaseClass {
    String msme_Option;
    boolean e_InvState;

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void createServiceProvider(HashMap<String, String> input, ITestContext context) throws ParseException, InterruptedException {
        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("testName", input.get("test_Name"));
        //------------------------------Application Login--------------------------------------------------------------

        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));
        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //------------------------------Portal Selection--------------------------------------------------------------

        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //------------------------------Navigating to the Store Page--------------------------------------------------------
        ServiceProviderPage serviceProviderPageObj = (ServiceProviderPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));
        serviceProviderPageObj.clickAddBtn();

        //------------------------------Basic Tab Handling Methods--------------------------------------------------------
        serviceProviderPageObj.setServiceProviderName(input.get("service_Provider_Name") != null ? input.get("service_Provider_Name") : "");
        serviceProviderPageObj.setServiceProviderEmail(input.get("service_Provider_Email") != null ? input.get("service_Provider_Email") : "");

        if (input.get("service_Provider_Type") != null) {
            serviceProviderPageObj.selectServiceProviderType(input.get("service_Provider_Type"));
            if (input.get("service_Provider_Type").equals("Public Company") || input.get("service_Provider_Type").equals("Private Company")) {
                serviceProviderPageObj.setCINNumber(input.get("cin_Number") != null ? input.get("cin_Number") : "");
            }
        }
        serviceProviderPageObj.setERPCode(input.get("erp_Code") != null ? input.get("erp_Code") : "");

        serviceProviderPageObj.clickNextButton();

        List<String> expected_Basic_Msg = new ArrayList<String>();
        expected_Basic_Msg.add("Service Provider name Required");
        expected_Basic_Msg.add("Service Provider email id Required");
        expected_Basic_Msg.add("");
        expected_Basic_Msg.add("service_provider Type Required");
        List<String> actual_Basic_Msg = serviceProviderPageObj.getBasicFieldsErrMsg();

        if (!actual_Basic_Msg.isEmpty()) {
            soft_Assert.assertTrue(expected_Basic_Msg.containsAll(actual_Basic_Msg), "Basic Fields Error Messages Assertion Failed");
        }


        //----------------------------------------Address Tab Handling Methods-------------------------------------------------------

        if (serviceProviderPageObj.checktheStatusofTab("Address")) {
            serviceProviderPageObj.clickAddBtn();
            if (input.get("address_Type") != null) {
                serviceProviderPageObj.selectAddressType(input.get("address_Type"));
            }
            serviceProviderPageObj.setAddressLine1(input.get("address_Line1") != null ? input.get("address_Line1") : "");
            serviceProviderPageObj.setAddressLine2(input.get("address_Line2") != null ? input.get("address_Line2") : "");


            if (input.get("country") != null) {
                serviceProviderPageObj.selectCountry(input.get("country"));
            }
            if (input.get("state") != null) {
                serviceProviderPageObj.selectState(input.get("state"));
            }
            if (input.get("city") != null) {
                serviceProviderPageObj.selectCity(input.get("city"));
            }
            serviceProviderPageObj.setPincode(input.get("pincode") != null ? input.get("pincode") : "");
            serviceProviderPageObj.clickSaveButton(1);


            List<String> expected_Adrs_Msg = new ArrayList<String>();
            expected_Adrs_Msg.add("Address Type Required");
            expected_Adrs_Msg.add("Address Line 1 Required");
            expected_Adrs_Msg.add("Country Required");
            expected_Adrs_Msg.add("State Required");
            expected_Adrs_Msg.add("City Required");
            expected_Adrs_Msg.add("Pin Code Required");
            List<String> actual_Adrs_Msg = serviceProviderPageObj.getFieldsErrMsg();

            if (!actual_Adrs_Msg.isEmpty()) {
                soft_Assert.assertTrue(expected_Adrs_Msg.containsAll(actual_Adrs_Msg), "Address Fields Error Messages Assertion Failed");
            } else {
                soft_Assert.assertEquals(serviceProviderPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message
                serviceProviderPageObj.clickNextButton();

            }


        }

        //----------------------------------------Contact Person Tab Handling Methods-------------------------------------------------------
        if (serviceProviderPageObj.checktheStatusofTab("Contact Person")) {
            serviceProviderPageObj.clickAddBtn();
            serviceProviderPageObj.setContPersnName(input.get("contact_Person_Name") != null ? input.get("contact_Person_Name") : "");
            serviceProviderPageObj.setMobNum(input.get("mobile_Number") != null ? input.get("mobile_Number") : "");
            serviceProviderPageObj.setEmailAdd(input.get("contact_Email") != null ? input.get("contact_Email") : "");
            if (Boolean.parseBoolean(input.get("contact_Person_Type"))) {
                serviceProviderPageObj.setPrimaryContPersn();
            }
            serviceProviderPageObj.clickSaveButton(1);

            List<String> expected_Contact_Msg = new ArrayList<String>();
            expected_Contact_Msg.add("Contact Person Name Required");
            expected_Contact_Msg.add("Mobile Number Required");
            expected_Contact_Msg.add("Email Address Required");
            List<String> actual_Contact_Msg = serviceProviderPageObj.getFieldsErrMsg();

            if (!actual_Contact_Msg.isEmpty()) {
                soft_Assert.assertTrue(expected_Contact_Msg.containsAll(actual_Contact_Msg), "Contact Fields Error Messages Assertion Failed");
            } else {
                soft_Assert.assertEquals(serviceProviderPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal"); //Assertion For Successfull Contact save verifying from the toast message
                serviceProviderPageObj.clickNextButton();
            }


        }

        //----------------------------------------Compliance Person Tab Handling Methods-------------------------------------------------------

        if (serviceProviderPageObj.checktheStatusofTab("Compliance")) {
            serviceProviderPageObj.setPanNo(input.get("pan_Number") != null ? input.get("pan_Number") : "");

            if (input.get("pan_File_Path") != null) {
                serviceProviderPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));
            }
            if (input.get("msme_Option") != null) {
                serviceProviderPageObj.selectMsmeReg(input.get("msme_Option"));

                msme_Option = input.get("msme_Option");
                if (!msme_Option.equals(" Not Available ")) {

                    serviceProviderPageObj.setMsmeRegNo(input.get("msme_Registration_Number") != null ? input.get("msme_Registration_Number") : "");
                    if (input.get("msme_Exp_Date") != null) {
                        serviceProviderPageObj.setMsmeExpDate(input.get("msme_Exp_Date"));  //Implement this as dynamic data as well
                    }

                    if (input.get("msme_File_Path") != null) {
                        serviceProviderPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
                    }
                }
            }

            e_InvState = Boolean.parseBoolean(input.get("e_Invoice"));
            if (e_InvState) {
                serviceProviderPageObj.setE_Inv();
            }
            serviceProviderPageObj.clickSaveButton(1);

            List<String> expected_Comp_Msg = new ArrayList<String>();
            expected_Comp_Msg.add("PAN Number Required");
            expected_Comp_Msg.add("MSME Registration Required");
            expected_Comp_Msg.add("MSME Registration Number Required");
            expected_Comp_Msg.add("PAN Number mf_frm_val_regx_err");
            List<String> actual_Comp_Msg = serviceProviderPageObj.getFieldsErrMsg();

            if (!actual_Comp_Msg.isEmpty()) {
                soft_Assert.assertEquals(serviceProviderPageObj.getToastMessage("Please fill required details").get(0), "", "Compliance Details Error Toast Message Values are not Equal");
                soft_Assert.assertTrue(expected_Comp_Msg.containsAll(actual_Comp_Msg), "Compliance Fields Error Messages Assertion Failed");
            } else {
                soft_Assert.assertEquals(serviceProviderPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");
            }
            //----------------------------------------Gst Details Handling Methods-------------------------------------------------------
            if (serviceProviderPageObj.addButtonStatus()) {
                serviceProviderPageObj.clickAddBtn();
                if (input.get("gst_Status") != null) {
                    serviceProviderPageObj.selectGstStatus(input.get("gst_Status"));
                }
                serviceProviderPageObj.setGstNo(input.get("gst_Number") != null ? input.get("gst_Number") : "");
                if (input.get("gst_Country") != null) {
                    serviceProviderPageObj.selectCountry(input.get("gst_Country"));
                }
                if (input.get("gst_State") != null) {
                    serviceProviderPageObj.selectState(input.get("gst_State"));
                }
                if (input.get("gst_File_Path") != null) {
                    serviceProviderPageObj.uploadGstCerti(System.getProperty("user.dir") + input.get("gst_File_Path"));
                }

                serviceProviderPageObj.clickSaveButton(2);


                List<String> expected_GST_Msg = new ArrayList<String>();
                expected_GST_Msg.add("GST Status Required");
                expected_GST_Msg.add("GST Number Required");
                expected_GST_Msg.add("Country Required");
                expected_GST_Msg.add("State Required");
                expected_GST_Msg.add("GST Number mf_frm_val_regx_err");
                List<String> actual_GST_Msg = serviceProviderPageObj.getGstFieldErr();

                if (!actual_GST_Msg.isEmpty()) {
                    soft_Assert.assertTrue(expected_GST_Msg.containsAll(actual_GST_Msg), "GST Fields Error Messages Assertion Failed");
                } else {
                    soft_Assert.assertEquals(serviceProviderPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");
                    serviceProviderPageObj.clickNextButton();
                }

            }
        }
        //----------------------------------------Bank Details Tab Handling Methods-------------------------------------------------------
        if (serviceProviderPageObj.checktheStatusofTab("Bank")) {
            serviceProviderPageObj.clickAddBtn();
            serviceProviderPageObj.setIfscCode(input.get("ifsc_Code") != null ? input.get("ifsc_Code") : "");
            serviceProviderPageObj.setBankName(input.get("bank_Name") != null ? input.get("bank_Name") : "");
            serviceProviderPageObj.setBranchName(input.get("bank_Branch") != null ? input.get("bank_Branch") : "");
            if (input.get("bank_Account_Type") != null) {
                serviceProviderPageObj.selectBankAccType(input.get("bank_Account_Type"));
            }
            serviceProviderPageObj.setAccHolderName(input.get("account_Holder_Name") != null ? input.get("account_Holder_Name") : "");
            serviceProviderPageObj.setAccNum(input.get("account_Number") != null ? input.get("account_Number") : "");
            if (input.get("cheque_File_Path") != null) {
                serviceProviderPageObj.uploadCheque(System.getProperty("user.dir") + input.get("cheque_File_Path"));
            }
            if (input.get("payout") != null) {
                serviceProviderPageObj.selectPayout(input.get("payout"));
            }

            serviceProviderPageObj.clickSaveButton(1);

            List<String> actual_Bank_Msg = serviceProviderPageObj.getFieldsErrMsg();

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
                expected_Bank_Msg.add("Account Holder Name mf_frm_val_regx_err");
                soft_Assert.assertTrue(expected_Bank_Msg.containsAll(actual_Bank_Msg), "Bank Fields Error Messages Assertion Failed");
            }
            else {
                serviceProviderPageObj.clickNextButton();
            }
        }
        //----------------------------------------Sub Code Mapping Tab Handling Methods-------------------------------------------------------
        if (serviceProviderPageObj.checktheStatusofTab("Sub Code Mapping")) {
            serviceProviderPageObj.clickAddBtn();
            if (input.get("gst_Number") != null) {
                serviceProviderPageObj.selectSiteGst(input.get("gst_Number"));
            }
//             serviceProviderPageObj.selectSiteAddress(input.get("site_Address"));

//            serviceProviderPageObj.selectSiteAddress("Test Adddress 1 , Ramesh Nagar,Bihar,India");

            serviceProviderPageObj.setServiceProviderSiteName(input.get("service_Provider_Site_Name") != null ? input.get("service_Provider_Site_Name") : "");
            serviceProviderPageObj.setServiceProviderSiteID(input.get("service_Provider_Site_Id") != null ? input.get("service_Provider_Site_Id") : "");

            if (input.get("account_Holder_Name") != null) {
                serviceProviderPageObj.selectSiteBank(input.get("account_Holder_Name"));
            }
            if (input.get("contact_Person_Name") != null) {
                serviceProviderPageObj.selectSiteContPersn(input.get("contact_Person_Name"));
            }
            if (input.get("site_Invoice_Terms") != null) {
                serviceProviderPageObj.selectSiteInvoiceTerms(input.get("site_Invoice_Terms"));
            }
            serviceProviderPageObj.clickSaveButton(1);


            List<String> actual_SubCode_Msg = serviceProviderPageObj.getFieldsErrMsg();

            if (!actual_SubCode_Msg.isEmpty()) {
                List<String> expected_SubCode_Msg = new ArrayList<String>();
                expected_SubCode_Msg.add("Site Address Required");
                expected_SubCode_Msg.add("Service Provider Site Name Required");
                expected_SubCode_Msg.add("Service Provider Site Code/ID Required");
                expected_SubCode_Msg.add("Site Bank Required");
                expected_SubCode_Msg.add("Site Contact Person Required");
                expected_SubCode_Msg.add("Bank Key (IFSC Code) mf_frm_val_regx_err");
                expected_SubCode_Msg.add("Bank Account No mf_frm_val_regx_err");
                expected_SubCode_Msg.add("Branch Name mf_frm_val_regx_err");
                expected_SubCode_Msg.add("Account Holder Name mf_frm_val_regx_err");
                soft_Assert.assertTrue(expected_SubCode_Msg.containsAll(actual_SubCode_Msg), "SubCode Fields Error Messages Assertion Failed");
            }

            serviceProviderPageObj.clickSubmitBtn();


            soft_Assert.assertAll();

        }
    }
}
