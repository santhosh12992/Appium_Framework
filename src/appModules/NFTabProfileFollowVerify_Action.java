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
import pageObjects.Profile_Settings;

public class NFTabProfileFollowVerify_Action 
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
		
		//NAVIGATING TO PROFILE TAB TO RECORD TO FOLLOWING COUNT.
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String initialFollowingCount = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		Screenshots.takeScreenshots(driver, "9_before_Following_a_Profile_from_Newsfeed");
		int fCount = Integer.parseInt(initialFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("Initial Following Count: "+fCount, 9, 2);
		System.out.println("Total count of Following = "+fCount);
		Log.info("User "+sUserName+" has "+initialFollowingCount+" Posts");
		System.out.println("User "+sUserName+" has "+initialFollowingCount+" Posts");
		 
		// NAVIGATING TO SOS tab		
		EventWrapper.click(driver, Home_Screen.sosTab(driver));
		
		//NAVIGATING TO A PARTICULAR PROFILE USER
		String feedUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Profile User: "+feedUsername, 9, 1);
		EventWrapper.click(driver, By.id(SOSTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);				
	
		//CLICKING THE FOLLOW BUTTON TO FOLLOW A PARTICULAR USER
		EventWrapper.click(driver, By.name(SOSTab_Screen.nameFollowbutton()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.verify(driver, By.name(SOSTab_Screen.nameFollowingbutton()), "FOLLOWING");
		Screenshots.takeScreenshots(driver, "9_during_Following_a_Profile_from_Newsfeed");
		
		//NAVIGATING BACK TO THE SOS TAB
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 3000);		
		
		//NAVIGATING TO PROFILE TAB TO VERIFY FOLLOWIG COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		int fCount1 = Integer.parseInt(newFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("New Following Count: "+fCount1, 9, 3);
		ExcelUtils.setCellData("Initial Following Count: "+fCount1, 9, 4);
		Screenshots.takeScreenshots(driver, "9_before_UnFollowing_a_Profile_from_Newsfeed");
		
		//VALIDAING THE FOLLOWING COUNT.
		if(fCount1==fCount+1)
			{
				Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
				System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
				ExcelUtils.setCellData("Pass", 9, 7);
				Screenshots.takeScreenshots(driver, "9_after_Following_a_Profile_from_Newsfeed_Pass");
			}
		else
			{
				Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
				System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
				ExcelUtils.setCellData("Fail", 9, 7);
				Screenshots.takeScreenshots(driver, "9_3_after_Following_a_Profile_from_Newsfeed_Fail");
			}
		
		assertEquals(fCount1, fCount+1);
		//NAVIGATE TO SOS TAB 
		EventWrapper.click(driver, Home_Screen.sosTab(driver));
		
		//NAVIGATING TO A PARTICULAR PROFILE USER
		String feedUsername1 = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Profile User: "+feedUsername1, 9, 5);
		EventWrapper.click(driver, By.id(SOSTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername1);
		System.out.println("Navigated to the profile user "+feedUsername1);
		
		//NAVIGATE TO PROFILE USER TO UN-FOLLOW THE PREVIOSULY FOLLOWING POST
		EventWrapper.click(driver, By.name(SOSTab_Screen.nameFollowingbutton()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.verify(driver, By.name(SOSTab_Screen.nameFollowbutton()), "FOLLOW");
		Screenshots.takeScreenshots(driver, "9_during_UnFollowing_a_Profile_from_Newsfeed");

		//NAVIGATING TO THE SOS TAB TYPE
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB TO VERIFY THE NEW FOLLOWING COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount1 = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		int fCount2 = Integer.parseInt(newFollowingCount1.substring(0, 1));	
		ExcelUtils.setCellData("New Following Count: "+fCount2, 9, 6);
		
		//VALIDAING THE FOLLOWING COUNT.
				if(fCount2==fCount1-1)
					{
						Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
						System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
						ExcelUtils.setCellData("Pass", 9, 7);
						Screenshots.takeScreenshots(driver, "9_after_UnFollowing_a_Profile_from_Newsfeed_Pass");
					}
				else
					{
						Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
						System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
						ExcelUtils.setCellData("Fail", 9, 7);
						Screenshots.takeScreenshots(driver, "9_after_UnFollowing_a_Profile_from_Newsfeed_Fail");
					}	
		assertEquals(fCount2, fCount1-1);
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 	
		Login_Out.Logout(driver);
		
	}

}
