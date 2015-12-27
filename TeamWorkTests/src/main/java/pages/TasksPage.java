package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TasksPage extends AbstractTeamWorkPage {
	//main frame
	private static By addTaskListButton = By.xpath("//button[@onclick='tw.ShowNewTaskList(145467);']");
	//add task to new TaskList frame
	private By addTaskButton = By.xpath("//div[@class='taskList ui-sortable']/div[last()]//button[@class='btn btn-success btn-hasIcon js-add-task']");
	private By taskName = By.xpath("//div[@class='taskList ui-sortable']/div[last()]//input[@class='tt-query']");
	private By whoDoItBox = By.xpath("//div[@class='taskList ui-sortable']/div[last()]//select[@name='taskAssignedToUserId']");
	private By saveChangesButton = By.xpath("//div[@class='taskList ui-sortable']/div[last()]//input[@type='submit']");
	private By cancelButton = By.xpath("//div[@class='taskList ui-sortable']/div[last()]//a[text()='Cancel']");

	public TasksPage(WebDriver driver_) {
		super(driver_);
	}
	
	public NewTaskListPage clickOnAddTaskList() {
		driver.findElement(addTaskListButton).click();
		return new NewTaskListPage(driver);
	}
	
	public void clickOnAddTask() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		driver.findElement(addTaskButton).click();
	}
	
	public void setNewTaskName(String taskName_) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(taskName));
		driver.findElement(taskName).sendKeys(taskName_);
	}
	
	public void setNewTaskWhoDoIt(String userName_) {
		Select droplist = new Select(driver.findElement(whoDoItBox));
		droplist.selectByVisibleText(userName_);
	}
	
	public void clickOnSaveChanges() {
		driver.findElement(saveChangesButton).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		driver.findElement(cancelButton).click();
	}
	
	public void findTaskListWith2Tasks(String taskListName_, String firstTaskName_, String secondTaskName_) {
		By firstTasksLable = By.xpath("//a[text()='" + taskListName_ + "']/../../div//div[@class='ko_container ui-sortable']//span[text()='" + firstTaskName_ + "']");
		By secondTasksLable = By.xpath("//a[text()='" + taskListName_ + "']/../../div//div[@class='ko_container ui-sortable']//span[text()='" + secondTaskName_ + "']");
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(firstTasksLable));
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(secondTasksLable));
	}
	
	public void deleteTaskList(String taskListName_) {
		By tasksListHeader = By.linkText(taskListName_);
		By tasksListOptionsButton = By.xpath("//button[@class='btn btn-default btn-hasIcon tipped']");
		By deleteListButton = By.xpath("//i[@class='ico-trash icon-large']/..");
		driver.findElement(tasksListHeader).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(tasksListOptionsButton));
		driver.findElement(tasksListOptionsButton).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(deleteListButton));
		driver.findElement(deleteListButton).click();
	}
	
	public void assertTaskListWithMilestone(String taskListName_, String milestoneName_) {
		By milestoneLable = By.xpath("//a[text()='" + taskListName_ + "']/../../div//a[text()='" + milestoneName_ + "']");
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(milestoneLable));
	}

	@Override
	public void assertInPage() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(addTaskListButton));
	}
}