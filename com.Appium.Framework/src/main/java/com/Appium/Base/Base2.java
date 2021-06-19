package com.Appium.Base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base2 {
	public static AppiumDriverLocalService service;
	public static AppiumDriverLocalService startServer() {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		return service;
	}
	public static AndroidDriver<AndroidElement> Capabilities(String device,String appName) throws IOException {
		System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\Appium\\Base\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		
			File appDir = new File("src");
			File app = new File(appDir, (String) prop.get(appName));
			DesiredCapabilities cap = new DesiredCapabilities();
			//
			/**
			 * for real device
			 */
			if(device.equals("emulator")) {
				cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.get(device));
			}
			else if(device.equals("real")){
				cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
			}
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 14);
			AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			return driver;
			
	}
}
