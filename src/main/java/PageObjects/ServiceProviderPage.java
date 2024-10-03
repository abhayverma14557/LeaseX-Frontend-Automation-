package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class ServiceProviderPage extends AbstractComponents {

    WebDriver driver;

    public ServiceProviderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //---------------------------------------Common Locators----------------------------------------------------------------
    @FindBy(xpath = "//div[contains(@class,'text-danger')]/div")
    private List<WebElement> fields_Common_Err_Elmnt;

    //---------------------------------------Service Provider Locators----------------------------------------------------------------
    @FindBy(css = ".fa.fa-plus")
    private WebElement add_Ser_Btn;

    //---------------------------------------Basic Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='name']")
    private WebElement serviceProvider_Name_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement serviceProvider_Email_Field_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='entity_type']")
    private WebElement serviceProvider_Type_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='erp_code']")
    private WebElement erp_Code_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='cin_number']")
    private WebElement cin_Number_Field_Elmnt;

    @FindBy(xpath = "//span[contains(@class,'text-danger')]/div")
    private List<WebElement> basic_Field_Err_Elmnt;
    //--------------------------------------Address Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='address_type']")
    private WebElement address_Type_Dropdown_Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line1']")
    private WebElement address_Line1_field_Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line2']")
    private WebElement address_Line2_field_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='country_id']")
    private WebElement country_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='state_id']")
    private WebElement state_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='city_id']")
    private WebElement city_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='zip_code']")
    private WebElement pincode_Field_Elmnt;


    //--------------------------------------Contact Person Tab Locators----------------------------------------------------------------


    @FindBy(xpath = "//input[@formcontrolname='mobile_no']")
    private WebElement mobile_Num_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='is_primary']")
    private WebElement primary_Contact_Chkbx_Elmnt;


    //-----------------------------------------------------------Compliance Tab Locator--------------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='pan_no']")
    private WebElement panNoElmnt;

    @FindBy(css = "#file")
    private List<WebElement> fileUplodElmnt;

    @FindBy(xpath = "//select[@formcontrolname='msme_register']")
    private WebElement msmeRegElmnt;

    @FindBy(xpath = "//input[@formcontrolname='msme_register_no']")
    private WebElement msme_RegNo_Elmnt;


    @FindBy(xpath = "//input[@formcontrolname='msme_expiry_date']")
    private WebElement msme_Exp_Date_Elmnt;

    @FindBy(xpath = "//label[@for='flexRadioDefault2']")
    private WebElement E_InvYesElmnt;

    @FindBy(xpath = "//fieldset/div/div[@class='ng-star-inserted']")
    private WebElement pan_Err_Msg_Elmnt;

    @FindBy(xpath = "//form/div/div/div[@class='ng-star-inserted']")
    private List<WebElement> compliance_Err_Msg_Elmnt;


    @FindBy(css = ".mt-2.cursorPointer.ng-star-inserted")
    private List<WebElement> uploadFile_Text_Elmnts;


    //-------------------------------------------------------Gst Details in Compliance----------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='gst_status']")
    private WebElement gstStatusElmnt;

    @FindBy(xpath = "//input[@formcontrolname='gst_no']")
    private WebElement gstNoElmnt;

    @FindBy(css = "//span/div[@class='ng-star-inserted']")
    private WebElement gst_Field_Err_Elmnt;


    @FindBy(xpath = "//span/div[@class='ng-star-inserted']")
    private List<WebElement> gst_field_Err_Elmnt;


    //-------------------------------------------Bank Details Step Locators----------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='ifsc_code']")
    private WebElement ifsc_Code_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='bank_name']")
    private WebElement bank_Name_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='branch_name']")
    private WebElement branch_Name_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='account_type_id']")
    private WebElement acc_Type_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='account_holder_name']")
    private WebElement acc_holder_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='account_no']")
    private WebElement acc_num_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='bank_payout_type_id']")
    private WebElement payout_Type_Elmnt;


    @FindBy(xpath = "//fieldset/div/div[@class='ng-star-inserted']")
    private List<WebElement> bank_field_Err_Elmnts;

    @FindBy(xpath = "//span/div/div[@class='ng-star-inserted']")
    private WebElement bank_Acc_Err_Elmnt;

    //---------------------------------------------------- SubCode Map Step Locators----------------------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='gst_id']")
    private WebElement gst_Id_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='address_id']")
    private WebElement site_Add_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='site_name']")
    private WebElement site_Name_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='site_code']")
    private WebElement site_Code_Elmnt;

    @FindBy(xpath = "//ng-select[@formcontrolname='bank_ids']")
    private WebElement site_Bank_Elmnt;

    @FindBy(css = ".ng-option-label")
    private List<WebElement> dropdown_Option;

    @FindBy(css = "select[formcontrolname='address_id']")
    private WebElement address_Dropdown_options;

    @FindBy(xpath = "//ng-select[@formcontrolname='contact_person']")
    private WebElement site_Cont_Per_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='invoice_payment_terms']")
    private WebElement invoice_Pay_Terms_Elmnt;

    @FindBy(xpath = "//span/button[text()='Submit']")
    private WebElement submit_Btn_Elmnt;


    @FindBy(xpath = "//form/div/div/div[@class='ng-star-inserted']")
    private List<WebElement> subMap_field_Err_Elmnt;


    //-------------------------------------------Remarks Pop Up Locators--------------------------------------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private WebElement remarks_field_Elmnt;

    @FindBy(xpath = "//div/button[text()='Submit']")
    private WebElement remarks_submit_btn_Elmnt;


    @FindBy(xpath = "//span/div[@class='ng-star-inserted']")
    private List<WebElement> field_Err_Msgs_Elmnt;

    //----------------------------------------Common Locators Handling Methods--------------------------------------------------------------------

    public List<String> getFieldsErrMsg()

    {
        List<String> msgs=new ArrayList<String>();
        for(WebElement element : fields_Common_Err_Elmnt)
        {
            msgs.add(element.getText());
        }
        return msgs;

    }
    //----------------------------------------Service Provider Page Handling Methods--------------------------------------------------------------------


    public void clickAddBtn() {
        add_Ser_Btn.click();
        waitforInvisiblityoftheElement(spinner_Elmnt, 4);
    }

    public boolean addButtonStatus()
    {
        System.out.println(add_Ser_Btn.getDomAttribute("disabled"));
        return false;
    }

    //----------------------------------------Basic Tab Handling Methods--------------------------------------------------------------------
    public void setServiceProviderName(String serviceProviderName) {
        serviceProvider_Name_Field_Elmnt.sendKeys(serviceProviderName);
    }

    public void setServiceProviderEmail(String serviceProviderEmail) {
        serviceProvider_Email_Field_Elmnt.sendKeys(serviceProviderEmail);
    }

    public void selectServiceProviderType(String serviceProviderType) {
        staticDropdownByVisibleText(serviceProvider_Type_Dropdown_Elmnt, serviceProviderType);
    }

    public void setERPCode(String erpCode) {
        erp_Code_Field_Elmnt.sendKeys(erpCode);
    }

    public void setCINNumber(String CINNumber) {
        cin_Number_Field_Elmnt.sendKeys(CINNumber);
    }


    public List<String> getBasicFieldsErrMsg()
    {
        List<String> msgs=new ArrayList<String>();
        for(WebElement element : basic_Field_Err_Elmnt)
        {
            msgs.add(element.getText());
        }
        return msgs;
    }


    //----------------------------------------Address Tab Handling Methods--------------------------------------------------------------------


    public void selectAddressType(String addressType) {

        staticDropdownByVisibleText(address_Type_Dropdown_Elmnt, addressType);
    }


    public void clearAddressLine1() {
        waitforDynamicLoading(address_Line1_field_Elmnt, "class", "ng-valid", 4);
        address_Line1_field_Elmnt.clear();

    }

    public void setAddressLine1(String addressLine1) {

        address_Line1_field_Elmnt.sendKeys(addressLine1);

    }

    public void clearAddressLine2() {
        address_Line2_field_Elmnt.clear();
    }

    public void setAddressLine2(String addressLine2) {
        address_Line2_field_Elmnt.sendKeys(addressLine2);
    }


    public void selectCountry(String country) {
        staticDropdownByVisibleText(country_Dropdown_Elmnt, country);
    }


    public void selectState(String state) {
        staticDropdownByVisibleText(state_Dropdown_Elmnt, state);
    }


    public void selectCity(String city) {
        staticDropdownByVisibleText(city_Dropdown_Elmnt, city);
    }

    public void clearPincode() {
        pincode_Field_Elmnt.clear();
    }

    public void setPincode(String pincode) {
        pincode_Field_Elmnt.sendKeys(pincode);
    }

    //----------------------------------------------------------------Contact Person Tab Handling----------------------------------------------------------------


    public void setContPersnName(String name) {
        // WaitforInvisiblityoftheElement(toastMsgElmnt,5);
        serviceProvider_Name_Field_Elmnt.sendKeys(name);
    }

    public void setMobNum(String mobNum) {
        mobile_Num_Field_Elmnt.sendKeys(mobNum);
    }

    public void setEmailAdd(String emailAdd) {
        serviceProvider_Email_Field_Elmnt.sendKeys(emailAdd);
    }

    public void setPrimaryContPersn() {
        primary_Contact_Chkbx_Elmnt.click();
    }


    public void clearContPersnName(String text) {
        //    waitforTextinElement(contPersnNameElmnt,text, 5);
        serviceProvider_Name_Field_Elmnt.click();
        serviceProvider_Email_Field_Elmnt.clear();
    }


    public void clearMobNum() {
        mobile_Num_Field_Elmnt.click();
        mobile_Num_Field_Elmnt.clear();
    }


    public void clearEmailAdd() {
        serviceProvider_Email_Field_Elmnt.click();
        serviceProvider_Email_Field_Elmnt.clear();
    }


    //-------------------------------------------------------Compliance Tab Methods----------------------------------------------------


    public void setPanNo(String panNo) {
        panNoElmnt.sendKeys(panNo);
    }

    public void clearPanNo() {
        panNoElmnt.clear();
    }

    public void uploadPanFile(String filePath) {
        fileUplodElmnt.get(0).sendKeys(filePath);
    }

    public void selectMsmeReg(String option) {

        staticDropdownByVisibleText(msmeRegElmnt, option);
    }

    public void setMsmeRegNo(String msmeRegNo) {
        msme_RegNo_Elmnt.sendKeys(msmeRegNo);
    }

    public void clearMsmeRegNo() {
        msme_RegNo_Elmnt.clear();
    }

    public void setMsmeExpDate(String date) throws ParseException {
        msme_Exp_Date_Elmnt.sendKeys(dateFormatter(date));
    }

    public void uploadMsmeCerti(String memeFilePath) {
        fileUplodElmnt.get(1).sendKeys(memeFilePath);
    }

    public void setE_Inv() {
        E_InvYesElmnt.click();
    }


    public String getPanErrorMsg() {
        return pan_Err_Msg_Elmnt.getText();
    }

    public List<String> getComplianceErrorMsg() {
        List<String> err_Msgs = new ArrayList<>();
        for (WebElement element : compliance_Err_Msg_Elmnt) {
            err_Msgs.add(element.getText());
        }

        return err_Msgs;

    }

    public List<String> verifyUploadedFileText(String[] text) {
        int i = 0;
        List<String> file_Name = new ArrayList<String>();
        for (WebElement element : uploadFile_Text_Elmnts) {
            waitforTextinElement(element, text[i++], 5);
            file_Name.add(element.getText());

        }
        return file_Name;

    }


    //-----------------------------------------Gst Details in Compliance--------------------------------------------------------

    public void selectGstStatus(String status) {
        staticDropdownByVisibleText(gstStatusElmnt, status);
    }

    public void setGstNo(String gst_No) {
        gstNoElmnt.sendKeys(gst_No);
    }

    public void uploadGstCerti(String gst_File_Path) {
        fileUplodElmnt.get(2).sendKeys(gst_File_Path);
    }




    public List<String> getGstFieldErr() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : gst_field_Err_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }


    //-----------------------------------------------Bank Details Tab Methods-----------------------------------------------------

    public void setIfscCode(String ifsc_Code) {
        ifsc_Code_Elmnt.sendKeys(ifsc_Code);
    }

    public void setBankName(String bank_Name) {
        bank_Name_Elmnt.sendKeys(bank_Name);
    }

    public void setBranchName(String branch_Name) {
        branch_Name_Elmnt.sendKeys(branch_Name);
    }

    public void selectBankAccType(String bank_AccType) {
        staticDropdownByVisibleText(acc_Type_Elmnt, bank_AccType);
    }

    public void setAccHolderName(String acc_Holder_Name) {
        acc_holder_Elmnt.sendKeys(acc_Holder_Name);
    }

    public void clearAccHolderName() {
        acc_holder_Elmnt.clear();
    }

    public void setAccNum(String acc_Num) {
        acc_num_Elmnt.sendKeys(acc_Num);
    }

    public void uploadCheque(String filePath) {
        fileUplodElmnt.get(0).sendKeys(filePath);
    }

    public void selectPayout(String option) {
        staticDropdownByVisibleText(payout_Type_Elmnt, option);
    }

    public List<String> getBankFieldErrMsg() {
        List<String> string = new ArrayList<String>();
        for (WebElement element : bank_field_Err_Elmnts) {
            string.add(element.getText());
        }
        return string;
    }

    public String getBankAccErrMsg() {
        return bank_Acc_Err_Elmnt.getText();
    }


    //-----------------------------------------------Sub Code Mapping Tab Methods----------------------------------------------------------------


    public void selectSiteGst(String site_Gst) {
        staticDropdownByVisibleText(gst_Id_Elmnt, site_Gst);
    }

    public void selectSiteAddress(String site_Address) {
        staticDropdownByVisibleText(address_Dropdown_options, site_Address);

    }

    public void setServiceProviderSiteName(String address) {
        site_Name_Elmnt.sendKeys(address);
    }

    public void setServiceProviderSiteID(String id) {
        site_Code_Elmnt.sendKeys(id);
    }

    public void selectSiteBank(String site_bank) {
        site_Bank_Elmnt.click();
        selectOptionCstmDropDown(dropdown_Option, site_bank);

    }

    public void selectSiteContPersn(String cont_persn) {
        site_Cont_Per_Elmnt.click();
        selectOptionCstmDropDown(dropdown_Option, cont_persn);

    }

    public void selectSiteInvoiceTerms(String inv) {
        staticDropdownByVisibleText(invoice_Pay_Terms_Elmnt, inv);
    }

    public void clickSubmitBtn() {
        submit_Btn_Elmnt.click();
    }


    public List<String> getSubMapErrMsg() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : subMap_field_Err_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }


}
