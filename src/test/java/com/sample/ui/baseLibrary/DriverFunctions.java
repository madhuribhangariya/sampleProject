package com.sample.ui.baseLibrary;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;

public class DriverFunctions {

	public static WebDriver driver;
	Properties config = null;
	static WebDriverWait wait;
	String browser_url = null;
	String browser;
	static String host = null;
	String port = null;
	int getWaitTime = 60;
	boolean result = true;

	public void initBrowser() {
		if (driver == null) {
			Properties config;
			try {
				config = setEnv();
				browser=System.getProperty("browser", "chrome");
				initializeBrowser(browser);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void initializeBrowser(String browser) throws MalformedURLException {
		browser = browser.toLowerCase().trim();
		String osName = System.getProperty("os.name");
		System.out.println("os name is: " +osName);

		switch (browser) {

		case "chrome":

			if (osName.toLowerCase().contains("windows".toLowerCase())||osName.toLowerCase().contains("linux")) {
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

			} else {
				System.setProperty("webdriver.chrome.driver", "user/bin/chromedriver");

			}
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-gpu");
			options.addArguments("window-size=1980,1080");
			options.addArguments("--no-sandbox");
			options.addArguments("disable-gpu");
			options.setAcceptInsecureCerts(true);

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default.content_settings.popup", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			options.setExperimentalOption("prefs", chromePrefs);

			driver = new RemoteWebDriver(new URL("http://192.168.1.12:4444/wd/hub"), options);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			break;

		case "firefox":
			if (osName.toLowerCase().contains("windows".toLowerCase())||osName.toLowerCase().contains("linux")) {
				System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");

			} else {
				System.setProperty("webdriver.gecko.driver", "user/bin/geckodriver");

			}

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			firefoxOptions.addArguments("--no-sandbox");

			driver = new RemoteWebDriver(new URL("http://192.168.1.12:4444/wd/hub"), firefoxOptions);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			break;

		case "ie":
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "ie");
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability("requirewindowFocus", true);
			System.setProperty("webDriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
			driver = new RemoteWebDriver(new URL("http://192.168.1.12:4444/wd/hub"), capabilities);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			break;

		default:
			driver = new RemoteWebDriver(new URL("http://192.168.1.12:4444/wd/hub"),DesiredCapabilities.chrome());
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		}

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String os = cap.getPlatform().toString();
		System.out.println("OS: " + os);
		String browserName = cap.getBrowserName().toString();
		System.out.println("started test on : " + browserName);
		String version = cap.getVersion().toString();
		System.out.println("Browser Version : " + version);
  
	}

	public Properties setEnv() throws IOException {
		config=CommonFunctions.LoadConfigProperty();
		return config;
	}
	
	public void getUrl()
	{
		host=System.getProperty("host", "google.com");
		String basePath=System.getProperty("basepath", "");
		RestAssured.reset();
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
		browser_url=RestAssured.baseURI="https://" + host + basePath;
		driver.get(browser_url);
		System.out.println("browser url is now: "+browser_url);
	}

}
