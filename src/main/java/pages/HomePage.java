package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ribbon.RibbonBar;

import java.util.List;

public class HomePage {
    // TODO: create a RibbonBar class for all WebElements to be declared there

    // private data-members
    private WebDriver driver;
    private By buttonsList = By.xpath("//ul[@class='button']");
    private By buttons = By.tagName("li");
    private By leftMenuItems = By.xpath("//ul[@class='leftmenu']");

    private By servicesATM = By.className("captionone");
    private By servicesOnline = By.className("captiontwo");

    public boolean servicesSectionIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        boolean displayServicesATM = (wait.until(ExpectedConditions.visibilityOfElementLocated(servicesATM))).isDisplayed();
        boolean displayServicesOnline = (wait.until(ExpectedConditions.visibilityOfElementLocated(servicesATM))).isDisplayed();
        return (displayServicesATM && displayServicesOnline);
    }

    // Constructor (web-driver initialization)
    public HomePage(WebDriver driver) { this.driver = driver; }

    public String getCurrentPageURL() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.urlContains(driver.getCurrentUrl().substring(0, 5)));

        return driver.getCurrentUrl();
    }

    public ContactPage clickContactButton() {
        // initializing the list of elements
        List<WebElement> pagesButtonsList = driver.findElement(buttonsList).findElements(buttons);
        // finding element from list to click
        for (WebElement li : pagesButtonsList) {
            if(li.getText().contains("contact")) { li.click(); }
        }
        return new ContactPage(driver);
    }

    public LoginPage clickLoginButton() {
        driver.findElement(By.xpath("//input[@class='button']")).click();
        return new LoginPage(driver);
    }

    public AdminPage clickAdminPage() {
        // initializing the list of left menu items
        List<WebElement> pageItemsList = driver.findElement(leftMenuItems).findElements(buttons);
        // finding element from list to click
        for (WebElement li : pageItemsList) {
            if(li.getText().contains("Admin Page")) { li.click(); }
        }
        return new AdminPage(driver);
    }

}
