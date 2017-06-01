package appModules;

import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.DiscoverTab_Screen;

public class DiscoverTabProfileCommentsCountVerify_Action 
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
	
		
		// NAVIGATING TO THE COLLECTION CATEGORY - TYPEFACES		
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));
		driver.scrollTo("Typefaces");
		String catName = driver.findElement(By.name(DiscoverTab_Screen.nameTypefaces())).getText();
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab "+catName);
		System.out.println("Navigated to the categories tab "+catName);
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);		
		
		//VERIFYING FEED USERNAME AND PROFILE USERNAME
		String profileName = driver.findElement(By.id(DiscoverTab_Screen.idprofileName())).getText();
		EventWrapper.verify(driver, By.id(DiscoverTab_Screen.idprofileName()), feedUsername);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 11, 1);
		Log.info("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		System.out.println("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		
		//SCROLL TO LIKES BUTTON TO COMMENT ON THE POST
		//EventWrapper.swipe(driver, By.id(categoriesTab_Screen.idlikeCountText()));
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 5000);
		Log.info("Scrolled down to perform Comments action");
		System.out.println("Scrolled down to perform Comments action");
		Screenshots.takeScreenshots(driver, "11_1_before_Commenting_in_a_Users_Post");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING COMMENT ACTION.	
		String commentCount = driver.findElement(By.id(DiscoverTab_Screen.idcommentCount())).getText();
		Integer cCount = Integer.parseInt(commentCount.substring(0, 1));
		EventWrapper.wait(driver, 5000);
		ExcelUtils.setCellData("Comment count before action: "+commentCount, 11, 2);
		Log.info("Comment count before performing comment action = "+commentCount);
		System.out.println("Comment count before performing Comment action = "+commentCount);
		
		// PERFORMING COMMENT ACTION TO THE POST	
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 5000);
		EventWrapper.sendKeys(driver, By.id(DiscoverTab_Screen.idcommentTextBox()), "Comments Entered");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idpostCommentButton()));
		Screenshots.takeScreenshots(driver, "11_2_during_Commenting_in_a_Users_Post");
		EventWrapper.wait(driver, 10000);
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 3000);
		String newCommentsCount = driver.findElement(By.id(DiscoverTab_Screen.idcommentCount())).getText();
		Integer cCount1 = Integer.parseInt(newCommentsCount.substring(0, 1));
		ExcelUtils.setCellData("Comment count after action: "+newCommentsCount, 11, 3);
		Log.info("Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count after performing Comment action = "+newCommentsCount);
		Log.info("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		
		//VERIFYING THE NEW LIKES COUNT
		if(cCount1==cCount+1)
			{
				ExcelUtils.setCellData("Pass", 11, 7);
				Screenshots.takeScreenshots(driver, "11_3_after_Commenting_in_a_Users_Post_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 11, 7);
				Screenshots.takeScreenshots(driver, "11_3_after_Commenting_in_a_Users_Post_Fail");
			}
		
		//NAVIGATING BACK TO THE CATEGORIES TYPE
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 5000);
		
		//NAVIGATING BACK TO THE CATERGORIES TAB
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}

}
