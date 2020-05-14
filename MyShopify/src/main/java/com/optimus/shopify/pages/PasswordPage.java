package com.optimus.shopify.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.optimus.framework.base.Base;
import com.optimus.framework.utilities.DriverWait;
import com.optimus.framework.utilities.DriverWait.WaitTime;
import static com.optimus.framework.utilities.UtilityMethods.*;

public class PasswordPage extends Base {

	public PasswordPage() {
		logger = Logger.getLogger(PasswordPage.class);
	}

	@FindBy(xpath = "//a[contains(text(),'Enter using password')]")
	private WebElement passwordLink;

	@FindBy(id = "Password")
	private WebElement pwdTxtField;

	@FindBy(xpath = "//button[contains(text(),'Enter')]")
	private WebElement enterButton;

	@Override
	public boolean isPageDisplayed() {
		logger.info("Waiting for password link");
		return DriverWait.isElementDisplayed(passwordLink,WaitTime.ONEMINUTE);
	}

	/**
	 * This method is used for logging application using password
	 * 
	 * @param pwd
	 *            - valid password
	 */
	public ShopifyHeader loginApplication(String pwd) {
		logger.info("Loggining Application");
		clickOnElement(passwordLink, "Password");
		inputText(pwdTxtField,pwd,"password");
		clickOnElement(enterButton,"Enter button");
		Assert.assertTrue(new ShopifyHeader().isPageDisplayed(),
				"Unable to loggin application, Either password is wrong or home page is not displayed");
		logger.info("Logged in application successfully");
		return new ShopifyHeader();
	}
}
