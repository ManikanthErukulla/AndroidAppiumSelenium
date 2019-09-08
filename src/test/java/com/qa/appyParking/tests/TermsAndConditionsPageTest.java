package com.qa.appyParking.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.appyParking.base.BasePage;
import com.qa.appyParking.pages.HassleFreeParkingPage;
import com.qa.appyParking.pages.HomePage;
import com.qa.appyParking.pages.RegisterUserPage;
import com.qa.appyParking.pages.TermsAndConditionsPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class TermsAndConditionsPageTest 
{	
	BasePage basePage;
	RegisterUserPage registerUserPage;
	HomePage homePage;
	TermsAndConditionsPage termsAndConditionsPage;
	HassleFreeParkingPage hassleFreeParkingPage;
	AndroidDriver<AndroidElement>  driver;
	SoftAssert softAssert = new SoftAssert();
	
	@BeforeClass
	@Parameters({"userName","userEmail","userPassword"})
	public void setUp(String userName, String userEmail, String userPassword) throws Exception
	{
		basePage = new BasePage();
		driver = basePage.Initialise();
		registerUserPage = new RegisterUserPage();
		termsAndConditionsPage = registerUserPage.doRegisterCustomer(userName, userEmail, userPassword);		
	}
	@Test
	public void validateTnCHeaderTest()
	{
		String tncHeader = termsAndConditionsPage.validateTnCHeader();
		softAssert.assertEquals(tncHeader, "Terms & Conditions" , "Terms & Conditions header text not matched");
		softAssert.assertAll();
	}
	@Test
	public void validateTnCBodyTest()
	{
		String tncBody = termsAndConditionsPage.validateTnCBody();
		softAssert.assertEquals(tncBody, "By registering, you agree to our Terms of Service, Data Usage and Privacy Policy.\n" + 
				"\n" + 
				"This includes using the app safely and in accordance with the road safety laws of the country where you are driving." , "Terms & Conditions body not matched");
		softAssert.assertAll();
	}
	@Test
	public void acceptTcBodyTest()
	{
		String accepttncBody = termsAndConditionsPage.acceptTcBody();
		softAssert.assertEquals(accepttncBody, "Accept Terms & Conditions" , "Accept Terms & Conditions text not matched");
		softAssert.assertAll();
	}
	@Test
	public void newsLettersBody()
	{
		String newsLettersBody = termsAndConditionsPage.newsLettersBody();
		softAssert.assertEquals(newsLettersBody, "Receive Appyparking news. Unsubscribe at any time" , "AppyParking news subscription text not matched");
		softAssert.assertAll();
	}
	
	@Test 
	public void acceptTcClickTest()
	{
		String tncClick = termsAndConditionsPage.acceptTcTick();
		softAssert.assertEquals(tncClick,"true","Accept T & C unable to click");
		softAssert.assertAll();	
	} 
	@Test
	public void newsLettersClickTest()
	{
		String newsLettersClick = termsAndConditionsPage.newsLettersTick();
		softAssert.assertEquals(newsLettersClick,"true","News Letters unable to click");
		softAssert.assertAll();		
	} 
	
	@Test 
	public void registerUserTest() throws IOException
	{
		hassleFreeParkingPage = termsAndConditionsPage.registerUser();		
		
	} 
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
