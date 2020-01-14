package org.br.pages;

import org.br.pageUtil.PopUpWindowCheck;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends TestBase {

	String[] productArrayExpected = new String[3];
	WebDriverWait wait = new WebDriverWait(driver, 2);
	By description = By.xpath("//*[@class='sds_g-4-5 shoppingBag__productDescColumn']/div/div/div");
	By size = By.id("shoppingBag__itemDesc_size");
	By price = By.xpath("//*[@class='sds_g-1-3 shoppingBag__itemPrice']/div");
	By orderSummary = By.id("orderSummary");
	By orderSummary_totalPrice = By.id("orderSummary__totalPrice");
	
	By guestUserButton = By.id("signInContainer__guestUser");
	By guestEmail = By.id("GuestSignInForm__email");
	By guestSignIn = By.id("guestSignInForm__continue--button");
	
	By returnUserButton = By.id("signInContainer__returnUser");
	By returnEmail = By.id("ReturningSignInForm__email");
	By returnPwd = By.id("ReturningSignInForm__pass");
	By returnSignInButton = By.id("ReturningSignInForm__button--signIn");
	
	By shipping = By.id("shippingAddress__title");
	By payment = By.id("payment__header--title");
	By shopBag = By.id("shoppingBag__title");
	By orderSummaryTitle = By.id("orderSummary__title");
	
	public CheckoutPage() {
		System.out.print("CheckoutPage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
		popUpWindowCheck.checkMktSticker();
	}
	
	public String[] getTitles() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(shipping));
		String[] titles = new String[5];
		titles[0] = driver.getTitle();
		titles[1] = driver.findElement(shipping).getText();
		titles[2] = driver.findElement(payment).getText();
		titles[3] = driver.findElement(shopBag).getText().split("\\(")[0];
		titles[4] = driver.findElement(orderSummaryTitle).getText();
		return titles;
	}
	
	public String[] getProductDetailActual() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(description));
		String[] productDetailActual = new String[3];
		productDetailActual[0] = driver.findElement(description).getText();
		productDetailActual[1] = driver.findElement(price).getText();
		productDetailActual[2] = driver.findElement(size).getText();
		return productDetailActual;
	}
	
	public Boolean getOrderSummaryBox() {
		Boolean orderSummaryBox = driver.findElement(orderSummary).isDisplayed();
		return orderSummaryBox;
	}
	
	public String getOrderSummaryTotalPrice() {
		String totalPrice = driver.findElement(orderSummary_totalPrice).getText();
		return totalPrice;
	}
	
	public Boolean[] getGuestUserDisplays() {
		Boolean[] guestUserDisplays = new Boolean[3];
		guestUserDisplays[0] = driver.findElement(guestUserButton).isDisplayed();
		guestUserDisplays[1] = driver.findElement(guestEmail).isDisplayed();
		guestUserDisplays[2] = driver.findElement(guestSignIn).isDisplayed();
		return guestUserDisplays;
	}
	
	public Boolean[] getReturnUserDisplays() {
		Boolean[] returnUserDisplays = new Boolean[4];
		returnUserDisplays[0] = driver.findElement(returnUserButton).isDisplayed();
		returnUserDisplays[1] = driver.findElement(returnEmail).isDisplayed();
		returnUserDisplays[2] = driver.findElement(returnPwd).isDisplayed();
		returnUserDisplays[3] = driver.findElement(returnSignInButton).isDisplayed();
		return returnUserDisplays;
	}
	
	public void clickGuestUser() {
		driver.findElement(guestUserButton).click();
	}
	
	public void clickReturnUser() {
		driver.findElement(returnUserButton).click();
	}
	
	public void loginReturnUser(String email, String pwd) {
		driver.findElement(returnEmail).sendKeys(email);;
		driver.findElement(returnPwd).sendKeys(pwd);
		driver.findElement(returnSignInButton).click();
	}
}
