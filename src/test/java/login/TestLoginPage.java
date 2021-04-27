package login;

import base.BaseTests;
import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.*;

public class TestLoginPage extends BaseTests {

    @Test
    public void testBlankLogin() {
        LoginPage loginPage = homePage.clickLoginButton();
        String txt = loginPage.pressLoginButton().getText();
        assertEquals(txt, "Please enter a username and password.");
    }

    @Test
    public void testFailedLogin() {
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setUsername("logintest1");
        loginPage.setPassword("pwdtest1");

        String txt = loginPage.pressLoginButton().getText();
        assertEquals(txt, "The username and password could not be verified.");
    }

    @Test(enabled = true)  // disabled not to overload screenshots' memory, set 'true' to test
    public void testSuccessfulLogin() {
        LoginPage loginPage = homePage.clickLoginButton();
        loginPage.setUsername("admin");
        loginPage.setPassword("admin123");

        String txt = loginPage.pressLoginButton().getText();
        assertEquals(txt, "The user is verified.");
    }

}