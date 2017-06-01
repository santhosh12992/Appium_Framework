package appModules;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.SOSTab_Screen;
import pageObjects.Home_Screen;


public class NFTabProfileCommentsCountVerify_Action 
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
				
		// NAVIGATING TO THE SOS Screen		
		EventWrapper.click(driver, Home_Screen.sosTab(driver));
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		EventWrapper.click(driver, By.id(SOSTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);		
		
		//VERIFYING FEED USERNAME AND PROFILE USERNAME
		String profileName = driver.findElement(By.id(SOSTab_Screen.idprofileName())).getText();
		EventWrapper.verify(driver, By.id(SOSTab_Screen.idprofileName()), feedUsername);
		ExcelUtils.setCellData(feedUsername, 10, 1);
		Log.info("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		System.out.println("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		
		//SCROLL TO LIKES BUTTON TO COMMENT ON THE POST
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "10_before_Commenting_a_Post_from_ProfileUser_SNewsfeed");
		Log.info("Scrolled down to perform Comments action");
		System.out.println("Scrolled down to perform Comments action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING COMMENT ACTION.	
		String commentCount = driver.findElement(By.id(SOSTab_Screen.idcommentCount())).getText();
		int cCount = Integer.parseInt(commentCount.substring(0, 2).trim());
		EventWrapper.wait(driver, 5000);
		ExcelUtils.setCellData("Comment count before action: "+commentCount, 10, 2);
		Log.info("Comment count before performing comment action = "+commentCount);
		System.out.println("Comment count before performing Comment action = "+commentCount);
		
		// PERFORMING COMMENT ACTION TO THE POST	
		EventWrapper.click(driver, By.id(SOSTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 5000);
		EventWrapper.sendKeys(driver, By.id(SOSTab_Screen.idcommentTextBox()), "Comments Entered");
		EventWrapper.click(driver, By.id(SOSTab_Screen.idpostCommentButton()));
		EventWrapper.wait(driver, 10000);
		Screenshots.takeScreenshots(driver, "10_during_Commenting_a_Post_from_ProfileUser_Newsfeed");
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 3000);
		String newCommentsCount = driver.findElement(By.id(SOSTab_Screen.idcommentCount())).getText();
		int cCount1 = Integer.parseInt(newCommentsCount.substring(0, 2).trim());
		ExcelUtils.setCellData("Comment count after action: "+newCommentsCount, 10, 3);
		Log.info("Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count after performing Comment action = "+newCommentsCount);
		Log.info("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		
		//VERIFYING THE NEW COMMENTCOUNT
		if(cCount1==cCount+1)
			{
				ExcelUtils.setCellData("Pass", 10, 7);
				Screenshots.takeScreenshots(driver, "10_after_Commenting_a_Post_from_ProfileUser_Newsfeed_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 10, 7);
				Screenshots.takeScreenshots(driver, "10_after_Commenting_a_Post_from_ProfileUser_Newsfeed_Fail");
			}
		assertEquals(cCount1, cCount+1);
		//NAVIGATING BACK TO SOS Tab
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 500);

		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}

}
