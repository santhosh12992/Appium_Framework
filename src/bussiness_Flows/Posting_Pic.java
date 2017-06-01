package bussiness_Flows;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import pageObjects.Camera_Screen;
import pageObjects.DiscoverTab_Screen;
import pageObjects.Home_Screen;
import pageObjects.Message_Prompts;
import pageObjects.ProfileTab_Screen;
import pageObjects.SOSTab_Screen;

public class Posting_Pic {
	
	/**
	 * Selects a non spam image from Gallery - Made Private
	 * @param driver
	 * @throws Exception
	 */
	
	private static void SelectNonSpam_Image(AppiumDriver driver) throws Exception
	{
		EventWrapper.click(driver, By.id(Camera_Screen.idCameraButton()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.id(Camera_Screen.idOpenGallery()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, Camera_Screen.non_SpamImage(driver));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.id(Camera_Screen.idCroped()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.sendKeys(driver, By.id(Camera_Screen.idCaption()), "Good Design");
		Log.info("Picture has been selected and caption entered successfully");
		System.out.println("Picture has been selected and caption entered successfully");
	}
	
	/**
	 * Selects a spam image from Gallery - Made Private
	 * @param driver
	 * @throws Exception
	 */
	private static void SelectSpam_Image(AppiumDriver driver) throws Exception
	{
		EventWrapper.click(driver, By.id(Camera_Screen.idCameraButton()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.id(Camera_Screen.idOpenGallery()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, Camera_Screen.spamImage(driver));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.id(Camera_Screen.idCroped()));
		EventWrapper.wait(driver, 3000);
	}
	
	/**
	 * Gives permission to Fontli to access the mobile camera and its features
	 * @param driver - Pass the Appium Driver
	 * @throws Exception - Exception for wait
	 */
	
	public static void Camera_Perm(AppiumDriver driver) throws Exception
	{
		EventWrapper.click(driver, By.id(Camera_Screen.idCameraButton()));
		EventWrapper.click(driver, By.id(Message_Prompts.idMessageOK()));
		EventWrapper.click(driver, By.id(Message_Prompts.idAllowPermission()));
		EventWrapper.click(driver, By.id(Message_Prompts.idAllowPermission()));
		EventWrapper.click(driver, By.id(Message_Prompts.idAllowPermission()));
	}
	
	/**
	 * Uploads a non span picture from phone gallery
	 * @param driver - Pass Appium Driver
	 * @throws Exception - Exception for Wait
	 */
	public static void Post_NonSpamFromHome(AppiumDriver driver) throws Exception
	{
		
		Posting_Pic.SelectNonSpam_Image(driver);
		EventWrapper.click(driver, By.id(Camera_Screen.idPublishOK()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.id(Camera_Screen.idPublishOK()));
		EventWrapper.wait(driver, 1000);
		driver.findElement(By.id(SOSTab_Screen.idfeedUsername()));
		Log.info("Picture has been Posted successfully");
		System.out.println("Picture has been Posted successfully");
	}
	
	public static void Post_NonSpam_Calligraphy(AppiumDriver driver) throws Exception
	{
		Posting_Pic.SelectNonSpam_Image(driver);
		EventWrapper.click(driver, By.id(Camera_Screen.idAddtoCollection()));
		driver.scrollTo("Calligraphy");
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameCalligraphy()));
		EventWrapper.click(driver, By.id(Camera_Screen.idActionSelectOK()));
		EventWrapper.click(driver, By.id(Camera_Screen.idPublishOK()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.click(driver, By.id(Camera_Screen.idPublishOK()));
	}
	
	/**
	 * Deletes the pic posted by user from him news feed
	 * @param driver - Pass the Appium driver
	 * @throws Exception - Exception for wait
	 */
	public static void Delete_Pic(AppiumDriver driver) throws Exception
	{
		driver.findElement(By.id(SOSTab_Screen.idfeedUsername()));
		EventWrapper.wait(driver, 500);
		driver.scrollTo("Like");
		EventWrapper.click(driver, By.id(ProfileTab_Screen.idMoreOptions()));
		EventWrapper.click(driver, By.name(ProfileTab_Screen.nameOptionDelete()));
		EventWrapper.click(driver, By.id(Message_Prompts.idMessageOK()));
		EventWrapper.wait(driver, 15000);
		Log.info("Picture has been deleted successfully");
		System.out.println("Picture has been deleted successfully");
	}
	
	/**
	 * Checks for the posted pic provided that user is the corresponding page
	 * @param driver - Pass Appium Driver
	 * @throws Exception - Exception for Wait
	 */
	
	
	public static void Check_PostedPic(AppiumDriver driver)
	{
		String feedTime = driver.findElement(By.id(DiscoverTab_Screen.idFeedTime())).getText();
		System.out.println(feedTime);
		assertTrue(feedTime.contains("ago")||feedTime.contains("now")||feedTime.contains("yet"));
		String profileUser = driver.findElement(By.id(ProfileTab_Screen.idfeedUsername())).getText();
		Log.info("Found a post by the user "+profileUser+" with time stamp "+feedTime);
		System.out.println("Found a post by the user "+profileUser+"with time stamp"+feedTime);
	}
	
	/**
	 * Tries to post a spam image.
	 * @param driver - Pass Appium Driver
	 * @throws Exception - Exception for wait
	 */
	
	public static void Post_SpamFromHome(AppiumDriver driver) throws Exception
	{
		Posting_Pic.SelectSpam_Image(driver);
		EventWrapper.click(driver, By.id(Camera_Screen.idPublishOK()));
		EventWrapper.wait(driver, 3000);
		EventWrapper.click(driver, By.name("OK"));
		Log.info("User is not able to post Spam post as expected");
		System.out.println("User is not able to post Spam post as expected");
		driver.navigate().back();
	}
	
	
}
 