package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import com.optimus.framework.utilities.DriverWait;
import com.optimus.framework.utilities.DriverWait.WaitTime;
import static com.optimus.framework.utilities.UtilityMethods.*;

public class HomePage extends ShopifyHeader {

	public HomePage() {
		logger = Logger.getLogger(HomePage.class);
	}

	@FindBy(id = "shopify-section-collection")
	private WebElement feauredCollectionSection;

	private By featuredCollectionItem(String productName) {
		return By.xpath("//div[@id='shopify-section-collection']//a[./span[text()='" + productName + "']]");
	}

	/**
	 * Select product from featured collection
	 * @param productName - Name of the product
	 * @return - returns ProductTemplatePage class instance
	 */
	public ProductTemplatePage selectProduct(String productName) {
		Assert.assertTrue(DriverWait.isElementDisplayed(featuredCollectionItem(productName), WaitTime.TENSECONDS),
				"Product " + productName + " name is not available in featured collection");
		clickOnElement(featuredCollectionItem(productName),"featuredCollectionItem");
		logger.info("Clicked on "+productName+" product in featured collection");
		Assert.assertTrue(new ProductTemplatePage().isPageDisplayed(), "Product Template Page is not displayed");
		logger.info("Product Template page is displayed");
		return new ProductTemplatePage();
	}
}
