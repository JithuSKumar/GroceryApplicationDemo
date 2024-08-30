package pageFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.FilePaths;
import utilities.FileUploadUtilities;
import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageSubCategoryPage {

	WebDriver driver;
	GeneralUtilities generaUtility = new GeneralUtilities();
	FilePaths filepath = new FilePaths();
	WaitUtilities waitUtility = new WaitUtilities();
	FileUploadUtilities fileUploadUtility = new FileUploadUtilities();
	String categoryNameRandomString;

	public ManageSubCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[text()='Sub Category']")
	WebElement subCategoryElement;
	@FindBy(xpath = "//table[@class='table table-bordered table-hover table-sm']")
	WebElement listofSubCategoryTableElement;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newSubCategoryCreationElement;
	@FindBy(xpath = "//select[@class='form-control selectpicker']")
	WebElement categoryDropdownElement;
	@FindBy(xpath = "//input[@type='text']")
	WebElement newSubCategoryNameElement;
	@FindBy(xpath = "//input[@type='file']")
	WebElement subCategoryImageElement;
	@FindBy(xpath = "//button[@type='submit']")
	WebElement saveElement;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successMessageElement;
	@FindBy(xpath = "//tbody//tr[1]/td[1]")
	WebElement firstCategoryInListElement;

	public boolean subCategoryPageSelection() {
		waitUtility.waitForElementClickable(driver, subCategoryElement);
		subCategoryElement.click();
		return listofSubCategoryTableElement.isDisplayed();
	}

	public void creationOfNewSubCategory(String subCategoryName, String CategoryType) {
		setRandomSubCategoryName(subCategoryName);
		subCategoryElement.click();
		newSubCategoryCreationElement.click();
		waitUtility.waitForElement(driver, categoryDropdownElement);
		generaUtility.selectDropdownbyText(categoryDropdownElement, CategoryType);
		newSubCategoryNameElement.sendKeys(getCategoryNameString());
		fileUploadUtility.fileUploadUsingSendKeys(subCategoryImageElement,
				filepath.IMAGEFILEFORMANAGECATEGORYCATEGORYPAGE);
		saveElement.click();
		waitUtility.waitForElement(driver, successMessageElement);

	}

	public void setRandomSubCategoryName(String subCategoryName) {
		this.categoryNameRandomString = subCategoryName + generaUtility.generateCurrentDateAndTime();
	}

	public String getCategoryNameString() {
		return categoryNameRandomString;
	}

	public String fetchingTheFirstEntryinTable() {
		String firstElementValueString = firstCategoryInListElement.getText();
		return firstElementValueString;
	}
}
