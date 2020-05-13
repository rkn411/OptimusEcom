package com.optimus.shopify.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.optimus.framework.driverfactory.DriverManager;
import com.optimus.framework.utilities.DriverWait;
import com.optimus.shopify.pages.CartPage;
import com.optimus.shopify.pages.PasswordPage;
import com.optimus.shopify.pages.ProductTemplatePage;
import com.optimus.shopify.pages.ShopifyHeader;

public class SearchAndAddProductTest extends DriverManager{
	
	private ShopifyHeader shopifyHeader;
	
	@BeforeClass
	public void loginApplication() {
		PasswordPage pwdPage=new PasswordPage();
		shopifyHeader=pwdPage.loginApplication("idgad");
	}
	
	@Test
	public void searchProduct() {
		String productName="RoundNeck Shirt";
		String quantity="2";
		String productSize="XS";
		
		ProductTemplatePage templatePage=shopifyHeader.searchProduct(productName);
		Assert.assertEquals(templatePage.getProductName(), productName, "Expected product is not selected");
		templatePage.addItemToCart();
		CartPage cartPage=templatePage.clickViewCart();
		float productPrice=cartPage.getProductPrice(productName, productSize);
		cartPage.enterQuantity(productName, productSize, quantity);
		DriverWait.customSleep(2);
		float productTotalPrice=cartPage.getProductTotalPrice(productName, productSize);
		Assert.assertEquals(productTotalPrice, productPrice*Integer.parseInt(quantity),"Total price is not updating when quantity is changed");	
	}

}
