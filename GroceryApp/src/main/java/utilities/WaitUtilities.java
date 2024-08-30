package utilities;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities {
	
	public static final long IMPLICIT_WAIT = 10;
	public static final long PAGE_LOAD_WAIT = 20;
	public static final long EXPLICIT_WAIT = 50;
	
	public void waitForElement(WebDriver driver, WebElement target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOf(target));
	}

	public void waitForElement(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.visibilityOfElementLocated(target));
	}

	public void waitForElementClickable(WebDriver driver, WebElement target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void waitForElementClickable(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void waitForElementSelected(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void waitFortextToBePresentInElement(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void waitForElementIsPresent(WebDriver driver, By target)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.presenceOfElementLocated(target));
	}
	
	public void waitForAlterIsPresent(WebDriver driver) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.alertIsPresent());
	}


	public void waitForAlterIsPresent(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForFrameToBeAvailableAndSwitchToIt(WebDriver driver, By target)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(target));
	}

	public void waitForInvisibilityOfElementLocated(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(target));
	}

	public void waitForPresenceOfAllElementsLocated(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(target));
	}

	public void waitForPresenceOfElementsLocatedBy(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.presenceOfElementLocated(target));
	}

	public void waitForPresenceOfElementsLocated(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.presenceOfElementLocated(target));
	}

	public void waitForTextToBePresentInElementLocated(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(target, null));
	}

	public void waitForAttributeToBe(WebDriver driver, By target) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
		wait.until(ExpectedConditions.attributeToBe(target, null, null));
	}

	public void pageLoadTimeout(WebDriver driver) 
	{
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_WAIT));
	}

	public void fluentwaitForElement(WebDriver driver, WebElement target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOf(target));
	}

	public void fluentWaitwaitForElement(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOfElementLocated(target));
	}

	public void fluentwaitForElementClickable(WebDriver driver, WebElement target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void fluentwaitForElementClickable(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void fluentwaitForElementSelected(WebDriver driver, WebElement element) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void fluentwaitFortextToBePresentInElement(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.elementToBeClickable(target));
	}

	public void fluentwaitForElementIsPresent(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(target));
	}

	public void fluentwaitForAlterIsPresent(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		//WebElement foo = (WebElement) fluentWait.until(ExpectedConditions.alertIsPresent());
	}

	public void fluentwaitForFrameToBeAvailableAndSwitchToIt(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(target));
	}

	public void fluentwaitForInvisibilityOfElementLocated(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(target));
	}

	public void fluentwaitForPresenceOfAllElementsLocated(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(target));
	}

	public void fluentwaitForPresenceOfElementsLocatedBy(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(target));
	}

	public void fluentwaitForPresenceOfElementsLocated(WebDriver driver, By target)
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.presenceOfElementLocated(target));
	}

	public static void fluentwaitForTextToBePresentInElementLocated(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.textToBePresentInElementLocated(target, null));
	}

	public void fluentwaitForAttributeToBe(WebDriver driver, By target) 
	{
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
		((FluentWait<WebDriver>) fluentWait).withTimeout(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).pollingEvery(Duration.ofSeconds(EXPLICIT_WAIT));
		((FluentWait<WebDriver>) fluentWait).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.attributeToBe(target, null, null));
	}

}

