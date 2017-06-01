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

public class AppNotificationComment_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		// GETTING USERNAME AND PASSWORD FROM EXCEL
		User user1 = User_Creation.NewUserCreation(driver);
		Login_Out.Logout(driver);
		
		//User2 Login to application
		User user2 = User_Creation.NewUserCreation(driver);
		
		//Posting a Pic and comments user1 name
		Posting_Pic.Post_NonSpamFromHome(driver);
		Newsfeed_Reusable.comment_Pic(driver, user1.username.toString());
		Login_Out.Logout(driver);
		
		//User one logs in and check for the notification
		Login_Out.LoginMail(driver, user1.username, user1.password);
		
		
		//Checking for the Notification
		Notification.check_Notification(driver, user1, user2, "COMMENT");
		Screenshots.takeScreenshots(driver, "TC023_Notification_Comment_Pass");
		ExcelUtils.setCellData("Pass", 23, 7);
		
		
		driver.navigate().back();
		Login_Out.Logout(driver);
		
	}
}
