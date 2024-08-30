package testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.ManageAdminUserPage;
import pageFiles.LoginPage;
import utilities.ExcelUtilities;

public class ManageAdminUserPageTest extends BaseClassTest {


	LoginPage loginpage;
	ManageAdminUserPage adminUserCreationPage;

	@Test(priority = 1)
	public void verifyValidAdminUserCreation() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		adminUserCreationPage = new ManageAdminUserPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		boolean isNavigatedToHomePage = loginpage.isHomePageDisplayed();
		assertTrue(isNavigatedToHomePage,Constant.homePageLogin);
		adminUserCreationPage.adminUserListSelection();
		adminUserCreationPage.fetchingTheFirstEntryinTable();
		String newuserName = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		String newUserpassword = ExcelUtilities.getString(1, 1,"AdminUserCreation");
		String newUserType = ExcelUtilities.getString(1, 2,"AdminUserCreation");
		adminUserCreationPage.newAdmineUserCreation(newuserName, newUserpassword, newUserType);
		adminUserCreationPage.fetchingTheFirstEntryinTable();
		String actualValueString = adminUserCreationPage.fetchingTheFirstEntryinTable();
		String expectedValue = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		Assert.assertEquals(actualValueString, expectedValue, Constant.newUserCreationFail);
	}
	
	@Test(priority = 2,groups = {"regression"})
	public void verifyFilteringofNewUser() throws InterruptedException, IOException
	{
		loginpage = new LoginPage(driver);
		adminUserCreationPage = new ManageAdminUserPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		adminUserCreationPage.adminUserListSelection();
		String userNameSearch = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		adminUserCreationPage.searchNewUser(userNameSearch);
		adminUserCreationPage.fetchingTheFirstEntryinTable();
		String actualValueString = adminUserCreationPage.fetchingTheFirstEntryinTable();
		String expectedValue = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		Assert.assertEquals(actualValueString, expectedValue, Constant.userFilter);
	}
	
	@Test(priority = 3)
	public void verifyNewUserLogin() throws IOException
	{
		String userName = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		String password = ExcelUtilities.getString(1, 1,"AdminUserCreation");
		loginpage = new LoginPage(driver);
		adminUserCreationPage = new ManageAdminUserPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		String actualValueString = adminUserCreationPage.getUserName();
		String expectedValue = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		Assert.assertEquals(actualValueString, expectedValue, Constant.loggedinUser);
	}
	
	@Test(priority = 4)
	public void verifyUserDeletion() throws IOException
	{
		loginpage = new LoginPage(driver);
		adminUserCreationPage = new ManageAdminUserPage(driver);
		loginpage.sendUsername(userName);
		loginpage.sendPassword(password);
		loginpage.signIn();
		adminUserCreationPage.adminUserListSelection();
		adminUserCreationPage.fetchingTheFirstEntryinTable();
		adminUserCreationPage.deletingTheFirstUser();
		String actualValueString = adminUserCreationPage.fetchingTheFirstEntryinTable();
		String expectedValue = ExcelUtilities.getString(1, 0,"AdminUserCreation");
		Assert.assertNotEquals(actualValueString, expectedValue, Constant.userDeletion);
	}





}
