package admin;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.AdminPage;

import java.io.IOException;

import static org.testng.Assert.*;

public class TestAdminPage extends BaseTests {

    @Test
    public void createApplication() throws IOException {
        AdminPage adminPage = homePage.clickAdminPage();
        adminPage.setDataAccessMode();
        adminPage.fillWebServiceEndpointREST("application/xml");
        adminPage.setInitialBalance(512.75);
        adminPage.setMinimalBalance(64.25);
        // check for mandatory fields to be filled in before pressing the 'Submit' button
        assertFalse(adminPage.getInitialBalance().isEmpty());
        assertFalse(adminPage.getMinimalBalance().isEmpty());
        // submit the application
        adminPage.pressSubmitBtn();
        // check for successful submission
        assertEquals(adminPage.getSubmissionStatus(), "Settings saved successfully.");
    }

}