package org.br.test;


import org.br.pages.ProductPage;
import org.br.pages.SearchPage;
import org.br.pages.ShopBagPage;
import org.br.testbase.TestBase;

import static org.testng.Assert.assertEquals;

import org.br.pageUtil.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductPageTest extends TestBase {
	
	ProductPage productPage;
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** ProductPageTest started ... ***");
		SearchPage searchPage = new SearchPage();
		searchPage.searchQuery("men coat");
		productPage = searchPage.selectItem();
	}

	@Test (priority=1)
	public void getTitleTest() {
		String[] titles = productPage.getTitles();
		assertEquals(titles[0], titles[1] + " | Banana Republic", "title mismatch");
	}
	
	@Test (enabled=true, priority=2)
	public void itemAddToBagTest() {
		productPage.itemAddToBag();
	}
	
	@Test (enabled=true, priority=3)
	public void getProductDetailTest() {
		String[] productArray = productPage.getProductDetail();
		// CHECK: System.out.println(productArray[0] + " | " + productArray[1] + " | " + productArray[2]);
	}
	
	@Test (enabled=true, priority=4)
	public void clickCheckoutTest() {
		String shopBagClass = productPage.clickCheckout().getClass().getName();
		assertEquals(shopBagClass, "org.br.pages.ShopBagPage", "class/type mismatch");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
