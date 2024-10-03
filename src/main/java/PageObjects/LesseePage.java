package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class LesseePage extends AbstractComponents {


    WebDriver driver;

    public LesseePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //---------------------------------------------Lessee Page Locators----------------------------------------------------
    @FindBy(xpath = "//label[contains(text(),'Register')]")
    private WebElement registration_Btn;

    @FindBy(xpath = "//tr/td[2]")
    private List<WebElement> lessor_List;

    @FindBy(xpath = "//nav/ul/li[@class='ng-star-inserted']")
    private List<WebElement> num_Of_page;

    @FindBy(css = "li[class='ng-star-inserted']")
    private List<WebElement> lessee_Steps_Elmnts;


    @FindBy(css = ".pagination-next")
    private WebElement page_Next_Btn;

    //---------------------------------------Basic Tab Locators----------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='Enter Name']")
    private WebElement lessee_Name_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Enter Email']")
    private WebElement lessee_Email_Elmnt;

    @FindBy(css = "select[formcontrolname='entity_type']")
    private WebElement lessee_Type_Elmnt;

    @FindBy(css = "input[formcontrolname='erp_code']")
    private WebElement erpCodeElmnt;

    @FindBy(css = "input[formcontrolname='cin_number']")
    private WebElement cinNumberElmnt;

    @FindBy(xpath = "//li[contains(text(),'Address')]")
    private WebElement addressTab_Btn;


    //-----------------------------------------Address Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//button[contains(text(),'Add')]")
    private WebElement addBtnElmnt;

    @FindBy(xpath = "//select[@formcontrolname='address_type']")
    private WebElement addressTypeElmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line1']")
    private WebElement addressLine1Elmnt;

    @FindBy(xpath = "//textarea[@formcontrolname='address_line2']")
    private WebElement addressLine2Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='country_id']")
    private WebElement countryElmnt;

    @FindBy(xpath = "//select[@formcontrolname='state_id']")
    private WebElement stateElmnt;

    @FindBy(xpath = "//select[@formcontrolname='city_id']")
    private WebElement cityElmnt;

    @FindBy(xpath = "//input[@formcontrolname='zip_code']")
    private WebElement pincodeElmnt;


    @FindBy(xpath = "//tbody/tr[1]/td")
    private List<WebElement> saved_Add_List_Elmnt;

    @FindBy(xpath = "//button[@class='btn']")
    private List<WebElement> addrs_Edit_Btn_Elmnt;

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> addrsLine1_Elmnt;


    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td")
    private List<WebElement> add_List_Elmnts;


    //-----------------------------------------------Contact Person Tab Locator----------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='name']")
    private WebElement contPersnNameElmnt;

    @FindBy(xpath = "//input[@formcontrolname='mobile_no']")
    private WebElement mobNumElmnt;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement emailAddrElmnt;

    @FindBy(xpath = "//input[@formcontrolname='is_primary']")
    private WebElement primaryContPersnElmnt;

    @FindBy(xpath = "//button[@title='Edit']")
    private WebElement edit_Btn_Elmnt;

    @FindBy(xpath = "//tbody/tr/td[1]")
    private List<WebElement> saved_Cont_Person_Elmnt;

    @FindBy(xpath = "//tbody/tr/td[2]")
    private List<WebElement> saved_Mob_Num_Elmnt;

    @FindBy(xpath = "//tbody/tr/td[3]")
    private List<WebElement> saved_Email_Elmnt;


    @FindBy(xpath = "//fieldset/div/div[@class='ng-star-inserted']")
    private List<WebElement> cntact_Form_Field_Err_Msg_Elmnt;


    //-----------------------------------------------Compliance Tab Locators----------------------------------------------------------------

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


    //---------------------------------------------Gst Details in Compliance----------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='gst_status']")
    private WebElement gstStatusElmnt;

    @FindBy(xpath = "//input[@formcontrolname='gst_no']")
    private WebElement gstNoElmnt;

    @FindBy(css = "//span/div[@class='ng-star-inserted']")
    private WebElement gst_Field_Err_Elmnt;


    @FindBy(xpath = "//span/div[@class='ng-star-inserted']")
    private List<WebElement> gst_field_Err_Elmnt;


    //-----------------------------------------------Bank Details Step Locators----------------------------------------------------

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


    //--------------------------------------------------TDS Details Step------------------------------------------------


    @FindBy(xpath = "//div[@title='Add Service']")
    private WebElement add_TDS_Btn_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='component_id']")
    private WebElement tds_Comp_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='selectedValue']")
    private WebElement tds_Code_Elmnt;

    @FindBy(xpath = "//div[@title='Save']")
    private WebElement save_Btn_Elmnt;

    @FindBy(css = ".mat-mdc-form-field-error")
    private List<WebElement> tds_Err_Msg_Elmnt;

    @FindBy(xpath = "//label[contains(text(),' Component*')]")
    private WebElement component_Txt_Elmnt;


    //--------------------------------------------------Document Step Locators------------------------------------------------
    @FindBy(css = "#file")
    private List<WebElement> document_Uplod_Elmnt;

    @FindBy(xpath = "//tbody/tr/td[3]")
    private List<WebElement> file_Name_Elmnt;


    //-----------------------------------------------------Sub Code Mapping Step Locators----------------------------------------------------

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

    @FindBy(css = "select[formcontrolname='address_id'] option")
    private List<WebElement> address_Dropdown_options;

    @FindBy(xpath = "//ng-select[@formcontrolname='contact_person']")
    private WebElement site_Cont_Per_Elmnt;

    @FindBy(xpath = "//span/button[text()='Submit']")
    private WebElement submit_Btn_Elmnt;


    @FindBy(xpath = "//form/div/div/div[@class='ng-star-inserted']")
    private List<WebElement> subMap_field_Err_Elmnt;


    //----------------------------------------Remarks Pop Up Locators-------------------------------------------------------------

    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private WebElement remarks_field_Elmnt;

    @FindBy(xpath = "//div/button[text()='Submit']")
    private WebElement remarks_submit_btn_Elmnt;


    @FindBy(xpath = "//span/div[@class='ng-star-inserted']")
    private List<WebElement> field_Err_Msgs_Elmnt;


    @FindBy(xpath = "//form/div/div/div[@class='ng-star-inserted']")
    private List<WebElement> form_Fields_Err_Elmnt;


    //Method to Access all Lessor Tab
    public boolean lessorTabStatus() {
        for (WebElement element : lessee_Steps_Elmnts) {
            if (!(element.getAttribute("style").equalsIgnoreCase("cursor: not-allowed;"))) ;
            {
                element.click();
                return true;
            }
        }
        return false;
    }


//Lessor Details Step

    public void clickRegistrationBtn() {
        waitforTheElementtoBeClickable(registration_Btn, 5);
        registration_Btn.click();
    }

    public void setLessorName(String vendorName) {

        waitfortheVisibilityofElement(lessee_Name_Elmnt, 4);
        lessee_Name_Elmnt.sendKeys(vendorName);
    }


    public void clearLessorName() {
        lessee_Name_Elmnt.clear();
    }

    public void setLessorEmail(String vendorEmail) {
        lessee_Email_Elmnt.sendKeys(vendorEmail);
    }

    public void clearVendorEmail() {
        lessee_Email_Elmnt.clear();
    }


    public void selectLessorType(String lessorType) {

        staticDropdownByVisibleText(lessee_Type_Elmnt, lessorType);
    }

    public void setErpCode(String erpCode) {
        erpCodeElmnt.sendKeys(erpCode);

    }

    public void setCINNumber(String cinNumber) {
        cinNumberElmnt.sendKeys(cinNumber);
    }


    public void addressTabNavigation() {
        addressTab_Btn.click();
    }

    //---------------------------------------Address Fields Starts from Here--------------------------------------------------------

    public void clickAddButton() {
        addBtnElmnt.click();
    }


    public void selectAddressType(String addressType) {

        staticDropdownByVisibleText(addressTypeElmnt, addressType);
    }

    public void clickEditAddress(String address) {
        int i = 0;
        for (WebElement element : addrsLine1_Elmnt) {
            if (element.getText().equalsIgnoreCase(address)) {
                addrs_Edit_Btn_Elmnt.get(i++).click();
            }
        }

    }

    public void clearAddressLine1() {
        waitforDynamicLoading(addressLine1Elmnt, "class", "ng-valid", 4);
        addressLine1Elmnt.clear();

    }

    public void setAddressLine1(String addressLine1) {

        addressLine1Elmnt.sendKeys(addressLine1);

    }

    public void clearAddressLine2() {
        addressLine2Elmnt.clear();
    }

    public void setAddressLine2(String addressLine2) {
        addressLine2Elmnt.sendKeys(addressLine2);
    }


    public void selectCountry(String country) {
        staticDropdownByVisibleText(countryElmnt, country);
    }


    public void selectState(String state) {
        staticDropdownByVisibleText(stateElmnt, state);
    }


    public void selectCity(String city) {
        staticDropdownByVisibleText(cityElmnt, city);
    }

    public void clearPincode() {
        pincodeElmnt.clear();
    }

    public void setPincode(String pincode) {
        pincodeElmnt.sendKeys(pincode);
    }


    public List<String> getFormFieldErrorMsg() {
        List<String> text = new ArrayList<String>();
        for (WebElement element : form_Fields_Err_Elmnt) {
            text.add(element.getText());
        }
        return text;
    }

    public List<String> getSavedAddress() {
        List<String> text = new ArrayList<String>();
        for (WebElement element : saved_Add_List_Elmnt) {
            text.add(element.getText());
        }
        return text;
    }

    public List<String> getAddressList() {
        List<String> text = new ArrayList<String>();
        for (WebElement element : add_List_Elmnts) {
            System.out.println(element.getText());
        }
        return text;
    }


    //-----------------------------------Contact Person Step Details Methods----------------------------------------------------------------


    public void setContPersnName(String name) {
        // WaitforInvisiblityoftheElement(toastMsgElmnt,5);
        contPersnNameElmnt.sendKeys(name);
    }

    public void setMobNum(String mobNum) {
        mobNumElmnt.sendKeys(mobNum);
    }

    public void setEmailAdd(String emailAdd) {
        emailAddrElmnt.sendKeys(emailAdd);
    }

    public void setPrimaryContPersn() {
        primaryContPersnElmnt.click();
    }


    public void clearContPersnName(String text) {
        //    waitforTextinElement(contPersnNameElmnt,text, 5);
        contPersnNameElmnt.click();
        contPersnNameElmnt.clear();
    }


    public void clearMobNum() {
        mobNumElmnt.click();
        mobNumElmnt.clear();
    }


    public void clearEmailAdd() {
        emailAddrElmnt.click();
        emailAddrElmnt.clear();
    }

    public List<String> getSavedContPerson() {
        List<String> str = new ArrayList<>();
        for (WebElement element : saved_Cont_Person_Elmnt) {
            str.add(element.getText());
        }
        return str;

    }

    public List<String> getSavedMobNum() {
        List<String> str = new ArrayList<>();
        for (WebElement element : saved_Mob_Num_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }

    public List<String> getSavedEmail() {
        List<String> str = new ArrayList<>();
        for (WebElement element : saved_Email_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }


    public void clickEditBtn() {
        edit_Btn_Elmnt.click();
    }


    public List<String> getContFieldErrMsg() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : cntact_Form_Field_Err_Msg_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }


    //-----------------------------------Compliance Step Details Function--------------------------------------------------------


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


    //-------------------------------------Gst Details in Compliance--------------------------------------------------------

    public void selectGstStatus(String status) {
        staticDropdownByVisibleText(gstStatusElmnt, status);
    }

    public void setGstNo(String gst_No) {
        gstNoElmnt.sendKeys(gst_No);
    }

    public void uploadGstCerti(String gst_File_Path) {
        fileUplodElmnt.get(2).sendKeys(gst_File_Path);
    }


    public List<String> getGstStatusErr() {
        List<String> text = new ArrayList<String>();
        for (WebElement element : gst_field_Err_Elmnt) {
            text.add(element.getText());
        }
        return text;
    }

    public List<String> getGstFieldErr() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : gst_field_Err_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }


    //------------------------------------Bank Details Steps Functions--------------------------------------------------------

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


    //-------------------------------------------TDS Details Functions-----------------------------------------------------


    public void clickAddTDSBtn() {

        waitfortheVisibilityofElement(component_Txt_Elmnt, 5);
        add_TDS_Btn_Elmnt.click();
    }

    public void selectTDSComponent(String component) {


        staticDropdownByVisibleText(tds_Comp_Elmnt, component);
    }

    public void selectTDSCode(String code) {
        staticDropdownByVisibleText(tds_Code_Elmnt, code);
    }

    public void clickSaveTDSBtn() {
        save_Btn_Elmnt.click();

    }

    public List<String> getTDSFieldErrMsg() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : tds_Err_Msg_Elmnt) {
            str.add(element.getText());
        }
        return str;
    }

    //--------------------------------------Documents Step Methods----------------------------------------------------

    public void uploadGSTDocument(String file_Path) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].click();", document_Uplod_Elmnt.get(0));
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe", file_Path);
        pb.start();

//        Runtime.getRuntime().exec("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe"+ file_Path);
//            waitforTextinElement(file_Name_Elmnt.get(0), file_Path.split("Resources\\\\")[1], 5);
    }

    public void uploadAdharDocument(String file_Path) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;


        js.executeScript("arguments[0].click();", document_Uplod_Elmnt.get(1));
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe", file_Path);
        pb.start();
//            Runtime.getRuntime().exec("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe"+ file_Path);
//             waitforTextinElement(file_Name_Elmnt.get(1), file_Path.split("Resources\\\\")[1], 5);
    }


    public List<String> getUploadedFilesName(String[] text) {

        List<String> file_Name = new ArrayList<String>();
        for (WebElement element : file_Name_Elmnt) {
            waitforTextinElement(element, text[file_Name_Elmnt.indexOf(element)], 5);
            file_Name.add(element.getText());
        }
        return file_Name;
    }


    //------------------------------------------Sub Code Mapping Step Functions--------------------------------------------------------


    public void selectSiteGst(String site_Gst) {
        staticDropdownByVisibleText(gst_Id_Elmnt, site_Gst);
    }

    public void selectSiteAddress(String site_Address) {
        site_Add_Elmnt.click();

        cstmDropDownwithContains(address_Dropdown_options, site_Address);
    }

    public void setLessorSiteName(String address) {
        site_Name_Elmnt.sendKeys(address);
    }

    public void setLessorSiteID(String id) {
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

    //------------------------------------------Remarks Pop Up Functions---------------------------------------------------------

    public void setRemarks(String remarks) {
        remarks_field_Elmnt.sendKeys(remarks);
    }

    public void clickRemarksSubmitBtn() {
        remarks_submit_btn_Elmnt.click();
    }

    //------------------------------------------Verification of Lessee Successfull Registration----------------------------------------------------------------

    public List<String> lessorDetails() {
        List<String> vendor_Names = new ArrayList<String>();

        waitfortheVisibilityofElement(lessor_List.get(0), 4);
        for (WebElement element : lessor_List) {
            vendor_Names.add(element.getText());
        }
        return vendor_Names;
    }

    public int totalPages() {

        waitfortheVisibilityofElement(page_Next_Btn, 5);
        scrollToElement(page_Next_Btn);
        return num_Of_page.size();

    }

    public void clickPageNxtBtn() {
        page_Next_Btn.click();
    }

    public List<String> getErrMsg() {
        List<String> error_Msg_List = new ArrayList<String>();
        for (WebElement element : field_Err_Msgs_Elmnt) {
            error_Msg_List.add(element.getText());
        }
        return error_Msg_List;
    }


}
