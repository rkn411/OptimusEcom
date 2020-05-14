package com.optimus.framework.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.optimus.framework.driverfactory.DriverManager;

public class DriverWait {
	/**
	 * Holds time constants
	 *
	 */
	public enum WaitTime{
		ONESECOND(1),
		TWOSECONDS(2),
		FIVESECONDS(5),
		TENSECONDS(10),
		TWENTYSECONDS(20),
		FIFTYSECONDS(50),
		ONEMINUTE(60);
		
		private int time;
		private WaitTime(int time) {
			this.time=time;
		}
		public int getTime() {
			return this.time;
		}
	}
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isElementDisplayed(WebElement element,WaitTime waitTime) {
		long before=System.currentTimeMillis();
		long after=0;
		do {
			try {
				return element.isDisplayed();
			} catch (NoSuchElementException noSuch) {
				after=System.currentTimeMillis();
			}
		}while(((after-before)/1000)<=waitTime.getTime());
		return false;
	}
	/**
	 * 
	 * @param locator
	 * @return
	 */

	/**
	 * 
	 * @param locator
	 *            - locator to display in UAT
	 * @return boolean
	 */
	public static boolean isElementDisplayed(By locator,WaitTime waitTime) {
		long before=System.currentTimeMillis();
		long after=0;
		do {
			try {
				return DriverManager.getDriver().findElement(locator).isDisplayed();
			} catch (NoSuchElementException noSuch) {
				after=System.currentTimeMillis();
			}
		}while(((after-before)/1000)<=waitTime.getTime());
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