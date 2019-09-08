package com.qa.appyParking.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;

import com.qa.appyParking.base.BasePage;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class HomePage extends BasePage {
	

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/cardViewMenu\")")
	AndroidElement cardView;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/txtSearchLocation\")")
	AndroidElement searchLocation;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.yellowlineparking.appyparking:id/txtTimeSet\")")
	AndroidElement timeSet;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"PARKING RULES\")")
	AndroidElement parkingRulesSwipe;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"CHEAPEST/NEAREST\")")
	AndroidElement cheapNearSwipe;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"PARK NOW\")")
	AndroidElement parkNowBtn;
	
	public HomePage() throws IOException {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		// TODO Auto-generated constructor stub
	}
	
	public HomePage searchLocation(String location) throws IOException, InterruptedException
	{
		searchLocation.click();
		AndroidElement searchEdit = driver.findElement(By.id("com.yellowlineparking.appyparking:id/search_edit"));
		//searchEdit.click();
		searchEdit.sendKeys(location);
		Thread.sleep(3000L);
		List<AndroidElement> searchList = driver.findElements(By.id("com.yellowlineparking.appyparking:id/place_txt"));
		
		if (searchList.size() > 0)
		{	
			for (AndroidElement searchListText : searchList)
			{
				if(searchListText.getText().contains(location))
				{
					searchListText.click();
					break;
				}
			}
			return new HomePage();
		}
		else
		{	
			driver.navigate().back();
			return new HomePage();
		}	
	}
	
	public String viewparkingRules() throws InterruptedException
	{
		
		TouchAction ts = new TouchAction(driver);
		PointOption p1= new PointOption();
	// below expression can be more generalised to suit all the phone sizes. 
	//	Due to time limitations, I am going with static values for now to suit my mobile
		Thread.sleep(2000L);
	//	ts.press(p1.withCoordinates(540, 1750)).moveTo(p1.withCoordinates(540, 1450)).release().perform();
		
		if (driver.findElements(By.id("com.yellowlineparking.appyparking:id/msg_layer")).size()>0)
				{
				return "FREE PARKING";
				}
		
		else if (driver.findElements(By.id("com.yellowlineparking.appyparking:id/paid_bays_radio")).size() > 0)
				{	
				return driver.findElement(By.id("com.yellowlineparking.appyparking:id/restriction_ends_txt")).getText();
				}
		else
				{
				ts.press(p1.withCoordinates(540, 1750)).moveTo(p1.withCoordinates(540, 1450)).release().perform();
				return driver.findElement(By.id("com.yellowlineparking.appyparking:id/restriction_ends_txt")).getText();
				}
	}
	
	
	public void selectCheapest(String regNum) throws InterruptedException
	{
		Thread.sleep(2000L);
		cheapNearSwipe.click();
		Thread.sleep(1000L);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/osp_image")).click();
		Thread.sleep(1000L);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/osp_distance")).click();
		parkNowBtn.click();
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/go_to_car")).click();
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/reg_no_et")).sendKeys(regNum);
		Thread.sleep(2000L);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/confirm_btn")).click();
		
	/*	if (driver.findElements(By.id("com.yellowlineparking.appyparking:id/reg_no_et")).size() > 1)
		{	
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/reg_no_et")).sendKeys(regNum);
		Thread.sleep(2000L);
		driver.findElement(By.id("com.yellowlineparking.appyparking:id/confirm_btn")).click();
		}
		*/
		
	}
		
}
