package Test.Store;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import PageObjects.StorePage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreCreationFlow extends BaseClass {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void createStoreFlow(HashMap<String, String> input, ITestContext context) throws InterruptedException {

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
        StorePage storePageObj = (StorePage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));
        storePageObj.clickAddButton();
        storePageObj.setName(input.get("store_Name") != null ? input.get("store_Name") : "");
        storePageObj.setErpCode(input.get("store_ErpCode") != null ? input.get("store_ErpCode") : "");
        storePageObj.setCostCenter(input.get("store_CostCenter") != null ? input.get("store_CostCenter") : "");
        storePageObj.setAddrsTitle(input.get("store_Addrs_Title") != null ? input.get("store_Addrs_Title") : "");
        storePageObj.clickPropertyAddBtn();
        if (input.get("property_To_Search") != null) {
            storePageObj.propertyToSearch(input.get("property_To_Search"));
        }
        if (input.get("property_To_Add") != null)
        {
            storePageObj.selectProperty(input.get("property_To_Add"));
            storePageObj.clickAssociateBtn();
            storePageObj.selectPropertyToApprove(input.get("property_To_Approve"));

        }
        else
        {
        storePageObj.clickCancelButton();
        }
        storePageObj.clickApproveBtn();
        storePageObj.setRemarksField(input.get("remarks") != null ? input.get("remarks") : "");
        storePageObj.clickSubmitButton(1);



        List<String> actual_Store_Msg = storePageObj.getStoreFieldErrMsg();

        if (!actual_Store_Msg.isEmpty()) {
            List<String> expected_Store_Msg = new ArrayList<String>();
            expected_Store_Msg.add("Name is required");
            expected_Store_Msg.add("Store ERP Code is required");
            expected_Store_Msg.add("Cost Center is required");
            expected_Store_Msg.add("Address Title is required");
            expected_Store_Msg.add("Site Contact Person Required");
            expected_Store_Msg.add("At least one property is required Store must have one primary property.");
            expected_Store_Msg.add("Store must have one primary property.");

            soft_Assert.assertTrue(expected_Store_Msg.containsAll(actual_Store_Msg), "Store Fields Error Messages Assertion Failed");
        }


        soft_Assert.assertAll();

    }
}
