package TestComponents;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestData extends BaseClass {
    @DataProvider
    public Object[][] getData(ITestContext context) throws IOException {

        //-------------------Test Param is coming from the TestNG---------------------
        String Id = context.getCurrentXmlTest().getParameter("test_param");

        //--------------------Getting data from Excel----------------------------
        List<HashMap<String, String>> data = testDatafromExcel(context.getCurrentXmlTest().getParameter("excel_Sheet"));

        //------------For filtering the data-----------------
        List<HashMap<String, String>> filteredData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get("test_Id") != null && data.get(i).get("test_Id").equals(Id)) {
                filteredData.add(data.get(i));

            }

        }
        // ----------------Prepare the testData array based on filtered data-------------
        Object[][] testData = new Object[filteredData.size()][1];

        for (int i = 0; i < filteredData.size(); i++) {
            testData[i][0] = filteredData.get(i);
        }

        // Return the filtered test data
        return testData;

    }


}
