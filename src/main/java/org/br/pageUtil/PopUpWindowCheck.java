package org.br.pageUtil;

import static org.testng.Assert.assertTrue;

import org.br.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUpWindowCheck extends TestBase {
	
	WebDriverWait wait = new WebDriverWait(driver, 2);
	By stickyContainer = By.className("sticky_container");
	By mktStickerClose = By.className("modal_popup_close");
	//By popupLoc = By.id("EmailPopUpContent");
	By popupLoc = By.xpath("//*[@id='EmailPopUpContent']");
	By popUpCloseLoc = By.xpath("//*[@class='universal-modal__header']/button");
	
	public void checkMktSticker() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(stickyContainer));
			driver.findElement(mktStickerClose).click();
			System.out.println("MktSticker pop-up found. closed MktSticker");
		} catch (Exception e) {
			System.out.println("MktSticker pop-up not found");
			// CHECK: e.printStackTrace();
		}
	}
	
	public void checkEmailPopupContent() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(popupLoc));
			driver.findElement(popUpCloseLoc).click();
			System.out.println("Email pop-up found. closed email pop-up");
		} catch (Exception e) {
			System.out.println("Email pop-up not found");
			// CHECK: e.printStackTrace();
		}

	}
}
