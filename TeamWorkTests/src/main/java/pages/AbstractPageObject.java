package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageObject {
	protected final WebDriver driver;

	public AbstractPageObject(WebDriver driver_) {
		this.driver = driver_;
		assertInPage();
	}

	public abstract void assertInPage();
}