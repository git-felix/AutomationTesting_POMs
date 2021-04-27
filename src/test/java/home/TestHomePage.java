package home;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.ContactPage;

import static org.testng.Assert.*;


public class TestHomePage extends BaseTests {

    @Test
    public void testCurrentPage() {
        assertEquals(homePage.getCurrentPageURL(),
                "https://parabank.parasoft.com/parabank/index.htm",
                "Error: incorrect Home Page address link."
        );
    }



}