package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.optimus.framework.driverfactory.DriverManager;
import com.optimus.framework.utilities.DriverWait;
public class CartPage extends ShopifyHeader {
	public CartPage() {
		logger = Logger.getLogger(CartPage.class);
	}
	@FindBy(xpath = "//div[@class='cart-header']//a[contains(text(),'Continue shopping')]")
	private WebElement continueShoppingBtn;
	
	private String getRow(String productName,String size) {
		return "//table//tr[.//a[contains(text(),'"+productName+"')]/../following-sibling::ul/li[contains(text(),'"+size+"')]]";
	}
	
	private By productPrice(String productName,String size) {
		return By.xpath(getRow(productName,size)+"//td//div[@data-cart-item-regular-price-group]//dd[text()]");
	}
	
	private By quantity(String productName,String size) {
		return By.xpath(getRow(productName,size)+"//td[contains(@class,'quantity')]//input");
	}
	
	private By totalPrice(String productName,String size) {
		return By.xpath(getRow(productName,size)+"//td[contains(@class,'final-price')]//span");
	}
	
	@Override
	public boolean isPageDisplayed() {
		logger.info("Waiting for continue shopping cart button");
		return DriverWait.isElementDisplayed(continueShoppingBtn);
	}
	
	/**
	 * Get the product price
	 * @param productName - takes name of the product
	 * @param size - takes size of the product
	 * @return - return product price of product in float format
	 */
	public float getProductPrice(String productName,String size) {
		String productPrice=DriverManager.getDriver().findElement(productPrice(productName, size)).getText().split(" ")[1];
		return Float.parseFloat(productPrice.replace(",", ""));
	}
	
	/**
	 * Enter quantity in quantity field
	 * @param productName - takes name of the product
	 * @param size - takes size of the product
	 * @param quantity - quantity to enter
	 */
	public void enterQuantity(String productName,String size,String quantity) {
		DriverManager.getDriver().findElement(quantity(productName, size)).clear();
		DriverManager.getDriver().findElement(quantity(productName, size)).sendKeys(quantity+Keys.TAB);
	}
	
	/**
	 * Get the total price of product
	 * @param productName - takes name of the product
	 * @param size - takes size of the product
	 * @return - return total price of product in float format
	 */
	public float getProductTotalPrice(String productName,String size) {
		String totalPrice=DriverManager.getDriver().findElement(totalPrice(productName, size)).getText().split(" ")[1];
		return Float.parseFloat(totalPrice.replace(",", ""));
	}
}
