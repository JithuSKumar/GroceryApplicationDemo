package testCases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.HomePage;
import pageFiles.LoginPage;
import pageFiles.ManageAdminUserPage;
import pageFiles.ManageCategoryPage;
import utilities.ExcelUtilities;

public class ManageCategoryPageTest extends BaseClassTest {

	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	
	public String getCreatedCategoryNameString()
	{	
		return createdCategoryNameString;
	}

	public void setCreatedCategoryNameString(String createdCategoryNameString)
	{
		this.createdCategoryNameString = createdCategoryNameString;
	}

	String createdCategoryNameString;
	
	
	@Test
	public void verifyChainingCategoryPage()
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		adminUserCreationPage= homePage.getDashboardTextChaining();
		manageCategoryPage = adminUserCreationPage.adminUserListSelectionChaining();
		manageCategoryPage.categoryPageSelection();
		String actualValueString = manageCategoryPage.getBreadCrumbText();
		String expectedValue = "List Categories"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}
	
	
	@Test (priority = 1,groups = "Individual")
	public void verifyIfCategoryListisLoaded() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();
		boolean actualTableStatus = manageCategoryPage.categoryPageSelection();
		Assert.assertEquals(actualTableStatus, true,Constant.manageCategoryList );
	}
	
	@Test (priority = 2, groups= "Individual")
	public void VerifyNewCategoryCreation() throws AWTException, IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();
		manageCategoryPage.fetchingTheFirstEntryinTable();
		manageCategoryPage.newCategoryCreation(ExcelUtilities.getString(1, 0,"ManageCategory&Subcategory"));
		setCreatedCategoryNameString(manageCategoryPage.getCategoryNameString());
		manageCategoryPage.categoryPageSelection();	
		String actualValueString = manageCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValue = manageCategoryPage.getCategoryNameString();
		Assert.assertEquals(actualValueString, expectedValue, Constant.newCategoryFail);
	}
	
	@Test (priority = 3, groups= "Individual")
	public void searchNewlyAddedCategory() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();	
		manageCategoryPage.searchNewlyAddedCategoryVisibility(getCreatedCategoryNameString());
		String actualValueString = manageCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValueString = getCreatedCategoryNameString();
		Assert.assertEquals(actualValueString, expectedValueString, Constant.categoryFilter);
		
	}
	
	@Test(priority = 4, enabled = false, groups ="Individual")
	public void VerifyDeleteofNewlyAddedCategory() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();	
		manageCategoryPage.fetchingTheFirstEntryinTable();
		manageCategoryPage.deleteNewlyAddedCategory();
		manageCategoryPage.fetchingTheFirstEntryinTable();
		String actualValueString = manageCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValue = ExcelUtilities.getString(1, 0,"ManageCategory&Subcategory");
		Assert.assertNotEquals(actualValueString, expectedValue, Constant.categoryDelete);
	}
}
