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
import pageFiles.ManageNewsPage;
import pageFiles.ManageSubCategoryPage;

public class ManageNewsPageTest extends BaseClassTest {

	ManageNewsPage manageNewsPage;
	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	ManageSubCategoryPage manageSubCategoryPage;
	ManageContactPage manageContactPage;
	
	
	@Test
	public void verifyChainingNewsPage()
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		adminUserCreationPage= homePage.getDashboardTextChaining();
		manageCategoryPage = adminUserCreationPage.adminUserListSelectionChaining();
		manageSubCategoryPage = manageCategoryPage.categoryPageSelectionChaining();
		manageContactPage = manageSubCategoryPage.subCategoryPageSelectionChaining();
		manageNewsPage = manageContactPage.contactPageSelectionChaining();
		manageNewsPage.newsPageSelectionChaining();
		String actualValueString = manageNewsPage.getBreadCrumbText();
		String expectedValue = "Manage News"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}

	
	
	
	
	@Test(priority = 1,groups = "Individual")
	public void verifyManageFooterPageLoad() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		manageNewsPage = new ManageNewsPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		manageNewsPage.newsPageVisibilityCheck();
		String actualValueString = manageNewsPage.getBreadCrumbText();
		String expectedValue = "News"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
		manageNewsPage.saveDataTofile();
	}
}
