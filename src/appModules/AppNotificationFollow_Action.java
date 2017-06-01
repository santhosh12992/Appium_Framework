package appModules;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Screenshots;
import Utilities.User;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Notification;
import bussiness_Flows.User_Creation;
import io.appium.java_client.AppiumDriver;
import pageObjects.Search_Screen;

public class AppNotificationFollow_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		User user1 = User_Creation.NewUserCreation(driver);
		Login_Out.Logout(driver);
		
		//User-2 Creation
		User user2 = User_Creation.NewUserCreation(driver);

		//User 2 following user 1
		EventWrapper.click(driver, By.id(Search_Screen.idSearchButton()));
		EventWrapper.sendKeys(driver, By.id(Search_Screen.idSearchString()), user1.username);
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		EventWrapper.click(driver, By.name(Search_Screen.nameFollow()));
		EventWrapper.wait(driver, 2000);
		driver.navigate().back();
		Login_Out.Logout(driver);
		
		//User one logs in and check for the notification
		Login_Out.LoginMail(driver, user1.username, user1.password);
		
		
		//Checking for the Notification
		Notification.check_Notification(driver, user1, user2, "FOLLOWED");
		Screenshots.takeScreenshots(driver, "TC021_Notification_Follow_Pass");
		ExcelUtils.setCellData("Pass", 21, 7);
		
		
		driver.navigate().back();
		Login_Out.Logout(driver);
				
	}

}
