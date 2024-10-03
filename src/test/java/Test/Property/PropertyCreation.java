package Test.Property;

import PageObjects.DashboardPage;
import PageObjects.PortalSelectionPage;
import PageObjects.PropertyPage;
import TestComponents.BaseClass;
import TestComponents.TestData;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class PropertyCreation extends BaseClass {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void PropertyRegistration(HashMap<String, String> input, ITestContext context) throws InterruptedException, IOException, AWTException {


        context.setAttribute("case_Type", input.get("case_Type"));
        context.setAttribute("testScenarioName", input.get("test_Scenario"));
        context.setAttribute("testName", input.get("test_Name"));
        //--------------------------------------------Login in Application-----------------------------------------------------------

        login.setUsername(input.get("userName"));
        login.setPassword(input.get("password"));
        login.setCompanyCode(input.get("company_Code"));


        PortalSelectionPage portalSelObj = login.clickLoginButton();

        //--------------------------------------------Portal Selection-----------------------------------------------------------

        DashboardPage dashPageObj = portalSelObj.portalSelection(input.get("portal"));

        //-----------------------------------------Navigating to the Property Page-----------------------------------------------
        PropertyPage propertyPageObj = (PropertyPage) dashPageObj.sideMenuOptionSelection(input.get("menu_Option"));


        propertyPageObj.clickAddPropertyBtn();

        //------------------------------------------Property Basic Tab Details-------------------------------------------------------

            propertyPageObj.setPropertyTitle(input.get("property_Title") != null ? input.get("property_Title") : "");
            if (input.get("property_Type") != null) {
                propertyPageObj.selectPropertyType(input.get("property_Type"));
            }
            if (input.get("property_SubType") != null) {
                propertyPageObj.selectSubPropertyType(input.get("property_SubType"));
            }

            propertyPageObj.setCarpetAreaField(input.get("carpet_Area") != null ? input.get("carpet_Area") : "");


            if (input.get("lease_Status") != null) {
                propertyPageObj.selectLeaseStatus(input.get("lease_Status"));
            }
            if (input.get("construction_Status") != null) {
                propertyPageObj.selectConstructionStatus(input.get("construction_Status"));
            }
            if (input.get("buildUp_Class") != null) {
                propertyPageObj.selectBuildUpClass(input.get("buildUp_Class"));
            }

            propertyPageObj.setPropertyPriceField(input.get("property_Price") != null ? input.get("property_Price") : "");
            propertyPageObj.setLatitudeField(input.get("latitude") != null ? input.get("latitude") : "");
            propertyPageObj.setLongitudeField(input.get("longitude") != null ? input.get("longitude") : "");
            propertyPageObj.setAddressLine1(input.get("address_Line1") != null ? input.get("address_Line1") : "");
            propertyPageObj.setAddressLine2(input.get("address_Line2") != null ? input.get("address_Line2") : "");

            if (input.get("country") != null) {
                propertyPageObj.selectCountry(input.get("country"));
            }
            if (input.get("state") != null) {
                propertyPageObj.selectState(input.get("state"));
            }
            if (input.get("city") != null) {
                propertyPageObj.selectCity(input.get("city"));
            }


            propertyPageObj.setPincode(input.get("pincode") != null ? input.get("pincode") : "");
            propertyPageObj.clickNextButton();

            List<String> expected_Msg = new ArrayList<>();

            expected_Msg.add("Property Title Required");
            expected_Msg.add("Type Required");
            expected_Msg.add("Sub-Type Required");
            expected_Msg.add("Carpet Area Required");
            expected_Msg.add("Address Line 1 Required");
            expected_Msg.add("Country Required");
            expected_Msg.add("State Required");
            expected_Msg.add("City Required");
            expected_Msg.add("Pin Code Required");
            List<String> actual_Basic_Msg = propertyPageObj.getFieldsErrMsg();
            if (!actual_Basic_Msg.isEmpty()) {
                soft_Assert.assertTrue(expected_Msg.containsAll(actual_Basic_Msg), "Basic Fields Error Message are not Correct");

            }

        //-------------------------------------------------------Property Additonal Tab Detials ----------------------------------------------------

        if(propertyPageObj.checktheStatusofTab("Additional")) {
            if (input.get("floor_Number") != null) {
                propertyPageObj.selectFloorNumber(input.get("floor_Number"));
            }
            propertyPageObj.setUnitNumber(input.get("unit_Number") != null ? input.get("unit_Number") : "");
            propertyPageObj.setPropertyAge(input.get("property_Age") != null ? input.get("property_Age") : "");


            if (input.get("landmark") != null) {
                propertyPageObj.selectLandmark(input.get("landmark"));
            }
            if (input.get("transaction_Type") != null) {
                propertyPageObj.selectTransaction(input.get("transaction_Type"));
            }
            if (input.get("furnishing") != null) {
                propertyPageObj.selectFurnishing(input.get("furnishing"));
            }
            propertyPageObj.setDescription(input.get("description") != null ? input.get("description") : "");
            propertyPageObj.clickNextButton();

            List<String> expected_Addti_Msg = new ArrayList<String>();
            expected_Addti_Msg.add("Unit Number not valid");
            expected_Addti_Msg.add("Property Age not valid");
            List<String> actual_Addti_Msg = propertyPageObj.getAddtionalFieldsErrMsg();
            if (!actual_Addti_Msg.isEmpty()) {

                soft_Assert.assertTrue(expected_Addti_Msg.containsAll(actual_Addti_Msg), "Fields Error Message are not Correct");

            }
        }

        //-------------------------------------------------------Property Document Tab Details----------------------------------------------------
        if(propertyPageObj.checktheStatusofTab("Document")) {
            if (input.get("property_Possession_Letter") != null) {
                propertyPageObj.uploadDocument(input.get("property_Possession_Letter"), 1);
            }
            if (input.get("property_Registry_Copy") != null) {

                propertyPageObj.uploadDocument(input.get("property_Registry_Copy"), 2);
            }
            if (input.get("address_Proof") != null) {

                propertyPageObj.uploadDocument(input.get("address_Proof"), 3);
            }
            if (input.get("property_Pictures") != null) {

                propertyPageObj.uploadDocument(input.get("property_Pictures"), 4);
            }
            propertyPageObj.clickNextButton();
        }

        //-------------------------------------------------------Property Owners Tab Details----------------------------------------------------
        if(propertyPageObj.checktheStatusofTab("Owners")) {
            propertyPageObj.clickAddOwnerBtn();
//        SWAGAT DEVELOPERS
            if (input.get("owner_Name")!=null) {
                propertyPageObj.setOwnerName(input.get("owner_Name"));
            }
            propertyPageObj.setOwnerRatio(input.get("owner_Ratio") != null ? input.get("owner_Ratio") : "");
            propertyPageObj.clickAddRecordBtn();
//        if(propertyPageObj.ownerErroStatus())
//        {
//            soft_Assert.assertEquals(propertyPageObj.getOwnerErrMsg(),"entity_site_id : This field may not be null.");
//        }
            propertyPageObj.clickSubmitButton(1);

            propertyPageObj.setRemarksField(input.get("remarks") != null ? input.get("remarks") : "");
            propertyPageObj.clickSubmitButton(2);

            if (input.get("owner_Name")==(null) || input.get("owner_Ratio") == null)
            {
                soft_Assert.assertEquals(propertyPageObj.getToastMessage("All mandatory fields are required"),"Owner Name is required.");
            }
            Thread.sleep(7000);
            soft_Assert.assertAll();
        }

    }
}