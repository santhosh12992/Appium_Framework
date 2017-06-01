package appModules;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Discover;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Posting_Pic;
import io.appium.java_client.AppiumDriver;

public class CameraPicAddtoTypeCollections_Action 
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
		
		//Upload a picture from gallery to Discover
		Posting_Pic.Post_NonSpam_Calligraphy(driver);
		
		//NAVITAGE TO CORRESPONDING COLLECTION AND CHECK FOR THE POSTED PIC
		Discover.navigate_Calligraphy(driver);
		Posting_Pic.Check_PostedPic(driver);
		ExcelUtils.setCellData("Pass", 13, 7);
		Screenshots.takeScreenshots(driver, "13_Post_adding_to_Collections_Pass");
		
		
		//DELETING THE POST FROM DISCOVER TAB
		Screenshots.takeScreenshots(driver, "13_before_deleting_the_post_from_Collection");
		Posting_Pic.Delete_Pic(driver);
		Screenshots.takeScreenshots(driver, "13_after_deleting_the_post_from_Collection_Pass");
		
		//NAVIGATING BACK TO CATEGORY OPTION
		EventWrapper.swipeUp(driver);
		EventWrapper.back(driver);
		EventWrapper.swipeUp(driver);
		EventWrapper.swipeUp(driver);
		driver.scrollTo("Calligraphy");
		
		
		//NAVIGATING TO PROFILE TAB TO LOGOUT
		Login_Out.Logout(driver);
	}
}