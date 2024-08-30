package utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class GeneralUtilities {

	String pageTitle;
	
	public String getTextElement(WebElement element) {
		return element.getText();
	}

	public String selectValueFromDropdown(WebElement element, String value) {
		Select object = new Select(element);
		object.selectByValue(value);
		WebElement selectedElement = object.getFirstSelectedOption();
		return selectedElement.getText();
	}

	public void selectDropdownbyText(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	public void alertHandlingaccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public boolean elementIsSelected(WebElement element) {
		element.click();
		boolean b = element.isSelected();
		return b;
	}

	public void mouseRightClick(WebDriver driver, WebElement element) {
		Actions actObject = new Actions(driver);
		actObject.contextClick(element).perform();
	}

	public void dragAnddrop(WebElement dragableItem, WebElement dropItem, WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.clickAndHold(dragableItem).moveToElement(dropItem).release(dropItem).build().perform();
	}

	/*
	 * public int randomInterger(int limit) { Random random = new Random(); int
	 * randomNumber = random.nextInt(limit); return randomNumber; }
	 */

	public int randomInteger(int limit)
	{
	    return new Random().nextInt(limit);
	}
	public String generateCurrentDateAndTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyhhmmss");//for 24hrs format ddMMyyHHmmss
		return formatter.format(date);
	}

	public String generateCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}
	
	public String pageTitle(WebDriver driver)
    {
		return pageTitle = driver.getTitle().replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }
	

}
