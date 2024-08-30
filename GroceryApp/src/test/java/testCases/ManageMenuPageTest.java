package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pageFiles.LoginPage;
import pageFiles.ManageMenuPage;
import utilities.ExcelUtilities;

public class ManageMenuPageTest extends BaseClassTest{

	LoginPage loginpage;
	ManageMenuPage manageMenuPage;

	String menuName = ExcelUtilities.getString(1, 0,"ManageMenu");
	String parentMenu = ExcelUtilities.getString(1, 1,"ManageMenu");
	String url =  ExcelUtilities.getString(1, 2,"ManageMenu");
	String favIcon =  ExcelUtilities.getString(1, 3,"ManageMenu"); 
	String tableValue =  ExcelUtilities.getString(1, 4,"ManageMenu");
	String fileValue =  ExcelUtilities.getString(1, 5,"ManageMenu");
	String colourValue =  ExcelUtilities.getString(1, 6,"ManageMenu");
	int menuOrder = ExcelUtilities.getInt(1, 7,"ManageMenu");
	
	@Test (priority = 1,groups = {"regression"})
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

	@Test (priority = 2)
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

	@Test (priority = 3,groups = {"regression"})
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

