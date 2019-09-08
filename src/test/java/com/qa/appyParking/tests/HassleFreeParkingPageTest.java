package com.qa.appyParking.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.appyParking.base.BasePage;
import com.qa.appyParking.pages.HassleFreeParkingPage;
import com.qa.appyParking.pages.HomePage;
import com.qa.appyParking.pages.LetsGoPage;
import com.qa.appyParking.pages.RegisterUserPage;
import com.qa.appyParking.pages.TermsAndConditionsPage;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class HassleFreeParkingPageTest
{
	BasePage basePage;
	RegisterUserPage registerUserPage;
	HomePage homePage;
	TermsAndConditionsPage termsAndConditionsPage;
	HassleFreeParkingPage hassleFreeParkingpage;
	LetsGoPage letsGoPage;
	AndroidDriver<AndroidElement>  driver;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	@Parameters({"registerYN","userName","userEmail","userPassword"})
	public void setUp(String registerYN, String userName, String userEmail, String userPassword) throws Exception
	{
		basePage = new BasePage();
		driver = basePage.Initialise();
		registerUserPage = new RegisterUserPage();
		if (registerYN.equalsIgnoreCase("Y"))
			{		
					
				termsAndConditionsPage = registerUserPage.doRegisterCustomer(userName, userEmail, userPassword);	
				hassleFreeParkingpage = termsAndConditionsPage.registerUser();
			}
		else if (registerYN.equalsIgnoreCase("N"))
		{
			hassleFreeParkingpage = registerUserPage.alreadyRegisteredClick(userEmail, userPassword);
		}
	}	

	@Test
	public void validateHassleFreeHeaderTest()
	{
		String header_text = hassleFreeParkingpage.validateHassleFreeHeader();
		softAssert.assertEquals(header_text, "Enjoy hassle-free parking in Harrogate" , "Hassle Free header text not matched");
		softAssert.assertAll();
	}
	@Test
	public void verifyHassleFreeImageTest()
	{
		softAssert.assertTrue(hassleFreeParkingpage.verifyHassleFreeImage());
		softAssert.assertAll();
	}
	@Test
	public void validateHassleFreeBodyTest()
	{
		String body_text = hassleFreeParkingpage.validateHassleFreeBody();
		softAssert.assertEquals(body_text, "Check parking availability across town in the app and pay with just One Click." , "Hassle Free body text not matched");
		softAssert.assertAll();
	}
	
	@Test 
	public void HassleFreeSignMeUpTest() throws IOException
	{
		letsGoPage = hassleFreeParkingpage.HassleFreeSignMeUp();		
		
	} 
	
	@Test 
	public void HassleFreeNoThanksTest() throws IOException
	{
		homePage = hassleFreeParkingpage.HassleFreeNoThanks();		
		
	} 
	@Test
	public void HassleFreeSkipTest() throws IOException
	{
		homePage = hassleFreeParkingpage.HassleFreeSkip();		
		
	} 	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	

}
