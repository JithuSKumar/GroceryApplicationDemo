package pageFiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.FileUploadUtilities;
import utilities.GeneralUtilities;
import utilities.WaitUtilities;

public class ManageMenuPage {

	WebDriver driver;
	GeneralUtilities generaUtility = new GeneralUtilities();
	WaitUtilities waitUtility = new WaitUtilities();
	FileUploadUtilities fileUploadUtility = new FileUploadUtilities();
	String menuNameRandomString;

	public ManageMenuPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='nav-icon sidebar-item-icon fa fa-cog']")
	WebElement settingsIconElement;
	@FindBy(xpath = "//p[text()='Manage Menu']")
	WebElement manageMenuElement;
	@FindBy(xpath = "//table[@class='table table-bordered']")
	WebElement tableElement;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-danger']")
	WebElement newManageElement;
	@FindBy(xpath = "//input[@id='name']")
	WebElement newMenuNameElement;
	@FindBy(id = "menu_id")
	WebElement parentMenuElement;
	@FindBy(id = "url")
	WebElement urlElement;
	@FindBy(id = "icon")
	WebElement favIconElement;
	@FindBy(id = "menu_table")
	WebElement menuTableElement;
	@FindBy(id = "active_file")
	WebElement activeFileElement;
	@FindBy(id = "menu_color")
	WebElement colourElement;
	@FindBy(xpath = "//button[@class='btn btn-block-sm btn-danger']")
	WebElement saveElement;
	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
	WebElement successMessageElement;
	@FindBy(id = "menu_order")
	WebElement menuOrderElement;
	@FindBy(xpath = "//a[@class='btn btn-rounded btn-primary'")
	WebElement searchElement;
	@FindBy(id = "sr_name")
	WebElement searchTextElement;

	public void managePageSelection() {
		waitUtility.waitForElementClickable(driver, settingsIconElement);
		settingsIconElement.click();
		manageMenuElement.click();
	}

	public boolean managePageListVisibility() {
		return tableElement.isDisplayed();
	}

	public void creatingNewMenu(String menuName, String parentMenu, String url, String favIcon, String tableValue,
			String fileValue, String colourValue, int menuOrder) {
		String generatedMenuName = menuName + generaUtility.generateCurrentDateAndTime();
		menuNameRandomString = generatedMenuName;
		newManageElement.click();
		waitUtility.waitForElement(driver, newManageElement);
		Actions actions = new Actions(driver);
		actions.moveToElement(newMenuNameElement).click().sendKeys(generatedMenuName).build().perform();
		menuOrderElement.clear();
		int randomMenuOrder = generaUtility.randomInteger(menuOrder);
		menuOrderElement.sendKeys(String.valueOf(randomMenuOrder));
		generaUtility.selectDropdownbyText(parentMenuElement, parentMenu);
		urlElement.sendKeys(url);
		favIconElement.sendKeys(favIcon);
		menuTableElement.sendKeys(tableValue);
		activeFileElement.sendKeys(fileValue);
		colourElement.sendKeys(colourValue);

		saveElement.click();
		waitUtility.waitForElement(driver, successMessageElement);
	}

	public void generateRandomMenuName(String menuName) {
		String menuNameRandomString = menuName + generaUtility.generateCurrentDateAndTime();
		this.menuNameRandomString = menuNameRandomString;
		System.out.println("Random Name: " + menuNameRandomString);
	}

	public String readRandomCategoryNameString() {
		return menuNameRandomString;
	}

	public void searchCreatedMenu() {
		WebElement element;

		List<WebElement> rowCountElement = driver
				.findElements(By.xpath("//table[@class='table table-bordered']//tbody//tr//td[1]"));
		int count = (rowCountElement.size());
		System.out.println(count);
		System.out.println("search word: " + "|-" + menuNameRandomString);
		for (int i = 0; i < count; i++) {
			if (rowCountElement.get(i).getText().equals(("|-" + menuNameRandomString))) {
				System.out.println("ele: " + rowCountElement.get(i).getText());
				System.out.println("entering loop");
				String locatorString = "//table[@class='table table-bordered']//tbody//tr[" + (i + 1) + "]//td";
				element = driver.findElement(By.xpath(locatorString));
				System.out.println("Value: " + element.getText());
				break;
			} else {
				System.out.println("No result");
			}
		}
	}
}