package org.br.pages;

import org.br.pageUtil.PopUpWindowCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.br.excelUtil.ReadWriteExcelUtil;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HomePage extends TestBase {
	
	public HomePage() {
		System.out.print("HomePage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
	}
	
	//By popupLoc = By.id("EmailPopUpContent");
	By popupLoc = By.xpath("//*[@id='EmailPopUpContent']");
	By popUpCloseLoc = By.xpath("//*[@class='universal-modal__header']/button");
	By logosLoc = By.xpath("//*[@class='sister-brands-bar--inner']/a");
	By navbarLoc = By.xpath("//*[@id='topNavWrapper']/child::div[2]/ul/li");
	By accountContainer = By.xpath("//*[@id='MyAccountContainer']/button");
	By accountContainerMenu = By.xpath("//*[@id='MyAccountContainer']/button/div");
	By shoppingBag = By.id("shopping-bag");
	
    public String getPageTitle() {
	    return driver.getTitle();
    }
	
	public List<WebElement> getLogos() {
		List<WebElement> logos =  driver.findElements(logosLoc);
		return logos;
	}
	
	public List<WebElement> getNavBarMenu() {
		List<WebElement> navbar =  driver.findElements(navbarLoc);
		return navbar;
	}
	
	public List<String> getSignInMenu() {
		List<WebElement> acctContainer = driver.findElements(accountContainerMenu);
		List<String> acctContainerTexts = new ArrayList<String>();
		for (int i=0;i<acctContainer.size();i++) {
			if (acctContainer.get(i).getText().equals("") || acctContainer.get(i).getText() == null) {
				acctContainer.remove(i);
			} 
		}	
		for (int i=0;i<acctContainer.size();i++) {
			if (acctContainer.get(i).getText().equals("")) {
				acctContainerTexts.add(acctContainer.get(i).getAttribute("aria-label"));
			} else {
				acctContainerTexts.add(acctContainer.get(i).getText());
			}
		}		
		return acctContainerTexts;
	}
	
	public LoginPage clickSignIn() {
		driver.findElement(accountContainer).click();
		driver.findElement(By.xpath("//*[@class='my-account__dropdown-sign-in-button']")).click();
		return new LoginPage();
	}
	
	public WebElement getShopBag() {
		WebElement shopBag = driver.findElement(shoppingBag);
		return shopBag;
	}

}
