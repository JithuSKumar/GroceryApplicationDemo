package testCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

import utilities.ExcelUtilities;
import utilities.ExtentReportUtilities;
import utilities.ScreenShotUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class BaseClassTest {

	WebDriver driver;
	String userName = ExcelUtilities.getString(1, 0,"LoginPage");
	String password = ExcelUtilities.getString(1, 1,"LoginPage");
	ScreenShotUtilities screenShotUtilities;
	ExtentReportUtilities extentReportUtilities;
	ExtentTest extentTest;
	
	public static Properties properties;

	public static void testBasics() throws IOException
	{
		properties =new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
		properties.load(fileInputStream);
	}

	@BeforeMethod
	@Parameters("Browser")
	public void beforeMethod(String browserName) throws IOException
	{
		extentReportUtilities = new ExtentReportUtilities();
		//extentTest = extentReportUtilities.createTest("Name");
		testBasics();
		if (browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if (browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		//driver.get("https://groceryapp.uniqassosiates.com/admin/login");
		driver.get(properties.getProperty("BaseUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
		
	}

	@AfterMethod
	public void afterMethod(ITestResult iTestResult) throws IOException {
		if (iTestResult.getStatus() == ITestResult.FAILURE) 
		{
			screenShotUtilities = new ScreenShotUtilities();
			screenShotUtilities.captureFailureScreenShot(driver, iTestResult.getName());
		}
		driver.quit();
	}

}
