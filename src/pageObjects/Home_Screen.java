package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class Home_Screen 
{
	
	//ALL THE ELEMENTS IN THE HOME SCREEN
	public static String logOutButton_xpath() 
	{
		String logOutButton_xpath = "//android.widget.TextView[@text='Logout']";
		return logOutButton_xpath;
	}
	
	
	public static WebElement homeTab(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.xpath(Home_Screen.menuTab()));
		return list.get(0);
	}
	
	
	public static WebElement collectionTab(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.xpath(Home_Screen.menuTab()));
		return list.get(1);
	}
	
	
	public static WebElement sosTab(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.xpath(Home_Screen.menuTab()));
		return list.get(0);
	}
	
	public static WebElement profileTab(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.xpath(Home_Screen.menuTab()));
		return list.get(2);
	}
	
	public static String noti_id() 
	{
		String noti_id = "com.imaginea.fontli:id/txt_notification";
		return noti_id;
	}
	
	public static String threedots_xpath() 
	{
		String threedots_xpath = "//android.support.v7.widget.LinearLayoutCompat/android.widget.ImageView";
		return threedots_xpath;
	}
	
	public static String menuTab() 
	{
		String menuTab = "//android.widget.HorizontalScrollView[@index='1']//android.widget.ImageView";
		return menuTab;
	}
	
	public static String searchButton() 
	{
		String searchButton = "com.imaginea.fontli:id/img_search";
		return searchButton;
	}
	
	public static String cameraButton() 
	{
		String cameraButton = "com.imaginea.fontli:id/camera_action_button";
		return cameraButton;
	}
	
	//ELEMENTS INSIDE THE PROFILE TAB
	public static String settingsButtonid() 
	{
		String settingsButton = "com.imaginea.fontli:id/img_settings";
		return settingsButton;
	}
	
	public static String settingsButton() 
	{
		String settingsButton = "//android.widget.ImageView[@index ='1']";
		return settingsButton;
	}
	public static String recentSubTab() 
	{
		String recentSubTab = "com.imaginea.fontli:id/recentTextView";
		return recentSubTab;
	}
	
	public static String popularSubTab() 
	{
		String popularSubTab = "com.imaginea.fontli:id/popularTextView";
		return popularSubTab;
	}	
	
}
