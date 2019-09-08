package com.qa.appyParking.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BasePage 
{
		
	public static AndroidDriver<AndroidElement>  driver;
	public static Properties prop;
	
	public BasePage() throws IOException
	{
		prop = new Properties();
	//	File f  = new File("configuration");
	//	File fs = new File(f,"config.properties");
	//  FileInputStream fis = new FileInputStream(fs.getAbsolutePath());
		
		FileInputStream fis = new FileInputStream("/Users/manikantherukulla/eclipse-workspace/AppyParking/src/main/java/com/qa/appyParking/configuration/Config.properties");
		prop.load(fis);
	}	
	
	public AndroidDriver<AndroidElement> Initialise() throws Exception
	{		
		String deviceType = prop.getProperty("deviceType");
		String resetRequired = prop.getProperty("resetRequired");
		
		if (deviceType.equalsIgnoreCase("real"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", prop.getProperty("android_device_name"));
			cap.setCapability("udid","b8941cc4");
			cap.setCapability("platformName",prop.getProperty("deviceOS"));
			cap.setCapability("platformVersion",prop.getProperty("deviceVersion"));
		    cap.setCapability("appPackage",prop.getProperty("app_package_name"));
			cap.setCapability("appActivity",prop.getProperty("app_activity"));
			cap.setCapability("autoAcceptAlerts", true);
			cap.setCapability("autoDismissAlerts", true);
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, cap);
		}
		else if (deviceType.equalsIgnoreCase("simulator"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("deviceName", prop.getProperty("android_device_name"));
			cap.setCapability("platformName",prop.getProperty("deviceOS"));
			cap.setCapability("noReset", resetRequired);
			cap.setCapability("app", prop.getProperty("apk_file_path"));
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<AndroidElement>(url, cap);
		}	
		
		int TimeOut_In_Seconds_int = Integer.parseInt(prop.getProperty("TimeOut_In_Seconds"));
		Long TimeOut_In_Seconds_long = Long.valueOf(TimeOut_In_Seconds_int);
		driver.manage().timeouts().implicitlyWait(TimeOut_In_Seconds_long, TimeUnit.SECONDS);
		return driver;
	}	
	
	public String getScreenshot() {
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File src=scrShot.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
