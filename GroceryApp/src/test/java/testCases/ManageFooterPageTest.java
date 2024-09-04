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
import pageFiles.ManageFooterPage;
import pageFiles.ManageNewsPage;
import pageFiles.ManageSubCategoryPage;

public class ManageFooterPageTest extends BaseClassTest {

	ManageFooterPage manageFooterPage;
	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	ManageSubCategoryPage manageSubCategoryPage;
	ManageContactPage manageContactPage;
	ManageNewsPage manageNewsPage;
	
	
	@Test
	public void verifyChainingFooterPage()
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
		manageFooterPage = manageNewsPage.newsPageSelectionChaining();
		manageFooterPage.footerPageVisibilityCheck();
		String actualValueString = manageFooterPage.getBreadCrumbText();
		String expectedValue = "Footer Text"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}

	@Test(priority = 1,groups ="Individual")
	public void verifyVManageFooterPageLoad() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		manageFooterPage = new ManageFooterPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		manageFooterPage.footerPageVisibilityCheck();
		String actualValueString = manageFooterPage.getBreadCrumbText();
		String expectedValue = "Footer"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
		
		manageFooterPage.saveDataTofile();
	}
	
}