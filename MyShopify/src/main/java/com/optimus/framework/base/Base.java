package com.optimus.framework.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.optimus.framework.driverfactory.DriverManager;

public abstract class Base {
	public static WebDriver driver;
	public Logger logger;

	public Base() {
		driver = DriverManager.getDriver();
		PageFactory.initElements(driver, this);
	}

	public abstract boolean isPageDisplayed();
}