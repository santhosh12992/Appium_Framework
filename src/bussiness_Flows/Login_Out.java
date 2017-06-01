package bussiness_Flows;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.Login_Screen;
import pageObjects.Message_Prompts;
import pageObjects.NewsFeed_Screen;
import pageObjects.Profile_Settings;

public class Login_Out {
	
	/**
	 * Log in to the Fontli App using Fontli credentials
	 * @param driver - Pass Appium Driver
	 * @param username - Pass Fontli Username
	 * @param Password - Pass Fontli Password
	 */
	
	public static void LoginMail(AppiumDriver driver, String username, String Password) 
	{
		
		EventWrapper.click(driver, By.name(Login_Screen.emailButton()));
		EventWrapper.click(driver, By.id(Login_Screen.existingUserCheckBox()));
		EventWrapper.sendKeys(driver, By.id(Login_Screen.emailuserName()), username);
		EventWrapper.sendKeys(driver, By.id(Login_Screen.emailpassWord()), Password);
		driver.hideKeyboard();
		Log.info("Keyboard is hidden expicitly by the user");	
		EventWrapper.click(driver, By.id(Login_Screen.emailloginButton()));	
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		
		System.out.println("User has been logged in Successfully with Fontli Credentials- "+username);
		Log.info("User has been logged in successfully with Fontli Credentials - "+username );
	}
	/**
	 * 
	 * @param driver - Pass the Appium Driver
	 * @param username - Pass the FB user name
	 * @param Password - Pass the FB password
	 */
	public static void LoginFb(AppiumDriver driver, String username, String Password) 
	{
		EventWrapper.click(driver, By.name(Login_Screen.fbButton()));
		EventWrapper.sendKeys(driver, By.xpath(Login_Screen.fbuserName()), username);
		EventWrapper.sendKeys(driver, By.xpath(Login_Screen.fbpassWord()), Password);
		driver.hideKeyboard();
		Log.info("Keyboard is hidden expicitly by the user");	
		EventWrapper.click(driver, By.xpath(Login_Screen.fbloginButton()));	
		EventWrapper.click(driver, By.xpath(Login_Screen.fbloginConfirmButton()));
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		
		System.out.println("User has been logged in Successfully with FB credentials- "+username);
		Log.info("User has been logged in successfully with FB credentials- "+username );
		
	}
	
	/**
	 * 
	 * @param driver - Pass the Appium Driver
	 * @param username - Pass the Twitter user name
	 * @param Password - Pass the Twitter password
	 * @throws Exception - Exception for wait
	 */
	
	public static void LoginTwtr(AppiumDriver driver, String username, String Password) throws Exception
	{
		EventWrapper.click(driver, By.name(Login_Screen.twButton()));
		EventWrapper.sendKeys(driver, By.xpath(Login_Screen.twuserName()), username);
		EventWrapper.sendKeys(driver, By.xpath(Login_Screen.twpassWord()), Password);
		driver.hideKeyboard();
		Log.info("Keyboard is hidden expicitly by the user");	
		EventWrapper.wait(driver, 2000);
		EventWrapper.click(driver, By.xpath(Login_Screen.twloginButton()));
		EventWrapper.wait(driver, 3000);
		driver.navigate().back();
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		
		System.out.println("User has been logged in Successfully with Twitter credentials- "+username);
		Log.info("User has been logged in successfully with Twitter credentials- "+username );
	}
	
	/**
	 * Logs Out from the application
	 * @param driver - Pass the Appium Driver
	 * @throws Exception - Exception for wait
	 */
	
	public static void Logout(AppiumDriver driver) throws Exception 
	{
		EventWrapper.swipeDown(driver);
		EventWrapper.click(driver, By.xpath(Home_Screen.threedots_xpath()));
		EventWrapper.click(driver, By.xpath(Home_Screen.logOutButton_xpath()));
		EventWrapper.click(driver, By.xpath(Message_Prompts.logOutmsgOK()));
		System.out.println("User has been logged out Successfully ");
		Log.info("User has been logged out successfully ");
     }

}
