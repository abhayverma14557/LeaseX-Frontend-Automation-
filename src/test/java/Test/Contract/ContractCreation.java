package Test.Contract;

import PageObjects.ContractPage;
import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import Test.Login.LeaseXLogin;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContractCreation extends BaseClass {
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void creationofContract(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {

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

        //-------------------------Navigating to the Contract Page and Add Contract Button Click------------------------

        ContractPage contractPageObj = (ContractPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));
        contractPageObj.clickAddContractButton();
        contractPageObj.selectContract(input.get("contract_Type"));
        if (input.get("contract_Type").equalsIgnoreCase("Lease")) {
            contractPageObj.selectContract(input.get("sub_Contract_Type"));
        }

        contractPageObj.clickProceedButton();


        //-------------------------------------Basic Tab Handling----------------------------------------------------

        if (input.get("lessee") != null) {
            contractPageObj.selectLessee(input.get("lessee"));
        }
        contractPageObj.setLeaseNum(input.get("lease_Num") != null ? input.get("lease_Num") : "");

        if ((input.get("country") != null)) {
            contractPageObj.selectCountry(input.get("country"));
        }
        if (input.get("state") != null) {
            contractPageObj.selectState(input.get("state"));
        }
        if (input.get("city") != null) {
            contractPageObj.selectCity(input.get("city"));
        }


        if (input.get("search_Store") != null) {

            contractPageObj.setSearchStore(input.get("search_Store"));
            contractPageObj.selectElementFromSearchStore(input.get("search_Store"));
        }
//        contractPageObj.setLocationCode(input.get("leaseX_Location_Code"));
//        contractPageObj.setLocationName(input.get("leaseX_Location_Name"));
//        contractPageObj.setErpLocationCode(input.get("erp_Location_Code"));
        if (input.get("contract_Nature") != null) {
            contractPageObj.selectContractNature(input.get("contract_Nature"));
        }
//         != null ? input.get("contract_Start_Date") : ""
        String start_Date = input.get("contract_Start_Date");
        String end_Date = input.get("contract_End_Date");
        contractPageObj.setContractStartDate(start_Date);
        contractPageObj.setContractEndDate(end_Date);


//        soft_Assert.assertEquals(contractPageObj.getContractPeriodFromField(), contractPageObj.calculateContractPeriod(start_Date, end_Date), "Contract Period Assertion Failed in Basic Tab");
//        System.out.println( contractPageObj.getContractPeriodFromField());
//        System.out.println( contractPageObj.calculateContractPeriod(start_Date, end_Date));

        if (input.get("lease_Copy_Status") != null) {
            contractPageObj.selectLeaseCopyStatus(input.get("lease_Copy_Status"));
        }
        if (input.get("lease_Copy_Status").equalsIgnoreCase("yes")) {
            if(input.get("lease_Copy_File")!=null) {
                contractPageObj.uploadLeaseCopy(System.getProperty("user.dir") + input.get("lease_Copy_File"));
            }
        }
        String actual_File_Name = contractPageObj.getUploadedFileName();
        String expected_File_Name = input.get("lease_Copy_File").split("Resources\\\\")[1];
        soft_Assert.assertEquals(actual_File_Name, expected_File_Name, "Uploaded File Name Assertion Failed in Basic Tab");

        if (input.get("renewable_Lease_Type") != null) {
            contractPageObj.selectRenewableLeaseType(input.get("renewable_Lease_Type"));
        }
        contractPageObj.setContractDescription(input.get("contract_Description") != null ? input.get("contract_Description") : "");
        contractPageObj.clickNextButton();




        List<String> actual_Basic_Msg = contractPageObj.getBasicFieldsErrMsg();
        if (!actual_Basic_Msg.isEmpty()) {
            List<String> expected_Basic_Msg = new ArrayList<String>();
            expected_Basic_Msg.add("Lease No Required");
            expected_Basic_Msg.add("Country Required");
            expected_Basic_Msg.add("State Required");
            expected_Basic_Msg.add("City Required");
            expected_Basic_Msg.add("Search Store Required");
            expected_Basic_Msg.add("LeaseX Location Code Required");
            expected_Basic_Msg.add("Location Name Required");
            expected_Basic_Msg.add("ERP Location Code Required");
            expected_Basic_Msg.add("Nature of Contract Required");
            expected_Basic_Msg.add("Contract Start Date Required");
            expected_Basic_Msg.add("Contract (Lease) Period (In-Months) Required");
            expected_Basic_Msg.add("Contract End Date Required");
            expected_Basic_Msg.add("Signed Lease copy available Required");
            expected_Basic_Msg.add("Renewable Lease Type Required");
            expected_Basic_Msg.add("Registration Date Required");
            expected_Basic_Msg.add("Possession Date Required");

            soft_Assert.assertTrue(expected_Basic_Msg.containsAll(actual_Basic_Msg), " Basic Fields Error Message are not Correct");


        }

        //-------------------------------------Property Tab Handling----------------------------------------------------

        if(contractPageObj.checktheStatusofTab("Property")) {
            contractPageObj.clickAddPropertyBtn();
            contractPageObj.searchProperty(input.get("search_Property"));
            contractPageObj.selectProperty(input.get("select_Property"));
            contractPageObj.clickAssociateBtn();
            contractPageObj.clickNextButton();

        }
        //-------------------------------------Allocation Tab Handling Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("Allocation")) {
            contractPageObj.clickAddRecordButton();
            if (input.get("allocation_Component") != null) {
                contractPageObj.selectComponent(input.get("allocation_Component"));
            }
            if (input.get("allocation_Property") != null) {
                contractPageObj.selectDropdownProperty(input.get("allocation_Property"));
            }
            if (input.get("allocation_Landlord") != null) {
                contractPageObj.selectLandlord(input.get("allocation_Landlord"));
            }
            contractPageObj.setAllocationPercenttage(input.get("allocation_Percentage") != null ? input.get("allocation_Percentage") : "");
            contractPageObj.clickSaveRecordButton();
            //Error Message Validation Part
            List<String> expected_Allocation_Msg = new ArrayList<>();
            expected_Allocation_Msg.add("COMPONENT required");
            expected_Allocation_Msg.add("PROPERTY required");
            expected_Allocation_Msg.add("LANDLORD/SERVICE PROVIDER required");
            expected_Allocation_Msg.add("ALLOCATION (%) required");


            List<String> actual_Allocation_Msg = contractPageObj.getFieldsErrMsg();
            if (!actual_Allocation_Msg.isEmpty()) {

                soft_Assert.assertTrue(expected_Allocation_Msg.containsAll(actual_Allocation_Msg), "Allocations Fields Error Message are not Correct");

            }
            contractPageObj.clickNextButton();
            if (input.get("allocation_Percentage") != null) {
                if (Integer.parseInt(input.get("allocation_Percentage")) < 100) {
                    soft_Assert.assertEquals(contractPageObj.geSnackBarText(), "");
                }
            }
        }


        //-------------------------------------Deposit Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("Deposit")) {
            contractPageObj.selectSteptoNavigate("Deposit");
            contractPageObj.clickAddRecordButton();
            if (input.get("deposit_Component") != null) {
                contractPageObj.selectComponent(input.get("deposit_Component"));
            }
            if (input.get("deposit_Property") != null) {
                contractPageObj.selectDropdownProperty(input.get("deposit_Property"));
            }
            if (input.get("deposit_Landlord") != null) {
                contractPageObj.selectLandlord(input.get("deposit_Landlord"));
            }

            contractPageObj.setAmount(input.get("amount") != null ? input.get("amount") : "");
            contractPageObj.setReleaseDate(input.get("release_Date") != null ? input.get("release_Date") : "");
            contractPageObj.setPaymentReference(input.get("payment_Reference") != null ? input.get("payment_Reference") : "");
            contractPageObj.setRemark(input.get("deposit_Remarks") != null ? input.get("deposit_Remarks") : "");

            if( input.get("deposit_File")!=null) {
                contractPageObj.uploadFile(System.getProperty("user.dir") + input.get("deposit_File"));
            }
            Thread.sleep(2000);
            contractPageObj.clickSaveRecordButton();

            List<String> expected_Deposit_Msg = new ArrayList<>();
            expected_Deposit_Msg.add("COMPONENT Required");
            expected_Deposit_Msg.add("PROPERTY Required");
            expected_Deposit_Msg.add("LANDLORD/SERVICE Required");
            expected_Deposit_Msg.add("AMOUNT Required");
            expected_Deposit_Msg.add("RELEASE DATE Required");
            expected_Deposit_Msg.add("PAYMENT REF Required");


            List<String> actual_Deposit_Msg = contractPageObj.getDepositFieldErrMsg();
            if (!actual_Deposit_Msg.isEmpty()) {

                soft_Assert.assertTrue(expected_Deposit_Msg.containsAll(actual_Deposit_Msg), "Deposit Fields Error Message are not Correct");

            }
            contractPageObj.clickNextButton();
        }

        //-------------------------------------Other Payout Tab Handling Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("Other Payout")) {
            contractPageObj.clickAddRecordButton();
            if (input.get("other_Component") != null) {
                contractPageObj.selectComponent(input.get("other_Component"));
            }


            contractPageObj.setTotalAmount(input.get("total_Amount") != null ? input.get("total_Amount") : "");
            contractPageObj.setSharePercentage(input.get("share_Percentage") != null ? input.get("share_Percentage") : "");


            if (input.get("payout_Frequency") != null) {
                contractPageObj.selectFrequency(input.get("payout_Frequency"));
            }
            if (input.get("whom_To_Pay") != null) {
                contractPageObj.selectWhomToPay(input.get("whom_To_Pay"));
            }
            contractPageObj.clickSaveRecordButton();


            List<String> expected_Otrs_Payout_Msg = new ArrayList<>();
            expected_Otrs_Payout_Msg.add("COMPONENT Required");
            expected_Otrs_Payout_Msg.add("TOTAL AMOUNT Required");
            expected_Otrs_Payout_Msg.add("SHARE Required");
            expected_Otrs_Payout_Msg.add("AMOUNT(LEASE) Required");
            expected_Otrs_Payout_Msg.add("FREQUENCY Required");
            expected_Otrs_Payout_Msg.add("WHOM TO PAY Required");


            List<String> actual_Otrs_Payout_Msg = contractPageObj.getFieldsErrMsg();
            if (!actual_Otrs_Payout_Msg.isEmpty()) {

                soft_Assert.assertTrue(expected_Otrs_Payout_Msg.containsAll(actual_Otrs_Payout_Msg), "Others Payout Fields Error Message are not Correct");

            } else {
                contractPageObj.clickNextButton();
            }
        }
        //-------------------------------------Terms and Conditions Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("Terms and Conditions")) {
            contractPageObj.setLesseeNoticePeriod(input.get("lessee_Notice_Period") != null ? input.get("lessee_Notice_Period") : "");
            contractPageObj.setLessorNoticePeriod(input.get("lessor_Notice_Period") != null ? input.get("lessor_Notice_Period") : "");
            contractPageObj.setLockingStartDate(input.get("lock_Start_Date") != null ? input.get("lock_Start_Date") : "");
            contractPageObj.setLockingEndDate(input.get("lock_End_Date") != null ? input.get("lock_End_Date") : "");
            contractPageObj.setLockingPeriod(input.get("lock_Period") != null ? input.get("lock_Period") : "");
            contractPageObj.setSalesCertDate(input.get("sales_Cert_Date") != null ? input.get("sales_Cert_Date") : "");
            contractPageObj.setAgreementPeriod(input.get("agreement_Period") != null ? input.get("agreement_Period") : "");


            if (input.get("audit_Sales_Frequency") != null) {
                contractPageObj.selectAuditSalesFreq(input.get("audit_Sales_Frequency"));
            }
            if (input.get("sales_Certificate") != null) {
                contractPageObj.selectSalesCerificate(input.get("sales_Certificate"));
            }
            if (input.get("sales_Certi_Frequency") != null) {
                contractPageObj.selectSalesCertificateFreq(input.get("sales_Certi_Frequency"));
            }
            if (input.get("payment_Cycle") != null) {
                contractPageObj.selectPaymentCycle(input.get("payment_Cycle"));
            }
            contractPageObj.clickAddRecordButton();
            if (input.get("terms_Component") != null) {
                contractPageObj.selectComponent(input.get("terms_Component"));
            }

            contractPageObj.setComponentStartDate(input.get("component_Start_Date") != null ? input.get("component_Start_Date") : "");
            contractPageObj.setComponentEndDate(input.get("component_End_Date") != null ? input.get("component_End_Date") : "");
            contractPageObj.setRemark(input.get("terms_Remark") != null ? input.get("terms_Remark") : "");
            contractPageObj.clickSaveRecordButton();


            List<String> actual_Terms_Msg = contractPageObj.getTermsandConditionsFieldMsg();

            if (!actual_Terms_Msg.isEmpty()) {
                List<String> expected_Terms_Msg = new ArrayList<>();
                expected_Terms_Msg.add("Rent Commencement Date Required");
                expected_Terms_Msg.add("Notice Period - Lessee (in Days) Required");
                expected_Terms_Msg.add("Notice Period-Lessor (In Days) Required");
                expected_Terms_Msg.add("COMPONENT Required");
                expected_Terms_Msg.add("START DATE Required");
                expected_Terms_Msg.add("END DATE Required");
                soft_Assert.assertTrue(expected_Terms_Msg.containsAll(actual_Terms_Msg), "Terms and Condition Fields Error Message are not Correct");

            } else {
                contractPageObj.clickNextButton();
            }
        }

        //-------------------------------------INDAS 116 Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("INDAS 116")) {
            contractPageObj.setBorrowingRate(input.get("borrow_Rate") != null ? input.get("borrow_Rate") : "");
            contractPageObj.clickNextButton();
            List<String> expected_Indas_Msg = new ArrayList<>();
            expected_Indas_Msg.add("Borrowing Rate(%) is required");
            List<String> actual_Indas_Msg = contractPageObj.getIndasFieldErrMsg();
            if (!actual_Indas_Msg.isEmpty()) {

                soft_Assert.assertTrue(expected_Indas_Msg.containsAll(actual_Indas_Msg), "Terms and Condition Fields Error Message are not Correct");

            }
        }
        //-------------------------------------Document Tab Handling----------------------------------------------------

        //-------------------------------------Rent Tab Handling----------------------------------------------------
        if(contractPageObj.checktheStatusofTab("Documents")) {
            contractPageObj.selectSteptoNavigate("Rent");
            contractPageObj.clickAddComponentBtn();
            if (input.get("rent_Component_Type") != null) {
                contractPageObj.selectComponentType(input.get("rent_Component_Type"));
            }
            if (input.get("rent_Component_Type") != null) {
                contractPageObj.selectComponentName(input.get("rent_Component_Name"));
            }
            if (input.get("rent_Payout_Frequency") != null) {
                contractPageObj.selectPayoutFrequency(input.get("rent_Payout_Frequency"));
            }
            if (input.get("rent_Calculation_Type") != null) {
                contractPageObj.selectCalculationType(input.get("rent_Calculation_Type"));
            }
            contractPageObj.clickSaveButton(2);
            Thread.sleep(2000);


            contractPageObj.clickAddInvoiceBtn();
            if (input.get("formula_Type") != null) {
                contractPageObj.selectFormulaType(input.get("formula_Type"));
            }


            if (input.get("formula_Type").toLowerCase().contains("fixed") && (input.get("formula_Type") != null)) {

                contractPageObj.selectBaseComponent(input.get("base_Component"));
            }
            contractPageObj.setFormulaTitle(input.get("formula_Title") != null ? input.get("formula_Title") : "");

            if (input.get("expression_Type").toLowerCase().contains("normal") && (input.get("expression_Type") != null)) {
                contractPageObj.setFormula(input.get("formula_Field1") != null ? input.get("formula_Field1") : "", 1);
            } else {

                contractPageObj.selectConditionalExpression();

                contractPageObj.setFormula(input.get("formula_Field1") != null ? input.get("formula_Field1") : "", 1);
                contractPageObj.setFormula(input.get("formula_Field2") != null ? input.get("formula_Field2") : "", 2);
                contractPageObj.setFormula(input.get("formula_Field3") != null ? input.get("formula_Field3") : "", 3);
                contractPageObj.setFormula(input.get("formula_Field4") != null ? input.get("formula_Field4") : "", 4);
                if (input.get("formula_Condition") != null) {
                    contractPageObj.selectFormulaCondition(input.get("formula_Condition"));
                }

            }

            contractPageObj.clickSaveButton(1);
            contractPageObj.clickPopUpOkBtn();
            contractPageObj.clickSubmitButton(1);
            contractPageObj.setRemark(input.get("final_Remarks") != null ? input.get("final_Remarks") : "");
            contractPageObj.clickSubmitButton(2);
        }
        //-------------------------------------Verification of Contract----------------------------------------------------


        int page_Count = contractPageObj.totalPages();

        for (int i = 0; i < page_Count; i++) {
            List<String> store_List = contractPageObj.getStoreListData();
            for (String store_Name : store_List) {
                if (store_Name.equalsIgnoreCase(input.get("erp_Location_Code"))) {
                    soft_Assert.assertTrue(true, "Contract Details Verification on Dashboard Failed");
                }
            }
            contractPageObj.clickPageNxtBtn();
        }
        soft_Assert.assertAll();
    }
}
