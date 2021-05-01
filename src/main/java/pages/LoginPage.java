package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    // private data-members
    private WebDriver driver;
    private By usernameInput = By.xpath("//input[@name='username']");
    private By passwordInput = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//input[@type='submit']");

    private By errorTitle = By.xpath("//h1[@class='title']");
    private By errorMessage = By.className("error");


    // constructor
    public LoginPage(WebDriver driver) { this.driver = driver; }
    // fill in login information
    public void setUsername(String login) { driver.findElement(usernameInput).sendKeys(login); }
    public void setPassword(String pwd) { driver.findElement(passwordInput).sendKeys(pwd); }
    // press button to log in
    public WebElement pressLoginButton() {
        driver.findElement(loginButton).click();
        // as error message gets updated not immediately, we need a waiting condition here
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(errorTitle), "Error!"));

        return driver.findElement(errorMessage);
    }
    public WebElement getLoginButton() {
        return driver.findElement(loginButton);
    }
}
