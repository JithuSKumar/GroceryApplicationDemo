package testCases;

import static org.testng.Assert.assertTrue;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.LoginPage;
import pageFiles.ManageContactPage;

public class ManageContactPageTest extends BaseClassTest {

	LoginPage loginpage;
	ManageContactPage manageContactPage;

	@Test(priority = 1,groups = {"regression"})
	public void verifyVManageContactPageLoad() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		manageContactPage = new ManageContactPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		manageContactPage.pageVisibilityCheck();
		manageContactPage.getBreadCrumbText();
		String actualValueString = manageContactPage.getBreadCrumbText();
		String expectedValue = "Contact";
		Assert.assertTrue(actualValueString.contains(expectedValue),Constant.breadCrumbsString + expectedValue);
		manageContactPage.saveDataTofile();
	}
	
}