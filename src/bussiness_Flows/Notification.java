package bussiness_Flows;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.User;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;

public class Notification {
	
	/**
	 * Checks for the Notification with the given string key from another fontli user
	 * @param driver - Pass the Appium driver
	 * @param user1 - Pass the current Login User
	 * @param user2 - Pass the User against which notification has been generated
	 * @param message - Pass the string key. Key word in the notification message
	 */
	public static void check_Notification(AppiumDriver driver, User user1,User user2, String message)
	{
		EventWrapper.click(driver, By.id(Home_Screen.noti_id()));
		driver.scrollTo(user2.username);
		Log.info(user1.username +" is able to find the notication stating "+user2.username+" "+message+" him");
		System.out.println(user1.username +" is able to find the notication stating "+user2.username+" "+message+" him");
	}

}
