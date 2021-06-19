package com.Appium.Base;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name="InputData")
	public Object getDataForField() {
		// 2 sets of data , "hello" and "#@!@123"
		Object obj = new Object[][] {{"hello"}, {"#@!@123"}};
		return obj;
	}
	
}
