package com.sample.ui.util;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.sample.api.util.CommonConstants;

public class AssertionUtil {
	static final Logger log=LoggerFactory.getLogger("AssertionUtil.class");

	public static void assertVisible(WebElement element) {

	}

	public static void assertNotVisible(WebElement element) {

	}

	public static void assertTextPresent(WebElement element) {

	}

	public static void addVerificationLogger(String message, String status) {
		if(status.equalsIgnoreCase("pass"))
		{
			System.out.println(CommonConstants.ANSI_GREEN + message + CommonConstants.ANSI_GREEN);
			log.info(CommonConstants.ANSI_GREEN + message + CommonConstants.ANSI_GREEN);
			
		}
		else
		{
			Assert.fail(message);
		}

	}

}
