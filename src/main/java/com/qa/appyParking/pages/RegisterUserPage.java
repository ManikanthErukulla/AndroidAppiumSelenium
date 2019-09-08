package com.qa.appyParking.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.qa.appyParking.base.BasePage;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RegisterUserPage extends BasePage{
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/appy_logo\")")
	AndroidElement logo;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Full name\")")
	AndroidElement firstName;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Email\")")
	AndroidElement email;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
	AndroidElement password;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"REGISTER\")")
	AndroidElement register;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Already have an account? Sign in\")")
	AndroidElement alreadyRegisteredLink;
	
	public RegisterUserPage() throws IOException 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		// TODO Auto-generated constructor stub
	}

	public boolean verifyLogo()
	{
		return  logo.isDisplayed();
	}
	
	public TermsAndConditionsPage doRegisterCustomer(String full_Name, String user_Email, String user_Password) throws IOException
	{
		
	    firstName.sendKeys(full_Name);
		email.sendKeys(user_Email);
		password.sendKeys(user_Password);
		register.click();
		return new TermsAndConditionsPage();
	}
	
	public HassleFreeParkingPage alreadyRegisteredClick(String user_Email, String user_Password) throws IOException
	{
		alreadyRegisteredLink.click();
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/frg_sign_in_new_email_edittext")).sendKeys(user_Email);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/frg_sign_in_new_password_edittext")).sendKeys(user_Password);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/frg_sign_in_new_sign_in_button")).click();
		driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
		return new HassleFreeParkingPage();
	}
	
}
