package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewTaskListPage extends AbstractTeamWorkPage {
	private static By taskListName = By.id("newTaskListName");
	private By createTaskListButton = By.id("btnCreateTaskList");

	public NewTaskListPage(WebDriver driver_) {
		super(driver_);
	}
	
	public void setTaskListName(String taskListName_) {
		driver.findElement(taskListName).sendKeys(taskListName_);
	}
	
	public TasksPage clickOnAddThisTaskList() {
		driver.findElement(createTaskListButton).click();
		return new TasksPage(driver);
	}

	@Override
	public void assertInPage() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(taskListName));
	}
}
