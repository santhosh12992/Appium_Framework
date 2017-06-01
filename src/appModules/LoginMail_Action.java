package appModules;

import org.openqa.selenium.By;
import Utilities.EventWrapper;
import Utilities.ExcelUtils;
import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import pageObjects.Home_Screen;
import pageObjects.Login_Screen;
import pageObjects.Message_Prompts;
import pageObjects.Profile_Settings;

public class LoginMail_Action {
	public static void Execute(AppiumDriver driver) throws Exception {

		// This is to get the values from Excel sheet, passing parameters (Row
		// num &amp; Col num)to getCellData method
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
			
		EventWrapper.click(driver, By.name(Login_Screen.skipButton()));	
		EventWrapper.click(driver, By.name(Login_Screen.emailButton()));
		EventWrapper.click(driver, By.id(Login_Screen.existingUserCheckBox()));
		EventWrapper.sendKeys(driver, By.id(Login_Screen.emailuserName()), sUserName);
		EventWrapper.sendKeys(driver, By.id(Login_Screen.emailpassWord()), sPassword);
		driver.hideKeyboard();
		Log.info("Keyboard is hidden expicitly by the user");	
		EventWrapper.click(driver, By.id(Login_Screen.emailloginButton()));	
		EventWrapper.click(driver, Home_Screen.profileTab(driver));	
		EventWrapper.click(driver, By.xpath(Home_Screen.settingsButton()));
		driver.scrollTo("Logout");
		EventWrapper.click(driver, By.id(Profile_Settings.logOutButton()));	
		EventWrapper.click(driver, By.xpath(Message_Prompts.logOutmsgOK()));
		ExcelUtils.setCellData("Pass", 3, 7);
		
	}

}
