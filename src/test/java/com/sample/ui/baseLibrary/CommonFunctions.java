package com.sample.ui.baseLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class CommonFunctions {
	
	public static String jsonFile;
	public static Properties config;
	static final Logger log=LoggerFactory.getLogger("CommonFunctions.class");
	public DriverFunctions functions=new DriverFunctions();
	
	@BeforeSuite
	public void ReadCommandLineParamters()
	{
		String jsonFilePath=System.getProperty("jsonFilePath" , "abc.json");
		
		if(jsonFilePath.equalsIgnoreCase("abc.json"))
		{
			jsonFile="src//test//resources//jsonFiles//abc.json";
			System.out.println("file path is: " +jsonFile);
			log.info("file path of jason file is : " +jsonFile);
		}
		
		functions.initBrowser();
		functions.getUrl();
		
		
	}
	
	public static Properties LoadConfigProperty() throws IOException {
		config=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");
		config.load(fis);
		return config;
		
	}

}
