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

public class AppNotificationTagged_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		// User-1 Creation and posts
		User user1 = User_Creation.NewUserCreation(driver);
		Posting_Pic.Post_NonSpamFromHome(driver);
		Login_Out.Logout(driver);
		
		//User-2 Creation and Tags the picture posted by User-1 to some fonts
		User user2 = User_Creation.NewUserCreation(driver);
		Search.find_User(driver, user1.username.toString());
		Newsfeed_Reusable.tagFont_pic(driver, "Times Got");
		Login_Out.Logout(driver);
		
		//User-1 logsin and checks for the notification
		Login_Out.LoginMail(driver, user1.username, user1.password);
		Notification.check_Notification(driver, user1, user2, "SPOTTED");
		Screenshots.takeScreenshots(driver, "TC025_Notification_FontTagged_Pass");
		ExcelUtils.setCellData("Pass", 25, 7);
		
		
		driver.navigate().back();
		Login_Out.Logout(driver);
		
	}

}
