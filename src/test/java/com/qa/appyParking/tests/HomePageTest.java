package com.qa.appyParking.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
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

public class HomePageTest
{
	BasePage basePage;
	RegisterUserPage registerUserPage;
	HomePage homePage;
	TermsAndConditionsPage termsAndConditionsPage;
	HassleFreeParkingPage hassleFreeParkingpage;
	LetsGoPage letsGoPage;
	AndroidDriver<AndroidElement>  driver;
	String regNum;
	SoftAssert softAssert = new SoftAssert();
	

	@BeforeClass
	@Parameters({"registerYN","userName","userEmail","userPassword","regNum"})
	public void setUp(String registerYN, String userName, String userEmail, String userPassword, String regNum) throws Exception
	{
		basePage = new BasePage();
		driver = basePage.Initialise();
		registerUserPage = new RegisterUserPage();
		this.regNum = regNum;
		if (registerYN.equalsIgnoreCase("Y"))
		{		
				
			termsAndConditionsPage = registerUserPage.doRegisterCustomer(userName, userEmail, userPassword);	
			hassleFreeParkingpage = termsAndConditionsPage.registerUser();
		}
		else if (registerYN.equalsIgnoreCase("N"))
		{
		hassleFreeParkingpage = registerUserPage.alreadyRegisteredClick(userEmail, userPassword);
		}
		homePage = hassleFreeParkingpage.HassleFreeNoThanks();
	}
	
	@Test (priority = 1)
	public void searchLocationTest() throws IOException, InterruptedException
	{
		homePage = homePage.searchLocation("Ilford");
	} 
	
	@Test (priority = 2)
	public void viewParkingRulesTest() throws InterruptedException
	{
		String parkingRule = homePage.viewparkingRules();
		
		if (parkingRule.contains("h"))
		{
			softAssert.assertEquals(parkingRule,"Free Parking"," Restricted timing is expected but app is showing something different");
		}
		else if (parkingRule.equalsIgnoreCase("FREE PARKING"))
		{
			softAssert.assertEquals(parkingRule,"FREE PARKING"," Free Parking is expected");

		}
		else
		{
			softAssert.assertEquals(parkingRule,"12h 20m"," App is showing different restricted values");

		}
		softAssert.assertAll();	
	}
	
	
	@Test (priority = 3)
	public void selectCheapCarPark() throws InterruptedException
	{
		homePage.selectCheapest(regNum);
		System.out.println("Car with "+regNum+" is parked");
	}
}
