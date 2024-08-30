package pageFiles;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.FilePaths;
import utilities.FileUploadUtilities;
import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageCategoryPage {

	WebDriver driver;
	GeneralUtilities generaUtility = new GeneralUtilities();
	FilePaths filepath = new FilePaths();
	WaitUtilities waitUtility = new WaitUtilities();
	FileUploadUtilities fileUploadUtility = new FileUploadUtilities();
	String categoryNameRandomString;

	public ManageCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @FindBy(xpath = "//section[@class='content']//div//div//div[3]//div//a")
	// WebElement manageCategoryIconElement;
	@FindBy(xpath = "//a//p[text()='Category']")
	WebElement categoryElement;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']")
	WebElement listCategoryTablElement;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newCategoryCreationElement;
	@FindBy(id = "category")
	WebElement categoryNameElement;
	@FindBy(xpath = "//li[@id='134-selectable']")
	WebElement draggingElement;
	@FindBy(xpath = "//input[@type='file']")
	WebElement uploadImagElement;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement categorySaveElement;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successMessageElement;
	@FindBy(xpath = "//tbody//tr[1]/td[1]")
	WebElement firstCategoryInListElement;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary']")
	WebElement searchElement;
	@FindBy(xpath = "//input[@placeholder='Category']")
	WebElement searchCategoryInputElement;
	@FindBy(xpath = "//button[@value='sr']")
	WebElement subSearchElement;
	@FindBy(xpath = "//tbody//tr[1]/td//i[@class='fas fa-trash-alt']")
	WebElement firstEntryDeletElement;

	public boolean categoryPageSelection() {
		// manageCategoryIconElement.click();
		categoryElement.click();
		return listCategoryTablElement.isDisplayed();
	}

	public String fetchingTheFirstEntryinTable() {
		String firstElementValueString = firstCategoryInListElement.getText();
		return firstElementValueString;
	}

	public void newCategoryCreation(String categoryName) throws AWTException {
		setRandomCategoryName(categoryName);
		newCategoryCreationElement.click();
		categoryNameElement.sendKeys(getCategoryNameString());
		draggingElement.click();
		fileUploadUtility.fileUploadUsingSendKeys(uploadImagElement,
				filepath.IMAGEFILEFORMANAGECATEGORYCATEGORYPAGE);
		categorySaveElement.click();
		waitUtility.waitForElement(driver, successMessageElement);

	}

	public String searchNewlyAddedCategoryVisibility(String searchInput) {
		searchElement.click();
		searchCategoryInputElement.sendKeys(searchInput);
		subSearchElement.click();
		String filteredresult = firstCategoryInListElement.getText();
		System.out.println(filteredresult);
		return filteredresult;
	}

	public void deleteNewlyAddedCategory() {
		firstEntryDeletElement.click();
		waitUtility.waitForAlterIsPresent(driver);
		generaUtility.alertHandlingaccept(driver);
	}

	public void setRandomCategoryName(String categoryName) {
		this.categoryNameRandomString = categoryName + generaUtility.generateCurrentDateAndTime();
	}

	public String getCategoryNameString() {
		return categoryNameRandomString;
	}
}
