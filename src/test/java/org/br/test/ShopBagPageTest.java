package org.br.test;

import static org.testng.Assert.assertEquals;

import org.br.pages.CheckoutPage;
import org.br.pages.ProductPage;
import org.br.pages.SearchPage;
import org.br.pages.ShopBagPage;
import org.br.testbase.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShopBagPageTest extends TestBase {
	
	ShopBagPage shopBagPage;
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** ShopBagPageTest started ... ***");
		SearchPage searchPage = new SearchPage();
		searchPage.searchQuery("men coat");
		ProductPage productPage;
		productPage = searchPage.selectItem();
		productPage.itemAddToBag();
		productPage.getProductDetail();
		shopBagPage = productPage.clickCheckout();
	}
	
	@Test (priority=0) 
	public void titleTest() {
		String title = shopBagPage.getTitle();
		assertEquals(title, "Shopping Bag | Banana Republic", "title mismatch");
	}
	
	@Test (priority=1)
	public void checkProductDetailTest() {
		String[] productDetailExpected = shopBagPage.getExpectedProductDetail();
		String[] productDetailActual = shopBagPage.getActualProductDetail();
		// CHECK:
		// System.out.println("actual: " + productDetailActual[0] + " | " + productDetailActual[1] + " | " + productDetailActual[2]);
		// System.out.println("expected: " + productDetailExpected[0] + " | " + productDetailExpected[1] + " | " + productDetailExpected[2]);
		
		for (int i=0;i<productDetailExpected.length;i++) {
			assertEquals(productDetailActual[i], productDetailExpected[i], "product detail mismatch");
		}
	}
	
	@Test (priority=2)
	public void clickCheckoutTest() {
		CheckoutPage checkoutPage = shopBagPage.clickCheckout();
		assertEquals(checkoutPage.getClass().getName(), "org.br.pages.CheckoutPage", "type/class mismatch");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
