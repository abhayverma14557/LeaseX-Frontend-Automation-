package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PoPage extends AbstractComponents {

    WebDriver driver;

    public PoPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".upload-button")
    private WebElement upload_Po_Btn_Elmnt;

    @FindBy(css = "#file-input")
    private WebElement file_Input_Elmnt;

    @FindBy(xpath = "//span[contains(text(),'Upload')]")
    private WebElement upload_Btn_Elmnt;

    @FindBy(xpath = "//a[@class='page-link']")
    private List<WebElement> num_Of_Pages;

    @FindBy(css = ".pagination-next")
    private WebElement page_Next_Btn_Elmnt;

    @FindBy(xpath = "//td[2]/span")
    private List<WebElement> po_List_Elmnt;


    public void clickUploadPoBtn() {

        waitforTheElementtoBeClickable(upload_Po_Btn_Elmnt, 3);
        upload_Po_Btn_Elmnt.click();
    }

    public void uploadPoFile(String filePath) {
        file_Input_Elmnt.sendKeys(filePath);

    }

    public void clickUploadBtn() {
        waitforTheElementtoBeClickable(upload_Btn_Elmnt, 3);
        upload_Btn_Elmnt.click();
    }


    public int getNumOfPages() {
        waitfortheVisibilityofElement(page_Next_Btn_Elmnt, 5);
        scrollToElement(page_Next_Btn_Elmnt);
        return (num_Of_Pages.size() - 3);

    }

    public void clickPageNextBtn() {
        waitfortheVisibilityofElement(page_Next_Btn_Elmnt, 5);
        scrollToElement(page_Next_Btn_Elmnt);
        page_Next_Btn_Elmnt.click();
    }


    public List<String> poNumDetails() {
        List<String> details = new ArrayList<String>();
        for (WebElement po : po_List_Elmnt) {
            details.add(po.getText());
        }
        return details;

    }


}
