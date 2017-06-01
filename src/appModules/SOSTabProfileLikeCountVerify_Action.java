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


public class SOSTabProfileLikeCountVerify_Action 
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
		
		// NAVIGATING TO SOS tab		
		EventWrapper.click(driver, Home_Screen.sosTab(driver));
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		EventWrapper.click(driver, By.id(SOSTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);		
		
		//VERIFYING FEED USERNAME AND PROFILE USERNAME
		String profileName = driver.findElement(By.id(SOSTab_Screen.idprofileName())).getText();
		EventWrapper.verify(driver, By.id(SOSTab_Screen.idprofileName()), feedUsername);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 18, 1);
		Log.info("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		System.out.println("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		
		//SCROLL TO LIKES BUTTON TO PERFORM LIKES COUNT CHECK
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "18_1_before_Liking_a_Post_from_Profile");
		Log.info("Scrolled down to perform Like action");
		System.out.println("Scrolled down to perform Like action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING LIKE ACTION.	
		String likeCount = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount = Integer.parseInt(likeCount.substring(0, 1));
		EventWrapper.wait(driver, 5000);
		ExcelUtils.setCellData("Like count before Like action: "+likeCount, 18, 2);
		Log.info("Like count before performing Like action = "+likeCount);
		System.out.println("Like count before performing Like action = "+likeCount);
		
		// PERFORMING LIKE ACTION TO THE POST	
		EventWrapper.click(driver, By.id(SOSTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "19_1_before_UnLiking_a_Post_from_Profile");
		String newLikesCount = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount1 = Integer.parseInt(newLikesCount.substring(0, 1));
		ExcelUtils.setCellData("Like count after Like action: "+newLikesCount, 18, 3);
		if(lcount1==lcount+1)
			{
				ExcelUtils.setCellData("Pass", 18, 7);
				Screenshots.takeScreenshots(driver, "18_2_after_Liking_a_Post_from_Profile_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 18, 7);
				Screenshots.takeScreenshots(driver, "18_2_after_Liking_a_Post_from_Profile_Fail");
			}

		Log.info("Like count after performing Like action = "+newLikesCount);
		System.out.println("Like count after performing Like action = "+newLikesCount);
		Log.info("Like count before performing Like action = "+likeCount+" Like count after performing Like action = "+newLikesCount);
		System.out.println("Like count before performing Like action = "+likeCount+" Like count after performing Like action = "+newLikesCount);
		
		//PERFORMING UN-LIKE ACTION AND VERIFYING THE NEW LIKE COUNT
		ExcelUtils.setCellData("Like count before UnLike action: "+newLikesCount, 19, 2);
		EventWrapper.click(driver, By.id(SOSTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		String newLikesCount1 = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount2 = Integer.parseInt(newLikesCount1.substring(0, 1));
		if(lcount2==lcount1-1)
			{
				ExcelUtils.setCellData("Pass", 19, 7);
				Screenshots.takeScreenshots(driver, "19_2_after_UnLiking_a_Post_from_Profile_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 19, 7);
				Screenshots.takeScreenshots(driver, "19_2_after_UnLiking_a_Post_from_Profile_Fail");
			}
		ExcelUtils.setCellData("Like count after UnLike action: "+newLikesCount1, 19, 3);
		ExcelUtils.setCellData(EventWrapper.verify(driver, By.id(SOSTab_Screen.idlikeCountText()), likeCount), 7, 7);
		Log.info("Like count before performing UnLike action = "+newLikesCount);
		System.out.println("Like count before performing UnLike action = "+newLikesCount);
		Log.info("Like count before performing UnLike action = "+newLikesCount+" Like count after performing UnLike action = "+newLikesCount1);
		System.out.println("Like count before performing UnLike action = "+newLikesCount+" Like count after performing UnLike action = "+newLikesCount1);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 19, 1);
		
		//NAVIGATING BACK TO SOS tab
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 500);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}
}
