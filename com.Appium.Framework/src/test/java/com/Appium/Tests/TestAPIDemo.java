package com.Appium.Tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.Appium.Base.Base;
import com.Appium.Base.TestData;

import PageObjects.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TestAPIDemo extends Base {
	@BeforeTest
	public void KillAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	@Test(dataProvider = "InputData",dataProviderClass = TestData.class)
	public void apiDemo(String input) throws InterruptedException, IOException {
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("emulator","apidemo");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		HomePage hm = new HomePage(driver);
		hm.enterWifiPassword(input);
		service.stop();
	}

	
	
	
}
