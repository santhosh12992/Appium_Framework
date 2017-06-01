package appModules;

import io.appium.java_client.AppiumDriver;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Discover;
import bussiness_Flows.Login_Out;
import pageObjects.Home_Screen;

public class DiscovertabAllCategoriesVerify_Action 	{

	public static void Execute(AppiumDriver driver) throws Exception 
	{
		
		// GETTING USERNAME AND PASSWORD FOR LOGGING INTO THE FONTLI APP
		
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
		
		// LOGGING INTO THE APPLICATION WITH FACEBOOK CREDENTIALS
		Login_Out.LoginMail(driver, sUserName, sPassword);
		
		//NAVIGATE TO COLLECTIONS TAB AND PERFORM CHECK
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));		
		Discover.Verify_Categeries(driver);
		Screenshots.takeScreenshots(driver, "4_1_Pass");
		ExcelUtils.setCellData("Pass", 4, 7);
		
		// LOGOUT
		Login_Out.Logout(driver);
			
		}
	
}
