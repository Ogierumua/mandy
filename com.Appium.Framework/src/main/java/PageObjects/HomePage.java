package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All th objects belonging to one page will be defined in java class
public class HomePage {
// 1. Is to call the driver object from testcase to Pageobject file

	// Concatenate driver
	public HomePage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='3. Preference dependencies']")
	public WebElement PreferenceDepedencies;

	@AndroidFindBy(id = "android:id/checkbox")
	public WebElement CheckBox;

	@AndroidFindBy(className = "android.widget.RelativeLayout")
	public List<WebElement> WifiSettings;

	@AndroidFindBy(className = "android.widget.EditText")
	public WebElement txt_wifipwd;

	@AndroidFindBy(className = "android.widget.Button")
	public List<WebElement> btn_Ok;

	public void enterWifiPassword(String pwd) {
		Preferences.click();
		PreferenceDepedencies.click();
		CheckBox.click();
		WifiSettings.get(1).click();
		txt_wifipwd.sendKeys(pwd);
		btn_Ok.get(1).click();
	}

}
