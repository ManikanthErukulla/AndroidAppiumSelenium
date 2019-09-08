package com.qa.appyParking.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.appyParking.base.BasePage;


import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TermsAndConditionsPage extends BasePage 
{

	
	@AndroidFindBy(xpath = "//*[@class = 'android.widget.TextView'][1]")
	AndroidElement tcContent1;
	
	@AndroidFindBy(xpath = "//*[@class = 'android.widget.TextView'][2]")
	AndroidElement tcContent2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Accept Terms & Conditions\")")
	AndroidElement tcContent3;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Receive Appyparking news. Unsubscribe at any time\")")
	AndroidElement tcContent4;
	
	@AndroidFindBy(xpath = "//*[@class = 'android.widget.CheckBox'][1]")
	AndroidElement tcBox1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_terms_newsletter_checkbox\")")
	AndroidElement tcBox2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_terms_complete_button\")")
	AndroidElement register;
	
	
	public TermsAndConditionsPage() throws IOException 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		// TODO Auto-generated constructor stub
	}
	
	public String validateTnCHeader()
	{
		return tcContent1.getText();
	}
	
	public String validateTnCBody()
	{
		return tcContent2.getText();
	}

	public String acceptTcBody()
	{
		return tcContent3.getText();
		
	}
	
	public String newsLettersBody()
	{
		return tcContent4.getText();
	}
	
	public String acceptTcTick()
	{
		if (tcBox1.getAttribute("checked").equalsIgnoreCase("false"))
		{
		 tcBox1.click();
		}
		 return tcBox1.getAttribute("checked");
		 
	}
	
	public String newsLettersTick()
	{
		 tcBox2.click();
		 return tcBox2.getAttribute("checked");
		 
	}
	
	
	public HassleFreeParkingPage registerUser() throws IOException
	{
		acceptTcTick();
		register.click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		return new HassleFreeParkingPage();
	}
}
