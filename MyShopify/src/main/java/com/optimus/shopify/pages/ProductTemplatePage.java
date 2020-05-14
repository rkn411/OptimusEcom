package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.optimus.framework.utilities.DriverWait;
import com.optimus.framework.utilities.DriverWait.WaitTime;
import com.optimus.framework.utilities.UtilityMethods;
import com.optimus.framework.utilities.UtilityMethods.SelectionType;
import static com.optimus.framework.utilities.UtilityMethods.*;

public class ProductTemplatePage extends ShopifyHeader {

	public ProductTemplatePage() {
		logger = Logger.getLogger(ProductTemplatePage.class);
	}

	@FindBy(xpath = "//button[text()='Buy it now']")
	private WebElement buyItNowButton;

	@FindBy(className = "product-single__title")
	private WebElement productName;

	@FindBy(xpath = "//span[contains(@class,'price-item--regular')]")
	private WebElement productRegularPrice;

	@FindBy(xpath = "//label[contains(text(),'Color')]/following-sibling::select")
	private WebElement colorDropDown;

	@FindBy(xpath = "//label[contains(text(),'Size')]/following-sibling::select")
	private WebElement sizeDropDown;

	@FindBy(name = "add")
	private WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='cart-popup']//a")
	private WebElement viewCartButton;

	@Override
	public boolean isPageDisplayed() {
		logger.info("Waiting for Buy IT Now element to make sure product template page is displayed");
		return DriverWait.isElementDisplayed(buyItNowButton,WaitTime.ONEMINUTE);
	}

	/**
	 * Get the name of the product
	 * 
	 * @return - product name as string
	 */
	public String getProductName() {
		return productName.getText();
	}

	/**
	 * Get the product regular price
	 * 
	 * @return
	 */
	public String getProductRegularPrice() {
		return productRegularPrice.getText();
	}

	/**
	 * This method select option in size drop down
	 * 
	 * @param sizeOption
	 */
	public void selectProductSize(String sizeOption) {
		UtilityMethods.selectOptionFromDropDown(sizeDropDown, sizeOption, SelectionType.TEXT);
	}

	/**
	 * This method returns option selected in size drop down
	 * 
	 * @return
	 */
	public String getSelectedSize() {
		return UtilityMethods.getDropDownSelectedOption(sizeDropDown);
	}

	/**
	 * Add item to the cart and validate whether item is added to the cart or not
	 */
	public void addItemToCart() {
		int countBeforeAddingItem = getCartItemCount();
		clickOnElement(addToCartButton,"add to cart button");
		DriverWait.customSleep(2);
		int countAfterAddingItem = getCartItemCount();
		Assert.assertTrue(countAfterAddingItem > countBeforeAddingItem, "Unable to add item to cart");
		logger.info("Item is added to the card successfully");
	}

	/**
	 * Clicks on View Cart button in pop up and takes to the cart page
	 */
	public CartPage clickViewCart() {
		clickOnElement(viewCartButton, "Cart button");
		Assert.assertTrue(new CartPage().isPageDisplayed(), "Cart Item page is not displayed");
		logger.info("Cart Item page is displayed");
		return new CartPage();
	}
}
