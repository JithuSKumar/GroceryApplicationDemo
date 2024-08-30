package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.LoginPage;
import pageFiles.ManageCategoryPage;
import pageFiles.ManageSubCategoryPage;
import utilities.ExcelUtilities;

public class ManageSubCategoryPageTest extends BaseClassTest {
	
	LoginPage loginpage;
	ManageSubCategoryPage manageSubCategoryPage;
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
	
	@Test (priority = 1)
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
 
	@Test (priority = 2)
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
