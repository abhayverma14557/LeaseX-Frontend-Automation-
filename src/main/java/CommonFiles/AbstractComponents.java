package CommonFiles;

import PageObjects.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractComponents {


    WebDriver driver;
    WebDriverWait webDriverWait;
    public JavascriptExecutor js;
    public Actions action;

    //Constructor to initialize the driver and implement page factory
    public AbstractComponents(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }


    //Locator to Naviagte Vendor Page from Dashboard
    @FindBy(xpath = "//span[@class='fontSize']")
    private List<WebElement> side_Menu_Elmt;


    @FindBy(xpath = "//button[contains(text(),'Next')]")    //Locator for Next Button
    private WebElement nxt_Btn_Elmnt;

    @FindBy(xpath = "//button[contains(text(),'Save')]")   //Locator for Save Button
    private List<WebElement> saveBtn;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")   //Locator for Sumbit Button
    private List<WebElement> submit_Btn;

    @FindBy(xpath = "//button[contains(text(),'Add')]")   //Locator for Add Button
    private WebElement add_Btn;

    @FindBy(css = "#toast-container")
    private List<WebElement> toastMsgElmnt;

    @FindBy(css = ".spinner")
    protected WebElement spinner_Elmnt;

    @FindBy(css = "#file")
    protected WebElement file_Upload_Elmnt;

    @FindBy(css = "img[alt='Mind Logo Icon']")
    protected WebElement mynd_Logo;

    @FindBy(css = ".pagination-next")
    private WebElement page_Next_Btn;

    @FindBy(xpath = "//nav/ul/li[@class='ng-star-inserted']")
    private List<WebElement> num_Of_page;

    @FindBy(xpath = "//li[@data-step-target='step']")
    private List<WebElement> step_Elmnts;

    @FindBy(xpath = "//div[contains(@class,'text-danger')]/div")
    private List<WebElement> fields_Common_Err_Elmnt;

    //Scroll to the Particular Web Element
    public void scrollToElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView();", element);

    }

    public void scrollToPageEnd() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    public void waitTillUrlMatches(String text, int time) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.urlContains(text));


    }


    // Explicit Wait function untill text is not appearing in the element
    public void waitforTextinElement(WebElement element, String Text, int time) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(element, Text));

    }

    public void waitforDynamicLoading(WebElement element, String attribute, String attribute_Value, int time) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(element, attribute, attribute_Value)));
    }

    // Explicit Wait function untill element is not clickable
    public void waitforTheElementtoBeClickable(WebElement element, int time) {


        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

    }

    // Explicit Wait function  untill element is not visible
    public void waitfortheVisibilityofElement(WebElement element, int time) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));

    }

    public void waitforListofElement(List<WebElement> element, int time) {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    // Explicit Wait function untill element is not visible
    public void waitforInvisiblityoftheElement(WebElement element, int time) {

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.invisibilityOf(element));


    }


    // Function to Navigate to any page from side menu

    public Object sideMenuOptionSelection(String option) {
        //  driver.navigate().refresh();

        waitTillUrlMatches("dashboard", 12);

        for (WebElement element : side_Menu_Elmt) {

            waitfortheVisibilityofElement(element, 8);

            if (element.getText().equalsIgnoreCase(option)) {
                element.click();
                break;
            }
        }

        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
        return switch (option.toLowerCase()) {
            case "lessor" -> new LessorPage(driver);
            case "lessee" -> new LesseePage(driver);
            case "po" -> new PoPage(driver);
            case "mrn" -> new MrnPage(driver);
            case "invoice" -> new InvoicePage(driver);
            case "property" -> new PropertyPage(driver);
            case "contract" -> new ContractPage(driver);
            case "store" -> new StorePage(driver);
            case "service providers" -> new ServiceProviderPage(driver);
            case "sales" -> new SalesPage(driver);
            default -> new IllegalArgumentException("Invalid page option: " + option);
        };

    }

    // To Handle Static Dropdowns
    public void staticDropdownByVisibleText(WebElement element, String dropDown) {
        waitfortheVisibilityofElement(element, 3);
        Select select = new Select(element);
        select.selectByVisibleText(dropDown);
    }


    public String getSelectedTextStaticDropDown(WebElement element)
    {
        waitfortheVisibilityofElement(element, 3);
        Select select = new Select(element);
        return select.getFirstSelectedOption().getText();
    }
    public void staticDropdownByIndex(WebElement element, int index) {
        waitfortheVisibilityofElement(element, 3);
        Select select = new Select(element);
        select.selectByIndex(index);

    }

    public void staticDropdownByValue(WebElement element, String value) {
        waitfortheVisibilityofElement(element, 3);
        Select select = new Select(element);
        select.selectByValue(value);

    }

    // Function to implement next button functionality
    public void clickNextButton() {
        waitfortheVisibilityofElement(nxt_Btn_Elmnt, 5);
        action.scrollToElement(nxt_Btn_Elmnt).build().perform();
        nxt_Btn_Elmnt.click();
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);

    }




    // Function to implement save button functionality
    public void clickSaveButton(int num) {
        waitforTheElementtoBeClickable(saveBtn.get(num - 1), 3);
        saveBtn.get(num - 1).click();

    }

    public void clickSubmitButton(int num) {
        submit_Btn.get(num - 1).click();
    }

    public void clickAddButton() {
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
        add_Btn.click();
    }

    //Selecting Value from the custom drop down
    public void selectOptionCstmDropDown(List<WebElement> element_List, String option) {
        try {

            for (WebElement element : element_List) {
                waitfortheVisibilityofElement(element, 2);
                System.out.println(element.getText());
                if (element.getText().equalsIgnoreCase(option)) {
                    element.click();
                    break;
                }

            }
        } catch (NoSuchElementException e) {
            System.out.println("Option Not Found" + e.getMessage());
        }
    }

    public void cstmDropDownwithContains(List<WebElement> element_List, String option) {
        try {

            for (WebElement element : element_List) {
                System.out.println(element.getText());
                waitfortheVisibilityofElement(element, 2);
                if (element.getText().contains(option)) {
                    element.click();
                    break;
                }

            }
        } catch (NoSuchElementException e) {
            System.out.println("Option Not Found" + e.getMessage());
        }
    }

    //Get Text from the Toast Message
    public List<String> getToastMessage(String text) {
        List<String> msg = new ArrayList<String>();
        for (WebElement element : toastMsgElmnt) {
            waitforTextinElement(element, text, 3);
            msg.add(element.getText());
            waitforInvisiblityoftheElement(element, 6);
        }
        return msg;
    }


    public static ExtentReports getReportObject(ITestContext context) {
        ExtentReports extentReports;
        ExtentTest extentTest;
        String timeStamp = new SimpleDateFormat("yyyyMMdd/HHmmss").format(new Date());
        String suite_Name = context.getSuite().getName();


        String path = System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\Report " + timeStamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path); //This is for the Configuration and the report path
        reporter.config().setReportName(suite_Name);
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        reporter.config().setTimelineEnabled(true); // Enable timeline view


        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Tester", "AbhayVerma");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("OS Arch", System.getProperty("os.arch"));
        extentReports.setSystemInfo("Environment", "QA");
//          extentTest.log(Status.PASS, MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN));
////        extentTest.log(Status.FAIL, MarkupHelper.createLabel("Test Failed", ExtentColor.RED));
////        extentTest.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped", ExtentColor.BLUE));

        return extentReports;
    }


    public String dateFormatter(String date_To_Format) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = inputFormat.parse(date_To_Format);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        return outputFormat.format(date);
    }


    public int totalPages() {

        waitfortheVisibilityofElement(page_Next_Btn, 5);
        scrollToElement(page_Next_Btn);
        return num_Of_page.size();

    }

    public void clickPageNxtBtn() {
        page_Next_Btn.click();
    }


    public void selectSteptoNavigate(String step) {
        for (WebElement element : step_Elmnts) {
            if (element.getText().equalsIgnoreCase(step)) {
                element.click();
                break;
            }
        }
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
    }

    public boolean checktheStatusofTab(String tab)
    {
        for (WebElement element : step_Elmnts) {

            if (element.getText().equalsIgnoreCase(tab) && element.getAttribute("class").contains("active")) {
                return true;
            }
        }
        waitforInvisiblityoftheElement(spinner_Elmnt, 3);
        return false;
    }

    public List<String> getFieldsErrMsg()

    {
        List<String> msgs=new ArrayList<String>();
        for(WebElement element : fields_Common_Err_Elmnt)
        {
            msgs.add(element.getText());
        }
        return msgs;

    }


}
