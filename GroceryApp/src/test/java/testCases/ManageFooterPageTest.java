package testCases;

import static org.testng.Assert.assertTrue;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.LoginPage;
import pageFiles.ManageFooterPage;

public class ManageFooterPageTest extends BaseClassTest {

	LoginPage loginpage;
	ManageFooterPage manageFooterPage;

	@Test(priority = 1,groups = {"regression"})
	public void verifyVManageFooterPageLoad() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		manageFooterPage = new ManageFooterPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		manageFooterPage.pageVisibilityCheck();
		String actualValueString = manageFooterPage.getBreadCrumbText();
		String expectedValue = "Footer"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
		
		manageFooterPage.saveDataTofile();
	}
	
}