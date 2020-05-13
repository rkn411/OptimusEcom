package com.optimus.framework.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.optimus.framework.base.Base;
import com.optimus.framework.driverfactory.DriverManager;

public class DriverWait {
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isElementDisplayed(WebElement element) {
		for (int itrCount = 1; itrCount <= 60; itrCount++) {
			try {
				return element.isDisplayed();
			} catch (NoSuchElementException noSuch) {

			} catch (StaleElementReferenceException stale) {

			}
			customSleep(1);
		}
		return false;
	}

	/**
	 * 
	 * @param locator
	 * @return
	 */

	public static boolean isElementDisplayed(By locator) {
		for (int itrCount = 1; itrCount <= 60; itrCount++) {
			try {
				return DriverManager.getDriver().findElement(locator).isDisplayed();
			} catch (NoSuchElementException noSuch) {

			} catch (StaleElementReferenceException stale) {

			}
			customSleep(1);
		}
		return false;
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isElementEnabled(WebElement element) {
		if (isElementDisplayed(element)) {
			int itrCount = 1;
			do {
				if (element.isEnabled()) {
					return true;
				}
				customSleep(1);
				itrCount++;
			} while (itrCount <= 30);
		}
		return false;
	}

	/**
	 * Make thread to sleep for specified time
	 * 
	 * @param waitTime
	 *            - wait time in long
	 */
	public static void customSleep(long waitTime) {
		try {
			Thread.sleep(1000 * waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
	}

}
