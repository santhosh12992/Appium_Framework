package appModules;

import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import Utilities.Screenshots;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.Message_Prompts;
import pageObjects.Profile_Settings;
import pageObjects.SOSTab_Screen;

public class SOSTabSOSClick_Action 
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
		
		// NAVIGATING TO SOS tab		
		EventWrapper.click(driver, Home_Screen.sosTab(driver));	
		
		//RECORDING THE PROFILE NAME		
		String feedUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		Log.info("Performing SOS Tag  action to the "+feedUsername+ " post");
		System.out.println("Performing SOS Tag action to the "+feedUsername+ " post");	
		ExcelUtils.setCellData("Profile Username: "+feedUsername, 24, 1);
		
		//CLICKING THE SOS BUTTON FOR A POST
		EventWrapper.click(driver, By.id(SOSTab_Screen.idsosButton()));
		EventWrapper.verify(driver, By.id(SOSTab_Screen.idsosTitlebar()), "Spotted Fonts");
		EventWrapper.click(driver, By.id(SOSTab_Screen.idsosButton()));
		EventWrapper.wait(driver, 1000);
		
		
		//NAVIGATING BACK TO SOS TAB
		EventWrapper.back(driver);
		String profileUsername = driver.findElement(By.id(SOSTab_Screen.idfeedUsername())).getText();
		if(feedUsername == profileUsername)
			{
				Log.info("Unable to perform SOS Tag action to "+feedUsername+ "'s post");
				System.out.println("Unable to perform SOS Tag action to "+feedUsername+ "'s post");
				ExcelUtils.setCellData("Pass", 24, 7);
				Screenshots.takeScreenshots(driver, "24_SOSTab__SOSAction_Pass");
			}
		else
			{
				Log.info("Able to perform SOS Tag action to "+feedUsername+ "'s post. This test case is failing");
				System.out.println("Able to perform SOS Tag action to "+feedUsername+ "'s post. This test case is failing");
				ExcelUtils.setCellData("Fail", 24, 7);
				Screenshots.takeScreenshots(driver, "24_SOSTab__SOSAction_Fail");
			}
		
		//NAVIGATING TO PROFILE TAB FOR LOGOUT ACTION
		EventWrapper.click(driver, Home_Screen.profileTab(driver));	
		EventWrapper.click(driver, By.xpath(Home_Screen.settingsButton()));
		driver.scrollTo("Logout");
		EventWrapper.click(driver, By.id(Profile_Settings.logOutButton()));
		EventWrapper.click(driver, By.xpath(Message_Prompts.logOutmsgOK()));
				
	}	
}
