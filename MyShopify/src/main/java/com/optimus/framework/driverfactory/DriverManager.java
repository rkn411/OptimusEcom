package com.optimus.framework.driverfactory;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.optimus.framework.support.io.PropertiesFile;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Manager browser launch and kill activities Load property file and launch
 * browser based on programmer choice given properties file Kills browser for
 * completion of every class execution
 *
 */
public class DriverManager {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static String baseDir = System.getProperty("user.dir");
	public static PropertiesFile propFile;

	@BeforeTest
	public void beforeTest() {
		String path = baseDir + "/src/main/java/com/optimus/shopify/";
		propFile = new PropertiesFile(path + "/config/config.properties");
	}

	/**
	 * method to  launch browser and navigate URL based on browser name given in xml
	 */
	@Parameters("browser")
	@BeforeClass
	public void launchBrowser(String browser) {
		driver.set(getDriverFor(browser));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(propFile.getProperty("URL"));
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
/**
 * method to set driver 
 * @param brName
 * @return browser driver
 */
	public static WebDriver getDriverFor(String brName) {
		switch (brName.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-gpu");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--no-sandbox");
			options.addArguments("ignore-certificate-errors");
			options.setAcceptInsecureCerts(true);
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.addArguments("--disable-notifications");
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(options);
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		// add more browsers list
			
		default:
			System.out.println("No Valid browser found with the name");
			return null;
		}
	}

	/**
	 * This function closes all browser drivers.
	 */
	@AfterClass
	public static void closeDriver() {
		if (getDriver() != null) {
			try {
				getDriver().quit();
			} catch (NoSuchMethodError nsme) {
				nsme.printStackTrace();
			} catch (NoSuchSessionException nsse) {
				nsse.printStackTrace();
			} catch (SessionNotCreatedException snce) {
				snce.printStackTrace();
			}
		}
	}

	/**
	 *  method to get the driver instance
	 *
	 */
	public static WebDriver getDriver() {
		return driver.get();
	}
}
