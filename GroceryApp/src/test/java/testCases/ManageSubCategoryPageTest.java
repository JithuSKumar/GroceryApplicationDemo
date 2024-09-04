package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.HomePage;
import pageFiles.LoginPage;
import pageFiles.ManageAdminUserPage;
import pageFiles.ManageCategoryPage;
import pageFiles.ManageSubCategoryPage;
import utilities.ExcelUtilities;

public class ManageSubCategoryPageTest extends BaseClassTest {
	
	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	ManageSubCategoryPage manageSubCategoryPage;
	
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
	public void verifyChainingSubCategoryPage()
	{
		loginpage = new LoginPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		homePage=loginpage.signInChaining();
		adminUserCreationPage= homePage.getDashboardTextChaining();
		manageCategoryPage = adminUserCreationPage.adminUserListSelectionChaining();
		manageSubCategoryPage = manageCategoryPage.categoryPageSelectionChaining();
		manageSubCategoryPage.subCategoryPageSelectionChaining();
		String actualValueString = manageSubCategoryPage.getBreadCrumbText();
		String expectedValue = "List Sub Categories"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}
	
	@Test (priority = 1,groups = "Individual")
	public void verifyIfCategoryListisLoaded() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageSubCategoryPage = new ManageSubCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageSubCategoryPage.subCategoryPageSelection();
		boolean actualTableStatus = manageSubCategoryPage.subCategoryPageSelection();
		Assert.assertEquals(actualTableStatus, true, Constant.subCategoryList);
	}
 
	@Test (priority = 2,groups = "Individual")
	public void verifyCreationofNewSubCategory() throws IOException 
	{
		loginpage = new LoginPage(driver);
		manageCategoryPage = new ManageCategoryPage(driver);
		manageSubCategoryPage = new ManageSubCategoryPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageCategoryPage.categoryPageSelection();	
		String CategoryType = manageCategoryPage.fetchingTheFirstEntryinTable();//searchNewlyAddedCategoryVisibility(getCreatedCategoryNameString());
		manageSubCategoryPage.subCategoryPageSelection();
		String subCategoryName = ExcelUtilities.getString(1, 0,"ManageCategory&Subcategory");
		manageSubCategoryPage.creationOfNewSubCategory(subCategoryName, CategoryType);
		manageSubCategoryPage.subCategoryPageSelection();
		String actualValueString = manageSubCategoryPage.fetchingTheFirstEntryinTable();
		String expectedValue = manageSubCategoryPage.getCategoryNameString();
		Assert.assertEquals(actualValueString, expectedValue, Constant.newCategoryFail);
	}
}
