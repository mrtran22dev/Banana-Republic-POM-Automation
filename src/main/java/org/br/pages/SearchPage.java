package org.br.pages;

import java.util.List;

import org.br.pageUtil.PopUpWindowCheck;
import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends TestBase {

	public SearchPage() {
		System.out.print("SearchPage object created: ");
		PopUpWindowCheck popUpWindowCheck = new PopUpWindowCheck();
		popUpWindowCheck.checkEmailPopupContent();
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 2);
	By searchInput = By.xpath("//*[@id='search-form']/input");
	By searchButton = By.xpath("//*[@id='search-form']/button");
	By searchResult = By.xpath("//*[@class='product-card-grid__root grid ism-root']/div");
	By firstSearchResult = By.xpath("//*[@class='product-card-grid__root grid ism-root']/div/div/div/a/div/img");
	By popupLoc = By.xpath("//*[@id='EmailPopUpContent']");
	By popUpCloseLoc = By.xpath("//*[@class='universal-modal__header']/button");
	By size = By.xpath("//*[@class='swatches swatches_dimension']/div/label");
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public List<WebElement> searchQuery(String query) {
		driver.findElement(searchInput).sendKeys(query);
		driver.findElement(searchButton).click();
		// need to wait for page to load first to get height
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Long height = (Long) js.executeScript("return document.body.scrollHeight");
		System.out.println("page height: " + height);
		
		// need to scroll section-by-section and wait to see images (img). otherwise will only get size count, but error getting 'img' tag
		for(int start=0;start<height;start=start+500) {
			js.executeScript("window.scrollBy(0, 500)");
			try {
				Thread.sleep(500);
				// Object currentHeight = js.executeScript("return $(window).scrollTop()");
				// System.out.println("current: " + currentHeight);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		List<WebElement> searchResults = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResult));
		return searchResults;
	}

	public ProductPage selectItem() {
		driver.findElement(firstSearchResult).click();
		return new ProductPage();
	}	
	
}
