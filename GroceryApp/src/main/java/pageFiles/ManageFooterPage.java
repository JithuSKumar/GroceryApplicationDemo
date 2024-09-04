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

public class ManageFooterPage {
	WebDriver driver;
	GeneralUtilities generaUtility = new GeneralUtilities();
	WaitUtilities waitUtility = new WaitUtilities();
	ExcelUtilities excelutility = new ExcelUtilities();
	ScreenShotUtilities screenShotUtilities = new ScreenShotUtilities();
	String pageTitle;
	
	public void pageTitle() {
		this.pageTitle = driver.getTitle().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
	}
	
	public ManageFooterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a//p[text()='Manage Footer Text']")
	WebElement manageFooterElement;
	@FindBy(xpath = "//div[@class='card']")
	WebElement tableElement;
	@FindBy(xpath = "//ol[@class='breadcrumb float-sm-right']")
	WebElement breadCrumbElement;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']//tbody")
	List<WebElement> tableRows;

	public void footerPageVisibilityCheck() {
		manageFooterElement.click();
	}

	public String getBreadCrumbText() {
		return breadCrumbElement.getText();
	}

	public void saveDataTofile() throws IOException {
		footerPageVisibilityCheck();
		excelutility.saveTableContentsToNewExcelFile(tableRows, generaUtility.pageTitle(driver),
				this.getClass().getSimpleName());
	}
	
	public ManageMenuPage footerPageSelectionChaining() {
		manageFooterElement.click();
		pageTitle();

		try {
			screenShotUtilities.captureScreenShot(driver, pageTitle);
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return new ManageMenuPage(driver);
	}

}
