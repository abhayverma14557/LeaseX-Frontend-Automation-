package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class BlankInputLessorContactTab extends BaseClass {
    ////Verify Saving of Contact Details on Blank Fields and Verifying Error Messages are disappearing after correct text input

    //Test Case ID in Excel= Lessor_REG-008

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankInputContactDetails(HashMap<String, String> input, ITestContext context) throws InterruptedException {
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

        Thread.sleep(2000);
        //Navigating to the Vendor Page and Registeration Button Click
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
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Value are not Equal"); //Assertion For Successfull Address save verifying from the toast message
        lessorPageObj.clickNextButton();
        //Add Validation for the Listing

        //Contact Person Details Step

        lessorPageObj.clickAddButton();
        lessorPageObj.clickSaveButton(1);
        String[] exptd_Err_Msg = {"Contact Person Name Required", "Mobile Number Required", "Email Address Required"};

        List<String> err_Msg_List = lessorPageObj.getContFieldErrMsg();
        int i = 0;
        for (String actual_Err_Msge : err_Msg_List) {

            soft_Assert.assertEquals(actual_Err_Msge, exptd_Err_Msg[i], " Contact Person Fields Error Message Values are not Equal");
            i++;
        }


        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        List<String> new_Err_Msg_List = lessorPageObj.getContFieldErrMsg();
        soft_Assert.assertTrue(new_Err_Msg_List.isEmpty());
        lessorPageObj.clickSaveButton(1);

        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");
        lessorPageObj.clickNextButton();
        soft_Assert.assertAll();

    }

}
