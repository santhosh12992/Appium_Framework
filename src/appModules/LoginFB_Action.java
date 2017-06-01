package appModules;


import io.appium.java_client.AppiumDriver;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import Utilities.ExcelUtils;

// Now this method does not need any arguments

public class LoginFB_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		
		// This is to get the values from Excel sheet, passing parameters (Row
		// num &amp; Col num)to getCellData method
		String sUserName = ExcelUtils.getCellData(1, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(1, 2);
		Log.info("Password from Excel sheet is "+sPassword);
		
		//Beginning of Actions
		Login_Out.LoginFb(driver, sUserName, sPassword);
		Screenshots.takeScreenshots(driver, "1_Pass");
		ExcelUtils.setCellData("Pass", 1, 7);
	    	
		//Logout
		Login_Out.Logout(driver);
		
		
	}

}