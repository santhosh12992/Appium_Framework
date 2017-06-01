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

public class DiscoverTabProfileLikeCountVerify_Action 
{
	public static void Execute(AppiumDriver driver) throws Exception 
	
	{

		// GETTING USERNAME AND PASSWORD FROM EXCEL
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
			
		Login_Out.LoginMail(driver, sUserName, sPassword);
		
		// NAVIGATING TO THE COLLECTION CATEGORY - TYPEFACES		
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));
		driver.scrollTo("Typefaces");
		//String catName = driver.findElement(By.name(DiscoverTab_Screen.nameTypefaces())).getText();
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab TYPEFACES");
		System.out.println("Navigated to the categories tab TYPEFACES");
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);		
		
		//VERIFYING FEED USERNAME AND PROFILE USERNAME
		String profileName = driver.findElement(By.id(DiscoverTab_Screen.idprofileName())).getText();
		EventWrapper.verify(driver, By.id(DiscoverTab_Screen.idprofileName()), feedUsername);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 8, 1);
		Log.info("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		System.out.println("Comparing actioned user "+feedUsername+" with the profile title "+profileName);
		
		//SCROLL TO LIKES BUTTON TO PERFORM LIKES COUNT CHECK	
		//EventWrapper.swipe(driver, By.id(categoriesTab_Screen.idlikeCountText()));
		driver.scrollTo("Comment");
		EventWrapper.wait(driver, 5000);
		Log.info("Scrolled down to perform Like action");
		System.out.println("Scrolled down to perform Like action");
		Screenshots.takeScreenshots(driver, "8_before_performing_Like");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING LIKE ACTION.	
		String likeCount = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		Integer lcount = Integer.parseInt(likeCount.substring(0, 1));
		EventWrapper.wait(driver, 5000);
		ExcelUtils.setCellData("Like count before action: "+likeCount, 8, 2);
		Log.info("Like count before performing Like action = "+likeCount);
		System.out.println("Like count before performing Like action = "+likeCount);
		
		// PERFORMING LIKE ACTION TO THE POST	
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		String newLikesCount = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		Integer lcount1 = Integer.parseInt(newLikesCount.substring(0, 1));
		if(lcount1==lcount+1)
			{
				ExcelUtils.setCellData("Pass", 8, 7);
				Screenshots.takeScreenshots(driver, "8_after_performing_Like_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 8, 7);
				Screenshots.takeScreenshots(driver, "8_after_performing_Like_Fail");
			}
		ExcelUtils.setCellData("Like count after action: "+newLikesCount, 8, 3);
		Log.info("Like count after performing Like action = "+newLikesCount);
		System.out.println("Like count after performing Like action = "+newLikesCount);
		Log.info("Like count before performing Like action = "+likeCount+" Like count after performing Like action = "+newLikesCount);
		System.out.println("Like count before performing Like action = "+likeCount+" Like count after performing Like action = "+newLikesCount);
		
		//PERFORMING UN-LIKE ACTION AND VERIFYING THE NEW LIKE COUNT
		Screenshots.takeScreenshots(driver, "8_before_performing_UnLike");
		ExcelUtils.setCellData("Like count before action: "+newLikesCount, 8, 4);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		String newLikesCount1 = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		Integer lcount2 = Integer.parseInt(newLikesCount.substring(0, 1));
		if(lcount2==lcount1-1)
			{
				ExcelUtils.setCellData("Pass", 8, 7);
				Screenshots.takeScreenshots(driver, "8_after_performing_UnLike_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 8, 7);
				Screenshots.takeScreenshots(driver, "8_after_performing_UnLike_Pass");
			}
		ExcelUtils.setCellData("Like count after action: "+newLikesCount1, 8, 5);
		ExcelUtils.setCellData(EventWrapper.verify(driver, By.id(DiscoverTab_Screen.idlikeCountText()), likeCount), 13, 7);
		Log.info("Like count before performing unLike action = "+newLikesCount);
		System.out.println("Like count before performing unLike action = "+newLikesCount);
		Log.info("Like count before performing unLike action = "+newLikesCount+" Like count after performing unLike action = "+newLikesCount1);
		System.out.println("Like count before performing unLike action = "+newLikesCount+" Like count after performing unLike action = "+newLikesCount1);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 8, 6);
		
		//NAVIGATING BACK TO THE CATEGORIES TYPE
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 5000);
		
		//NAVIGATING BACK TO THE CATERGORIES TAB
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}
}
