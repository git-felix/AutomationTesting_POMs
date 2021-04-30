package pages;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webelements.WebElementsList;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AdminPage {
    // private data-members
    // driver declaration
    private WebDriver driver;
    // page fields
    private By checkboxAccessModeSOAP = By.id("accessMode1");   // for later usage
    private By checkboxAccessModeRESTxml = By.id("accessMode2");
    private By inputWebServiceEndpointREST = By.id("restEndpoint");
    private By initialBalance = By.id("initialBalance");
    private By minimalBalance = By.id("minimumBalance");
    private By menuLoanProcessor = By.id("loanProcessor");
    private By loanProcessorThreshold = By.id("loanProcessorThreshold");

    private By submitBtn = By.cssSelector("input[value='Submit']");
    private By submissionStatus = By.xpath("//p[contains(@style, 'color')][1]/b[1]");
    // error messages
    private By errorMsgInitBalance = By.id("initialBalance.errors");
    private By errorMsgMinBalance = By.id("minimumBalance.errors");
    private By errorMsgThreshold = By.id("loanProcessorThreshold.errors");



    // constructor
    public AdminPage(WebDriver driver) { this.driver = driver; }

    // Fluent Wait (initialization of wait instance)
    public FluentWait<WebDriver> createFluentWait(int durationMLS, int periodMLS) {
        return new FluentWait<WebDriver>(driver)
                .withTimeout(durationMLS, TimeUnit.MILLISECONDS)
                .pollingEvery(periodMLS, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
    }

    // set Data Access Mode type
    public void setDataAccessMode() {
        WebElement setAccessModeToRESTxml = createFluentWait(3000, 100)
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
        WebElement inputInitBalance = createFluentWait(2000, 100)
                .until(ExpectedConditions.visibilityOfElementLocated(initialBalance));
        if(inputInitBalance.isEnabled()) {
            clearFieldValue(inputInitBalance);
            inputInitBalance.sendKeys(String.valueOf(initBalance));
        }
        else { throw new IOException("Error: Min.Balance field is not editable"); }
    }
    public void setMinimalBalance(double minBalance) throws IOException {
        WebElement inputMinBalance = createFluentWait(2000, 100)
                .until(ExpectedConditions.visibilityOfElementLocated(minimalBalance));
        if(inputMinBalance.isEnabled()) {
            clearFieldValue(inputMinBalance);
            inputMinBalance.sendKeys(String.valueOf(minBalance));
        }
        else { throw new IOException("Error: Min.Balance field is not editable"); }
    }

    public void clearFieldValue(WebElement inputField) {
        if( ! inputField.getAttribute("value").isEmpty() ) {
            inputField.clear();
        }
    }
    // TODO: refactor and place all WebElements into a Map, to access them by single function
    public WebElement getInitBalanceElement() {
        return driver.findElement(initialBalance);
    }
    public WebElement getMinBalanceElement() {
        return driver.findElement(minimalBalance);
    }
    public WebElement getThresholdElement() {
        return driver.findElement(loanProcessorThreshold);
    }

    // getter functions for mandatory fields of balance
    public String getInitialBalance() {
        return driver.findElement(initialBalance).getAttribute("value");
    }
    public String getMinimalBalance() {
        return driver.findElement(minimalBalance).getAttribute("value");
    }

    public void setLoanProcessorType(String loanProcessorType) {
        Select loanProcessorOptions = new Select(driver.findElement(menuLoanProcessor));
        loanProcessorOptions.selectByValue(loanProcessorType);
    }

    public boolean checkSubmissionStatusAvailability() {
        return (!driver.findElements(submissionStatus).isEmpty());
    }

    // TODO: refactor and create single GetBlankFieldError(errorMessageType) method
    //       to return error message for specified field by parameter
    public String getBlankInitBalanceError() {
        if (!driver.findElements(errorMsgInitBalance).isEmpty()) {
            return driver.findElement(errorMsgInitBalance).getText();
        }
        return "";
    }
    public String getBlankMinBalanceError() {
        if (!driver.findElements(errorMsgMinBalance).isEmpty()) {
            return driver.findElement(errorMsgMinBalance).getText();
        }
        return "";
    }
    public String getBlankThresholdError() {
        if (!driver.findElements(errorMsgThreshold).isEmpty()) {
            return driver.findElement(errorMsgThreshold).getText();
        }
        return "";
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
