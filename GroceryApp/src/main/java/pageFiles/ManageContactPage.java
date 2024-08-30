package pageFiles;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ExcelUtilities;
import utilities.GeneralUtilities;
import utilities.ScreenShotUtilities;
import utilities.WaitUtilities;

public class ManageContactPage {

	WebDriver driver;
	String pageTitle;

	GeneralUtilities generaUtility = new GeneralUtilities();
	WaitUtilities waitUtility = new WaitUtilities();
	ExcelUtilities excelutility = new ExcelUtilities();
	ScreenShotUtilities screenShotUtilities = new ScreenShotUtilities();

	public ManageContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a//p[text()='Manage Contact']")
	WebElement manageContactElement;
	@FindBy(xpath = "//div[@class='card']")
	WebElement tableElement;
	@FindBy(xpath = "//ol[@class='breadcrumb float-sm-right']")
	WebElement breadCrumbElement;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody//tr")
	List<WebElement> tableRows;

	public void pageVisibilityCheck() {
		manageContactElement.click();
	}

	public String getBreadCrumbText() {
		return breadCrumbElement.getText();
	}

	public void saveDataTofile() throws IOException {
		pageVisibilityCheck();
		generaUtility.pageTitle(driver);
		screenShotUtilities.captureScreenShot(driver, pageTitle);
		excelutility.saveTableContentsToNewExcelFile(tableRows, generaUtility.pageTitle(driver),
				this.getClass().getSimpleName());
	}

}
