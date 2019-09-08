package com.qa.appyParking.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.appyParking.base.BasePage;
import com.qa.appyParking.pages.HassleFreeParkingPage;
import com.qa.appyParking.pages.HomePage;
import com.qa.appyParking.pages.LetsGoPage;
import com.qa.appyParking.pages.RegisterUserPage;
import com.qa.appyParking.pages.TermsAndConditionsPage;
import com.qa.appyParking.utils.TestUtil;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class SearchPageTest 
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
	
	@DataProvider
	public Object[][] getSearchData()
	{
		Object data[][] = TestUtil.getTestData("Sheet1");
		return data;
	}
	
	@Test (dataProvider = "getSearchData")
	public void searchLocationTest(String location, String expectedResult) throws IOException, InterruptedException 
	{
		homePage = homePage.searchLocation(location);
		
		TouchAction ts = new TouchAction(driver);
		PointOption p1= new PointOption();
		
		String actualResult=null;

		if (driver.findElements(By.id("com.yellowlineparking.appyparking:id/msg_layer")).size()>0)
				{
				actualResult = "FREE PARKING";
				}
		
		else if (driver.findElements(By.id("com.yellowlineparking.appyparking:id/paid_bays_radio")).size() > 0)
				{	
				actualResult = driver.findElement(By.id("com.yellowlineparking.appyparking:id/restriction_ends_txt")).getText();
				}
		else
				{
				ts.press(p1.withCoordinates(540, 1750)).moveTo(p1.withCoordinates(540, 1450)).release().perform();
				actualResult = driver.findElement(By.id("com.yellowlineparking.appyparking:id/restriction_ends_txt")).getText();
				}
		
		if (actualResult.contains("h"))
		{
			softAssert.assertEquals(actualResult,expectedResult," Restricted timing is expected but app is showing something different");
		}
		else if (actualResult.equalsIgnoreCase("FREE PARKING"))
		{
			softAssert.assertEquals(actualResult,expectedResult," Free Parking is expected");

		}
		else
		{
			softAssert.assertEquals(actualResult,expectedResult," App is showing different restricted values");

		}
		softAssert.assertAll();	
	} 
	
	/*
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	} */

}
