package com.sample.ui.baseLibrary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class CommonFunctions {
	
	public static String jsonFile;
	static final Logger log=LoggerFactory.getLogger("CommonFunctions.class");
	
	@BeforeSuite
	public void ReadCommandLineParamters()
	{
		String jsonFilePath=System.getProperty("jsonFilePath" , "abc.json");
		
		if(jsonFilePath.equalsIgnoreCase("abc.json"))
		{
			jsonFile="src//test//resources//jsonFiles//abc.json";
			System.out.println("file path is: " +jsonFile);
			log.info("file path is: " +jsonFile);
		}
		
		
	}

}
