package appModules;

import Utilities.ExcelUtils;
import Utilities.Screenshots;
import Utilities.User;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Newsfeed_Reusable;
import bussiness_Flows.Notification;
import bussiness_Flows.Posting_Pic;
import bussiness_Flows.User_Creation;
import io.appium.java_client.AppiumDriver;

public class AppNotificationUserMentioned_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		// GETTING USERNAME AND PASSWORD FROM EXCEL
		User user1 = User_Creation.NewUserCreation(driver);
		Posting_Pic.Post_NonSpamFromHome(driver);
		Login_Out.Logout(driver);
		
		//User2 Creation of user2 and User2 posts and comments User1 name on that post		
		User user2 = User_Creation.NewUserCreation(driver);	
		
		
		//Posting a Pic
		Newsfeed_Reusable.comment_Pic(driver, user1.username.toString());
		Login_Out.Logout(driver);
		
		//User one logs in and check for the notification
		Login_Out.LoginMail(driver, user1.username, user1.password);
		
		
		//Checking for the Notification
		Notification.check_Notification(driver, user1, user2, "MENTIONED");
		Screenshots.takeScreenshots(driver, "TC024_Notification_Follow_Pass");
		ExcelUtils.setCellData("Pass", 24, 7);
		
		
		driver.navigate().back();
		Login_Out.Logout(driver);
		
		
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		