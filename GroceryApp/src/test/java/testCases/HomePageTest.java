package testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.HomePage;
import pageFiles.LoginPage;

public class HomePageTest extends BaseClassTest {
	
	
	LoginPage loginpage;
	HomePage homePage;
	
	@Test
	public void verifyChainingHomePage()
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		String actualValueString = homePage.getBreadCrumbText();
		String expectedValue = "Dashboard"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}
	
	@Test(groups = "Individual")
	public void verifyTheUserAbleToLoginWithValidCredentials() throws IOException
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
	}
	
	
}
