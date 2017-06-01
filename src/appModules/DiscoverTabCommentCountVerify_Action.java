package appModules;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;

import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.DiscoverTab_Screen;
import pageObjects.Home_Screen;

public class DiscoverTabCommentCountVerify_Action 
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
		
		
		// NAVIGATING TO THE COLLECTION CATEGORY - TYPEFACES		
		EventWrapper.click(driver,Home_Screen.collectionTab(driver));
		driver.scrollTo("Typefaces");
		String catName = driver.findElement(By.name(DiscoverTab_Screen.nameTypefaces())).getText();
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab "+catName);
		System.out.println("Navigated to the categories tab "+catName);
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Feed Username: "+feedUsername, 6, 1);

		//SCROLL TO COMMENTS BUTTON TO COMMENT ON THE POST
		//EventWrapper.swipe(driver, By.id(categoriesTab_Screen.idlikeCountText()));
		driver.scrollTo("Comment");
		EventWrapper.wait(driver, 5000);
		Log.info("Scrolled down to perform Comments action");
		System.out.println("Scrolled down to perform Comments action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING COMMENT ACTION.	
		String commentCount = driver.findElement(By.id(DiscoverTab_Screen.idcommentCount())).getText();
		int cCount = Integer.parseInt(commentCount.substring(0, 2).trim());
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "6_before_Comments");
		ExcelUtils.setCellData("Comment count before action: "+commentCount, 6, 2);
		Log.info("Comment count before performing comment action = "+commentCount);
		System.out.println("Comment count before performing Comment action = "+commentCount);
		
		// PERFORMING COMMENT ACTION TO THE POST	
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 5000);
		EventWrapper.sendKeys(driver, By.id(DiscoverTab_Screen.idcommentTextBox()), "Comments Entered");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idpostCommentButton()));
		EventWrapper.wait(driver, 15000);
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 10000);
		String newCommentsCount = driver.findElement(By.id(DiscoverTab_Screen.idcommentCount())).getText();
		int cCount1 = Integer.parseInt(newCommentsCount.substring(0, 2).trim());
		ExcelUtils.setCellData("Comment count after action: "+newCommentsCount, 6, 3);
		Log.info("Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count after performing Comment action = "+newCommentsCount);
		Log.info("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		System.out.println("Comment count before performing Comment action = "+commentCount+" Comment count after performing Comment action = "+newCommentsCount);
		
		//VERIFYING THE NEW Comments COUNT
		if(cCount1==cCount+1)
			{
				Screenshots.takeScreenshots(driver, "6_after_Comments_Pass");	
				ExcelUtils.setCellData("Pass", 6, 7);
			}
		else
			{
				ExcelUtils.setCellData("Fail", 6, 7);
				Screenshots.takeScreenshots(driver, "6_after_Comments_Fail");	
			}
		assertEquals(cCount1,cCount+1);
		//NAVIGATING BACK TO THE CATEGORIES TAB
		EventWrapper.back(driver);
				
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}

}
