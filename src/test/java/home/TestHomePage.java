package home;

import base.BaseTests;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import webelements.WebElementsList;

import java.util.List;

import static org.testng.Assert.*;


public class TestHomePage extends BaseTests {

    @Test
    public void testCurrentPage() {
        assertEquals(homePage.getCurrentPageURL(),
                "https://parabank.parasoft.com/parabank/index.htm",
                "Error: incorrect Home Page address link."
        );
    }

    @Test
    public void testSectionsDisplay() {
        assertTrue(homePage.servicesSectionsAreDisplayed());
        // TODO: assert all sections' items are displayed and clickable
    }

    @Test
    public void testATMServicesElements() {
        String[] services = new String[]{ "Withdraw Funds", "Transfer Funds", "Check Balances", "Make Deposits" };
        List<WebElement> servicesATM = homePage.getATMServicesElements();
        for(short i = 1; i < servicesATM.size(); ++i) {
            assertEquals(servicesATM.get(i).getText(), services[i - 1]);
        }
    }

    @Test
    public void testOnlineServicesElements() {
        String[] services = new String[]{ "Bill Pay", "Account History", "Transfer Funds" };
        List<WebElement> servicesOnline = homePage.getOnlineServicesElements();
        for(short i = 1; i < servicesOnline.size(); ++i) {
            assertEquals(servicesOnline.get(i).getText(), services[i - 1]);
        }
    }


}