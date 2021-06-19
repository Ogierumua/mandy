package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckOutPage {
	public CheckOutPage(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement totalAmount;
	
	public static double getAmount(String value) {
		value = value.substring(1);
		double amtvalue = Double.parseDouble(value);
		return amtvalue;
	}
	
	public void ValidateProductsPrice(AndroidDriver<AndroidElement> driver) {
		int count1 = productList.size();
		double sumofProducts = 0;
		for (int i = 0; i < count1; i++) {
			String amount = productList.get(i)
					.getText();
			double amount2 = getAmount(amount);
			sumofProducts = sumofProducts + amount2;
		}
		System.out.println("The sum of products: " + sumofProducts);
		String totAmt = totalAmount.getText();
		double totAmtValue = getAmount(totAmt);
		System.out.println("The total value of products: " + totAmtValue);
		Assert.assertEquals(sumofProducts, totAmtValue);
	}
}
