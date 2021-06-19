package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.Appium.utilities.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage {
	//AndroidDriver<AndroidElement> driver1;
	//public static AndroidDriver<AndroidElement> driver;
	public FormPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	//com.androidsample.generalstore:id/nameField
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	public WebElement nameField;
	
	//driver.findElement(By.xpath("//*[@text='Female']"))
	@AndroidFindBy(xpath="//*[@text='Female']")
	public WebElement femaleOption;
	
	//driver.findElement(By.id("android:id/text1")).click();
	@AndroidFindBy(id="android:id/text1")
	public WebElement countryDropDwn;
	
	@AndroidFindBy(xpath="//*[@text='Argentina']")
	public WebElement selCountry;
	
	//By.id("com.androidsample.generalstore:id/btnLetsShop"
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement btnLetsShop;
	
	public void fillUpFormPage(String name,AndroidDriver<AndroidElement> driver,String countryName) throws InterruptedException {
		
		nameField.sendKeys(name);
		femaleOption.click();
		countryDropDwn.click();
		Thread.sleep(3000);
		Utilities.scrolltoText(driver, countryName);
		//driver.findElement(By.xpath("//*[@text='Argentina']")).click();
		selCountry.click();
		btnLetsShop.click();
	}
}
