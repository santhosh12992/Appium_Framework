package appModules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import Utilities.ExcelUtils;
import Utilities.Log;
import bussiness_Flows.Login_Out;
import io.appium.java_client.AppiumDriver;
import pageObjects.NewsFeed_Screen;


public class NewsFeed_Action {

	public static void Execute(AppiumDriver driver) throws Exception{
		
		String sUserName = ExcelUtils.getCellData(3, 1);
		Log.info("Username from Excel sheet is "+sUserName);
		String sPassword = ExcelUtils.getCellData(3, 2);
		Log.info("Password from Excel sheet is "+sPassword);
		ExcelUtils.setCellData("Fail", 3, 7);
		
		//LOGIN TO APPLICATION
		Login_Out.LoginMail(driver, sUserName, sPassword);

		//Scroll to Likes Word
		driver.findElement(By.id(NewsFeed_Screen.idFeedUsername()));
		driver.scrollTo("Like");
    
		Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)    
    		    .withTimeout(120, TimeUnit.SECONDS)    
    		    .pollingEvery(5, TimeUnit.SECONDS)   
    		    .ignoring(NoSuchElementException.class);
		
		//getting webelement on likes check box button
		WebElement likesCheckBox = driver.findElement(By.id(NewsFeed_Screen.feedLikeCheckBox()));
		wait.until(ExpectedConditions.elementToBeClickable(likesCheckBox));
		
		//getting likes count
		String actualLikesCount = driver.findElement(By.id(NewsFeed_Screen.feedLikesCount())).getText();
		int endIndex = actualLikesCount.indexOf(' ');
		Log.info("likes count before like :"+actualLikesCount);
		System.out.println("likes count before like :"+actualLikesCount);
	
		
		if(likesCheckBox.getAttribute("checked").contentEquals("true")){
			//unlike if already likes the feed and get count
			
			
			driver.tap(1,likesCheckBox, 3);
			Thread.sleep(10000);
			String newLikesCountAfterUnLike = likesCheckBox.getText();
			int newEndIndex=newLikesCountAfterUnLike.indexOf(' ');
			Log.info("after unlike, likes count is: "+newLikesCountAfterUnLike);
			System.out.println("after unlike, likes count is: "+newLikesCountAfterUnLike);
			ExcelUtils.setCellData("Pass", 3, 7);

			//like the feed if no like and get the count
		}else{

			driver.tap(1,likesCheckBox, 2);
			Thread.sleep(10000);
			String newLikesCountAfterLike = driver.findElement(By.id(NewsFeed_Screen.feedLikesCount())).getText();
			int newEndIndex=newLikesCountAfterLike.indexOf(' ');
			Log.info("after like, new likes count is: "+newLikesCountAfterLike);
			System.out.println("after like, new likes count is: "+newLikesCountAfterLike);
			
		}

		Login_Out.Logout(driver);
		
	}



	

}
