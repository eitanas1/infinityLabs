package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends AbstractTeamWorkPage {
	private static By addButton = By.xpath("//button[@class='btn btn-success btn-hasIcon tipped projectOverviewAddButton']");

	public HomePage(WebDriver driver_) {
		super(driver_);
	}

	@Override
	public void assertInPage() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(addButton));
	}
}
