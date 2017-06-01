package bussiness_Flows;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.Log;
import Utilities.User;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.Login_Screen;
import pageObjects.NewUserRegistration_screen;
import pageObjects.NewsFeed_Screen;

public class User_Creation {
	
	/**
	 * Creates a new user and returns an User object
	 * @param driver - Pass Appium driver
	 * @return - Returns new user object
	 * @throws Exception - Exception for wait
	 */
	public static User NewUserCreation(AppiumDriver driver) throws Exception
	{	
		EventWrapper.click(driver, By.name(Login_Screen.emailButton()));
		String userName = "auto"+(int)(Math.random()*Integer.MAX_VALUE);
		String emailID = "fontli_"+userName+"@imaginea.com";
		String password = userName;
		
		EventWrapper.sendKeys(driver, By.id(NewUserRegistration_screen.userName()), userName);
		EventWrapper.sendKeys(driver, By.id(NewUserRegistration_screen.userMailID()), emailID);
		EventWrapper.sendKeys(driver, By.id(NewUserRegistration_screen.userPassword()), password);
		EventWrapper.sendKeys(driver, By.id(NewUserRegistration_screen.confirmPassword()), password);
		driver.hideKeyboard();
		Log.info("Keyboard is hidden expicitly by the user");	
		EventWrapper.click(driver, By.id(NewUserRegistration_screen.registerButton()));
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		
		System.out.println("User has been created Successfully - "+userName);
		Log.info("User has been created successfully - "+userName );
		return new User(userName,password);
	}

}
