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

public class BlankInputLessorComplianceTab extends BaseClass {

    String msme_Option;


    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void blankInputCompliance(HashMap<String, String> input, ITestContext context) throws InterruptedException, ParseException {

        //Test Case ID in Excel Lessor_REG-011
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

        //Lessor Details Step
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

        //Contact Person Details Step

        lessorPageObj.clickAddButton();
        lessorPageObj.setContPersnName(input.get("contact_Person_Name"));
        lessorPageObj.setMobNum(input.get("mobile_Number"));
        lessorPageObj.setEmailAdd(input.get("contact_Email"));
        lessorPageObj.setPrimaryContPersn();
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Data save successfully").get(0), "Data save successfully", "Contact Person Details Toast Message Value are not Equal");
        lessorPageObj.clickNextButton();


        //Compliance Details Step

        //Verifying the Field and Toast Error Messages
        String[] expected_Err_Msg = {"PAN Copy Required", "MSME Registration Required"};
        lessorPageObj.clickSaveButton(1);


        soft_Assert.assertEquals(lessorPageObj.getPanErrorMsg(), "PAN Number Required", "PAN Number Field Error Message Values are not Matching ");


        List<String> actual_Msg = lessorPageObj.getComplianceErrorMsg();
        int i = 0;
        for (String msg : actual_Msg) {

            soft_Assert.assertEquals(msg, expected_Err_Msg[i++]);
        }
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Please fill required details").get(0), "Please fill required details", "Compliance Field Error Message Values are not Matching");

        //Setting the Msme Option then verifying the field and toast error message
        lessorPageObj.selectMsmeReg(input.get("msme_Option"));
        lessorPageObj.clickSaveButton(1);

        soft_Assert.assertEquals(lessorPageObj.getComplianceErrorMsg().get(1), "MSME Registration Number Required", "MSME Registration Number Field Error Message Values are not Matching");
        soft_Assert.assertEquals(lessorPageObj.getToastMessage("Please fill required details").get(0), "Please fill required details", "MSME Details Toast Message Value are not Equal");

        lessorPageObj.setPanNo(input.get("pan_Number"));
        lessorPageObj.uploadPanFile(System.getProperty("user.dir") + input.get("pan_File_Path"));


        //Setting the Msme Option
        msme_Option = input.get("msme_Option");
        if (!msme_Option.equals(" Not Available ")) {

            lessorPageObj.setMsmeRegNo(input.get("msme_Registration_Number"));


            lessorPageObj.setMsmeExpDate(input.get("msme_Exp_Date"));
            lessorPageObj.uploadMsmeCerti(System.getProperty("user.dir") + input.get("msme_File_Path"));
        }


        //Get the Uploaded File to Validated and for the explicit wait in the pageObject
        String[] pan_File = input.get("pan_File_Path").split("Resources\\\\");
        String[] msme_File = input.get("msme_File_Path").split("Resources\\\\");
        String[] expected_File_Name = {pan_File[1], msme_File[1]};

        // Getting the file name from the WebPage
        List<String> actual_File_Name = lessorPageObj.verifyUploadedFileText(expected_File_Name);

        //Verifying file name from the WebPage and from the Test Data
        for (String file : actual_File_Name) {
            soft_Assert.assertEquals(file, expected_File_Name[actual_File_Name.indexOf(file)]);
        }
        lessorPageObj.clickSaveButton(1);
        soft_Assert.assertAll();
    }

}