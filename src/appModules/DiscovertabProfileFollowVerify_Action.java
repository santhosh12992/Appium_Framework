package appModules;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.DiscoverTab_Screen;
import pageObjects.Home_Screen;
import pageObjects.Profile_Settings;

public class DiscovertabProfileFollowVerify_Action 
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
		Screenshots.takeScreenshots(driver, "14_1_before_Following_a_Profile");
		Integer fCount = Integer.parseInt(initialFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("Initial Following Count: "+fCount, 14, 2);
		System.out.println("Total count of Following = "+fCount);
		Log.info("User "+sUserName+" has "+initialFollowingCount+" Posts");
		System.out.println("User "+sUserName+" has "+initialFollowingCount+" Posts");
		 
		// NAVIGATING TO THE COLLECTION CATEGORY - TYPEFACES		
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));
		//String catName = driver.findElement(By.xpath(DiscoverTab_Screen.xTypefaces())).getText();
		driver.scrollTo("Typefaces");
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab TYPEFACES");
		System.out.println("Navigated to the categories tab TYPEFACES");
		
		//NAVIGATING TO A PARTICULAR PROFILE USER
		String feedUsername = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Profile User: "+feedUsername, 14, 1);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername);
		System.out.println("Navigated to the profile user "+feedUsername);				
	
		//CLICKING THE FOLLOW BUTTON TO FOLLOW A PARTICULAR USER
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameFollowbutton()));
		EventWrapper.wait(driver, 2000);
		Screenshots.takeScreenshots(driver, "14_2_during_Following_a_Profile");
		EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameFollowingbutton()), "FOLLOWING");
		
		//NAVIGATING BACK TO THE CATEGORIES TYPE
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 500);
		
		//NAVIGATING BACK TO THE CATEGORIES TAB
		EventWrapper.back(driver);
				
		
		//NAVIGATING TO PROFILE TAB TO VERIFY FOLLOWIG COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		Integer fCount1 = Integer.parseInt(newFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("New Following Count: "+fCount1, 14, 3);
		ExcelUtils.setCellData("Initial Following Count: "+fCount1, 15, 2);
		Screenshots.takeScreenshots(driver, "15_1_before_UnFollowing_a_Profile");
		
		//VALIDAING THE FOLLOWING COUNT.
		if(fCount1==fCount+1)
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
			System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
			ExcelUtils.setCellData("Pass", 14, 7);
			Screenshots.takeScreenshots(driver, "14_3_after_Following_a_Profile_Pass");
			}
		else
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
			System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
			ExcelUtils.setCellData("Fail", 14, 7);
			Screenshots.takeScreenshots(driver, "14_3_after_Following_a_Profile_Fail");
			}
		
		
		//NAVIGATE TO COLLECTION TAB 
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));
		//String catName1 = driver.findElement(By.name(DiscoverTab_Screen.xTypefaces())).getText();
		driver.scrollTo("Typefaces");
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab TYPEFACES");
		System.out.println("Navigated to the categories tab TYPEFACES");
		
		//NAVIGATING TO A PARTICULAR PROFILE USER
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		String feedUsername1 = driver.findElement(By.id(DiscoverTab_Screen.idfeedUsername())).getText();
		ExcelUtils.setCellData("Profile User: "+feedUsername1, 15, 1);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idfeedUsername()));
		Log.info("Navigated to the profile user "+feedUsername1);
		System.out.println("Navigated to the profile user "+feedUsername1);
		
		//NAVIGATE TO PROFILE USER TO UN-FOLLOW THE PREVIOSULY FOLLOWING POST
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameFollowingbutton()));
		EventWrapper.wait(driver, 2000);
		Screenshots.takeScreenshots(driver, "15_2_during_UnFollowing_a_Profile");
		EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameFollowbutton()), "FOLLOW");	

		//NAVIGATING TO THE DISCOVER TAB TYPE
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 500);
		
		//NAVIGATING TO THE DISCOVER TAB
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB TO VERIFY THE NEW FOLLOWING COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount1 = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		Integer fCount2 = Integer.parseInt(newFollowingCount1.substring(0, 1));	
		ExcelUtils.setCellData("New Following Count: "+fCount2, 15, 3);
		
		//VALIDAING THE FOLLOWING COUNT.
				if(fCount2==fCount1-1)
					{
					Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
					System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
					ExcelUtils.setCellData("Pass", 15, 7);
					Screenshots.takeScreenshots(driver, "15_3_after_UnFollowing_a_Profile_Pass");
					}
				else
					{
					Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
					System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
					ExcelUtils.setCellData("Fail", 15, 7);
					Screenshots.takeScreenshots(driver, "15_3_after_UnFollowing_a_Profile_Fail");
					}	
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 	
		Login_Out.Logout(driver);
		
	}

}