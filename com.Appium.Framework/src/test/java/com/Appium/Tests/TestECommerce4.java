package com.Appium.Tests;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Appium.Base.Base;

import PageObjects.CheckOutPage;
import PageObjects.FormPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;

public class TestECommerce4 extends Base {
	
	@BeforeTest
	public void KillAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(3000);
	}
	@Test
	public void testValidation() throws InterruptedException, IOException {
		service = startServer();
		AndroidDriver<AndroidElement> driver = Capabilities("emulator","GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FormPage fp = new FormPage(driver);
		fp.fillUpFormPage("hello", driver, "Argentina");
		int count = driver.findElementsByXPath("//*[@text='ADD TO CART']").size();
		for (int i = 0; i < count; i++) {
			driver.findElementByXPath("//*[@text='ADD TO CART']").click();
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(3000);
		CheckOutPage cp = new CheckOutPage(driver);
		cp.ValidateProductsPrice(driver);
//tap options
		WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
		TouchAction t = new TouchAction(driver);
		t.tap(TapOptions.tapOptions().withElement(element(checkbox))).perform();

		WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
		t.longPress(LongPressOptions.longPressOptions().withElement(element(tc)).withDuration(ofSeconds(2))).release()
				.perform();
		driver.findElement(By.xpath("//*[@text='CLOSE']")).click();
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		service.stop();
	}

}
