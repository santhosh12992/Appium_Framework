package appModules;


import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.SOSTab_Screen;

public class SOSTabLikeCountVerify_Action 
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
		Log.info("Performing Like action to the "+feedUsername+ " post");
		System.out.println("Performing Like action to the "+feedUsername+ " post");	
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 16, 1);
		
		//SCROLL TO LIKES BUTTON TO PERFORM LIKES COUNT CHECK	
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 2000);
		Screenshots.takeScreenshots(driver, "16_1_before_Liking_a_Post");
		Log.info("Scrolled down to perform Like action");
		System.out.println("Scrolled down to perform Like action");
		
		//RECORDING THE LIKES COUNT BEFORE PERFORMING LIKE ACTION.	
		String likeCount = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount = Integer.parseInt(likeCount.substring(0, 1));
		ExcelUtils.setCellData("Like count before Like action: "+likeCount, 16, 2);
		Log.info("Like count before performing Like action = "+likeCount);
		System.out.println("Like count before performing Like action = "+likeCount);
		
		// PERFORMING LIKE ACTION TO THE POST	
		EventWrapper.click(driver, By.id(SOSTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
		Screenshots.takeScreenshots(driver, "17_1_before_UnLiking_a_Post");
		String newLikesCount = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount1 = Integer.parseInt(newLikesCount.substring(0, 1));
		if(lcount1==lcount+1 || lcount1==lcount-1 )
			{
				ExcelUtils.setCellData("Pass", 16, 7);
				Screenshots.takeScreenshots(driver, "16_2_after_Liking_a_Post_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 16, 7);
				Screenshots.takeScreenshots(driver, "16_2_after_Liking_a_Post_Fail");
			}
		ExcelUtils.setCellData("Like count after Like action: "+lcount1, 16, 3);
		ExcelUtils.setCellData("Like count before UnLike action: "+lcount1, 17, 2);
		Log.info("Like count after performing Like action = "+lcount1);
		System.out.println("Like count after performing Like action = "+lcount1);
		Log.info("Like count before performing Like action = "+lcount+" Like count after performing Like action = "+lcount1);
		System.out.println("Like count before performing Like action = "+lcount+" Like count after performing Like action = "+lcount1);
		
		//PERFORMING UN-LIKE ACTION AND VERIFYING THE NEW LIKE COUNT
		EventWrapper.click(driver, By.id(SOSTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 3000);
		String newLikesCount1 = driver.findElement(By.id(SOSTab_Screen.idlikeCountText())).getText();
		Integer lcount2 = Integer.parseInt(newLikesCount1.substring(0, 1));
		if(lcount2==lcount1-1)
			{
				ExcelUtils.setCellData("Pass", 17, 7);
				Screenshots.takeScreenshots(driver, "17_2_after_UnLiking_a_Post_Pass");
			}
		else
			{
				ExcelUtils.setCellData("Fail", 17, 7);
				Screenshots.takeScreenshots(driver, "17_2_after_UnLiking_a_Post_Fail");
			}
		ExcelUtils.setCellData("Like count after Unlike action: "+lcount2, 17, 3);
		Log.info("Like count before performing UnLike action = "+lcount1);
		System.out.println("Like count before performing UnLike action = "+lcount1);
		Log.info("Like count before performing UnLike action = "+lcount1+" Like count after performing UnLike action = "+lcount2);
		System.out.println("Like count before performing UnLike action = "+lcount1+" Like count after performing UnLike action = "+lcount2);
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 17, 1);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 
		Login_Out.Logout(driver);
		
	}
}
