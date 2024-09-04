package testCases;

import static org.testng.Assert.assertTrue;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.HomePage;
import pageFiles.LoginPage;
import pageFiles.ManageAdminUserPage;
import pageFiles.ManageCategoryPage;
import pageFiles.ManageContactPage;
import pageFiles.ManageSubCategoryPage;

public class ManageContactPageTest extends BaseClassTest {

	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	ManageSubCategoryPage manageSubCategoryPage;
	ManageContactPage manageContactPage;
	
	
	@Test
	public void verifyChainingContactPage()
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		adminUserCreationPage= homePage.getDashboardTextChaining();
		manageCategoryPage = adminUserCreationPage.adminUserListSelectionChaining();
		manageSubCategoryPage = manageCategoryPage.categoryPageSelectionChaining();
		manageContactPage = manageSubCategoryPage.subCategoryPageSelectionChaining();
		manageContactPage.contactPageSelectionChaining();
		String actualValueString = manageSubCategoryPage.getBreadCrumbText();
		String expectedValue = "Contact Us"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}

	@Test(priority = 1,groups = "Individual")
	public void verifyVManageContactPageLoad() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		manageContactPage = new ManageContactPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		manageContactPage.contactPageVisibilityCheck();
		manageContactPage.getBreadCrumbText();
		String actualValueString = manageContactPage.getBreadCrumbText();
		String expectedValue = "Contact";
		Assert.assertTrue(actualValueString.contains(expectedValue),Constant.breadCrumbsString + expectedValue);
		manageContactPage.saveDataTofile();
	}
	
}