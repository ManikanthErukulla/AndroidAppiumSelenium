package com.qa.appyParking.tests;

import java.io.IOException;


import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.appyParking.base.BasePage;
import com.qa.appyParking.pages.HassleFreeParkingPage;
import com.qa.appyParking.pages.RegisterUserPage;
import com.qa.appyParking.pages.TermsAndConditionsPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class RegisterUserPageTest 
{
	BasePage basePage;
	RegisterUserPage registerUserPage;
	TermsAndConditionsPage termsAndConditionsPage;
	HassleFreeParkingPage hassleFreeParkingPage;
	AndroidDriver<AndroidElement>  driver;
	
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	public void setUp() throws Exception
	{
		
		basePage = new BasePage();
		driver = basePage.Initialise();
		registerUserPage = new RegisterUserPage();
	}
	
	@Test (priority = 1)
	public void verifyLogoTest()
	{
		softAssert.assertTrue(registerUserPage.verifyLogo());
		softAssert.assertAll();
	}
	
	@Test (priority = 2)
	@Parameters({"userName","userEmail","userPassword"})
	public void doRegisterCustomerTest(String fullName_param, String email_param, String password_param) throws IOException
	{

		termsAndConditionsPage = registerUserPage.doRegisterCustomer(fullName_param, email_param, password_param);
		
	}
	
	@Test
	@Parameters({"userEmail","userPassword"})
	public void alreadyRegisteredClickTest(String email_param, String password_param) throws IOException
	{
		hassleFreeParkingPage = registerUserPage.alreadyRegisteredClick(email_param, password_param);
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

}
