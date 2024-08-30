package pageFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

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
	@FindBy(xpath = "//i[@class='nav-icon fas fa-list-alt']")
	WebElement manageCategoryElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-users']")
	WebElement adminUserElement;
	@FindBy(xpath = "//i[@class='nav-icon fas fa-']")
	WebElement manageProductElement1;

	public String getDashboardText() {
		return dashboardElement.getText();
	}

	public boolean manageProdcutStatus() {
		return manageCategoryElement.isSelected();
	}

}
