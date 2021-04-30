package admin;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;

import java.io.IOException;

import static org.testng.Assert.*;

public class TestAdminPage extends BaseTests {

    @Test
    public void createFilledApplication() throws IOException {
        AdminPage adminPage = homePage.clickAdminPage();
        adminPage.setDataAccessMode();
        adminPage.fillWebServiceEndpointREST("application/xml");
        adminPage.setInitialBalance(512.75);
        adminPage.setMinimalBalance(64.25);
        adminPage.setLoanProcessorType("combined");
        // check for mandatory fields to be filled in before pressing the 'Submit' button
        assertFalse(adminPage.getInitialBalance().isEmpty());
        assertFalse(adminPage.getMinimalBalance().isEmpty());
        // submit the application
        adminPage.pressSubmitBtn();
        // check for successful submission
        assertEquals(adminPage.getSubmissionStatus(), "Settings saved successfully.");
    }

    // TODO: refactor the below 3 functions into 1
    //  to check the error message by one single method
    @Test
    public void createBlankInitBalanceApplication() throws IOException {
        AdminPage adminPage = homePage.clickAdminPage();
        adminPage.clearFieldValue(adminPage.getInitBalanceElement());
        adminPage.pressSubmitBtn(); // submit the application

        assertFalse(adminPage.checkSubmissionStatusAvailability());  // check for failed submission message absence
        assertEquals(adminPage.getBlankInitBalanceError(), "Initial balance is required."); // check for error message existence
    }

    @Test
    public void createBlankMinBalanceApplication() throws IOException {
        AdminPage adminPage = homePage.clickAdminPage();
        adminPage.clearFieldValue(adminPage.getMinBalanceElement());
        adminPage.pressSubmitBtn(); // submit the application

        assertFalse(adminPage.checkSubmissionStatusAvailability());     // check for failed submission message absence
        assertEquals(adminPage.getBlankMinBalanceError(), "Minimum balance is required."); // check for error message existence
    }
    @Test
    public void createBlankThresholdApplication() throws IOException {
        AdminPage adminPage = homePage.clickAdminPage();
        adminPage.clearFieldValue(adminPage.getThresholdElement());
        adminPage.pressSubmitBtn(); // submit the application

        assertFalse(adminPage.checkSubmissionStatusAvailability());     // check for failed submission message absence
        assertEquals(adminPage.getBlankThresholdError(), "Threshold is required."); // check for error message existence
    }

}