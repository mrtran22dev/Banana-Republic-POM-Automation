package org.br.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.br.pages.HomePage;
import org.br.pages.LoginPage;
import org.br.pages.SearchPage;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchPageTest extends TestBase {

	SearchPage searchPage;
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** SearchPageTest started ... ***");
		LoginPage loginPage = new LoginPage();
		searchPage = loginPage.clickSearchPage();
	}
	
	@Test (enabled=true, priority=1)
	public void titleTest() {
		String title = searchPage.getTitle();
		assertEquals(title, "Search Page", "title mismatch");
	}
	
	@Test (enabled=true, priority=2)
	public void searchQueryTest() {
		By imgTag = By.tagName("img");
		List<WebElement> searchResults = searchPage.searchQuery("men coat");
		System.out.println("# of search results: " + searchResults.size());
		for(int i=0;i<searchResults.size();i++) {
			// CHECK: System.out.println("image " + i + ": " + searchResults.get(i).findElement(imgTag).isDisplayed());
			assertTrue(searchResults.get(i).findElement(imgTag).isDisplayed());
		}
	}
	
	@Test (enabled=true, priority=3)
	public void selectItemTest() {
		String productPageClass = searchPage.selectItem().getClass().getName();
		assertEquals(productPageClass, "org.br.pages.ProductPage", "class/type mismatch");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
