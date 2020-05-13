package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.optimus.framework.base.Base;
import com.optimus.framework.driverfactory.DriverManager;
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
	/**
	 * Search for the product and select product from product list
	 * 
	 * @param productName
	 */
	public ProductTemplatePage searchProduct(String productName) {
		logger.info("Searching for product " + productName);
		searchIcon.click();
		searchTxtField.sendKeys(productName);
		Assert.assertTrue(DriverWait.isElementDisplayed(getSearchItem(productName)),
				"Item does not exist with product name " + productName);
		DriverManager.getDriver().findElement(getSearchItem(productName)).click();
		logger.info(productName + " product is selected from search result");
		Assert.assertTrue(new ProductTemplatePage().isPageDisplayed(), "Product Template page is not displayed");
		logger.info("Product template page is displayed");
		return new ProductTemplatePage();
	}

	/**
	 * Click on cart icon in header section
	 * 
	 * @return -returns CartPage instance
	 */
	public CartPage cartIcon() {
		cartIcon.click();
		logger.info("Clicked on cart icon in shopping header");
		Assert.assertTrue(new CartPage().isPageDisplayed(), "Cart page is not displayed");
		logger.info("Cart page is displayed");
		return new CartPage();
	}
	
	/**
	 * This method get cart items count
	 * @return
	 */
	public int getCartItemCount() {
		String cartCount=cartItemsCount.getText();
		if(cartCount.equals("")) {
			return 0;
		}
		return Integer.parseInt(cartCount);
	}
}
