package TestComponents;

import PageObjects.LoginPage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

public class BaseClass {


    public WebDriver driver;
    public LoginPage login;
    public SoftAssert soft_Assert;
    TestData test_data;
String landing_Page;

    ChromeOptions options;

    protected String bro;


    //---------------------------------------------------Method to Initialize the WebDriver-----------------------------------------------------------------
    public WebDriver intializeDriver() throws IOException {
        //Taking Browser and Run Mode Information from the Global Properties Files

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\Global.properties");
        properties.load(fis);

        //------Code to Set the Browser and Mode Information Either from maven command or taking default values from the Global Properties files-------------
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
        String mode = System.getProperty("mode") != null ? System.getProperty("mode") : properties.getProperty("mode");
         landing_Page=System.getProperty("landingpage") != null ? System.getProperty("landingpage") : properties.getProperty("landingpage");
        options = new ChromeOptions();


        if (browser.equalsIgnoreCase("Chrome")) {
            if (mode.equalsIgnoreCase("Headless")) {
                options.addArguments("Headless");
            }
            driver = new ChromeDriver(options);      // Use this for Running into the HeadMode
        } else if (browser.equalsIgnoreCase("Firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return driver;


    }

    //--------------------------------------------Method to launch the application and start with the first or landing page-------------------------------
    @BeforeMethod
    public LoginPage launchApplication() throws IOException {

        soft_Assert = new SoftAssert();
        driver = intializeDriver();
        login = new LoginPage(driver);
        login.landingPage(landing_Page);


        return login;
    }

    //----------------------------------------Method to close  all the browser and log all the assertions----------------------------------------------------------------
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


//    //Method to read data from JSON file using Jackson Databind Dependency in Pom.xml
//    public List<HashMap<String, String>> jsonDatatoMap(String filePath) throws IOException {
//        String json_Content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
//
//        ObjectMapper mapper = new ObjectMapper();
//        List<HashMap<String, String>> value = mapper.readValue(json_Content, new TypeReference<List<HashMap<String, String>>>() {
//        });
//        return value;
//
//    }


    //----------------------------------------------For Validatiing Data from Any Excel Sheet----------------------------------------------
    public List<String> getExcelData(String file_Path) throws IOException {
        List<String> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file_Path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {

            if (workbook.getSheetName(i).equalsIgnoreCase("Sheet1")) {
                XSSFSheet sheet = workbook.getSheetAt(i);


                Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
                rows.next();

                while (rows.hasNext()) {
                    Row row = rows.next();

                    Iterator<Cell> ce = row.cellIterator();


                    while (ce.hasNext()) {
                        Cell value = ce.next();
                        switch (value.getCellType()) {
                            case NUMERIC -> {
                                data.add(String.valueOf((long) value.getNumericCellValue()));
                            }
                            case STRING -> {

                                data.add(value.getStringCellValue());

                            }
                            default -> System.out.println("");
                        }

                    }
                }

            }

        }
        return data;

    }

//Code to Take Screenshot in the Normal PNG file
//    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
//        TakesScreenshot ts=(TakesScreenshot)driver;
//        File screenShot=ts.getScreenshotAs(OutputType.FILE);
//        String path=System.getProperty("user.dir")+"\\src\\main\\java\\Reports\\Screenshot\\"+testCaseName+".png";
//        File file=new File(path);
//        FileUtils.copyFile(screenShot,file);
//       String[] relative_Path=path.split("Reports");
//        System.out.println(relative_Path[1]);
//        return relative_Path[1];
//    }

    //-------------------------------------------Code to Take screenshot in Base 64 fromat--------------------------------------------------------
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String screenshotbase = ts.getScreenshotAs(OutputType.BASE64);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\Reports\\Screenshot\\" + testCaseName + timeStamp + ".png";
        File file = new File(filePath);
        String[] base64ScreenshotParts = screenshotbase.split("(?<=\\G.{76})");
        StringBuilder base64StringBuilder = new StringBuilder();
        for (String part : base64ScreenshotParts) {
            base64StringBuilder.append(part);
        }
        String base64Screenshot = base64StringBuilder.toString();

        // Decode the concatenated Base64 string into a byte array
        byte[] decodedBytes = Base64.getDecoder().decode(base64Screenshot);

        // Create directories if they do not exist
        file.getParentFile().mkdirs();

        // Write the decoded bytes to a file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
        } catch (IOException e) {
            System.out.println("Exception while saving screenshot: " + e.getMessage());
        }
        return screenshotbase;

    }


    //-------------------------------------------------Code to Get the Test Data from Excel File------------------------------------------------
    public List<HashMap<String, String>> testDatafromExcel(String excel_Sheet) throws IOException {
        List<HashMap<String, String>> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\LeasX_Sheet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        System.out.println("Number of sheets: " + sheets);

        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(excel_Sheet)) {
                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
                Row headerRow = rows.next(); // Assuming the first row is the header row

                List<String> headers = new ArrayList<>();
                Iterator<Cell> headerCells = headerRow.cellIterator();
                while (headerCells.hasNext()) {
                    headers.add(headerCells.next().getStringCellValue());
                }

                // Iterate over the remaining rows
                while (rows.hasNext()) {
                    Row row = rows.next();
                    HashMap<String, String> rowData = new HashMap<>();
                    Iterator<Cell> cells = row.cellIterator();
                    int cellIndex = 0;

                    // Iterate over each cell in the row
                    while (cellIndex < headers.size()) { // Ensure we process all headers, even if some cells are missing
                        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL); // Return null for missing/blank cells
                        String cellValue = null;

                        if (cell == null) {
                            cellValue = null; // Explicitly setting null for missing/blank cells
                        } else {
                            // Handle the different types of cell values
                            switch (cell.getCellType()) {
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        // Handle date cells and format them to dd-MM-yyyy
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                                        cellValue = dateFormat.format(cell.getDateCellValue());
                                    } else {
                                        // Handle numeric values (non-date)
                                        cellValue = String.format("%.0f", cell.getNumericCellValue());
                                    }
                                    break;
                                case BOOLEAN:
                                    cellValue = String.valueOf(cell.getBooleanCellValue());
                                    break;
                                case STRING:
                                    cellValue = cell.getStringCellValue();
                                    break;
                                default:
                                    cellValue = null; // For other types, set null explicitly
                            }
                        }

                        rowData.put(headers.get(cellIndex), cellValue); // Map header to cell value (which may be null)
                        cellIndex++;
                    }

                    data.add(rowData); // Add the row data to the list
                }
            }
        }

        workbook.close();
        fis.close();

        return data;
    }


    public void setInputField(Consumer<String> setterMethod, String value) {
        setterMethod.accept(value != null ? value : "");
    }

    public void selectIfNotNull(Consumer<String> selectionMethod, String value) {
        if (value != null) {
            selectionMethod.accept(value);
        }
    }

    public void uploadIfNotNull(Consumer<String> uploadMethod, String filePath) {
        if (filePath != null) {
            uploadMethod.accept(System.getProperty("user.dir") + filePath);
        }
    }


    public void verifyAndLogAssertions() {
        try {
            soft_Assert.assertAll();
        } catch (AssertionError e) {
            // Log the failure to the listener
            ITestResult result = Reporter.getCurrentTestResult();
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(e);  // Set the throwable to capture the failure cause
            throw e; // Rethrow to ensure the failure is marked properly
        }
    }


}
