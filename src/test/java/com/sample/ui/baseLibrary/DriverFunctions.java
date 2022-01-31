package com.sample.ui.baseLibrary;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
	String execution;

	public void initBrowser() {
		if (driver == null) {
			//Properties config;
			try {
				config = setEnv();
				browser=System.getProperty("browser", "chrome");
				execution=System.getProperty("execution", "local");
				initializeBrowser(browser,execution);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void initializeBrowser(String browser,String execution) throws MalformedURLException {
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

			if(execution.equalsIgnoreCase("hub"))
			{
			driver = new RemoteWebDriver(new URL("http:127.0.0.1:4444/wd/hub"), options);
			}
			else
			{
				driver = new ChromeDriver(options);
			}
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

			if(execution.equalsIgnoreCase("hub"))
			{
			driver = new RemoteWebDriver(new URL("http:127.0.0.1:4444/wd/hub"), firefoxOptions);
			}
			else
			{
				driver = new FirefoxDriver(firefoxOptions);
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			break;

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
	
	public String getUrl()
	{
		host=System.getProperty("host", "thetestingworldapi.com/api");
		String basePath=System.getProperty("basepath", "");
		RestAssured.reset();
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
		browser_url=RestAssured.baseURI="https://" + host + basePath;
		System.out.println("browser url is now: "+browser_url);
		return browser_url;
	}

}
