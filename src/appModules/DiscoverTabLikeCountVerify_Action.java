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


public class DiscoverTabLikeCountVerify_Action 
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
		String catName = driver.findElement(By.name(DiscoverTab_Screen.nameTypefaces())).getText();
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab "+catName);
		System.out.println("Navigated to the categories tab "+catName);
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		Log.info("Performing Like action to the "+feedUsername+ " post");
		System.out.println("Performing Like action to the "+feedUsername+ " post");	
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 5, 1);
		
		//SCROLL TO LIKES BUTTON TO PERFORM LIKES COUNT CHECK	
		//EventWrapper.swipe(driver, By.id(categoriesTab_Screen.idlikeCountText()));
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 2000);
		Log.info("Scrolled down to perform Like action");
		System.out.println("Scrolled down to perform Like action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING LIKE ACTION.	
		String likeCount = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		int lcount = Integer.parseInt(likeCount.substring(0, 1));
		ExcelUtils.setCellData("Like count before action: "+likeCount, 5, 2);
		Log.info("Like count before performing Like action = "+likeCount);
		System.out.println("Like count before performing Like action = "+likeCount);
		Screenshots.takeScreenshots(driver, "5_before_Like");
		
		// PERFORMING LIKE ACTION TO THE POST	
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		String newLikesCount = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		int lcount1 = Integer.parseInt(newLikesCount.substring(0, 1));
		if(lcount1==lcount+1)
			{
				Screenshots.takeScreenshots(driver, "5_after_Like_Pass");
				//ExcelUtils.setCellData("Pass", 5, 7);
			}
		else
			{
				ExcelUtils.setCellData("Fail", 5, 7);
				Screenshots.takeScreenshots(driver, "5_after_Like_Fail");
			}
		assertEquals(lcount1, lcount+1);
		ExcelUtils.setCellData("Like count after action: "+lcount1, 5, 3);
		ExcelUtils.setCellData("Like count before action: "+lcount1, 5, 4);
		Log.info("Like count after performing Like action = "+lcount1);
		System.out.println("Like count after performing Like action = "+lcount1);
		Log.info("Like count before performing Like action = "+lcount+" Like count after performing Like action = "+lcount1);
		System.out.println("Like count before performing Like action = "+lcount+" Like count after performing Like action = "+lcount1);
		
		//PERFORMING UN-LIKE ACTION AND VERIFYING THE NEW LIKE COUNT
		Screenshots.takeScreenshots(driver, "5_before_UnLike");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		String newLikesCount1 = driver.findElement(By.id(DiscoverTab_Screen.idlikeCountText())).getText();
		int lcount2 = Integer.parseInt(newLikesCount1.substring(0, 1));
		if(lcount2==lcount1-1)
			{
				Screenshots.takeScreenshots(driver, "5_after_UnLike_Pass");
				ExcelUtils.setCellData("Pass", 5, 7);
			}
		else
			{
				Screenshots.takeScreenshots(driver, "5_after_UnLike_Fail");	
				ExcelUtils.setCellData("Fail", 5, 7);
			}
		assertEquals(lcount2, lcount1-1);
		ExcelUtils.setCellData("Like count after action: "+lcount2, 5, 5);
		Log.info("Like count before performing UnLike action = "+lcount1);
		System.out.println("Like count before performing UnLike action = "+lcount1);
		Log.info("Like count before performing UnLike action = "+lcount1+" Like count after performing UnLike action = "+lcount2);
		System.out.println("Like count before performing UnLike action = "+lcount1+" Like count after performing UnLike action = "+lcount2);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 5, 6);
		
		//NAVIGATING BACK TO THE CATEGORIES TAB
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}

}
