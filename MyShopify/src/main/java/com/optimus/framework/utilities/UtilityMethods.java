package com.optimus.framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.google.common.io.Files;
import com.optimus.framework.driverfactory.DriverManager;

public class UtilityMethods {

	/**
	 * method to click on element
	 * 
	 * @param WebElement
	 *            -element
	 * @param fieldName
	 */
	public static void clickOnElement(WebElement element, String fieldName) {
		try {
			element.click();
		} catch (NoSuchElementException e) {
			Logger.getLogger(UtilityMethods.class).error(e);
			Assert.assertTrue(false, fieldName + ":Element not found");
		}
	}

	/**
	 * method to click on elment
	 * 
	 * @param By-
	 *            element
	 * @param fieldName
	 */
	public static void clickOnElement(By element, String fieldName) {
		try {
			DriverManager.getDriver().findElement(element).click();
		} catch (NoSuchElementException e) {
			Logger.getLogger(UtilityMethods.class).error(e);
			Assert.assertTrue(false, fieldName + ":Element not found");
		}
	}

	/**
	 * method to enter text in the field
	 * 
	 * @param element
	 * @param textToEnter
	 * @param fieldName
	 */
	public static void inputText(WebElement element, String textToEnter, String fieldName) {
		try {
			element.sendKeys(textToEnter);
		} catch (NoSuchElementException e) {
			Logger.getLogger(UtilityMethods.class).error(e);
			Assert.assertTrue(false, fieldName + ":unable to enter text");
		}
	}

	/**
	 * method to enter text in the field
	 * 
	 * @param element
	 * @param textToEnter
	 * @param fieldName
	 */
	public static void inputText(By element, String textToEnter, String fieldName) {
		try {
			DriverManager.getDriver().findElement(element).sendKeys(textToEnter);
		} catch (NoSuchElementException e) {
			Logger.getLogger(UtilityMethods.class).error(e);
			Assert.assertTrue(false, fieldName + ":unable to enter text");
		}
	}

	/**
	 * Add Screenshot to Report file
	 * 
	 * @param driver
	 * @param screenshotName
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public void takeScreenshot(String screenshotName) throws Exception {
		String html = "";
		try {
			File sourcePath = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
			File destinationPath = new File(
					DriverManager.screenShotFilePath + File.separator + screenshotName + ".png");
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
		}
	}

	/**
	 * Method for Convert the screenshot to Base64 format
	 * 
	 * @param file
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String covertScreenshotToBase64(File file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			byte byteArray[] = new byte[(int) file.length()];
			fis.read(byteArray);
			String imageString = Base64.encodeBase64String(byteArray);
			return doImageClickAnimation(imageString, "Test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * Method for Image Click Animation
	 * 
	 * @param img
	 * @param screenName
	 * @return
	 */
	public static String doImageClickAnimation(String img, String screenName) {
		String image = "<img onclick='clickImage(this)' src=\"data:image/png;base64, " + img + "\" alt=\"" + screenName
				+ "\" width=\"710\" height=\"450\"/>";
		return image;
	}

	/**
	 * Method for Add Screenshot
	 * 
	 * @param file
	 * @return
	 */
	public String addScreenshot(File file) {
		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedBase64;
	}

	public enum SelectionType {
		INDEX, VALUE, TEXT;
	}

	/**
	 * This utility method is used to select options from drop down
	 * 
	 * @param dropDown
	 *            - Takes drop down web element
	 * @param option
	 *            - Option which has to be selected from drop down
	 * @param type
	 *            - Selection type (Index or Value or Text)
	 */
	public static Select selectOptionFromDropDown(WebElement dropDown, String option, SelectionType type) {
		Select s = new Select(dropDown);
		if (type.toString().equalsIgnoreCase("Index")) {
			s.selectByIndex(Integer.parseInt(option));
		} else if (type.toString().equalsIgnoreCase("Value")) {
			s.selectByValue(option);
		} else {
			s.selectByVisibleText(option);
		}
		return s;
	}

	/**
	 * This method is used get selected option from drop down
	 * 
	 * @param dropDown
	 * @return
	 */
	public static String getDropDownSelectedOption(WebElement dropDown) {
		Select s = new Select(dropDown);
		return s.getFirstSelectedOption().getText();
	}

}
