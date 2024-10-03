package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class RepeatedDetailsDiffAddrsType extends BaseClass {
    //Verifying Adding Multiple Address with Different Address Type
//Test Case ID= Lessor-Reg006
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void repeatedAddressWithDifferentType(HashMap<String, String> input, ITestContext context) {
        //Used to Set The Test Case Type in Test Report

        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));


        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));
        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //Portal Selection
        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //Navigating to the Lessor Page and Registeration Button Click

        LessorPage lessorPageObj = (LessorPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));

        lessorPageObj.clickRegistrationBtn();


        //     Lessor Details Step
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

        //Selecting Coprarte Type Adddress
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
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "First Save Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message


        //Select Property Type Address
        lessorPageObj.clickAddButton();
        lessorPageObj.selectAddressType(input.get("address_Type2"));
        lessorPageObj.setAddressLine1(input.get("address_Line1"));
        lessorPageObj.setAddressLine2(input.get("address_Line2"));
        lessorPageObj.selectCountry(input.get("country"));
        lessorPageObj.selectState(input.get("state"));
        lessorPageObj.selectCity(input.get("city"));
        lessorPageObj.setPincode(input.get("pincode"));
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Second Save Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message

        //Select Site Type Address
        lessorPageObj.clickAddButton();
        lessorPageObj.selectAddressType(input.get("address_Type3"));
        lessorPageObj.setAddressLine1(input.get("address_Line1"));
        lessorPageObj.setAddressLine2(input.get("address_Line2"));
        lessorPageObj.selectCountry(input.get("country"));
        lessorPageObj.selectState(input.get("state"));
        lessorPageObj.selectCity(input.get("city"));
        lessorPageObj.setPincode(input.get("pincode"));
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Third Save Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message

        //Validation to Verify the Saved Address

        soft_Assert.assertAll();


    }
}






