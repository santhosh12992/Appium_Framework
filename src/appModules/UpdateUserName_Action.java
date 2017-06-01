package appModules;

import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.User;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Profile;
import io.appium.java_client.AppiumDriver;


public class UpdateUserName_Action 
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
		
		//UPDATING USERNAME,EMAIL AND PASSWORD
		Profile.update_Username(driver, new User(sUserName,sPassword));
		ExcelUtils.setCellData("Pass",18, 1);
		//LOGOUT FROM APPLICATION
		Login_Out.Logout(driver);
		
	}

	
	}
	


