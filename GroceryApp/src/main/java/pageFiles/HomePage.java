package pageFiles;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ScreenShotUtilities;

public class HomePage {

	WebDriver driver;
	ScreenShotUtilities screenShotUtilities = new ScreenShotUtilities();
	String pageTitle;

	public void pageTitle() {
		this.pageTitle = driver.getTitle().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[text()='Dashboard']")
	WebElement dashboardElement;
	@FindBy(xpath = "//body[@class='sidebar-mini layout-fixed']")
	WebElement dashboardBodyFullElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-tasks']")
	WebElement manageProductElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-edit']")
	WebElement manageContentElement;
	@FindBy(xpath = "//ol[@class='breadcrumb float-sm-right']")
	WebElement breadCrumbElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-users']")
	WebElement adminUserElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-']")
	WebElement manageProductElement1;

	public String getDashboardText() {
		return dashboardElement.getText();
	}

	public String getBreadCrumbText() {
		return breadCrumbElement.getText();
	}

	public ManageAdminUserPage getDashboardTextChaining() {
		dashboardElement.getText();
		pageTitle();

		try {
			screenShotUtilities.captureScreenShot(driver, pageTitle);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return new ManageAdminUserPage(driver);
	}
}
