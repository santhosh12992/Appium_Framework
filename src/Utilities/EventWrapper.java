package Utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import Utilities.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;

public class EventWrapper 
{

	public static void click(AppiumDriver driver, By by) 
	{
	    try 
	    	{
	    	
		    	Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)    
		    		    .withTimeout(240, TimeUnit.SECONDS)    
		    		    .pollingEvery(5, TimeUnit.SECONDS)   
		    		    .ignoring(NoSuchElementException.class);
		    	
		        System.out.println("Waiting for the Element");
		    	WebElement element = driver.findElement(by);
		    	wait.until(ExpectedConditions.elementToBeClickable(by));
		        System.out.println("Desired Actionable element found");
		        if(element != null)
			        {
			        	element.click();
				        Log.info("Button"+ element +" Clicked");
			        }
		        else
			        {
			        	Log.info("Button"+ by +" Not Clicked");
				        System.out.println("Not Clicked Button");
			        }
	        
	    	}
	    
	    catch (StaleElementReferenceException sere) 
	    	{
	        // simply retry finding the element in the refreshed DOM
		        //driver.findElement(by).click();
	    		StringBuilder sb = new StringBuilder();
	    		for (StackTraceElement element : sere.getStackTrace()) 
		    		{
				        sb.append(element.toString());
				        sb.append("\n");
		    		}
	    		Log.info(sb.toString());
		        Log.info("Button"+ by +" Not Clicked");
		        System.out.println("Not Clicked Button");
	    	}
	}
	
	public static void click(AppiumDriver driver,WebElement element)
	{
		element.click();
	}

	public static void sendKeys(AppiumDriver driver, By by, String passValue) {
	    try {
	    	
		    	Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)    
		    		    .withTimeout(120, TimeUnit.SECONDS)    
		    		    .pollingEvery(5, TimeUnit.SECONDS)   
		    		    .ignoring(NoSuchElementException.class);
		    	
		        System.out.println("Waiting for the Element");
		    	WebElement element = driver.findElement(by);
		    	wait.until(ExpectedConditions.visibilityOf(element));
		        System.out.println("Desired Actionable element found");
		        if(element != null)
		        {
		        	element.sendKeys(passValue);
			        Log.info("Value "+ passValue +" entered in the Text Area");
		        }
		        else
		        {
			        Log.info("Value "+ passValue +" not entered in the Text Area");
			        System.out.println("Not Entered the value"+passValue+" in Text Area");
		        }
	    	}
	    
	    catch (StaleElementReferenceException sere) 
	    	{
		    	StringBuilder sb = new StringBuilder();
	    		for (StackTraceElement element : sere.getStackTrace()) 
		    		{
				        sb.append(element.toString());
				        sb.append("\n");
		    		}
	    		Log.info(sb.toString());
		        // simply retry finding the element in the refreshed DOM
		        Log.info("Value "+ passValue +" not entered in the Text Area");
		        System.out.println("Not Entered the value"+passValue+" in Text Area");
		        
	    	}
	    	
	}

	
	public static  String verify(AppiumDriver driver, By by, String check) 
	{	
		
    	String element = driver.findElement(by).getText();
    	System.out.println("Actual Element Present is - "+element);
    	String result = null;
		try 
	    	{
	    	
		    	Wait<AppiumDriver> wait = new FluentWait<AppiumDriver>(driver)    
		    		    .withTimeout(120, TimeUnit.SECONDS)    
		    		    .pollingEvery(5, TimeUnit.SECONDS)   
		    		    .ignoring(NoSuchElementException.class);
		    	WebElement toVerify = driver.findElement(by);
		    	wait.until(ExpectedConditions.visibilityOf(toVerify));	    	
		        System.out.println("Verifying the condition given in the test case ");
		    	if(element.equals(check))
			        {
				        System.out.println("Verification of the desired element successful");
				        Log.info("Verification of the desired element successful");
				        result = "Pass";
			        }
		        else
			        {
				        System.out.println("Verification of the desired element is un-successful");
				        Log.info("Verification of the desired element is un-successful");
				        result = "Fail";
			        }
	        
	    	}
	    
	    catch (StaleElementReferenceException sere) 
	    	{
		    	StringBuilder sb = new StringBuilder();
	    		for (StackTraceElement element1 : sere.getStackTrace()) 
		    		{
				        sb.append(element1.toString());
				        sb.append("\n");
		    		}
	    		Log.info(sb.toString());
				System.out.println("Verification of the desired element is un-successful");
				Log.info("Verification of the desired element is un-successful");
				result = "Fail";
			        
	    	}
		return result;
	}
	
	public static void swipe(AppiumDriver driver, By by)
	
		{
			try 
				{
					
					WebElement toVerify = driver.findElement(by);
					Actions actions = new Actions(driver);
					actions.moveToElement(toVerify);
					// actions.click();
					actions.perform();
			    
				 }

			catch (StaleElementReferenceException sere) 
				 {	
					StringBuilder sb = new StringBuilder();
			    	for (StackTraceElement element : sere.getStackTrace()) 
				    	{
							sb.append(element.toString());
						    sb.append("\n");
				    	}
			    	Log.info(sb.toString());
			        Log.info("Sroll un-successfull");
				    System.out.println("Sroll un-successfull");
				}
		}
	
	public static void back(AppiumDriver driver)
	
		{
			try 
				{
			    	driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
					driver.navigate().back();
		        	Log.info("Successfully clicked device back button");
			        System.out.println("Successfully clicked device back button");
			    
				 }

			catch (StaleElementReferenceException sere) 
				{	
		        	
					StringBuilder sb = new StringBuilder();
			    	for (StackTraceElement element : sere.getStackTrace()) 
				    	{
							sb.append(element.toString());
						    sb.append("\n");
				    	}
			    	Log.info(sb.toString());
					Log.info("Device back button is not cliked");
		        	System.out.println("Device back button is not cliked");
				}
		}

	
	public static void wait(AppiumDriver driver, long milliSeconds) throws Exception
	
		{
			try 
				{
					Thread.sleep(milliSeconds);
		        	Log.info("Explicit wait for "+milliSeconds+" milliseconds");
			        System.out.println("Explicit wait for "+milliSeconds+" milliseconds");
			    
				 }

			catch (StaleElementReferenceException sere) 
				{	
					StringBuilder sb = new StringBuilder();
			    	for (StackTraceElement element : sere.getStackTrace()) 
				    	{
							sb.append(element.toString());
						    sb.append("\n");
				    	}
			    	Log.info(sb.toString());
					Log.info("No wait");
			        System.out.println("No wait");
				}
		}
	
	public static void swipeUp(AppiumDriver driver) throws InterruptedException {
		  //Get the size of screen.
		  Dimension  size = driver.manage().window().getSize();
		  System.out.println(size);
		   
		  //Find swipe start and end point from screen's with and height.
		  //Find starty point which is at bottom side of screen.
		  int starty = (int) (size.height * 0.80);
		  //Find endy point which is at top side of screen.
		  int endy = (int) (size.height * 0.65);
		  //Find horizontal point where you wants to swipe. It is in middle of screen width.
		  int startx = size.width / 2;
		  System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		  //Swipe from Bottom to Top.
		  driver.swipe(startx, starty, startx, endy, 1000);
		  Thread.sleep(200);
		  //Swipe from Top to Bottom.
		  //driver.swipe(startx, endy, startx, starty, 3000);
		  //Thread.sleep(2000);
		 }

	public static void swipeDown(AppiumDriver driver) throws InterruptedException 
	{
		Dimension  size = driver.manage().window().getSize();
		System.out.println(size);
	   
		//Find swipe start and end point from screen's with and height.
		//Find starty point which is at bottom side of screen.
		int starty = (int) (size.height * 0.80);
		//Find endy point which is at top side of screen.
		int endy = (int) (size.height * 0.65);
		//Find horizontal point where you wants to swipe. It is in middle of screen width.
		int startx = size.width / 2;
		System.out.println("starty = " + starty + " ,endy = " + endy + " , startx = " + startx);

		//Swipe from Bottom to Top.
		driver.swipe(startx, endy, startx, starty, 1000);
		Thread.sleep(200);
		//Swipe from Top to Bottom.
		//driver.swipe(startx, endy, startx, starty, 3000);
		//Thread.sleep(2000);
		
	}
	
}