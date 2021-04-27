package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class AdminPage {
    // private data-members
    private WebDriver driver;
    private By checkboxAccessModeSOAP = By.id("accessMode1");   // for later usage
    private By checkboxAccessModeRESTxml = By.id("accessMode2");
    private By inputWebServiceEndpointREST = By.id("restEndpoint");
    private By initialBalance = By.id("initialBalance");
    private By minimalBalance = By.id("minimumBalance");
    private By submitBtn = By.cssSelector("input[value='Submit']");
    private By submissionStatus = By.xpath("//p[contains(@style, 'color')][1]/b[1]");

    // constructor
    public AdminPage(WebDriver driver) { this.driver = driver; }

    // set Data Access Mode type
    public void setDataAccessMode() {
        WebElement setAccessModeToRESTxml = new WebDriverWait(driver, 7)
                .until(ExpectedConditions.elementToBeClickable(checkboxAccessModeRESTxml));
        setAccessModeToRESTxml.click();
    }
    // fill Web Service information
    public void fillWebServiceEndpointREST(String endpoint) {
        WebElement inputEndpointREST = driver.findElement(inputWebServiceEndpointREST);
        if( ! inputEndpointREST.getAttribute("value").isEmpty()) {
            inputEndpointREST.clear();
        }
        inputEndpointREST.sendKeys(endpoint);
    }
    // fill Application information
    public void setInitialBalance(double initBalance) throws IOException {
        WebElement inputInitBalance = driver.findElement(initialBalance);
        if(inputInitBalance.isEnabled()) {
            if( ! inputInitBalance.getAttribute("value").isEmpty()) {
                inputInitBalance.clear();
            }
            inputInitBalance.sendKeys(String.valueOf(initBalance));
        }
        else { throw new IOException("Error: Min.Balance field is not editable"); }
    }
    public void setMinimalBalance(double minBalance) throws IOException {
        WebElement inputMinBalance = driver.findElement(minimalBalance);
        if(inputMinBalance.isEnabled()) {
            if( ! inputMinBalance.getAttribute("value").isEmpty()) {
                inputMinBalance.clear();
            }
            inputMinBalance.sendKeys(String.valueOf(minBalance));
        }
        else { throw new IOException("Error: Min.Balance field is not editable"); }
    }
    // getter functions for mandatory fields of balance
    public String getInitialBalance() {
        return driver.findElement(initialBalance).getAttribute("value");
    }
    public String getMinimalBalance() {
        return driver.findElement(minimalBalance).getAttribute("value");
    }

    // submit the filled in applications
    public void pressSubmitBtn() {
        driver.findElement(submitBtn).click();
    }
    // check if submission was successful
    public String getSubmissionStatus() {
        return driver.findElement(submissionStatus).getText();
    }

}
