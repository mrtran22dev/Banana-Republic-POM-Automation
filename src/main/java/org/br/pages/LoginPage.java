package org.br.pages;

import java.util.List;

import org.br.pageUtil.PopUpWindowCheck;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends TestBase {
	
	public LoginPage() {
		System.out.print("LoginPage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
	}
	
	By email = By.id("emailAddress");
	By password = By.id("password");
	By signInButton = By.id("signInButton");
	By acctSettings = By.id("accountSettings");
	By acctEmail = By.xpath("//*[@id='accountSettings']/div/ul/child::li[2]/span/a/span");
	By orderStatus = By.id("orderStatus");
	By acctDropDown = By.xpath("//*[@class='universal-nav--right']/div/button");
	By acctDropDownItems = By.xpath("//*[@class='dropdown-content dropdown-content_my-account dropdown-content_anchor-right']/li");
	By signOutButton = By.id("myAccountSignOut");
	By searchInput = By.xpath("//*[@id='search-form']/input");
	By searchButton = By.xpath("//*[@id='search-form']/button");
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void loginAccount(String username, String pwd) {
		driver.findElement(email).sendKeys(username);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(signInButton).click();
	}
	
	public Object[] getAccountInfo() {
		boolean acctDisplay = driver.findElement(acctSettings).isDisplayed();
		boolean orderDisplay = driver.findElement(orderStatus).isDisplayed();
		String email = driver.findElement(acctEmail).getText();
		Object[] acctObject = {acctDisplay, orderDisplay, email};
		return acctObject;
	}
	
	public List<WebElement> getAccountDropDownMenu() {
		driver.findElement(acctDropDown).click();
		List<WebElement> acctDropDownContents = driver.findElements(acctDropDownItems);
		return acctDropDownContents;
	}
	
	public void clickSignOut() {
		driver.findElement(signOutButton).click();											// do NOT have to return anything 
	}																						// because after sign-out clicked
																							// page goes back to sign-in page
	public SearchPage clickSearchPage() {
		driver.findElement(searchInput).sendKeys("*");
		driver.findElement(searchButton).click();
		return new SearchPage();
	}
}
