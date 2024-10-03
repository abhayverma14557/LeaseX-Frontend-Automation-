package PageObjects;

import CommonFiles.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PortalSelectionPage extends AbstractComponents {
    WebDriver driver;

    //----------------------------Constructor to initialize the driver and implement page factory----------------------------------------------------------------
    public PortalSelectionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locator for user selection
    @FindBy(xpath = "//button")
    private List<WebElement> user_List;

    @FindBy(xpath = "//button[contains(text(),'APX')]")
    private WebElement apx_Btn_Elmnt;

    //-------------------------------------Function to select any protal from the protal selection page----------------------------------------------------------------
    public DashboardPage portalSelection(String userType) {
        waitTillUrlMatches("routes", 5);
        waitforTextinElement(apx_Btn_Elmnt, "APX", 5);

        for (WebElement user : user_List) {

            if (user.getText().equalsIgnoreCase(userType)) {

                waitforTextinElement(user, userType, 3);
                user.click();


            }
        }
        return new DashboardPage(driver);


    }


}
