package appModules;

import Utilities.ExcelUtils;
import Utilities.Log;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Posting_Pic;
import io.appium.java_client.AppiumDriver;

public class PostingSpam_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		

		// GETTING USERNAME AND PASSWORD FROM EXCEL
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
			
		//LOGIN TO APPLICATION
		Login_Out.LoginMail(driver, sUserName, sPassword);
		Posting_Pic.Post_SpamFromHome(driver);
		ExcelUtils.setCellData("Pass", 26, 7);
		//LOG OUT 
		Login_Out.Logout(driver);
		
		

	}
	
	
}

