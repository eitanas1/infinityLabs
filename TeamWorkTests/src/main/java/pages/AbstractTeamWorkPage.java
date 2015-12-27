package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractTeamWorkPage extends AbstractPageObject {
	private By tasksButton = By.xpath("//a[@href='projects/145467-webdriver-training/tasks']");
	private By milestonesButton = By.xpath("//a[@href='projects/145467-webdriver-training/milestones']");
	
	private By profileButton = By.xpath("//div[@id='trUserPic']/a");
	private By logoutButton = By.xpath("//a[@href='?action=logout']");

	public AbstractTeamWorkPage(WebDriver driver_) {
		super(driver_);
	}
	
	public TasksPage clickTasksButtonAndGoTasks() {
		driver.findElement(tasksButton).click();
		return new TasksPage(driver);
	}
	
	public MilestonesPage clickMilestonesButtonAndGoMilestones() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(milestonesButton));
		driver.findElement(milestonesButton).click();
		return new MilestonesPage(driver);
	}
	
	public LoginPage logoutAndGoLoginPage() {
		driver.findElement(profileButton).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
		driver.findElement(logoutButton).click();
		return new LoginPage(driver);
	}
}