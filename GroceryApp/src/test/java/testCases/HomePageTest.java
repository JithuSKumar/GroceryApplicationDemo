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
	public void verifyTheUserAbleToLoginWithValidCredentials() throws IOException
	{
		loginpage = new LoginPage(driver);
		homePage = new HomePage(driver);
		
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		
		String actual = homePage.getDashboardText();
		String expected = "Dashboard";
		Assert.assertEquals(actual, expected,Constant.homePageDashboard);
		
		homePage.manageProdcutStatus();
		boolean actualProductStatus = homePage.manageProdcutStatus();
		Assert.assertEquals(actualProductStatus, false,Constant.homePageProductStatus );
	}
	
	
}
