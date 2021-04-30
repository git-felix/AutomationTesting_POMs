package home;

import base.BaseTests;
import org.testng.annotations.Test;

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
        assertTrue(homePage.servicesSectionIsDisplayed());
        // TODO: assert all sections' items are displayed and clickable
    }


}