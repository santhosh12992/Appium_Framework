package appModules;

import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Posting_Pic;
import bussiness_Flows.Profile;
import io.appium.java_client.AppiumDriver;

public class CameraPicAddtoProfile_Action 
{
public static void Execute(AppiumDriver driver) throws Exception 
	
	{

		// GETTING USERNAME AND PASSWORD FROM EXCEL
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
			
		//LOGIN TO APPLICATION
		Login_Out.LoginMail(driver, sUserName, sPassword);
		
		
		// POSTING A PIC 
		Posting_Pic.Post_NonSpamFromHome(driver);
		
		//CHECKING PIC IN RECENT
		Profile.navigate_Recent(driver);
		Posting_Pic.Check_PostedPic(driver);
		
		Screenshots.takeScreenshots(driver, "14_before_deleting_the_post");
		Posting_Pic.Delete_Pic(driver);
		Screenshots.takeScreenshots(driver, "14_After_deleting_the_post");
		//TO SCROLL UP
		ExcelUtils.setCellData("Pass", 14, 7);
		
		//LOGOUT
		Login_Out.Logout(driver);
		
		
	}
}
