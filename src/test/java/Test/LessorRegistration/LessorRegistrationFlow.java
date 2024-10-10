package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
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
        lessorPageObj.setLessorName(input.get("lessor_Name") != null ? input.get("lessor_Name") : "");
        lessorPageObj.setLessorEmail(input.get("lessor_Email") != null ? input.get("lessor_Email") : "");

        if (input.get("lessor_Type") != null) {
            lessorPageObj.selectLessorType(input.get("lessor_Type"));
            String lessor_type = input.get("lessor_Type");
            if (lessor_type.equals("Private Company") || lessor_type.equals("Public Company")) {
                lessorPageObj.setCINNumber(input.get("cin_Number"));
            }
        }
        lessorPageObj.setErpCode(input.get("erp_Code") != null ? input.get("erp_Code") : "");
            lessorPageObj.clickNextButton();

            List<String> actual_Basic_Msg = lessorPageObj.getBasicFieldErrorMessage();

            if (!actual_Basic_Msg.isEmpty()) {
                List<String> expected_Basic_Msgs = new ArrayList<>();
                expected_Basic_Msgs.add("Name Required");
                expected_Basic_Msgs.add("Email ID Required");
                expected_Basic_Msgs.add("Lessor Type Required");
                expected_Basic_Msgs.add("Name Only alphabets allowed");
                expected_Basic_Msgs.add("Email ID not valid");


                soft_Assert.assertTrue(expected_Basic_Msgs.containsAll(actual_Basic_Msg), "Basic Fields Error Messages Assertion Failed=" +actual_Basic_Msg);


            }
            //---------------------------------------Address Details Step--------------------------------------------------------
            if (lessorPageObj.checktheStatusofTab("Address")) {
                lessorPageObj.clickAddButton();

                if(input.get("address_Type")!=null) {
                    lessorPageObj.selectAddressType(input.get("address_Type"));
                }

                lessorPageObj.setAddressLine1(input.get("address_Line1")!=null?input.get("address_Line1"):"");
                lessorPageObj.setAddressLine2(input.get("address_Line2")!=null?input.get("address_Line2"):"");
                if(input.get("country")!=null)
                {
                    lessorPageObj.selectCountry(input.get("country"));
                }
                if(input.get("state")!=null) {
                    lessorPageObj.selectState(input.get("state"));
                }
                if(input.get("city")!=null) {
                    lessorPageObj.selectCity(input.get("city"));
                }
                lessorPageObj.setPincode(input.get("pincode")!=null?input.get("pincode"):"");
                lessorPageObj.clickSaveButton(1);


                List<String> actual_Adrs_Msg = lessorPageObj.getFieldsErrMsg();

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
                    soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message

                    lessorPageObj.clickNextButton();
                }
            }
            //Add Validation for the Listing

            //---------------------------------------Contact Person Details Step--------------------------------------------------------
            if (lessorPageObj.checktheStatusofTab("Contact Person")) {
                lessorPageObj.clickAddButton();
                lessorPageObj.setContPersnName(input.get("contact_Person_Name")!=null?input.get("contact_Person_Name"):"");
                lessorPageObj.setMobNum(input.get("mobile_Number")!=null?input.get("mobile_Number"):"");
                lessorPageObj.setEmailAdd(input.get("contact_Email")!=null?input.get("contact_Email"):"");
                lessorPageObj.setPrimaryContPersn();
                lessorPageObj.clickSaveButton(1);

                List<String> expected_Contact_Msg = new ArrayList<String>();
                expected_Contact_Msg.add("Contact Person Name Required");
                expected_Contact_Msg.add("Mobile Number Required");
                expected_Contact_Msg.add("Contact Person Name not valid");
                expected_Contact_Msg.add("Mobile Number not valid");
                expected_Contact_Msg.add("Email Address Required");
                List<String> actual_Contact_Msg = lessorPageObj.getFieldsErrMsg();

                if (!actual_Contact_Msg.isEmpty()) {
                    soft_Assert.assertTrue(expected_Contact_Msg.containsAll(actual_Contact_Msg), "Contact Fields Error Messages Assertion Failed");
                } else {

                    soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");
                    lessorPageObj.clickNextButton();
                }


            }

            //---------------------------------------Compliance Details Step--------------------------------------------------------
            if (lessorPageObj.checktheStatusofTab("Compliance")) {
                lessorPageObj.setPanNo(input.get("pan_Number") != null ? input.get("pan_Number") : "");
                if (input.get("pan_File_Path") != null) {
                    lessorPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));
                }
                if (input.get("msme_Option") != null) {
                    lessorPageObj.selectMsmeReg(input.get("msme_Option"));

                    msme_Option = input.get("msme_Option");
                    if (!msme_Option.equals(" Not Available ")) {
                        lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));
                        lessorPageObj.setMsmeExpDate("12-12-2026");  //Implement this as dynamic data as well
                        lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
                    }
                }

                e_InvState = Boolean.parseBoolean(input.get("e_Invoice") != null ? input.get("e_Invoice") : "false");
                if (e_InvState) {
                    lessorPageObj.setE_Inv();
                }
                lessorPageObj.clickSaveButton(1);

                List<String> expected_Comp_Msg = new ArrayList<String>();
                expected_Comp_Msg.add("PAN Number Required");
                expected_Comp_Msg.add("MSME Registration Required");
                expected_Comp_Msg.add("PAN Copy Required");
                expected_Comp_Msg.add("MSME Registration Number Required");
                expected_Comp_Msg.add("MSME Registration Number minlength_12");
                expected_Comp_Msg.add("PAN Number not valid");
                List<String> actual_Comp_Msg = lessorPageObj.getFieldsErrMsg();

                if (!actual_Comp_Msg.isEmpty()) {
                    soft_Assert.assertEquals(lessorPageObj.getToastMessage("Please fill required details").get(0), "Please fill required details", "Compliance Details Error Toast Message Values are not Equal");
                    soft_Assert.assertTrue(expected_Comp_Msg.containsAll(actual_Comp_Msg), "Compliance Fields Error Messages Assertion Failed");
                } else {
                    soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Compliance Details Toast Message Values are not Equal");

                }


                //---------------------------------------Gst Details in Compliance----------------------------------------------------------------
                if (actual_Comp_Msg.isEmpty()) {
                    lessorPageObj.clickAddButton();
                    if (input.get("gst_Status") != null) {
                        lessorPageObj.selectGstStatus(input.get("gst_Status"));
                    }
                    lessorPageObj.setGstNo(input.get("gst_Number") != null ? input.get("gst_Number") : "");
                    if (input.get("gst_Country") != null) {
                        lessorPageObj.selectCountry(input.get("gst_Country"));
                    }
                    if (input.get("gst_State") != null) {
                        lessorPageObj.selectState(input.get("gst_State"));
                    }
                    if (input.get("gst_File_Path") != null) {
                        lessorPageObj.uploadGstCerti(System.getProperty("user.dir") + input.get("gst_File_Path"));
                    }
                    lessorPageObj.clickSaveButton(2);


                    List<String> actual_GST_Msg = lessorPageObj.getGstFieldErr();

                    if (!actual_GST_Msg.isEmpty()) {
                        List<String> expected_GST_Msg = new ArrayList<String>();
                        expected_GST_Msg.add("Country Required");
                        expected_GST_Msg.add("State Required");
                        expected_GST_Msg.add("GST Number not valid");
                        soft_Assert.assertTrue(expected_GST_Msg.containsAll(actual_GST_Msg), "GST Fields Error Messages Assertion Failed");


                    } else {
                        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "GST Details Toast Message Values are not Equal");
                        lessorPageObj.clickNextButton();

                    }

                }
            }


            //------------------------------------------Bank Details Step--------------------------------------------------------------
            if (lessorPageObj.checktheStatusofTab("Bank")) {
                lessorPageObj.clickAddButton();
                lessorPageObj.setIfscCode(input.get("ifsc_Code")!=null?input.get("ifsc_Code"):"");
                lessorPageObj.setBankName(input.get("bank_Name")!=null?input.get("bank_Name"):"");
                lessorPageObj.setBranchName(input.get("bank_Branch")!=null?input.get("bank_Branch"):"");

                if(input.get("bank_Account_Type")!=null) {
                    lessorPageObj.selectBankAccType(input.get("bank_Account_Type"));
                }
                lessorPageObj.setAccHolderName(input.get("account_Holder_Name")!=null?input.get("account_Holder_Name"):"");
                lessorPageObj.setAccNum(input.get("account_Number")!=null?input.get("account_Number"):"");

                if(input.get("cheque_File_Path")!=null) {
                    lessorPageObj.uploadCheque(System.getProperty("user.dir") + input.get("cheque_File_Path"));
                }
                if(input.get("payout")!=null) {
                    lessorPageObj.selectPayout(input.get("payout"));
                }
                lessorPageObj.clickSaveButton(1);

                List<String> actual_Bank_Msg = lessorPageObj.getFieldsErrMsg();

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
                    lessorPageObj.clickNextButton();
                }

            }
            //------------------------------------------TDS Step Handling----------------------------------------------
            if (lessorPageObj.checktheStatusofTab("TDS")) {
                lessorPageObj.clickAddTDSBtn();
                if(input.get("TDS_Component")!=null)
                {
                    lessorPageObj.selectTDSComponent(input.get("TDS_Component"));
                }
                if(input.get("TDS_Code")!=null) {
                    lessorPageObj.selectTDSCode(input.get("TDS_Code"));
                }
                lessorPageObj.clickSaveTDSBtn();
                List<String> actual_TDS_Msg = lessorPageObj.getTDSFieldErrMsg();

                if (!actual_TDS_Msg.isEmpty()) {
                    List<String> expected_TDS_Msg = new ArrayList<String>();
                    expected_TDS_Msg.add(" Component Required");
                    expected_TDS_Msg.add(" TDS Code Required");
                    soft_Assert.assertTrue(expected_TDS_Msg.containsAll(actual_TDS_Msg), "TDS Fields Error Messages Assertion Failed");
                } else {
                    lessorPageObj.clickNextButton();
                }
            }
            //------------------------------------------Sub Code Mapping Step----------------------------------------------
            if (lessorPageObj.checktheStatusofTab("Sub Code Mapping")) {
                lessorPageObj.clickAddButton();
                if(input.get("gst_Number")!=null) {
                    lessorPageObj.selectSiteGst(input.get("gst_Number"));
                }
                if(input.get("state")!=null) {
                    lessorPageObj.selectSiteAddrs(input.get("address_Line1"));
                }
                lessorPageObj.setLessorSiteName(input.get("lessor_Site_Name")!=null?input.get("lessor_Site_Name"):"");
                lessorPageObj.setLessorSiteID(input.get("lessor_Site_Id")!=null?input.get("lessor_Site_Id"):"");
                if(input.get("account_Holder_Name")!=null) {
                    lessorPageObj.selectSiteBank(input.get("account_Holder_Name"));
                }//Should Come from Bank Details But Taking the Contact Person name
                if(input.get("contact_Person_Name")!=null) {
                    lessorPageObj.selectSiteContPersn(input.get("contact_Person_Name"));
                }//Coming From Contact Person Details
                if(input.get("payment_Terms")!=null) {
                    lessorPageObj.selectPaymentTerms(input.get("payment_Terms"));
                }
                lessorPageObj.clickSaveButton(1);
                List<String> actual_SubCode_Msg = lessorPageObj.getFieldsErrMsg();

                if (!actual_SubCode_Msg.isEmpty()) {
                    List<String> expected_SubCode_Msg = new ArrayList<String>();
                    expected_SubCode_Msg.add("Site Address Required");
                    expected_SubCode_Msg.add("Lessee Site Name Required");
                    expected_SubCode_Msg.add("Lessee Site Code/ID Required");
                    expected_SubCode_Msg.add("Site Bank Required");
                    expected_SubCode_Msg.add("Site Contact Person Required");

                    soft_Assert.assertTrue(expected_SubCode_Msg.containsAll(actual_SubCode_Msg), "SubCode Fields Error Messages Assertion Failed");
                } else {
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
                }
            }
            soft_Assert.assertAll();
        }


    }

