package org.br.test;
import org.br.pages.HomePage;
import org.br.pages.LoginPage;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.br.excelUtil.*;

public class HomePageTest extends TestBase {
	
	HomePage homePage;
	ReadWriteExcelUtil excelUtil;

//	HomePageTest() {																		// 'super' extends TestBase class and can used to immediately invoke super/parent methods
//		super();																			// and direct access to member variables
//	}																						// *** call super class constructor
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** HomePageTest started ... ***");
		homePage = new HomePage();
		String pageName = homePage.getClass().toString().split("class org.br.pages.")[1];
		excelUtil = new ReadWriteExcelUtil(pageName); 
	}
	
	@Test (priority=1)
	public void getTitleTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> titleExp = excelUtil.getExpectedData(methodName);
		String actual = homePage.getPageTitle();
		excelUtil.checkPassFail(0, actual, titleExp.get(0));
		assertEquals(actual, titleExp.get(0), "title mismatch");
	}
	
	@Test (enabled=true, priority=2)
	public void checkLogosTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> logosExpData = excelUtil.getExpectedData(methodName);
		List<WebElement> logos = homePage.getLogos();
		String actual;
		SoftAssert softAssert1 = new SoftAssert();
		for (int i=0;i<logos.size();i++) {
			actual = logos.get(i).getAttribute("aria-label");
			// CHECK: System.out.println("actual: " + actual + ", expected: " + logosData.get(i));
			softAssert1.assertEquals(actual, logosExpData.get(i), "logos mismatch");
			excelUtil.checkPassFail(i, actual, logosExpData.get(i));
		}
		softAssert1.assertAll();
	}
	
	@Test (enabled=true, priority=3)
	public void checkNavBarMenuTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> navBarExpData =excelUtil.getExpectedData(methodName);
		List<WebElement> navbarWebElements = homePage.getNavBarMenu();
		SoftAssert softAssert2 = new SoftAssert();
		WebElement e;
		String actual;
		Actions builder = new Actions(driver);
		Action mouseOverNavMenu;
		// CHECK: System.out.println("\nStarting navbar check ... ");
		// CHECK: System.out.println("navbar size: " + navbarWebElements.size());
		
		for (int i=0;i<navbarWebElements.size();i++) {
			//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			mouseOverNavMenu = builder.moveToElement(navbarWebElements.get(i)).build();
			mouseOverNavMenu.perform();
			e = navbarWebElements.get(i).findElement(By.tagName("a"));
			actual = e.getAttribute("aria-label");
			softAssert2.assertEquals(actual, navBarExpData.get(i), "nav bar mismatch");
			excelUtil.checkPassFail(i, actual, navBarExpData.get(i));
		}
		
		// move mouse cursor to another web element so mouse cursor is freed for successive tests
		WebElement we = driver.findElement(By.xpath("//*[@class='universal-nav--inner-wrapper'][1]"));			
		mouseOverNavMenu = builder.moveToElement(we, 1080, 20).build();
		mouseOverNavMenu.perform();
		softAssert2.assertAll();
	}
	
	@Test (enabled=true, priority=4)
	public void checkShopBagTest() {
		WebElement shopBag = homePage.getShopBag();
		assertTrue(shopBag.isDisplayed());
	}
	
	@Test (enabled=true, priority=5)
	public void checkSignInMenuTest() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		ArrayList<String> acctExpData = excelUtil.getExpectedData(methodName);
		List<String> acctContainerTexts = homePage.getSignInMenu();
		SoftAssert softAssert3 = new SoftAssert();
		for (int i=0;i<acctContainerTexts.size();i++) {
			String actual = acctContainerTexts.get(i);
			softAssert3.assertEquals(actual, acctExpData.get(i), "sign-in menu mismatch");
			excelUtil.checkPassFail(i, actual, acctExpData.get(i));
		}
		softAssert3.assertAll();
	}
	
	@Test (enabled=true, priority=6)
	public void clickSignInTest() {
		LoginPage loginPage = homePage.clickSignIn();
		assertEquals(loginPage.getClass().getName(), "org.br.pages.LoginPage", "returned type not LoginPage");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
