package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;

public class ContactPage {
    // private data-members
    private WebDriver driver;
    private By pageTitle = By.className("title");
    private By customerName = By.id("name");
    private By customerEmail = By.id("email");
    private By customerPhone = By.id("phone");
    private By customerMessage = By.id("message");
    private By sendRequestBtn = By.xpath("//input[@value='Send to Customer Care']");
    // fluent Wait (initialization of wait instance)
    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
            .withTimeout(10, TimeUnit.SECONDS)
            .pollingEvery(1, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class);

    // constructor
    public ContactPage(WebDriver driver) { this.driver = driver; }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        // Fluent Wait (usage for getting the page title that may appear not immediately)
        WebElement pageTitleText = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(pageTitle);
            }
        });
        // returning page title as string
        return pageTitleText.getText();
    }

    public void setName(String name) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(customerName).sendKeys(name);
    }
    public void setEmail(String email) throws IOException {
        if( email.contains("@") && email.contains(".") && (email.indexOf("@") < email.indexOf(".")) ) {
            driver.findElement(customerEmail).sendKeys(email);
        }
        else {
            driver.findElement(customerEmail).sendKeys(email);
            throw new IOException("Error: invalid email provided.");
        }
    }
    public String getEmail() {
        return driver.findElement(customerEmail).getAttribute("value");
    }
    public void setPhone(String phone) {
        driver.findElement(customerPhone).sendKeys(phone);
    }
    public void setMessage(String message) {
        driver.findElement(customerMessage).sendKeys(message);
    }

    public void pressSendRequestBtn() {
        driver.findElement(sendRequestBtn).click();
    }

}
