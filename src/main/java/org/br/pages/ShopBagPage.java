package org.br.pages;

import org.br.pageUtil.PopUpWindowCheck;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;

public class ShopBagPage extends TestBase {
	
	String[] productDetailActual = new String[3];
	String[] productArrayExpected = new String[3];
	By description = By.xpath("//*[@class='productInfoList']/child::dd[1]/a");
	By size = By.xpath("//*[@class='productInfoList']/child::div[2]/dd/a");
	By price = By.xpath("//*[@class='productInfoList']/child::div[3]/dd/child::span[2]");
	By checkout = By.xpath("//*[@class='shoppingBagCheckout']/div/button");
	
	public ShopBagPage() {
		System.out.print("ShopBagPage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
	}
	
	public ShopBagPage(String expDescription, String expPrice, String expSize) {
		this.productArrayExpected[0] = expDescription;
		this.productArrayExpected[1] = expPrice;
		this.productArrayExpected[2] = expSize;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String[] getExpectedProductDetail() {
		return productArrayExpected;
	}
	
	public String[] getActualProductDetail() {
		productDetailActual = new String[3];
		productDetailActual[0] = driver.findElement(description).getText();
		productDetailActual[1] = driver.findElement(price).getText();
		productDetailActual[2] = driver.findElement(size).getText();
		return productDetailActual;
	}
	
	public CheckoutPage clickCheckout() {
		driver.findElement(checkout).click();
		// CHECK: System.out.println("checkout(): " + productDetailActual[0] + " | " +  productDetailActual[1] + " | " +  productDetailActual[2]);
		return new CheckoutPage();
	}
}
