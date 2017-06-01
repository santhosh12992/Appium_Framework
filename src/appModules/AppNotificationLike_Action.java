package appModules;

import Utilities.ExcelUtils;
import Utilities.Screenshots;
import Utilities.User;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Newsfeed_Reusable;
import bussiness_Flows.Notification;
import bussiness_Flows.Posting_Pic;
import bussiness_Flows.Search;
import bussiness_Flows.User_Creation;
import io.appium.java_client.AppiumDriver;

public class AppNotificationLike_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		// GETTING USERNAME AND PASSWORD FROM EXCEL
		User user1 = User_Creation.NewUserCreation(driver);
		Posting_Pic.Post_NonSpamFromHome(driver);
		Login_Out.Logout(driver);
		
		
		//User-2 Creation
		User user2 = User_Creation.NewUserCreation(driver);
		Search.find_User(driver, user1.username.toString());
		Newsfeed_Reusable.like_pic(driver);
		driver.navigate().back();
		driver.navigate().back();
		
		//User 2 Logs out
		Login_Out.Logout(driver);
		
		//User one logs in and check for the notification
		Login_Out.LoginMail(driver, user1.username, user1.password);
		
		//Checking for the Notification
		Notification.check_Notification(driver, user1, user2, "LIKED");
		Screenshots.takeScreenshots(driver, "TC022_Notification_Like_Pass");
		ExcelUtils.setCellData("Pass", 22, 7);
		driver.navigate().back();
		Login_Out.Logout(driver);
		
	}
	
	

}
