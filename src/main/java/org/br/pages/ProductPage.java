package org.br.pages;

import java.util.List;

import org.br.pageUtil.PopUpWindowCheck;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends TestBase {
	
	public ProductPage() {
		System.out.print("ProductPage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
		popUpWindowCheck.checkMktSticker();
	}

	WebDriverWait wait = new WebDriverWait(driver, 2);
	String[] productArray = new String[3];
	By productTitle_ = By.className("product-title__text");
	By checkOutButton = By.id("checkoutButton");
	By sizes = By.xpath("//*[@class='swatches swatches_dimension']/div/label");
	By modalWindow = By.id("modalWindow");
	By description = By.xpath("//*[@class='g-lg-5-6 g-1-1 border-box right info-content']/child::div[1]/a/h4");
	By price = By.xpath("//*[@class='g-lg-5-6 g-1-1 border-box right info-content']/child::div[2]/h4");
	By size = By.xpath("//*[@class='g-lg-5-6 g-1-1 border-box right info-content']/ul/child::li[2]/child::span[2]");
	By addToBagError = By.xpath("//*[@class='add-to-bag__error-messaging']");
	By addToBagButton = By.xpath("//*[@id='addToBag']/button");
	
	public String[] getTitles() {
		String productTitle = driver.findElement(productTitle_).getText();
		String[] titles = {driver.getTitle(), productTitle};
		return titles;
	}
	
	public String[] getProductDetail() {
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
		// CHECK: System.out.println("product detail modal window: " + e.isDisplayed());
		this.productArray[0] = driver.findElement(description).getText();
		this.productArray[1] = driver.findElement(price).getText();
		this.productArray[2] = driver.findElement(size).getText();
		return productArray;
	}
	
	public void itemAddToBag() {
		List<WebElement> sizeList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(sizes));
		
		for (WebElement e: sizeList) {
			e.click();
			// CHECK: System.out.println(driver.findElement(addToBagButton).isEnabled());
			wait.until(ExpectedConditions.visibilityOfElementLocated(addToBagButton));
			if (driver.findElement(addToBagButton).isEnabled()) {
				try {
					driver.findElement(addToBagButton).click();
					driver.findElement(addToBagError).isDisplayed();
					// do nothing, goes to next size to check available
				} catch (NoSuchElementException ex) {
					System.out.println("add to bag error message not shown - OK!");
					// CHECK: ex.printStackTrace();
					break;
				}
			}
		}
	}
	
	public ShopBagPage clickCheckout() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutButton));
		driver.findElement(checkOutButton).click();
		return new ShopBagPage(productArray[0], productArray[1], productArray[2]);
	}
}
