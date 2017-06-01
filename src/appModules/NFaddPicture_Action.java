package appModules;


import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Posting_Pic;
import io.appium.java_client.AppiumDriver;


public class NFaddPicture_Action 
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
		Posting_Pic.Post_NonSpamFromHome(driver);
			
		
		//CLICKING THE CAMERA AND NECESSAY PERMISSION
		Screenshots.takeScreenshots(driver, "12_Picture_Uploaded_NewsFeedTab_Pass");
		ExcelUtils.setCellData("Pass", 12, 7);
		Screenshots.takeScreenshots(driver, "12_before_deleting_the_post_from_NewsFeedTab");
		
		//Deleting a Pic
		Posting_Pic.Delete_Pic(driver);
		Screenshots.takeScreenshots(driver, "12_after_deleting_the_post_from_NewsFeedTab_Fail");
		ExcelUtils.setCellData("Pass", 12, 7);
		
		
	
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}
}