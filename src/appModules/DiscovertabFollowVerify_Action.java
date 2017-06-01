package appModules;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.Profile_Settings;
import pageObjects.DiscoverTab_Screen;

public class DiscovertabFollowVerify_Action 
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
	
		//NAVIGATING TO PROFILE TAB TO RECORD TO FOLLOWING COUNT.
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String initialFollowingCount = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		Screenshots.takeScreenshots(driver, "7_before_Following_a_Collection");
		int fCount = Integer.parseInt(initialFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("Initial Following Count: "+fCount, 7, 1);
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
	
		//CLICKING THE FOLLOW BUTTON FOR A CATEGORY	
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameFollowbutton()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameFollowingbutton()), "FOLLOWING");
		Screenshots.takeScreenshots(driver, "7after_Following_a_Collection");
		
		//NAVIGATING BACK TO THE CATEGORIES tab
		EventWrapper.back(driver);
		EventWrapper.wait(driver, 500);
		
		//NAVIGATING TO PROFILE TAB TO VERIFY FOLLOWIG COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		int fCount1 = Integer.parseInt(newFollowingCount.substring(0, 1));
		ExcelUtils.setCellData("New Following Count: "+fCount1, 7, 2);
		ExcelUtils.setCellData("Initial Following Count: "+fCount1, 7, 3);
		Screenshots.takeScreenshots(driver, "7_before_UnFollowing_a_Collection");
		
		//VALIDAING THE FOLLOWING COUNT.
		if(fCount1==fCount+1)
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
			System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is correct value");
			ExcelUtils.setCellData("Pass", 7, 7);
			Screenshots.takeScreenshots(driver, "7_after_Following_a_Collection_Pass");
			}
		else
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
			System.out.println("User "+sUserName+" now has "+newFollowingCount+" Posts. This is the old value and the test case is failing");
			ExcelUtils.setCellData("Fail", 7, 7);
			Screenshots.takeScreenshots(driver, "7_after_Following_a_Collection_Fail");
			}
		assertEquals(fCount1, fCount+1);
		
		//NAVIGATE TO COLLECTION TAB TO UN-FOLLOW THE PREVIOSULY FOLLOWING POST
		EventWrapper.click(driver, Home_Screen.collectionTab(driver));	
		driver.scrollTo("Typefaces");
		EventWrapper.click(driver, By.name(DiscoverTab_Screen.nameTypefaces()));
		Log.info("Navigated to the categories tab TYPEFACES");
		System.out.println("Navigated to the categories tab TYPEFACES");
		
		//CLICKING THE FOLLOWING BUTTON TO UNFOLLOW THE COLLECTION TYPE
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idFollowingbutton()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.verify(driver, By.name(DiscoverTab_Screen.nameFollowbutton()), "FOLLOW");
		Screenshots.takeScreenshots(driver, "7_after_UnFollowing_a_Collection");

		//NAVIGATING TO THE DISCOVER TAB
		EventWrapper.back(driver);
		
		//NAVIGATING TO PROFILE TAB TO VERIFY THE NEW FOLLOWING COUNT
		EventWrapper.click(driver, Home_Screen.profileTab(driver));
		String newFollowingCount1 = driver.findElement(By.id(Profile_Settings.followingCount())).getText();
		Integer fCount2 = Integer.parseInt(newFollowingCount1.substring(0, 1));	
		ExcelUtils.setCellData("New Following Count: "+fCount2, 7, 4);
		
		//VALIDAING THE FOLLOWING COUNT.
		if(fCount1==fCount2-1)
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
			System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is correct value");
			ExcelUtils.setCellData("Pass", 7, 7);
			Screenshots.takeScreenshots(driver, "10_3_after_UnFollowing_a_Collection_Pass");
			}
		else
			{
			Log.info("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
			System.out.println("User "+sUserName+" now has "+newFollowingCount1+" Posts. This is the old value and the test case is failing");
			ExcelUtils.setCellData("Fail", 7, 7);
			Screenshots.takeScreenshots(driver, "10_3_after_UnFollowing_a_Collection_Fail");
			}	
		assertEquals(fCount1, fCount2-1);
		
		//NAVIGATING TO PROFILE TAB AND CLICK ON LOGOUT 	
		Login_Out.Logout(driver);
		
	}


}
