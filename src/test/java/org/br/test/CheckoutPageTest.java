package org.br.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.br.pages.CheckoutPage;
import org.br.pages.ProductPage;
import org.br.pages.SearchPage;
import org.br.pages.ShopBagPage;
import org.br.testbase.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CheckoutPageTest extends TestBase {
	
	CheckoutPage checkoutPage;
	String[] productDetailsExpected = new String[3];
	
	@BeforeTest
	public void setUp() {
		init();
		System.out.println("*** CheckoutPageTest started ... ***");
		SearchPage searchPage = new SearchPage();
		searchPage.searchQuery("men coat");
		ProductPage productPage;
		productPage = searchPage.selectItem();
		productPage.itemAddToBag();
		productDetailsExpected = productPage.getProductDetail();
		ShopBagPage shopBagPage;
		shopBagPage = productPage.clickCheckout();
		checkoutPage = shopBagPage.clickCheckout();
	}

	@Test (priority=1)
	public void productDetailActualTest() {
		SoftAssert softAssert = new SoftAssert();
		String[] productDetailsActual = checkoutPage.getProductDetailActual();
		// CHECK: System.out.println(productDetailsActual[0] + " | " + productDetailsActual[1] + " | " + productDetailsActual[2]);
		for (int i=0;i<productDetailsExpected.length;i++) {
			softAssert.assertEquals(productDetailsActual[i], productDetailsExpected[i], "product detail mismatch");
		}
		softAssert.assertAll();
	}
	
	@Test (enabled=true, priority=2)
	public void orderSummaryTest() {
		Boolean orderSummaryBox = checkoutPage.getOrderSummaryBox();
		String totalPrice = checkoutPage.getOrderSummaryTotalPrice();
		System.out.println(orderSummaryBox + " | " + totalPrice);
		assertTrue(orderSummaryBox);
	}
	
	@Test (enabled=true, priority=3)
	public void guestUserDisplayTest() {
		SoftAssert softAssert1 = new SoftAssert();
		checkoutPage.clickGuestUser();
		Boolean[] guestUserDisplays = checkoutPage.getGuestUserDisplays();
		// CHECK: System.out.println(guestUserDisplays[0] + " | " + guestUserDisplays[1] + " | " + guestUserDisplays[2]);
		for (int i=0;i<guestUserDisplays.length;i++) {
			softAssert1.assertTrue(guestUserDisplays[i]);
		}
		softAssert1.assertAll();
	}
	
	@Test (enabled=true, priority=4)
	public void returnUserDisplayTest() {
		SoftAssert softAssert2 = new SoftAssert();
		checkoutPage.clickReturnUser();
		Boolean[] returnUserDisplays = checkoutPage.getReturnUserDisplays();
		// CHECK: System.out.println(returnUserDisplays[0] + " | " + returnUserDisplays[1] + " | " + returnUserDisplays[2]);
		for (int i=0;i<returnUserDisplays.length;i++) {
			softAssert2.assertTrue(returnUserDisplays[i]);
		}
		softAssert2.assertAll();
	}
	
	@Test (enabled=true, priority=5)
	public void loginReturnUserTest() {
		checkoutPage.loginReturnUser("bananarepublic.2222@gmail.com", "*2222Abc");
	}
	
	@Test (priority=6)
	public void getTitles() {
		String[] titles = checkoutPage.getTitles();
		for (String title: titles) {
			System.out.println("-- " + title);
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
