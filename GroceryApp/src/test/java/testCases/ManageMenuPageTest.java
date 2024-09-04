package testCases;

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
import pageFiles.ManageMenuPage;
import pageFiles.ManageNewsPage;
import pageFiles.ManageSubCategoryPage;
import utilities.ExcelUtilities;

public class ManageMenuPageTest extends BaseClassTest{
	
	String menuName = ExcelUtilities.getString(1, 0,"ManageMenu");
	String parentMenu = ExcelUtilities.getString(1, 1,"ManageMenu");
	String url =  ExcelUtilities.getString(1, 2,"ManageMenu");
	String favIcon =  ExcelUtilities.getString(1, 3,"ManageMenu"); 
	String tableValue =  ExcelUtilities.getString(1, 4,"ManageMenu");
	String fileValue =  ExcelUtilities.getString(1, 5,"ManageMenu");
	String colourValue =  ExcelUtilities.getString(1, 6,"ManageMenu");
	int menuOrder = ExcelUtilities.getInt(1, 7,"ManageMenu");
	
	ManageMenuPage manageMenuPage;
	LoginPage loginpage;
	HomePage homePage;
	ManageAdminUserPage adminUserCreationPage;
	ManageCategoryPage manageCategoryPage;
	ManageSubCategoryPage manageSubCategoryPage;
	ManageContactPage manageContactPage;
	ManageNewsPage manageNewsPage;
	ManageFooterPage manageFooterPage;
	
	
	@Test
	public void verifyChainingMenuPage()
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
		manageMenuPage = manageFooterPage.footerPageSelectionChaining();
		manageMenuPage.managePageSelectionChaining();
		String actualValueString = manageMenuPage.getBreadCrumbText();
		String expectedValue = "Menu Management"; 
		Assert.assertTrue(actualValueString.contains(expectedValue), Constant.breadCrumbsString + expectedValue);
	}

	
	@Test (priority = 1,groups = "Individual")
	public void verifyIfManageMenuListIsLoaded() throws IOException {

		loginpage = new LoginPage(driver);
		manageMenuPage = new ManageMenuPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageMenuPage.managePageSelection();
		manageMenuPage.managePageListVisibility();
		boolean actualTableStatus = manageMenuPage.managePageListVisibility();
		Assert.assertEquals(actualTableStatus, true, Constant.manageMenuList);
	}

	@Test (priority = 2,groups = "Individual")
	public void createNewMenuItem() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageMenuPage = new ManageMenuPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageMenuPage.managePageSelection();
		manageMenuPage.creatingNewMenu(menuName, parentMenu, url, favIcon, tableValue, fileValue, colourValue,menuOrder);
	}

	@Test (priority = 3,groups = "Individual")
	public void verifyIfNewlyCreatedElementInList() throws IOException
	{
		loginpage = new LoginPage(driver);
		manageMenuPage = new ManageMenuPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		manageMenuPage.generateRandomMenuName(menuName);
		manageMenuPage.managePageSelection();
		manageMenuPage.searchCreatedMenu();
	}
}

