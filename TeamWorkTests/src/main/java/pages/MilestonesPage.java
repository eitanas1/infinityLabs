package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MilestonesPage extends AbstractTeamWorkPage {
	//main frame
	private static By addMilestoneButton = By.xpath("//button[@data-bind='click: function(){Milestones.ShowNewMilestone(projectId)}']");
	//add Milestone frame
	private By milestoneName = By.id("milestoneName");
	private By addThisMilestoneButton = By.xpath("//input[@type='submit']");
	//attach TaskList frame
	
	
	public MilestonesPage(WebDriver driver_) {
		super(driver_);
	}
	
	public void addNewMilestone(String milestoneName_) {
		driver.findElement(addMilestoneButton).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(milestoneName));
		driver.findElement(milestoneName).sendKeys(milestoneName_);
		driver.findElement(addThisMilestoneButton).click();
	}
	
	public void attachTaskListToMilestone(String milestoneName_, String taskListName_) {
		By milestoneHeader = By.linkText(milestoneName_);
		By attachTextListButton = By.xpath("//a[text()='" + milestoneName_ + "']/../..//img[@src='images/newui/attachTaskList.png']/..");
		By taskListSelectBox = By.xpath("//a[text()='" + milestoneName_ + "']/../..//select");
		
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(milestoneHeader));
		Actions mousePointer = new Actions(driver);
		Actions hoverOverMilestone = mousePointer.moveToElement(driver.findElement(milestoneHeader));
		hoverOverMilestone.perform();
		driver.findElement(attachTextListButton).click();
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(taskListSelectBox));
		Select mySelect = new Select(driver.findElement(taskListSelectBox));
		mySelect.selectByVisibleText(taskListName_);
	}

	@Override
	public void assertInPage() {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(addMilestoneButton));
	}
}