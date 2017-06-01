package appModules;


import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.SOSTab_Screen;
import pageObjects.Home_Screen;


public class SOSTabCommentCountVerify_Action 
{
public static void Execute(AppiumDriver driver) throws Exception 
	
	{

		// GETTING USERNAME AND PASSWORD FROM EXCEL
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
			
		//LOGIN TO THE APPLICATION
		Login_Out.LoginMail(driver, sUserName, sPassword);
		
		// NAVIGATING TO SOS Tab		
		EventWrapper.click(driver, Home_Screen.sosTab(driver));
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Feed Username: "+feedUsername, 20, 1);

		//SCROLL TO LIKES BUTTON TO COMMENT ON THE POST
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "20_1_before_Commenting_a_Post_from_SOSTab");
		Log.info("Scrolled down to perform Comments action");
		System.out.println("Scrolled down to perform Comments action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING COMMENT ACTION.	
		String commentCount = driver.findElement(By.id(SOSTab_Screen.idcommentCount())).getText();
		Integer cCount = Integer.parseInt(commentCount.substring(0, 1));
		EventWrapper.wait(driver, 5000);
		ExcelUtils.setCellData("Comment count before action: "+commentCount, 20, 2);
		Log.info("Comment count before performing comment action = "+commentCount);
		System.out.println("Comment count before performing Comment action = "+commentCount);
		
		// PERFORMING COMMENT ACTION TO THE POST	
		EventWrapper.click(driver, By.id(SOSTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 5000);
		EventWrapper.sendKeys(driver, By.id(SOSTab_Screen.idcommentTextBox()), "Comments Entered");
		EventWrapper.click(driver, By.id(SOSTab_Screen.idpostCommentButton()));
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "20_2_during_Commenting_a_Post_from_SOSTab");
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 3000);
		String newCommentsCount = driver.findElement(By.id(SOSTab_Screen.idcommentCount())).getText();
		Integer cCount1 = Integer.parseInt(newCommentsCount.substring(0, 1));
		ExcelUtils.setCellData("Comment count after action: "+newCommentsCount, 20, 3);
		Log.info("Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count after performing Comment action = "+newCommentsCount);
		Log.info("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		
		//VERIFYING THE NEW LIKES COUNT
		if(cCount1==cCount+1)
			{
				ExcelUtils.setCellData("Pass", 20, 7);
				Screenshots.takeScreenshots(driver, "20_3_after_Commenting_a_Post_from_SOSTab_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 20, 7);
				Screenshots.takeScreenshots(driver, "20_3_after_Commenting_a_Post_from_SOSTab_Fail");
			}		
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
	}
}