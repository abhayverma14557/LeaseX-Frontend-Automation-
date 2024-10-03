package Test.LessorRegistration;

import PageObjects.DashboardPage;
import PageObjects.LessorPage;
import PageObjects.PortalSelectionPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EditContactToPrimaryLessorTab extends BaseClass {
    //Verifying Edit of the Contact Details and Making the Contact Primary
//    //Incomplete Because of Issue in Functionality

    //Test Case ID in Excel= Lessor-REG009
    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void changeContactToPrimary(HashMap<String, String> input, ITestContext context) throws InterruptedException {

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
        //Navigating to the Lessor Page and Registeration Button Click
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
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Address Details Toast Message Values are not Equal"); //Assertion For Successfull Address save verifying from the toast message
        lessorPageObj.clickNextButton();


        //Contact Person Details Step
        lessorPageObj.clickAddButton();
        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Details Toast Message Values are not Equal");

        //Clicking Edit Button

        lessorPageObj.clickEditBtn();

        Thread.sleep(3000);
        lessorPageObj.clearContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.clearEmailAdd();
        lessorPageObj.clearMobNum();


        lessorPageObj.setContPersnName(input.get("edited_Contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("edited_Mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("edited_Contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        lessorPageObj.clickSaveButton(1);

        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data update successfully").get(0), "Data update successfully", "Contact Details Update Toast Message Values are not Equal");

        soft_Assert.assertEquals(lessorPageObj.getSavedContPerson().get(0), input.get("contact_Person_Name"), "Saved Person Name Values are not Equal");
        soft_Assert.assertEquals(lessorPageObj.getSavedMobNum().get(0), input.get("mobile_Number"), "Saved Mobile Number Values are not Equal");
        soft_Assert.assertEquals(lessorPageObj.getSavedEmail().get(0), input.get("contact_Email"), "Saved Contact Email Values are not Equal");


        soft_Assert.assertAll();
    }


}
