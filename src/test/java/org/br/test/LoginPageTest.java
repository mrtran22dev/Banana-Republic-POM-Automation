package org.br.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.br.excelUtil.ReadWriteExcelUtil;
import org.br.pages.HomePage;
import org.br.pages.LoginPage;
import org.br.pages.SearchPage;
import org.br.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ReadWriteExcelUtil excelUtil; 
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** LoginPageTest started ... ***");
		homePage = new HomePage();
		loginPage = homePage.clickSignIn();
		String pageName = loginPage.getClass().toString().split("class org.br.pages.")[1];
		excelUtil = new ReadWriteExcelUtil(pageName); 
	}
	
	@Test (enabled=true, priority=1)
	public void titleTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> titleExp = excelUtil.getExpectedData(methodName);
		String actual = loginPage.getTitle();
		excelUtil.checkPassFail(0, actual, titleExp.get(0));
		assertEquals(actual, titleExp.get(0), "title mismatch");
	}
	
	@Test (enabled=true, priority=2)
	public void loginAccountTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> loginData = excelUtil.getExpectedData(methodName);
		loginPage.loginAccount(loginData.get(0), loginData.get(1));
	}
	
	@Test (enabled=true, priority=3)
	public void accountInfoTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> loginData = excelUtil.getExpectedData(methodName);
		Object[] acctObject = loginPage.getAccountInfo();
		// CHECK: System.out.println(acctObject[0] + " | " + acctObject[1] + " | " + acctObject[2]);
		excelUtil.checkPassFail(0, acctObject[2].toString(), loginData.get(0));
		assertTrue((Boolean) acctObject[0]);
		assertTrue((Boolean) acctObject[1]);
		assertEquals(acctObject[2], loginData.get(0), "email display mismatch");
	}
	
	@Test (enabled=true, priority=4)
	public void accountDropDownMenuTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> menuExpData = excelUtil.getExpectedData(methodName);
		List<WebElement> acctDropDownContent = loginPage.getAccountDropDownMenu();
		acctDropDownContent.remove(acctDropDownContent.size()-1);									// remove last null element
		String actual;
		for (int i=0;i<acctDropDownContent.size();i++) {
			// CHECK: System.out.println(menuExpData.get(i) + " | " + acctDropDownContent.get(i).getAttribute("data-site-cat-key"));
			actual = acctDropDownContent.get(i).getAttribute("data-site-cat-key");
			excelUtil.checkPassFail(i, actual, menuExpData.get(i));
		}
	}
	
	@Test (enabled=true, priority=5)
	public void clickSignOutTest() {
		loginPage.clickSignOut();
	}
	
	@Test (enabled=true, priority=6)
	public void clickSearchPageTest() {
		String searchPageClass = loginPage.clickSearchPage().getClass().getName();
		assertEquals(searchPageClass, "org.br.pages.SearchPage", "class/type mismatch");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
