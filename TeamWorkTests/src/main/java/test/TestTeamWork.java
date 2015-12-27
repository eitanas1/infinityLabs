package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import pages.MilestonesPage;
import pages.NewTaskListPage;
import pages.TasksPage;

public class TestTeamWork {
	private WebDriver myDriver;

	@BeforeSuite
	public void setUp() {
		// set FirefoxDriver
		myDriver = new FirefoxDriver();
	}

	@BeforeTest
	public void openPage() {
		// open web-page
		myDriver.get("https://topq.teamwork.com/");
	}

	@AfterSuite
	public void endUp() {
		// close the driver
		//myDriver.close();
	}

	@Test(description = "Test addition of task to task list")
	// , enabled = false
	public void TestAddTaskToList() {
		// login and go to HomePage
		LoginPage logIn = new LoginPage(myDriver);
		logIn.setUserName("fake08@fake.com");
		logIn.setUserPass("err");
		logIn.clickLogInButtonAndFail();
		logIn.setUserPass("fake");
		HomePage home = logIn.clickLogInButtonAndGoHome();

		// go to TasksPage
		TasksPage tasks = home.clickTasksButtonAndGoTasks();

		// add 1 TaskList
		String listTaskName = "FirstListTask" + Long.toString(System.currentTimeMillis());
		NewTaskListPage newTask = tasks.clickOnAddTaskList();
		newTask.setTaskListName(listTaskName);
		tasks = newTask.clickOnAddThisTaskList();

		// add 2 tasks to TaskList
		tasks.clickOnAddTask();
		tasks.setNewTaskName("FirstTask");
		tasks.setNewTaskWhoDoIt("fake08 fake08 (me)");
		tasks.clickOnSaveChanges();
		tasks.clickOnAddTask();
		tasks.setNewTaskName("SecondTask");
		tasks.setNewTaskWhoDoIt("fake08 fake08 (me)");
		tasks.clickOnSaveChanges();

		// go to MilestonesPage and back to TasksPage
		MilestonesPage milestones = tasks.clickMilestonesButtonAndGoMilestones();
		tasks = milestones.clickTasksButtonAndGoTasks();

		// verify existence of TaskList
		// TOOD: Return an object that represents the task list. This page
		// object will have a service that allows the user to query the number
		// of tasks in the task list.
		tasks.findTaskListWith2Tasks(listTaskName, "FirstTask", "SecondTask");

		// delete TaskList
		tasks.deleteTaskList(listTaskName);
		tasks.assertInPage();

		// logout and go LoginPage
		logIn = tasks.logoutAndGoLoginPage();
	}

	@Test(description = "Test addition of task list to milestone", dependsOnMethods = "TestAddTaskToList")
	public void TestAddListToMilestone() {
		// login and go to HomePage
		LoginPage logIn = new LoginPage(myDriver);
		logIn.setUserName("fake08@fake.com");
		logIn.setUserPass("fake");
		HomePage home = logIn.clickLogInButtonAndGoHome();

		// go to TasksPage
		TasksPage tasks = home.clickTasksButtonAndGoTasks();

		// add 1 TaskList
		String listTaskName = "FirstListTask" + Long.toString(System.currentTimeMillis());
		NewTaskListPage newTask = tasks.clickOnAddTaskList();
		newTask.setTaskListName(listTaskName);
		tasks = newTask.clickOnAddThisTaskList();

		// go to MilestonesPage
		MilestonesPage milestones = tasks.clickMilestonesButtonAndGoMilestones();

		// add Milestone
		String milestoneName = "FirstMilestone" + Long.toString(System.currentTimeMillis());
		milestones.addNewMilestone(milestoneName);
		milestones.attachTaskListToMilestone(milestoneName, listTaskName);

		// go to TasksPage
		tasks = milestones.clickTasksButtonAndGoTasks();
		
		// verify attachment of TaskList to Milestone
		tasks.assertTaskListWithMilestone(listTaskName, milestoneName);
	}
}