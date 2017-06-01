package bussiness_Flows;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import io.appium.java_client.AppiumDriver;
import pageObjects.DiscoverTab_Screen;
import pageObjects.Home_Screen;
import pageObjects.Login_Screen;
import pageObjects.NewsFeed_Screen;
import pageObjects.ProfileTab_Screen;

public class Discover {
	/**
	 * Navigates to Calligraphy page 
	 * @param driver - Pass Appium Driver
	 * @throws Exception - Exception gor wait
	 */
	public static void navigate_Calligraphy(AppiumDriver driver) throws Exception
	{
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));
		EventWrapper.wait(driver, 1000);
		driver.scrollTo("calligraphy");
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameCalligraphy()));
		driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		Log.info("Navigated to Calligraphy page");
		System.out.println("Navigated to Calligraphy page");
	}
	
	
	/**
	 * Verifies few collection of elements present in the Discover tab
	 * @param driver - Pass the Appium driver
	 * @throws Exception - Exception for wait
	 */
	
	public static void Verify_Categeries(AppiumDriver driver) throws Exception
	{
		// START OF VERIFICATION OF COLLECTION TAB ELEMENTS
		//
		//TYPEFACES
		driver.scrollTo("Typefaces");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameTypefaces()),"Typefaces")=="Pass")
			{
				Log.info("Verifed the presence of category - Typefaces");
				System.out.println("Verifed the presence of category - Typefaces");
			}
		else 
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Typefaces");	
				Log.info("Typefaces Collection is not present in the Discover tab Gridview");
			}
	
		//CALIGRAPHY
		driver.scrollTo("Calligraphy");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameCalligraphy()),"Calligraphy")=="Pass")
			{
				Log.info("Verifed the presence of category - Caligraphy");	
				System.out.println("Verifed the presence of category - Caligraphy");
			}
		else 
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Caligraphy");	
				Log.info("Caligraphy Collection is not present in the Discover tab Gridview");
			}		
	
		//POPULAR
		driver.scrollTo("Popular");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.namePopular()),"Popular")=="Pass")
			{	
				Log.info("Verifed the presence of category - Popular");
				System.out.println("Verifed the presence of category - Popular");
				
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Popular");	
				Log.info("Popular Collection is not present in the Discover tab Gridview");
			}
		
	
		//MONOGRAM
		driver.scrollTo("Monogram");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameMonogram()),"Monogram")=="Pass")
			{
				
				Log.info("Verifed the presence of category - Monogram");
				System.out.println("Verifed the presence of category - Monogram");
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Monogram");	
				Log.info("Monogram Collection is not present in the Discover tab Gridview");
			}
		
		
		//ILLUSTRATION
		driver.scrollTo("Illustration");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameIllustration()),"Illustration")=="Pass")
			{
				Log.info("Verifed the presence of category - Illustration");
				System.out.println("Verifed the presence of category - Illustration");
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Illustration");	
				Log.info("Illustration Collection is not present in the Discover tab Gridview");
			}
		
		//SIGNAGE
		driver.scrollTo("Signage");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameSignage()),"Signage")=="Pass")
			{
				Log.info("Verifed the presence of category - Signage");
				System.out.println("Verifed the presence of category - Signage");
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Signage");	
				Log.info("Signage Collection is not present in the Discover tab Gridview");
			}
	
		//LETTERING
		driver.scrollTo("Lettering");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameLettering()), "Lettering")=="Pass")
			{
				Log.info("Verifed the presence of category - Lettering");
				System.out.println("Verifed the presence of category - Lettering");
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Lettering");	
				Log.info("Lettering Collection is not present in the Discover tab Gridview");
			}
	
		//LOGOTYPE
		driver.scrollTo("Logotype");
		if(EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameLogotype()),"Logotype")=="Pass")
			{
				Log.info("Verifed the presence of category - Logotype");
				System.out.println("Verifed the presence of category - Logotype");
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_1_Fail_Logotype");	
				Log.info("Logotype Collection is not present in the Discover tab Gridview");
			}
		
	}
	
	


}
