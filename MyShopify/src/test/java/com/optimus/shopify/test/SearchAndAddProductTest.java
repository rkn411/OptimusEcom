package com.optimus.shopify.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.optimus.framework.driverfactory.DriverManager;
import com.optimus.framework.utilities.DriverWait;
import com.optimus.shopify.pages.CartPage;
import com.optimus.shopify.pages.HomePage;
import com.optimus.shopify.pages.PasswordPage;
import com.optimus.shopify.pages.ProductTemplatePage;
import com.optimus.shopify.pages.ShopifyHeader;

public class SearchAndAddProductTest extends DriverManager {

	private ShopifyHeader shopifyHeader;

	@BeforeClass
	public void loginApplication() {
		PasswordPage pwdPage = new PasswordPage();
		shopifyHeader = pwdPage.loginApplication("idgad");
	}

	@Test()
	public void searchProduct() {
		try {
			test = report.createTest("Search and add product to cart :" + browser);
			String productName = "RoundNeck Shirt";
			String quantity = "2";
			String productSize = "XS";
			//Step 1.
			ProductTemplatePage templatePage = shopifyHeader.searchProduct(productName);
			test.log(Status.INFO, "Search product using name:"+ productName);
			Assert.assertEquals(templatePage.getProductName(), productName, "Expected product is not selected");
			//step 2.
			templatePage.addItemToCart();
			CartPage cartPage = templatePage.clickViewCart();
			float productPrice = cartPage.getProductPrice(productName, productSize);
			test.log(Status.INFO, "Product name and size is:"+ productName +" "+productSize);
			//step 3.
			cartPage.enterQuantity(productName, productSize, quantity);
			test.log(Status.INFO, "enter quantity:"+ productName +" "+productSize + " "+quantity);
			DriverWait.customSleep(2);
			//step 4.
			float productTotalPrice = cartPage.getProductTotalPrice(productName, productSize);
			test.log(Status.INFO, "getProductTotalPrice:"+ productTotalPrice);
			Assert.assertEquals(productTotalPrice, productPrice * Integer.parseInt(quantity),
					"Total price is not updating when quantity is changed");
		} catch (AssertionError e) {
			test.log(Status.FAIL, e);
		}
	}

	@Test()
	public void addFeaturedCollectionProduct() throws Exception {
		try {
			test = report.createTest("Add product to cart from featured collection :" + browser);
			String productName = "RoundNeck Shirt 14";
			String size = "S";
			//step 1
			HomePage homePage = shopifyHeader.clickHomeLink();
			ProductTemplatePage templatePage = homePage.selectProduct(productName);
			test.log(Status.INFO, "selected prdouct name"+productName);
			//step 2
			templatePage.selectProductSize(size);
			test.log(Status.INFO, "select size to: "+ size);
			//step 3
			templatePage.addItemToCart();
			//step 4
			CartPage cartPage = templatePage.clickViewCart();
			
			Assert.assertTrue(cartPage.isProductAvailableInCardItems(productName, size),
					productName + " product is not added to the cart");
			
		} catch (AssertionError e) {
			test.log(Status.FAIL, e);
		}
	}
}
