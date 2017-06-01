package bussiness_Flows;

import static org.testng.Assert.assertNotEquals;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.Log;
import Utilities.Screenshots;
import Utilities.User;
import Utilities.generateData;
import io.appium.java_client.AppiumDriver;
import pageObjects.DiscoverTab_Screen;
import pageObjects.Home_Screen;
import pageObjects.NewsFeed_Screen;
import pageObjects.ProfileTab_Screen;
import pageObjects.Profile_Settings;
import pageObjects.SOSTab_Screen;

public class Profile {
	
	/**
	 * Navigates to the Recent Tab and swipes up upto Like button
	 * @param driver - Pass Appium Driver
	 * @throws Exception - Exception for Wait
	 */
	
	public static void navigate_Recent(AppiumDriver driver) throws Exception
	{
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		EventWrapper.wait(driver, 1000);
		EventWrapper.click(driver, By.id(ProfileTab_Screen.idPopularPosts()));
		EventWrapper.click(driver, By.id(ProfileTab_Screen.idRecentPosts()));
		driver.findElement(By.id(SOSTab_Screen.idfeedUsername()));
		EventWrapper.swipeUp(driver);
		EventWrapper.swipeUp(driver);
		EventWrapper.swipeUp(driver);
		EventWrapper.swipeUp(driver);		
	}
	
	/**
	 * Updates the User name alone. Start case - Home Page, End case - Home PAge.
	 * @param driver - Pass the Appium Driver
	 * @param user1 - Pass the Current user details.
	 * @return 
	 */
	
	
	public static User update_Username(AppiumDriver driver, User user1)
	{
		String newUserName = "auto"+(int)(Math.random()*Integer.MAX_VALUE);
		
		//Navigating to setting page and updating the UserName
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		EventWrapper.click(driver, By.id(ProfileTab_Screen.settingsButton()));
		String oldUserName = driver.findElement(By.id(Profile_Settings.accountnameText())).getText();
		EventWrapper.sendKeys(driver, By.id(Profile_Settings.accountnameText()), newUserName);
		driver.hideKeyboard();
		EventWrapper.click(driver, By.id(Profile_Settings.saveButton()));
		EventWrapper.click(driver, By.id(Profile_Settings.saveButton()));
		String newName= driver.findElement(By.id(Profile_Settings.accountnameText())).getText();
		assertNotEquals(newName, oldUserName);
		Log.info("Old User Name: "+oldUserName);
		System.out.println("Old User Name: "+oldUserName);
		Log.info("New User Name: "+newUserName);
		System.out.println("New User Name: "+newUserName);
		
		//Back to Home page;
		driver.navigate().back();
		EventWrapper.click(driver, Home_Screen.homeTab(driver));
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		return user1;
		
	}
	
	/**
	 * Updates the User EmailID alone. Start case - Home Page, End case - Home Page.
	 * @param driver - Pass the Appium Driver
	 * @param user1 - Pass the Current user details.
	 * @return 
	 */
	public static User update_MailID(AppiumDriver driver, User user1)
	{
		String newUserName = "auto"+(int)(Math.random()*Integer.MAX_VALUE);
		String newEmailID = "fontli_"+newUserName+"@imaginea.com";
		
		//Navigating to setting page and updating the EmailID
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		EventWrapper.click(driver, By.id(ProfileTab_Screen.settingsButton()));
		String oldMailID = driver.findElement(By.id(Profile_Settings.accountemailText())).getText();
		
		EventWrapper.sendKeys(driver, By.id(Profile_Settings.accountemailText()), newEmailID);
		EventWrapper.click(driver, By.id(Profile_Settings.saveButton()));
		String newMail = driver.findElement(By.id(Profile_Settings.accountemailText())).getText();
		assertNotEquals(newMail, oldMailID);
		Log.info("Old User Name: "+oldMailID);
		System.out.println("Old User Name: "+oldMailID);
		Log.info("New Email ID: "+newMail);
		System.out.println("New Email ID: "+newMail);
		
		//Back to Home page;
		driver.navigate().back();
		EventWrapper.click(driver, Home_Screen.homeTab(driver));
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		return user1;
		
	}
	
	/**
	 * Updates the User Password alone. Start case - Home Page, End case - Home Page.
	 * @param driver - Pass the Appium Driver
	 * @param user1 - Pass the Current user details.
	 * @return 
	 */
	
	public static User update_Password(AppiumDriver driver, User user1)
	{
		//Generating Random Data
		String newPassword = "auto"+(int)(Math.random()*Integer.MAX_VALUE);
		
		//Updating the Password
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		EventWrapper.click(driver, By.id(ProfileTab_Screen.settingsButton()));
		EventWrapper.click(driver, By.id(Profile_Settings.changePassword()));
		EventWrapper.sendKeys(driver, By.id(Profile_Settings.currentPassword()),user1.password);
		EventWrapper.sendKeys(driver, By.id(Profile_Settings.newPassword()), newPassword);
		EventWrapper.sendKeys(driver, By.id(Profile_Settings.reTypePassword()), newPassword);
		EventWrapper.click(driver, By.id(Profile_Settings.saveButton())); 
		EventWrapper.click(driver, By.id(Profile_Settings.saveButton()));
		Log.info("Old PassWord: "+user1.password);
		System.out.println("Old PassWord: "+user1.password);
		Log.info("Updated PassWord: "+newPassword);
		System.out.println("Updated PassWord: "+newPassword);
		
		//Back to Home page;
		EventWrapper.click(driver, Home_Screen.homeTab(driver));
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		return new User(user1.username, newPassword);
	}
	
	
	
	

}
