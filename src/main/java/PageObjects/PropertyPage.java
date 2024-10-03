package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PropertyPage extends AbstractComponents {
    WebDriver driver;

    public PropertyPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//label[contains(text(),'Add Property')]")
    private WebElement add_Property_Btn_Elmnt;

    //--------------------------------------------------Basic Tab Elements----------------------------------------------------------------
    @FindBy(xpath = "//input[@formcontrolname='property_title']")
    private WebElement property_Title_Field_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='property_type_id']")
    private WebElement property_Type_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='property_sub_type_id']")
    private WebElement sub_Property_Type_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='build_up_area']")
    private WebElement carpet_Area_Field_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='lease_status']")
    private WebElement lease_Status_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='construction_status_id']")
    private WebElement constrution_Status_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='build_class_id']")
    private WebElement build_Class_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='property_price']")
    private WebElement property_Price_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='latitude']")
    private WebElement latitude_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='longitude']")
    private WebElement longitude_Field_Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line1']")
    private WebElement addrs_Line1_Field_Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line2']")
    private WebElement addrs_Line2_Field_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='country_id']")
    private WebElement country_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='state_id']")
    private WebElement state_Id_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='city_id']")
    private WebElement city_Id_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='zip']")
    private WebElement pincode_Field_Elmnt;

    @FindBy(xpath = "//div[contains(@class,'text-danger')]")
    private List<WebElement> basic_Field_Err_Elmnt;


    //----------------------------------------------Additional Tab Elements----------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='floor_no']")
    private WebElement floorNum_DropDown_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='unit_no']")
    private WebElement unitNum_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='property_age']")
    private WebElement property_Age_Field_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='landmark']")
    private WebElement landmark_DropDown_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='transaction_type']")
    private WebElement trnsction_Type_DropDown_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='furnishing']")
    private WebElement furnishing_DropDown_Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='description']")
    private WebElement description_Area_Elmnt;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    private List<WebElement> additional_Field_Err_Elmnt;

    //--------------------------------------------------Document Tab Locators------------------------------------------------

    @FindBy(xpath = "//div/input[@type='file']")
    private List<WebElement> uploaded_File_Elmnt;

    @FindBy(xpath = "//tr[2]/td[4]/div")
    private WebElement upload_Reg;

    @FindBy(css = ".fa-download")
    private List<WebElement> download_Logo_Elmnt;


    //--------------------------------------------------Owners Tab Locators------------------------------------------------
    @FindBy(css = ".fa.fa-plus")
    private WebElement owners_Add_Btn;


    @FindBy(xpath = "//input[@formcontrolname='ownerName']")
    private WebElement ownerName_Field;

    @FindBy(xpath = "//fieldset/div/div[2]/div/ol/li")
    private List<WebElement> name_List;

    @FindBy(xpath = "//input[@formcontrolname='ownership_ratio']")
    private WebElement owner_Ratio_Field;

    @FindBy(css = ".fa.fa-check")
    private WebElement add_Record_Btn;


    @FindBy(xpath = "//tbody/tr/td[3]")
    private List<WebElement> upload_Files_Name;


    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private WebElement remarks_Field_Elmnt;

    @FindBy(xpath = "//span[contains(@class,'text-danger')]")
    private WebElement owner_Field_Err_Elmnt;

    //-------------------------------------Basic Tab Handling Methods----------------------------------------------------

    public void clickAddPropertyBtn() {
        waitfortheVisibilityofElement(add_Property_Btn_Elmnt, 5);
        add_Property_Btn_Elmnt.click();
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
    }

    public void setPropertyTitle(String title) {
        waitfortheVisibilityofElement(property_Title_Field_Elmnt, 5);
        property_Title_Field_Elmnt.sendKeys(title);
    }

    public void selectPropertyType(String propertyType) {
        staticDropdownByVisibleText(property_Type_Elmnt, propertyType);
    }

    public void selectSubPropertyType(String subPropertyType) {
        staticDropdownByVisibleText(sub_Property_Type_Elmnt, subPropertyType);
    }

    public void setCarpetAreaField(String carpetAreaField) {
        carpet_Area_Field_Elmnt.sendKeys(carpetAreaField);
    }

    public void selectLeaseStatus(String leaseStatus) {
        staticDropdownByVisibleText(lease_Status_Elmnt, leaseStatus);
    }

    public void selectConstructionStatus(String constructionStatus) {
        staticDropdownByVisibleText(constrution_Status_Elmnt, constructionStatus);
    }

    public void selectBuildUpClass(String buildUpStatus) {
        staticDropdownByVisibleText(build_Class_Elmnt, buildUpStatus);
    }

    public void setPropertyPriceField(String propertyPriceField) {
        property_Price_Field_Elmnt.sendKeys(propertyPriceField);
    }

    public void setLatitudeField(String latitudeField) {
        latitude_Field_Elmnt.sendKeys(latitudeField);
    }

    public void setLongitudeField(String longitudeField) {
        longitude_Field_Elmnt.sendKeys(longitudeField);
    }

    public void setAddressLine1(String addressLine1) {
        addrs_Line1_Field_Elmnt.sendKeys(addressLine1);
    }

    public void setAddressLine2(String addressLine2) {
        addrs_Line2_Field_Elmnt.sendKeys(addressLine2);
    }

    public void selectCountry(String country) {
        staticDropdownByVisibleText(country_Elmnt, country);
    }

    public void selectState(String state) {
        staticDropdownByVisibleText(state_Id_Elmnt, state);
    }

    public void selectCity(String city) {
        staticDropdownByVisibleText(city_Id_Elmnt, city);
    }

    public void setPincode(String pincodeField) {
        pincode_Field_Elmnt.sendKeys(pincodeField);
    }

public List<String> getFieldsErrMsg()
{
    List<String> msgs=new ArrayList<String>();
    for(WebElement element: basic_Field_Err_Elmnt)
    {
        msgs.add(element.getText());
    }
    return msgs;
}


    //-------------------------------------Additional Tab Handling Methods----------------------------------------------

    public void selectFloorNumber(String floorNum) {
        staticDropdownByVisibleText(floorNum_DropDown_Elmnt, floorNum);
    }

    public void setUnitNumber(String floor) {
        unitNum_Field_Elmnt.sendKeys(floor);
    }

    public void setPropertyAge(String propertyAge) {
        property_Age_Field_Elmnt.sendKeys(propertyAge);
    }

    public void selectLandmark(String landmark) {
        staticDropdownByVisibleText(landmark_DropDown_Elmnt, landmark);
    }

    public void selectTransaction(String transaction) {
        staticDropdownByVisibleText(trnsction_Type_DropDown_Elmnt, transaction);
    }

    public void selectFurnishing(String furnishing) {
        staticDropdownByVisibleText(furnishing_DropDown_Elmnt, furnishing);
    }

    public void setDescription(String description) {
        description_Area_Elmnt.sendKeys(description);
    }

    public List<String> getAddtionalFieldsErrMsg()
    {
        List<String> msgs=new ArrayList<String>();
        for(WebElement element: additional_Field_Err_Elmnt)
        {
            msgs.add(element.getText());
        }
        return msgs;
    }

    //----------------------------------Document Upload Handling Functions---------------------------------------

    public void uploadDocument(String files, int number) throws InterruptedException, IOException, AWTException {
        action.moveToElement(uploaded_File_Elmnt.get(number - 1)).click().build().perform();
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe", (System.getProperty("user.dir") + files));
        Process pr = pb.start();
        pr.waitFor();
        Thread.sleep(2000);

//        waitforTextinElement(upload_Files_Name.get(number-1),"sample-pan-card-front.jpg",10);


    }


    //---------------------------------------------Owners Tab Handling Functions----------------------------------------------------


    public void clickAddOwnerBtn() {
        waitfortheVisibilityofElement(owners_Add_Btn, 2);
        owners_Add_Btn.click();
    }

    public boolean ownerErroStatus()
    {
      return  owner_Field_Err_Elmnt.isDisplayed();
    }
    public String getOwnerErrMsg()
    {
      return   owner_Field_Err_Elmnt.getText();

    }

    public void setOwnerName(String ownerName) throws InterruptedException {
        ownerName_Field.sendKeys(ownerName.substring(0, 3));
        Thread.sleep(2000);
        for (WebElement element : name_List) {
            waitfortheVisibilityofElement(element, 2);
            if (element.getText().contains(ownerName)) {
                element.click();
                break;
            }

        }

    }


    public void setOwnerRatio(String ownerRatio) {
        owner_Ratio_Field.sendKeys(ownerRatio);
    }

    public void clickAddRecordBtn() {
        add_Record_Btn.click();
    }

    public void setRemarksField(String remarksField) {
        remarks_Field_Elmnt.sendKeys(remarksField);
    }

}
