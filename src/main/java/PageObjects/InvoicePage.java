package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InvoicePage extends AbstractComponents {
    WebDriver driver;

    public InvoicePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = ".h5")
    private WebElement invoice_Count_Elmnt;

    @FindBy(xpath = "//small[contains(text(),'Total Count')]")
    private WebElement total_count_Elmnt;

    @FindBy(css = ".btn-sm.btn-primary")
    private WebElement upload_invoice_btn_Elmnt;

    @FindBy(css = ".ng-value-label")
    private WebElement dropdown_PlaceHolder_Elmnt;

    @FindBy(id = "vendor_code")
    private WebElement vendor_Select_Elmnt;

    @FindBy(css = ".ng-option-label")
    private List<WebElement> dropdown_Options;

    @FindBy(xpath = "//input[@placeholder='Invoice Number']")
    private WebElement invoice_Num_Elmnt;

    @FindBy(id = "po_number")
    private WebElement po_Num_Select_Elmnt;

    @FindBy(id = "mrn_number")
    private WebElement mrn_Num_Select_Elmnt;

    @FindBy(css = "#file")
    private WebElement file_Upload_Elmnts;

    @FindBy(xpath = "//mat-icon[contains(text(),'download')]")
    private WebElement download_Btn_Elmnt;

    @FindBy(xpath = "//span[contains(text(),'Submit')]")
    private WebElement submit_Btn_Elmnt;


    public String getInvoiceCount() {
        waitforTextinElement(total_count_Elmnt, "Total Count", 4);
        return invoice_Count_Elmnt.getText();
    }


    public void clickUploadInvBtn() {
        upload_invoice_btn_Elmnt.click();
        waitforTextinElement(dropdown_PlaceHolder_Elmnt, "Please Select", 4);
    }

    public void selectVendor(String vendor) {
        vendor_Select_Elmnt.click();

        selectOptionCstmDropDown(dropdown_Options, vendor);
    }

    public void setInvoiceNum(String invoiceNum) {
        invoice_Num_Elmnt.sendKeys(invoiceNum);
    }

    public void selectPoNum(String poNum) {
        po_Num_Select_Elmnt.click();

        selectOptionCstmDropDown(dropdown_Options, poNum);

    }

    public void selectMrnNum(String poNum) {
        mrn_Num_Select_Elmnt.click();
        selectOptionCstmDropDown(dropdown_Options, poNum);

    }


    public void uploadInvoiceFile(String filePath) {

        file_Upload_Elmnts.sendKeys(filePath);
        waitfortheVisibilityofElement(download_Btn_Elmnt, 5);


    }

    public void clickSubmitBtn() {
        waitforTheElementtoBeClickable(submit_Btn_Elmnt, 5);
        submit_Btn_Elmnt.click();
    }

}
