package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SalesPage extends AbstractComponents {
   WebDriver driver;
    public SalesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Common Locators

    //---------------------------Sales Page Locators--------------------------------------------------------

    @FindBy(css = "label[for='btnradi1']")
    private WebElement add_Sales_Btn;

    @FindBy(css=".mat-simple-snackbar")
    private WebElement snackbar_Elmnt;

    //---------------------------Bulk Upload Locators--------------------------------------------------------

    @FindBy(css = ".btn-upload")
    private WebElement master_Data_Upload_Btn;

    //---------------------------Single Store Tab Locators--------------------------------------------------------

    @FindBy(xpath = "//a[@href='#single']")
    private WebElement single_Store_Tab_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='storeId']")
    private WebElement store_Code_dropdown_Elmnt;

    @FindBy(xpath = "//select[@formcontrolname='salesComponentType']")
    private WebElement sales_Component_dropdown_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='totalSales']")
    private WebElement total_Sales_Field_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='salesPeriodFrom']")
    private WebElement sale_Period_Start_Elmnt;

    @FindBy(xpath = "//input[@formcontrolname='salesPeriodTill']")
    private WebElement sale_Period_End_Elmnt;


    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private List<WebElement> remarks_Field_Elmnt;

    @FindBy(xpath = "//button[contains(text(),'Approve')]")
    private WebElement approve_Btn_Elmnt;

    @FindBy(css=".text-danger")
    private List<WebElement> fields_Err_Msg_Elmnt;
    //------------------------------------------Common Methods----------------------------------------------------------------


    //-----------------------Sales Page Handling Mehtod--------------------------------------------------------

    public void clickAddSalesBtn() {
        add_Sales_Btn.click();
    }

    public String getSnackBarMsg()
    {
       return snackbar_Elmnt.getText();
    }
    //---------------------------Bulk Upload Handling Methods--------------------------------------------------------

    public void clickMasterUploadBtn() {
        master_Data_Upload_Btn.click();

    }
    public void uploadMaster(String file_Path) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\abhay.verma\\IdeaProjects\\MyndLeaseX\\src\\main\\java\\Resources\\FileUploadScript.exe", (System.getProperty("user.dir") + file_Path));
        Process pr = pb.start();
        pr.waitFor();
    }

    //---------------------------Single Store Upload Handling Methods--------------------------------------------------------

    public void navigateToSingleStore()
    {
        single_Store_Tab_Elmnt.click();
    }

    public void selectStoreCode(String store)
    {
        staticDropdownByVisibleText(store_Code_dropdown_Elmnt, store);
    }

    public void selectSalesCompType(String sales_Comp_Type)
    {
        staticDropdownByVisibleText(sales_Component_dropdown_Elmnt, sales_Comp_Type);
    }

    public void setTotalSalse(String total_Salse)
    {
        total_Sales_Field_Elmnt.sendKeys(total_Salse);
    }

    public void setSalePeriodFrom(String sale_Period) throws ParseException {
        sale_Period_Start_Elmnt.sendKeys(dateFormatter(sale_Period));
    }

    public void setSalesPeriodTill(String sale_Period) throws ParseException {

        sale_Period_End_Elmnt.sendKeys(dateFormatter(sale_Period));
    }

    public void setSalesRemarks(String remarks,int number)
    {
        waitfortheVisibilityofElement(remarks_Field_Elmnt.get(number-1),3);
        remarks_Field_Elmnt.get(number-1).sendKeys(remarks);
    }

    public void clickApproveBtn() {
        approve_Btn_Elmnt.click();
    }

    public List<String> getSalesFieldsErrMsg()
    {
        List <String>msgs = new ArrayList();
        for (WebElement element: fields_Err_Msg_Elmnt)
        {
            msgs.add(element.getText());
        }
        return msgs;
    }
}
