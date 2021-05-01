package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    // TODO: create a PageElements class for all WebElements to be declared there

    // private data-members
    private WebDriver driver;
    private By leftMenuItems = By.xpath("//ul[@class='leftmenu']//li");
    private By buttonsList = By.xpath("//ul[@class='button']//li");
    private By loginPanel = By.id("leftPanel");

    private By servicesATM = By.className("captionone");
    private By servicesOnline = By.className("captiontwo");

    private By ATMServicesList = By.xpath("//ul[@class='services']//li");
    private By OnlineServicesList = By.xpath("//ul[@class='servicestwo']//li");


    // Constructor (web-driver initialization)
    public HomePage(WebDriver driver) { this.driver = driver; }

    public String getCurrentPageURL() {
        WebDriverWait wait = new WebDriverWait(driver, 6);
        wait.until(ExpectedConditions.urlContains(driver.getCurrentUrl().substring(0, 5)));

        return driver.getCurrentUrl();
    }

    public boolean servicesSectionsAreDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        boolean displayServicesATM = (wait.until(ExpectedConditions.visibilityOfElementLocated(servicesATM))).isDisplayed();
        boolean displayServicesOnline = (wait.until(ExpectedConditions.visibilityOfElementLocated(servicesOnline))).isDisplayed();
        return (displayServicesATM && displayServicesOnline);
    }

    public List<WebElement> getATMServicesElements() {
        return driver.findElements(ATMServicesList);
    }
    public List<WebElement> getOnlineServicesElements() {
        return driver.findElements(OnlineServicesList);
    }

    public LoginPage clickLoginPage() {
        driver.findElement(loginPanel).click();
        return new LoginPage(driver);
    }

    public ContactPage clickContactButton() {
        clickPageByName("contact");
        return new ContactPage(driver);
    }

    public AdminPage clickAdminPage() {
        clickPageByName("Admin Page");
        return new AdminPage(driver);
    }

    private void clickPageByName(String pageName) {
        // initializing the list of ribbon bar items
        List<WebElement> pageItemsList = (driver.findElements(leftMenuItems));
        pageItemsList.addAll(driver.findElements(buttonsList));

        // finding element from list to click
        for (WebElement li : pageItemsList) {
            if(li.getText().equals(pageName)) { li.click(); break;}
        }
    }

}
