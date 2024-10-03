package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class StorePage extends AbstractComponents {
    WebDriver driver;

    public StorePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Name Field of Basic Section
    @FindBy(xpath = "//input[@formcontrolname='name']")
    private WebElement name_Field_Elmnt;

    //ERP Store Code Field of Basic Section
    @FindBy(xpath = "//input[@formcontrolname='erpCode']")
    private WebElement erp_Code_Field_Elmnt;

    //Cost Center Field of Basic Section
    @FindBy(xpath = "//input[@formcontrolname='erpCode']")
    private WebElement cost_Center_Field_Elmnt;

    //Address Title Field of Basic Section
    @FindBy(xpath = "//input[@formcontrolname='addresstitle']")
    private WebElement addrs_Title_Field_Elmnt;


    //Add Button of Properties
    @FindBy(css = ".btn-sm")
    private WebElement prop_Add_Btn_Elmnt;


    //List of Properties Add Properties Form
    @FindBy(xpath = "//div[@class='ms-3']/strong")
    private List<WebElement> prop_List_Elmnt;

    //Checkbox of Properties in Add Properties Form
    @FindBy(css = "#allow_phone")
    private List<WebElement> allow_Phone_Chbx_Elmnt;


    //Search Bar Element of Add Properties Form
    @FindBy(xpath = "//input[@placeholder=' Search']")
    private WebElement prop_Search_Elmnt;

    //Associate Button of Add Properties Form
    @FindBy(xpath = "//button[contains(text(),'Associate')]")
    private WebElement associate_Btn_Elmnt;

    @FindBy(css = "#Viewers")
    private List<WebElement> prop_Select_Chkbx;

    //Cancel Button of Add Properties Form
    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    private WebElement cancel_Btn_Elmnt;

    @FindBy(xpath = "//button[contains(text(),'Approve')]")
    private WebElement approve_Btn_Elmnt;

    //Delete Button of Added Properties
    @FindBy(css = ".fa.fa-trash-o")
    private List<WebElement> delete_Btn_Elmnt;

    //Remarks Field Element
    @FindBy(xpath = "//input[@formcontrolname='remarks']")
    private WebElement remarks_Field_Elmnt;

    @FindBy(xpath = "//span[@class='text-danger']")
    private List<WebElement> store_Field_Err_Elmnt;


    //Basic Section Fields Methods

    public void setName(String name) {

        name_Field_Elmnt.sendKeys(name);
    }

    public void setErpCode(String erpCode) {

        erp_Code_Field_Elmnt.sendKeys(erpCode);
    }

    public void setCostCenter(String cost) {
        cost_Center_Field_Elmnt.sendKeys(cost);
    }

    public void setAddrsTitle(String title) {
        addrs_Title_Field_Elmnt.sendKeys(title);
    }

    public void clickPropertyAddBtn() {
        prop_Add_Btn_Elmnt.click();
        waitfortheVisibilityofElement(spinner_Elmnt, 5);
    }


    public void propertyToSearch(String property) {
        prop_Search_Elmnt.sendKeys(property);

    }

    public void selectProperty(String text) throws InterruptedException {
        waitforListofElement(prop_List_Elmnt, 12);
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

    public void selectPropertyToApprove(String text) {


        for (WebElement element : prop_List_Elmnt) {
            if (element.getText().contains(text)) {
                prop_Select_Chkbx.get(prop_List_Elmnt.indexOf(element)).click();
                break;
            }
        }

    }

    public void clickApproveBtn() {
        approve_Btn_Elmnt.click();

    }

    public void setRemarksField(String remarksText) {
        remarks_Field_Elmnt.sendKeys(remarksText);
    }

    public void clickCancelButton()
    {
        cancel_Btn_Elmnt.click();
    }

    public List<String> getStoreFieldErrMsg() {
        List<String> errors = new ArrayList<String>();
        for (WebElement element : store_Field_Err_Elmnt) {
            errors.add(element.getText());
        }
        return errors;
    }
}
