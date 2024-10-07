package PageObjects;

import CommonFiles.AbstractComponents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginPage extends AbstractComponents {


    private static final Logger log = LogManager.getLogger(LoginPage.class);
    WebDriver driver;

    //--------------------------Constructor to initialize the driver and implement page factory----------------------------------------------------------------
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //-----------------------------------Landing Page Locators----------------------------------------------------------------
    public void landingPage(String startPage) {
        driver.get(startPage);
    }

    //UserName Field Locator
    @FindBy(xpath = "//input[@name='password']")
    private WebElement usrname_Field;

    //Password Field Locator
    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement paswrd_Field;

    //CompanyCode Field Locator
    @FindBy(xpath = "//input[@name='text']")
    private WebElement company_Code_Field;

    //Login Button Locator
    @FindBy(css = ".btn.btn-primary")
    private WebElement login_Btn;


    @FindBy(css = ".text-danger")
    private List<WebElement> error_Msg_Elmnt;

    @FindBy(css = ".input-group-text")
    private WebElement pass_View_Btn_Elmnt;


    //----------------------------------------Login Page Locator Implementation Methods----------------------------------------------------------------

    //Functions to set and Clear the Username on Login Screen
    public void setUsername(String username) {
        usrname_Field.sendKeys(username);
    }

    public void clearUsername() {
        usrname_Field.clear();
    }

    //Function to set and Clear  the Password on Login Screen
    public void setPassword(String password) {
        paswrd_Field.sendKeys(password);
    }

    public void clearPassword() {
        paswrd_Field.clear();
    }


    //Function to set and Clear the Company Code on Login Screen
    public void setCompanyCode(String companyCode) {
        company_Code_Field.sendKeys(companyCode);
    }

    public void clearCompanyCode() {
        company_Code_Field.clear();
    }

    //Function to click on Login Button

    public PortalSelectionPage clickLoginButton() {

        login_Btn.click();

        return new PortalSelectionPage(driver);
    }

    public boolean loginButtonStatus() {

        return login_Btn.isEnabled();
    }


    public List<String> getErrorMessage() {
        List<String> str = new ArrayList<String>();
        for (WebElement element : error_Msg_Elmnt) {
            str.add(element.getText());
        }
        return str;

    }

    public String getpasswordFieldState() {
        return paswrd_Field.getAttribute("type");
    }

    public void clickPswrdViewBtn() {
        pass_View_Btn_Elmnt.click();
    }


    public Set<Cookie> getCookies() {
        return driver.manage().getCookies();

    }
}
