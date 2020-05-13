package com.optimus.framework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.optimus.framework.driverfactory.DriverManager;

public abstract class Base {
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public Logger logger;

	public Base() {
		driver.set(DriverManager.getDriver());
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

	public abstract boolean isPageDisplayed();
}