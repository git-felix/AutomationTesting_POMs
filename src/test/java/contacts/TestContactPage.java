package contacts;

import base.BaseTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactPage;

import java.io.IOException;

import static org.testng.Assert.*;

public class TestContactPage extends BaseTests {

    @Test
    public void testContactPageOpen() {
        // initialize instance of type ContactPage
        ContactPage contactPage = homePage.clickContactButton();
        // check page address link
        assertEquals(contactPage.getCurrentPageURL(), "https://parabank.parasoft.com/parabank/contact.htm",
                "Error: incorrect Contact page address link.");
        // check page title
        assertEquals(contactPage.getPageTitle(), "Customer Care", "Error: incorrect Contact page title.");
    }

    @Test(enabled = true)   // disabled not to spam the website, set 'true' to test
    public void testCreateResponse() throws IOException {
        // initialize instance of type ContactPage
        ContactPage contactPage = homePage.clickContactButton();
        // fill in the customer information
        contactPage.setName("Jordan");
        contactPage.setEmail("jordan_green@mail.ru");
        contactPage.setPhone("+18440002002");
        contactPage.setMessage("Test response: please ignore this email.");
        // make sure the email is correct before sending
        assertEquals(contactPage.getEmail(), "jordan_green@mail.ru");
        contactPage.pressSendRequestBtn();
    }



}