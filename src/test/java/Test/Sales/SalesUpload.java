package Test.Sales;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import PageObjects.SalesPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class SalesUpload extends BaseClass {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void uploadSales(HashMap<String, String> input, ITestContext context) throws IOException, InterruptedException, ParseException {
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


        //-------------------------Navigating to the Sale Page and Add Button Click------------------------

        SalesPage salesObj = (SalesPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));
        salesObj.clickAddSalesBtn();
        if (input.get("upload_Type").equals("Bulk Upload")) {
            salesObj.clickMasterUploadBtn();
            System.out.println(System.getProperty("user.dir") + input.get("master_File_Path"));
            salesObj.uploadMaster(System.getProperty("user.dir") + input.get("master_File_Path"));
            soft_Assert.assertEquals(salesObj.getSnackBarMsg(), "Sales saved");

        } else {
            salesObj.navigateToSingleStore();
            if (input.get("store_Code") != null) {
                salesObj.selectStoreCode(input.get("store_Code"));
            }

            if (input.get("sales_Comp_Type") != null) {
                salesObj.selectSalesCompType(input.get("sales_Comp_Type"));
            }


            salesObj.setTotalSalse(input.get("total_Sales") != null ? input.get("total_Sales") : "");
            if (input.get("sales_Period_From") != null) {
                salesObj.setSalePeriodFrom(input.get("sales_Period_From"));
            }
            if (input.get("sales_Period_Till") != null) {
                salesObj.setSalesPeriodTill(input.get("sales_Period_Till"));
            }
            salesObj.setSalesRemarks(input.get("first_Remarks") != null ? input.get("first_Remarks") : "", 1);

            salesObj.clickApproveBtn();
            salesObj.setSalesRemarks(input.get("second_Remarks") != null ? input.get("second_Remarks") : "", 2);
            salesObj.clickSubmitButton(1);


            List<String> actual_Sales_Msg=salesObj.getSalesFieldsErrMsg();

            if (!actual_Sales_Msg.isEmpty())
            {
                List<String> expected_Sales_Msgs = new ArrayList<>();
                expected_Sales_Msgs.add("Store Code is required");
                expected_Sales_Msgs.add("Sales Component Type is required");
                expected_Sales_Msgs.add("Total Sales (Per Month) is required");
                expected_Sales_Msgs.add("Sales Period From is required");
                expected_Sales_Msgs.add("Sales Period Till is required");
                expected_Sales_Msgs.add("Remarks is required");
                soft_Assert.assertTrue(expected_Sales_Msgs.containsAll(actual_Sales_Msg), "Sales Fields Error Messages Assertion Failed");


            }
            }

        soft_Assert.assertAll();

    }
    }

