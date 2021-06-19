package com.Appium.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class Base {
	public static AppiumDriverLocalService service;
public static AndroidDriver<AndroidElement> driver;
	public static AppiumDriverLocalService startServer() {
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();

		}
		return service;
	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void startEmulator() throws IOException, InterruptedException {

		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\startEmulator.bat");
		Thread.sleep(20000);
	}

	public static AndroidDriver<AndroidElement> Capabilities(String device,String appName)
			throws IOException, InterruptedException {
		System.getProperty("user.dir");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\Appium\\Base\\global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		DesiredCapabilities cap = new DesiredCapabilities();
		//
		/**
		 * for real device
		 */
		//String device = System.getProperty("deviceName");
		if (device.equals("emulator")) {
			startEmulator();
			Thread.sleep(6000);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, prop.get("device"));
			//cap.setCapability(MobileCapabilityType.DEVICE_NAME,device);
		} else if (device.equals("real")) {
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		}
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		return driver;

	}
	
	public static void getScreenshot(String defectName) throws IOException {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") +"\\src\\Screenshots\\"+defectName+".png"));
	}
}
