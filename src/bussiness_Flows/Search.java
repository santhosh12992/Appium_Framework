package bussiness_Flows;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import Utilities.EventWrapper;
import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import pageObjects.Search_Screen;

public class Search {
	
	/**
	 * Searches and naviagates to the particular user page
	 * @param driver - Pass the Appium Driver
	 * @param username - Pass the username to be searched and visited
	 */
	
	public static void find_User(AppiumDriver driver, String username)
	{
		EventWrapper.click(driver, By.id(Search_Screen.idSearchButton()));
		EventWrapper.sendKeys(driver, By.id(Search_Screen.idSearchString()), username);
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		EventWrapper.click(driver, By.id(Search_Screen.idUserResult()));
		driver.findElement(By.id(Search_Screen.idProfileName()));
	}
	
	/**
	 * Searches the given string against the Fontli users and validates the result
	 * @param driver - Pass Appium Driver
	 * @param Searchvalue - Pass the String key
	 */
	
	public static void search_User(AppiumDriver driver, String Searchvalue)
	{
		EventWrapper.click(driver, By.id(Search_Screen.idSearchButton()));
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		EventWrapper.sendKeys(driver, By.id(Search_Screen.idSearchString()), Searchvalue);
		EventWrapper.click(driver, By.name(Search_Screen.nameCollectionTab()));
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		List<WebElement> weekObjectList = driver.findElements(By.xpath("//android.widget.ListView//android.widget.RelativeLayout"));
		Integer count = weekObjectList.size();
		System.out.println("Total People found with the given search string "+Searchvalue+" is "+count);
		Log.info("Total People found with the given search string "+Searchvalue+" is "+count);
		for(int i =0;i<count;i++)
		{
			String  fullName = weekObjectList.get(i).findElement(By.id(Search_Screen.idUserFullName())).getText();
			if(fullName.toLowerCase().contains(Searchvalue.toLowerCase()))
			{
				Log.info("Verifying Full User Name in the Result "+fullName+" with the Searched values "+ Searchvalue);
				System.out.println("Verifying Full User Name in the Result "+fullName+" with the Searched values "+ Searchvalue);
			}
			else
			{
				System.out.println(fullName +"doesnt match with"+Searchvalue);
				Log.info(fullName +"doesnt match with"+Searchvalue);
				assertEquals(true, false);
			}
		}
	
	}
	
	/**
	 * Searches the given string against the Hashtags and validates the result
	 * @param driver - Pass Appium Driver
	 * @param Searchvalue - Pass the String key
	 */
	
	public static void search_Hashtag(AppiumDriver driver, String Searchvalue)
	{
		EventWrapper.click(driver, By.id(Search_Screen.idSearchButton()));
		EventWrapper.sendKeys(driver, By.id(Search_Screen.idSearchString()), Searchvalue);
		driver.hideKeyboard();
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		EventWrapper.click(driver, By.id(Search_Screen.idHashtagsTab()));
		List<WebElement> weekObjectList = driver.findElements(By.xpath("//android.widget.ListView//android.widget.RelativeLayout"));	
		Integer count = weekObjectList.size();
		System.out.println("Total HashTag found with the given search string "+Searchvalue+" is "+count);
		Log.info("Total HashTag found with the given search string "+Searchvalue+" is "+count);
		for(int i =0;i<count;i++)
		{
			String  fullName = weekObjectList.get(i).findElement(By.id(Search_Screen.idHashtagValue())).getText();
			if(fullName.toLowerCase().contains(Searchvalue.toLowerCase()))
			{
				Log.info("Verifying HashTag in the Result "+fullName+" with the Searched values "+ Searchvalue);
				System.out.println("Verifying HashTag in the Result "+fullName+" with the Searched values "+ Searchvalue);
			}
			else
			{
				System.out.println(fullName +"doesnt match with"+Searchvalue);
				Log.info(fullName +"doesnt match with"+Searchvalue);
				assertEquals(true, false);
			}
		}
	}
	
	/**
	 * Searches the given string against the Collections and validates the result
	 * @param driver - Pass Appium Driver
	 * @param Searchvalue - Pass the String key
	 */
	
	public static void search_Collection(AppiumDriver driver, String Searchvalue) {
		EventWrapper.click(driver, By.id(Search_Screen.idSearchButton()));
		EventWrapper.sendKeys(driver, By.id(Search_Screen.idSearchString()), Searchvalue);
		driver.hideKeyboard();
		EventWrapper.click(driver, By.id(Search_Screen.idPeopleTab()));
		EventWrapper.click(driver, By.name(Search_Screen.nameCollectionTab()));
		List<WebElement> weekObjectList = driver.findElements(By.xpath("//android.widget.GridView//android.widget.RelativeLayout"));		
		int count = weekObjectList.size();
		System.out.println("Total Collections found with the given search string "+Searchvalue+" is "+count);
		Log.info("Total Collections found with the given search string "+Searchvalue+" is "+count);
		for(int i =0;i<count;i++)
		{
			String  fullName = weekObjectList.get(i).findElement(By.id(Search_Screen.idCollectionName())).getText();
			if(fullName.toLowerCase().contains(Searchvalue.toLowerCase()))
			{
				Log.info("Verifying Collections in the Result "+fullName+" with the Searched values "+ Searchvalue);
				System.out.println("Verifying Collections in the Result "+fullName+" with the Searched values "+ Searchvalue);
			}
			else
			{
				System.out.println(fullName +"doesnt match with"+Searchvalue);
				Log.info(fullName +"doesnt match with"+Searchvalue);
				assertEquals(true, false);
			}
		}
		
	}

}
