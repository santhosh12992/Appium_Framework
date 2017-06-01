package appModules;


import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import bussiness_Flows.Search;
import io.appium.java_client.AppiumDriver;


public class SearchforCollections_Action 
{
public static void Execute(AppiumDriver driver) throws Exception 
	
	{

			// GETTING USERNAME AND PASSWORD FROM EXCEL
			String sUserName = ExcelUtils.getCellData(3, 1);
			Log.info("Username from Excel sheet is "+sUserName);
			String sPassword = ExcelUtils.getCellData(3, 2);
			Log.info("Password from Excel sheet is "+sPassword);
			String Searchvalue = ExcelUtils.getCellData(17, 1);
			Log.info("Search string fetched from Data sheet is "+Searchvalue);
				
			//LOGIN TO THE APPLICATION
			Login_Out.LoginMail(driver, sUserName, sPassword);	
			
			//NAVIGATING TO THE SEARCH FIELD FOR SEARCHING
			Search.search_Collection(driver, Searchvalue);
			ExcelUtils.setCellData("Pass", 17, 7);
			Screenshots.takeScreenshots(driver, "17_Collections_Search_Result_Pass");
			EventWrapper.back(driver);
			
			//LOGOUT
			Login_Out.Logout(driver);		
		
		
	}

}
