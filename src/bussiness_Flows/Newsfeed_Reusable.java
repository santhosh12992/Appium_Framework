package bussiness_Flows;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import Utilities.EventWrapper;
import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import pageObjects.DiscoverTab_Screen;
import pageObjects.SOSTab_Screen;

public class Newsfeed_Reusable {
	
	/**
	 * Comments on the user pic 
	 * @param driver - Pass the Appium driver
	 * @param message - Pass the message that has to be commented
	 * @throws Exception - Exception for wait
	 */
	
	public static void comment_Pic(AppiumDriver driver, String message) throws Exception
	{
		EventWrapper.swipeUp(driver);
		driver.scrollTo("Comment");
		EventWrapper.wait(driver, 1000);
		Log.info("Scrolled down to perform Comments action");
		System.out.println("Scrolled down to perform Comments action");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 1000);
		EventWrapper.sendKeys(driver, By.id(DiscoverTab_Screen.idcommentTextBox()), "@"+message+" "+"commented");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idpostCommentButton()));
		EventWrapper.wait(driver, 5000);
		EventWrapper.back(driver);
	}

	/**
	 * Performs like action for the first pic in the current page
	 * @param driver - Pass the Appium driver
	 * @throws Exception - Exception for wait
	 */
	public static void like_pic(AppiumDriver driver) throws Exception 
	{
		EventWrapper.swipeUp(driver);
		EventWrapper.swipeUp(driver);
		driver.scrollTo("Like");
		EventWrapper.wait(driver, 1000);
		Log.info("Scrolled down to perform Like action");
		System.out.println("Scrolled down to perform Like action");
		EventWrapper.wait(driver, 1000);
		EventWrapper.click(driver, By.id(SOSTab_Screen.idLikeCheckBox()));
		EventWrapper.wait(driver, 5000);
	}
	
	/**
	 * Tags a picture to a Font family
	 * @TestIn - Page in which the post needed to be tagged appears. For eg.,
	 * NewsFeed page or a user's Profile page
	 * @TestOut - Fontli Home page.
	 * @param driver - Pass the Appium Driver
	 * @throws Exception - Exception for wait
	 */
	public static void tagFont_pic(AppiumDriver driver, String font) throws Exception 
	{
		driver.scrollTo("Comment");
		EventWrapper.wait(driver, 1000);
		Log.info("Scrolled down to perform Tagging action");
		System.out.println("Scrolled down to perform Tagging action");
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idcommentCount()));
		EventWrapper.wait(driver, 1000);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idtagFontButton()));
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idtapPicture()));
		driver.findElement(By.id(DiscoverTab_Screen.idFontNameEditBox())).sendKeys(font);
		driver.findElement(By.id(DiscoverTab_Screen.idTickFont())).sendKeys(Keys.ARROW_DOWN);
		driver.findElement(By.id(DiscoverTab_Screen.idTickFont())).sendKeys(Keys.ENTER);
		EventWrapper.wait(driver, 1000);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idFontSelectorCheckBox()));
		EventWrapper.wait(driver, 4000);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idTickFont()));
		EventWrapper.wait(driver, 2000);
		EventWrapper.click(driver, By.id(DiscoverTab_Screen.idTickFont()));
	    driver.findElement(By.id(DiscoverTab_Screen.idCommentedUserName()));
		driver.navigate().back();
		EventWrapper.wait(driver, 1000);
		driver.navigate().back();
		EventWrapper.wait(driver, 1000);
		driver.navigate().back();
		EventWrapper.wait(driver, 500);
		Log.info("Post has been tagged with a Font successfully");
		System.out.println("Post has been tagged with a Font successfully");
	}

}
