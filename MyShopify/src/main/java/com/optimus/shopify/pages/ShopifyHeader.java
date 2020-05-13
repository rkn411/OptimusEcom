package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.optimus.framework.base.Base;
import com.optimus.framework.utilities.DriverWait;

public class ShopifyHeader extends Base {

	public ShopifyHeader() {
		logger = Logger.getLogger(ShopifyHeader.class);
	}

	@FindBy(xpath = "//a[contains(@class,'logo')]")
	private WebElement logo;

	@FindBy(xpath = "//div[contains(@class,'header')]//a[./span[text()='Home']]")
	private WebElement homeLink;

	@FindBy(xpath = "//div[contains(@class,'header')]//a[./span[text()='Catalog']]")
	private WebElement catalogLink;

	@FindBy(xpath = "//button[contains(@class,'search-toggle')]")
	private WebElement searchIcon;

	@FindBy(name = "q")
	private WebElement searchTxtField;

	@FindBy(xpath = "//a[./*[contains(@class,'icon-cart')]]")
	private WebElement cartIcon;

	private By getSearchItem(String productName) {
		return By.xpath("//div[@class='predictive-search']//a[.//*[text()='" + productName + "']]");
	}

	@FindBy(xpath = "//div[@id='CartCount']/span[@data-cart-count]")
	private WebElement cartItemsCount;

	@Override
	public boolean isPageDisplayed() {
		return DriverWait.isElementDisplayed(cartIcon);
	}
}
