package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class LoginPage extends AbstractPageObject {
	private By userName = By.id("userLogin");
	private By userPass = By.id("password");
	private static By logInButton = By.id("ordLoginSubmitBtn");
	private By logInErr = By.id("msg-wrap");
	
	public LoginPage(WebDriver driver_) {
		super(driver_);
	}
	
	public void setUserName(String userName_) {
		driver.findElement(userName).clear();
		driver.findElement(userName).sendKeys(userName_);
	}
	
	public void setUserPass(String userPass_) {
		driver.findElement(userPass).clear();
		driver.findElement(userPass).sendKeys(userPass_);
	}
	
	public HomePage clickLogInButtonAndGoHome() {
		driver.findElement(logInButton).click();
		return new HomePage(driver);
	}
	
	public void clickLogInButtonAndFail() throws ElementNotFoundException {
		driver.findElement(logInButton).click();
		(new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(logInErr));
	}
	
	@Override
	public void assertInPage() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(logInButton));
	}
}