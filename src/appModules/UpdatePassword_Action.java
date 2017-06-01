package appModules;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.User;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Profile;
import io.appium.java_client.AppiumDriver;

public class UpdatePassword_Action {

	public static void Execute(AppiumDriver driver) throws Exception {
		


		// GETTING USERNAME AND PASSWORD FROM EXCEL
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
		
		//LOGIN TO APPLICATION
		Login_Out.LoginMail(driver, sUserName, sPassword);
		
		//UPDATING USERNAME,EMAIL AND PASSWORD
		User updatedUser = Profile.update_Password(driver, new User(sUserName,sPassword));
		
		//UPDATING THE NEW PASSWORD TO EXCEL
		ExcelUtils.setCellData(updatedUser.password, 3, 2);
		
		//LOGOUT FROM APPLICATION
		Login_Out.Logout(driver);
		EventWrapper.wait(driver, 20000);
		//CHECKING THE UPDATED PASSWORD BY LOGGING IN
		String sUserName1 = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName1);
		String sPassword1 = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword1);
		
		//LOGIN TO APPLICATION
		Login_Out.LoginMail(driver, sUserName1, sPassword1);
		Log.info("User is able to Login with Updated Password "+sPassword1);
		System.out.println("User is able to Login with Updated Password "+sPassword1);
		ExcelUtils.setCellData("Pass",20, 1);
		//LOGOUT FROM APPLICATION
		Login_Out.Logout(driver);
		
		
	}

}
