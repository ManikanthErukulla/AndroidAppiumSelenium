package com.qa.appyParking.pages;

import java.io.IOException;
import com.qa.appyParking.base.BasePage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HassleFreeParkingPage extends BasePage
{
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_payments_info_step1_title\")")
	AndroidElement header_text;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_payments_info_center_image\")")
	AndroidElement image;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_payments_info_step1_body\")")
	AndroidElement body_text;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_payments_info_step1_button_proceed\")")
	AndroidElement signMeUp_button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/frg_payments_info_step1_button_cancel\")")
	AndroidElement noThanks_button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SKIP\")")
	AndroidElement skip_link;
	
	public HassleFreeParkingPage() throws IOException 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		// TODO Auto-generated constructor stub
	}
	
	public String validateHassleFreeHeader()
	{
		return header_text.getText();
	}
	
	public boolean verifyHassleFreeImage()
	{
		return  image.isDisplayed();
	}
	
	public String validateHassleFreeBody()
	{
		return body_text.getText();
	}

	public LetsGoPage HassleFreeSignMeUp()
	{
		signMeUp_button.click();
		return new LetsGoPage();
	}
	
	public HomePage HassleFreeNoThanks() throws IOException
	{
		noThanks_button.click();
		return new HomePage();
	}
	
	public HomePage HassleFreeSkip() throws IOException
	{
		skip_link.click();
		return new HomePage();
	}
}
