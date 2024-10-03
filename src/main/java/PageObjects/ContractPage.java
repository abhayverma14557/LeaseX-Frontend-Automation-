package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ContractPage extends AbstractComponents {

    WebDriver driver;

    public ContractPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //-------------------------------------Common Locators----------------------------------------------------------------

    @FindBy(xpath = "//div[@title='Add Service']")
    private WebElement add_Record_Btn_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='propertyId']")
    private WebElement property_Dropdown_Elmnt;




    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private WebElement remarks_Field_Elmnt;

    @FindBy(css = ".mat-error")
    private List<WebElement> fields_Err_Elmnt;


    //-------------------------------------Basic Tab Locators----------------------------------------------------------------
    @FindBy(xpath = "//label[@data-bs-toggle='modal']")
    private WebElement add_Contract_Btn_Elmnt;

    @FindBy(xpath = "//div/span[@class='fw-bold']")
    private List<WebElement> contract_Radio_Elmnts;

    @FindBy(xpath = "//button[@routerlink='/layout/lease']")
    private WebElement lease_Btn_Elmnt;

    @FindBy(xpath = "//select[@aria-label='example']")
    private WebElement lesse_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Lease No']")
    private WebElement lease_No_Field_Elmnt;

    @FindBy(xpath = "//select[@id='vm_frm_lbl_country']")
    private WebElement country_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@id='vm_frm_lbl_state']")
    private WebElement state_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@id='vm_frm_lbl_city']")
    private WebElement city_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Search Store']")
    private WebElement store_Search_Field_Elmnt;

    @FindBy(css = ".dropdown-item")
    private List<WebElement> option_List_Elmnts;

    @FindBy(xpath = "//input[@placeholder='LeaseX Location Code']")
    private WebElement location_Code_Field_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Location Name']")
    private WebElement location_Name_Field_Elmnt;

    @FindBy(xpath = "//input[@placeholder='ERP Location Code']")
    private WebElement erp_Location_Code_Field_Elmnt;

    @FindBy(css = "#vm_frm_lbl_nature_contract")
    private WebElement contract_Nature_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Contract Start Date']")
    private WebElement contract_Start_Date_Field_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Contract (Lease) Period (In-Months)']")
    private WebElement contract_Period_Field_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Contract End Date']")
    private WebElement contract_End_Date_Field_Elmnt;

    @FindBy(css = "#vm_frm_lbl_signed_lease_copy")
    private WebElement lease_Copy_Status_Elmnt;

    @FindBy(css = "#file")
    private WebElement lease_Copy_File_Elmnt;

    @FindBy(css = ".mt-2.cursorPointer")
    private WebElement uplaodedFileNameElmnt;

    @FindBy(id = "vm_frm_lbl_renewable_lease_type")
    private WebElement renewable_lease_Dropdown_Elmnt;


    @FindBy(xpath = "//input[@placeholder='Registration Date']")
    private WebElement registration_Date_Field_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Possession Date']")
    private WebElement possession_Date_Field_Elmnt;

    @FindBy(xpath = "//textarea[@placeholder='Contract Description']")
    private WebElement contract_Description_Field_Elmnt;

    @FindBy(xpath = "//div[@class='card-body']/div/span")
    private WebElement lessee_Dropdown_Err_Elmnt;
    @FindBy(xpath = "//div[@class='text-danger']")
    private List<WebElement> fields_Err_Msg_Elmnt;

    //-------------------------------------Property Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//a[contains(text(),'Add Property')]")
    private WebElement add_Property_Btn_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Search by username, full name or email...']")
    private WebElement search_Bar_Elmnt;

    //List of Properties Add Properties Form
    @FindBy(xpath = "//div[@class='ms-3']/strong")
    private List<WebElement> prop_List_Elmnt;

    //Checkbox of Properties in Add Properties Form
    @FindBy(css = "#allow_phone")
    private List<WebElement> allow_Phone_Chbx_Elmnt;


    //Associate Button of Add Properties Form
    @FindBy(xpath = "//button[contains(text(),'Associate')]")
    private WebElement associate_Btn_Elmnt;

    //Delete Button of Added Properties
    @FindBy(css = ".fa.fa-trash-o")
    private List<WebElement> delete_Btn_Elmnt;


    //-------------------------------------Allocation Tab Locators----------------------------------------------------------------

    //Add Allocation Button

    @FindBy(xpath = "//select[@formcontrolname='componentId']")
    private WebElement component_Dropdown_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='entityId']")
    private WebElement landlord_Dropdown_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='allocationPercentage']")
    private WebElement allocation_Percentage_Field;

    @FindBy(xpath = "//div[@title='Save']")
    private WebElement save_Record_Btn_Elmnt;

    @FindBy(xpath = "//div[@title='Remove Service']")
    private WebElement remove_Record_Btn_Elmnt;

    @FindBy(css = ".mat-simple-snack-bar-content")
    private WebElement snack_Bar_Elmnt;


    //-------------------------------------Deposit Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//li[contains(text(),' Deposit')]")
    private WebElement deposit_Tab_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='amount']")
    private WebElement amount_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='releaseDate']")
    private WebElement release_Date_Calendar_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='paymentRef']")
    private WebElement payment_Ref_Field_Elmnt;

    @FindBy(css = ".mat-error.ng-star-inserted")
    private List<WebElement> deposit_Field_Err_Elmnt;

    //-------------------------------------Other Payout Tab Locators----------------------------------------------------------------


    @FindBy(xpath = "//input[@formcontrolname='totalAmount']")
    private WebElement total_Amount_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='sharedPercentage']")
    private WebElement share_Field_Elmnt;


    @FindBy(xpath = "//input[@formcontrolname='lesseeAmount']")
    private WebElement lessee_Amount_Field_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='frequencyId']")
    private WebElement frequency_Dropdown_Elmnt;


    @FindBy(xpath = "//select[@formcontrolname='entityTypeId']")
    private WebElement pay_Dropdown_Elmnt;


    //-------------------------------------Terms and Condition Tab Locators----------------------------------------------------------------


    @FindBy(xpath = "//input[@formcontrolname='Date']")
    private WebElement commence_Date_Calendar_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='noticePeriodLessee']")
    private WebElement notice_Period_Lessee_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='noticePeriodLessor']")
    private WebElement notice_Period_Lessor_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='lockinPeriodStartDate']")
    private WebElement start_Locking_Period_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='lockinPeriodEndDate']")
    private WebElement end_Locking_Period_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='lockinPeriodinMonths']")
    private WebElement locking_Duration_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='auditedSalesCertificateSubmissionDueDate']")
    private WebElement sales_Cert_Duration_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='initialAgreementPeriod']")
    private WebElement agreement_Period_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='whetherAuditedSalesCertificateRequired']")
    private WebElement audit_Sales_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='salesCertificateRequiredFrom']")
    private WebElement sales_Certificate_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='frequencyofAuditedSalesCertificateRequired']")
    private WebElement sales_Certificate_Frequency_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='paymentCycle']")
    private WebElement payment_Cycle_Elmnt;


    @FindBy(xpath = "//input[@formcontrolname='startDate']")
    private WebElement component_Start_Date_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='endDate']")
    private WebElement component_End_Date_Elmnt;

    @FindBy(xpath = "//div/span[@class='text-danger']")
    private List<WebElement> terms_Condition_Field_Error_Elmnt;

    //-------------------------------------INDAS116 Tab Locators----------------------------------------------------------------

    @FindBy(xpath = "//select[@formcontrolname='accountingRequired']")
    private WebElement lease_Accounting_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='accountingRequired']")
    private WebElement lease_Selected_Value_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='nonCancellablePeriod']")
    private WebElement lease_Cancellable_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='borrowingRateInPercentage']")
    private WebElement borrowing_Rate_Elmnt;

    @FindBy(xpath = "//span[@class='text-danger']")
    private List<WebElement> indas116_Field_Error_Elmnt;
    //-------------------------------------Document Tab Locators----------------------------------------------------------------


    //-------------------------------------Rent Tab Locators----------------------------------------------------------------


    @FindBy(xpath = "//h6/a[@class='component-add']")
    private WebElement component_Add_Elmnt;

    @FindBy(xpath = "//select[@class='form-select']")
    private WebElement component_Type_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@name='componenetName']")
    private WebElement component_Name_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@name='payoutFrequency']")
    private WebElement payout_Frequency_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@name='calculationType']")
    private WebElement calculation_Type_Dropdown_Elmnt;

    @FindBy(css = "#add-invoice")
    private WebElement add_Invoice_Btn_Elmnt;

    @FindBy(xpath = "//div[@title='Minimum Guarantee']")
    private WebElement minimum_Guarantee_Btn_Elmnt;

    @FindBy(xpath = "//div[contains(text(),'+')]")
    private WebElement plus_Elmnt;

    @FindBy(xpath = "//div[contains(text(),'-')]")
    private WebElement subtract_Elmnt;

    @FindBy(xpath = "//div[contains(text(),'*')]")
    private WebElement multiply_Elmnt;

    @FindBy(xpath = "//div[@class='components-list']/div")
    private List<WebElement> divide_Elmnt;

    @FindBy(xpath = "//div[contains(text(),'(')]")
    private WebElement open_Bracket_Elmnt;

    @FindBy(xpath = "//div[contains(text(),')')]")
    private WebElement close_Bracket_Elmnt;


//    @FindBy(xpath = "//div[@class='components-list']/div")
//    private List<WebElement> formula_Elmnts;

    @FindBy(css = ".form-select.col-6")
    private WebElement formula_Type_Dropdown_Elmnt;

    @FindBy(xpath = "//select[@name='componenetName']")
    private WebElement formula_Component_Dropdown_Elmnt;

    @FindBy(xpath = "//label[@for='cb0']")
    private WebElement conditional_Expression_Radio_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Title']")
    private WebElement formula_Title_Elmnt;

    @FindBy(xpath = "//input[@placeholder='Formula']")
    private List<WebElement> formula_Field_Elmnt;

    @FindBy(css = ".text-center.mr-2")
    private WebElement formula_Condition_Dropdown_Elmnt;

    @FindBy(css = ".swal2-confirm")
    private WebElement ok_Btn;

    //--------------------------------------Locators for Verification of the Contract----------------------------------------------------------------

    @FindBy(xpath = "//td[1]")
    private List<WebElement> contract_Code_Elmnt;

    @FindBy(xpath = "//td[5]")
    private List<WebElement> contract_Store_Elmnt;


    //-------------------------------------Contract Page Locators Handling Functions----------------------------------------------------------------


    public void clickAddContractButton() {
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
        add_Contract_Btn_Elmnt.click();
    }

    public void selectContract(String contract_Type) {
        for (WebElement element : contract_Radio_Elmnts) {
            if (element.getText().equalsIgnoreCase(contract_Type)) {
                element.click();
                break;
            }
        }
    }

    public void clickProceedButton() {
        lease_Btn_Elmnt.click();
        waitforInvisiblityoftheElement(spinner_Elmnt, 5);


    }

    //-------------------------------------Common Tab Locators Handling Functions----------------------------------------------------------------


    public void selectComponent(String component) {
        staticDropdownByVisibleText(component_Dropdown_Elmnt, component);
    }

    public void selectAllocationProperty(String property) {
        staticDropdownByVisibleText(property_Dropdown_Elmnt, property);

    }


    public void selectLandlord(String landlord) {
        staticDropdownByVisibleText(landlord_Dropdown_Elmnt, landlord);
    }

    public void setRemark(String remarks) {
        remarks_Field_Elmnt.sendKeys(remarks);
    }

    //Mehtod to Upload File
    public void uploadFile(String file_Path) {
        file_Upload_Elmnt.sendKeys(file_Path);
    }


    //Method to Save Record
    public void clickSaveRecordButton() {
        save_Record_Btn_Elmnt.click();
    }

    public void clickAddRecordButton() {

        add_Record_Btn_Elmnt.click();

    }




    public List<String> getFieldsErrMsg() {

        List<String> err_Msgs = new ArrayList<String>();
        for (WebElement element : fields_Err_Elmnt) {
            err_Msgs.add(element.getText());
        }
        return err_Msgs;
    }

    //-------------------------------------Basic Tab Locators Handling Functions----------------------------------------------------------------


    public void selectLessee(String lessee) {
        staticDropdownByVisibleText(lesse_Dropdown_Elmnt, lessee);
    }

    public void setLeaseNum(String lease_Num) {
        lease_No_Field_Elmnt.sendKeys(lease_Num);
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


    public void setSearchStore(String search_Store) throws InterruptedException {
        Thread.sleep(2000);
        store_Search_Field_Elmnt.sendKeys(search_Store);
        waitforDynamicLoading(store_Search_Field_Elmnt, "aria-expanded", "true", 4);

    }

    public void selectElementFromSearchStore(String search_Store) throws InterruptedException {

        for (WebElement element : option_List_Elmnts) {
            if (element.getText().contains(search_Store)) {
                element.click();
                break;
            }
        }
    }

    public void setLocationCode(String location) {
        location_Code_Field_Elmnt.sendKeys(location);
    }

    public void setLocationName(String location_Name) {
        location_Name_Field_Elmnt.sendKeys(location_Name);
    }

    public void setErpLocationCode(String erp_Location) {
        erp_Location_Code_Field_Elmnt.sendKeys(erp_Location);
    }

    public void selectContractNature(String contract_Nature) {
        staticDropdownByVisibleText(contract_Nature_Dropdown_Elmnt, contract_Nature);
    }


    public void setContractStartDate(String start_Date) throws ParseException {

        String formattedDate = dateFormatter(start_Date);

        contract_Start_Date_Field_Elmnt.sendKeys(formattedDate);
    }

    public void setContractPeriod(String contract_Period) {
        contract_Period_Field_Elmnt.sendKeys(contract_Period);
    }

    public String getContractPeriodFromField() {
        return contract_Period_Field_Elmnt.getText();
    }

    public void setContractEndDate(String end_Date) throws ParseException {
        String formattedDate = dateFormatter(end_Date);
        contract_End_Date_Field_Elmnt.sendKeys(formattedDate);
    }

    public String calculateContractPeriod(String startDate, String endDate) {
        int years = (Integer.parseInt(endDate.split("/")[2]) - Integer.parseInt(startDate.split("/")[2])) * 12;
        int months = Integer.parseInt(endDate.split("/")[1]) - Integer.parseInt(startDate.split("/")[1]);

        return String.valueOf(months + years);

    }

    public void getContractEndDate() {
        contract_End_Date_Field_Elmnt.getText();
    }

    public void selectLeaseCopyStatus(String status) {
        staticDropdownByVisibleText(lease_Copy_Status_Elmnt, status);
    }

    public void uploadLeaseCopy(String lease_Copy_Path) {
        lease_Copy_File_Elmnt.sendKeys(lease_Copy_Path);
    }

    public String getUploadedFileName() {
        waitfortheVisibilityofElement(uplaodedFileNameElmnt, 5);
        return uplaodedFileNameElmnt.getText();
    }

    public void selectRenewableLeaseType(String lease_Type) {

        switch (lease_Type.trim()) {
            case "Auto Closure" -> staticDropdownByIndex(renewable_lease_Dropdown_Elmnt, 1);
            case "Renewable" -> staticDropdownByIndex(renewable_lease_Dropdown_Elmnt, 2);

        }


    }

    public void setRegistrationDate(String registration_Date) {
        registration_Date_Field_Elmnt.sendKeys(registration_Date);

    }

    public void setPossessionDate(String possession_Date) {
        possession_Date_Field_Elmnt.sendKeys(possession_Date);

    }

    public void setContractDescription(String description) {
        contract_Description_Field_Elmnt.sendKeys(description);
        action.moveToElement(mynd_Logo).build().perform();

    }

    public String getLesseeDropdownErrMsg() {
        return lessee_Dropdown_Err_Elmnt.getText();
    }

    public List<String> getBasicFieldsErrMsg() {
        List<String> error_msgs = new ArrayList<>();

        for (WebElement element : fields_Err_Msg_Elmnt) {
            error_msgs.add(element.getText());
        }
        return error_msgs;
    }


    //-------------------------------------Property Tab Locators Handling Functions----------------------------------------------------------------


    public void clickAddPropertyBtn() {


        add_Property_Btn_Elmnt.click();
    }

    public void searchProperty(String search_Text) {
        search_Bar_Elmnt.sendKeys(search_Text);
    }


    public void selectProperty(String text) throws InterruptedException {
        waitforListofElement(prop_List_Elmnt, 5);
        System.out.println(prop_List_Elmnt.size());
        for (WebElement element : prop_List_Elmnt) {
            if (element.getText().contains(text)) {
                allow_Phone_Chbx_Elmnt.get(prop_List_Elmnt.indexOf(element)).click();
                break;
            }
        }
    }

    public void clickAssociateBtn() {
        associate_Btn_Elmnt.click();
    }


    //-------------------------------------Allocation Tab Locators Handling Functions----------------------------------------------------------------


    public void setAllocationPercenttage(String allocationPercent) {
        allocation_Percentage_Field.sendKeys(allocationPercent);
    }

    public void selectDropdownProperty(String property) {
        staticDropdownByVisibleText(property_Dropdown_Elmnt, property);
    }


    public String geSnackBarText() {
        return snack_Bar_Elmnt.getText();
    }

    //-------------------------------------Deposit Tab Locators Handling Functions----------------------------------------------------------------

    public void setAmount(String amount) {
        amount_Field_Elmnt.sendKeys(amount);
    }

    public void setReleaseDate(String releaseDate) throws ParseException {

        release_Date_Calendar_Elmnt.sendKeys(dateFormatter(releaseDate));
    }

    public void setPaymentReference(String paymentReference) {
        payment_Ref_Field_Elmnt.sendKeys(paymentReference);
    }

    public List<String> getDepositFieldErrMsg() {
        List<String> err_msgs = new ArrayList<String>();
        for (WebElement element : deposit_Field_Err_Elmnt) {
            err_msgs.add(element.getText());
        }
        return err_msgs;
    }

    //-------------------------------------Other Tab Locators Handling Functions----------------------------------------------------------------

    public void setTotalAmount(String totalAmount) {
        total_Amount_Field_Elmnt.sendKeys(totalAmount);
    }

    public void setSharePercentage(String sharePercentage) {
        share_Field_Elmnt.sendKeys(sharePercentage);
    }

    public void selectFrequency(String frequency) {
        staticDropdownByVisibleText(frequency_Dropdown_Elmnt, frequency);
    }

    public void selectWhomToPay(String person) {
        staticDropdownByVisibleText(pay_Dropdown_Elmnt, person);
    }

    //-------------------------------------Terms and Condition Locators Handling Functions----------------------------------------------------------------


    public String getRentCommenceDate() {
        return commence_Date_Calendar_Elmnt.getText();
    }

    public void setRentCommenceDate(String commence_Date) {
        commence_Date_Calendar_Elmnt.sendKeys(commence_Date);
    }

    public void clearRentCommenceDate() {
        commence_Date_Calendar_Elmnt.clear();
    }

    public void setLesseeNoticePeriod(String lessee_NP) {

        notice_Period_Lessee_Elmnt.sendKeys(lessee_NP);

    }

    public void setLessorNoticePeriod(String lessor_NP) {
        notice_Period_Lessor_Elmnt.sendKeys(lessor_NP);

    }

    public void setLockingStartDate(String locking_Start_Date) throws ParseException {
        start_Locking_Period_Elmnt.sendKeys(dateFormatter(locking_Start_Date));
    }

    public void setLockingEndDate(String locking_End_Date) throws ParseException {
        end_Locking_Period_Elmnt.sendKeys(dateFormatter(locking_End_Date));
    }

    public void setLockingPeriod(String locking_Period) {
        locking_Duration_Elmnt.sendKeys(locking_Period);
    }


    public void setSalesCertDate(String cert_Date) throws ParseException {
        sales_Cert_Duration_Elmnt.sendKeys(dateFormatter(cert_Date));
    }

    public void setAgreementPeriod(String agreement_Period) {
        agreement_Period_Elmnt.sendKeys(agreement_Period);
    }

    public void selectAuditSalesFreq(String audit_SalesFreq) {
        staticDropdownByVisibleText(audit_Sales_Elmnt, audit_SalesFreq);
    }

    public void selectSalesCerificate(String sales) {
        staticDropdownByVisibleText(sales_Certificate_Elmnt, sales);
    }

    public void selectSalesCertificateFreq(String sales_freq) {
        staticDropdownByVisibleText(sales_Certificate_Frequency_Elmnt, sales_freq);
    }

    public void selectPaymentCycle(String payment_Cycle) {
        staticDropdownByVisibleText(payment_Cycle_Elmnt, payment_Cycle);
    }


    public void setComponentStartDate(String start_Date) throws ParseException {
        component_Start_Date_Elmnt.sendKeys(dateFormatter(start_Date));
    }

    public void setComponentEndDate(String end_Date) throws ParseException {
        component_End_Date_Elmnt.sendKeys(dateFormatter(end_Date));
    }

    public List<String> getTermsandConditionsFieldMsg() throws ParseException {

        List<String> fetched_Msg = new ArrayList<>();
        for (WebElement element : terms_Condition_Field_Error_Elmnt) {
            fetched_Msg.add(element.getText());
        }
        return fetched_Msg;
    }


    //-------------------------------------INDAS 116 Locators Handling Functions----------------------------------------------------------------

    public void setBorrowingRate(String borrow_Rate) {
        borrowing_Rate_Elmnt.sendKeys(borrow_Rate);
    }

    public String getLeaseAccount() {
        return lease_Accounting_Elmnt.getText();
    }

    public String getSelectedLeaseOption() {
        return getSelectedTextStaticDropDown(lease_Accounting_Elmnt);
    }

    public void setLeaseAccounting(String lease_Account) {
        staticDropdownByVisibleText(lease_Accounting_Elmnt, lease_Account);
    }

    public String getLeaseCancellableDate() {
        return lease_Cancellable_Elmnt.getText();
    }

    public void setLeaseCancellableDate(String cancellable_Date) {
        lease_Cancellable_Elmnt.sendKeys(cancellable_Date);

    }
    public List<String> getIndasFieldErrMsg()
    {
        List< String> msg= new ArrayList<>();
        for (WebElement element : indas116_Field_Error_Elmnt)
        {
            msg.add(element.getText());
        }
        return  msg;
    }
//-------------------------------------Document Locators Handling Functions----------------------------------------------------------------


    //-------------------------------------Rent Locators Handling Functions----------------------------------------------------------------
    public void clickAddComponentBtn() {
        waitforInvisiblityoftheElement(spinner_Elmnt, 5);
        component_Add_Elmnt.click();
    }

    public void selectComponentType(String component_Type) {
        staticDropdownByVisibleText(component_Type_Dropdown_Elmnt, component_Type);
    }

    public void selectComponentName(String component) {
        staticDropdownByVisibleText(component_Name_Dropdown_Elmnt, component);

    }

    public void selectPayoutFrequency(String payoutFrequency) {
        staticDropdownByVisibleText(payout_Frequency_Dropdown_Elmnt, payoutFrequency);
    }

    public void selectCalculationType(String calculationType) {
        staticDropdownByVisibleText(calculation_Type_Dropdown_Elmnt, calculationType);
    }

    public void clickAddInvoiceBtn() {
        scrollToElement(mynd_Logo);
        add_Invoice_Btn_Elmnt.click();
    }

    public void selectFormulaType(String formulaType) {
        staticDropdownByVisibleText(formula_Type_Dropdown_Elmnt, formulaType);
    }

    public void selectBaseComponent(String baseComponent) {
        staticDropdownByVisibleText(formula_Component_Dropdown_Elmnt, baseComponent);
    }

    public void selectConditionalExpression() {
        conditional_Expression_Radio_Elmnt.click();
    }


    public void setFormulaTitle(String formulaTitle) {
        formula_Title_Elmnt.sendKeys(formulaTitle);
    }

    public void setFormula(String formula, int fieldNumber) {

        for (String formulaElem : formula.split(",")) {
            switch (formulaElem) {

                case "MG" ->
                        action.dragAndDrop(minimum_Guarantee_Btn_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case "+" -> action.dragAndDrop(plus_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case "-" ->
                        action.dragAndDrop(subtract_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case "/" ->
                        action.dragAndDrop(divide_Elmnt.get(1), formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case "*" ->
                        action.dragAndDrop(multiply_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case "(" ->
                        action.dragAndDrop(open_Bracket_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                case ")" ->
                        action.dragAndDrop(close_Bracket_Elmnt, formula_Field_Elmnt.get(fieldNumber - 1)).build().perform();
                default -> formula_Field_Elmnt.get(fieldNumber - 1).sendKeys(formulaElem);

            }

        }

    }

    public void selectFormulaCondition(String formulaCondition) {

        staticDropdownByVisibleText(formula_Condition_Dropdown_Elmnt, formulaCondition);
    }


    public void clickPopUpOkBtn() {
        waitfortheVisibilityofElement(ok_Btn, 3);
        ok_Btn.click();
    }

    public List<String> getStoreListData() {
        List<String> list_Data = new ArrayList<>();
        for (WebElement element : contract_Store_Elmnt) {
            list_Data.add(element.getText());
        }
        return list_Data;
    }
}




