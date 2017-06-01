package automationFramework;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import pageObjects.Login_Screen;
import Utilities.*;
import appModules.*;


public class Module_TC extends ExtentTestNGITestListener
{

	@BeforeTest
	public void beforeTest() throws Exception 
		{
			
			//CONFIGURING APPLICATION PATH PARAMETERS
			File app = new File(Constant.appPath, Constant.appName); 
	
			//CONFIGURING DEVICE CAPABILITIES
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName","ANDROID");
			capabilities.setCapability("platformVersion", "5.1");
			capabilities.setCapability("platformName",Constant.appPlatform);
			capabilities.setCapability("app", app.getAbsolutePath());
			capabilities.setCapability("appPackage", Constant.appPackage);
			capabilities.setCapability("appActivity",Constant.appActivity);
			capabilities.setCapability("no-reset", "true");
			capabilities.setCapability("full-reset", "False");
			capabilities.setCapability("newCommandTimeout", "100");
			//capabilities.setCapability("appWaitActivity",Constant.appActivity);
	
			//CONFIGURING LOG MACHANISM
			BasicConfigurator.configure();
			Logger OurLogger = LogManager.getLogger("OurLogger");
	
			//create a ConsoleAppender object 
			ConsoleAppender ConsoleAppender = new ConsoleAppender();
			ConsoleAppender.setLayout(new SimpleLayout());
	
			//Add the appender to our Logger Object. from now onwards all the logs will be directed
			//to file mentioned by FileAppender
			OurLogger.addAppender(ConsoleAppender);
			ConsoleAppender.activateOptions();
	
			driver = new AndroidDriver(new URL("http://127.0.0.1:4727/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			DOMConfigurator.configure("log4j.xml");  
	
			//CONFIGURING TEST DATA FETCHING MECHANISM
	
			ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
			Log.info("Test Data is taken from the path"+Constant.Path_TestData+Constant.File_TestData);
			
		}
		
	@BeforeMethod
	public void resetApp()
		{
			driver.resetApp();
			EventWrapper.click(driver, By.name(Login_Screen.skipButton()));	
		}
	
	
	@Test(description="TC001_Verify that user is able to login with Facebook credentials")
	public void TC001() throws Exception
		{
			Log.startTestCase("1. Verify that user is able to login with Facebook credentials");
			System.out.println("1. Verify that user is able to login with Facebook credentials");
			LoginFB_Action.Execute(driver);	
		}

	@Test(description="TC002_Verify that user is able to login with Twitter credentails")
	public void TC002() throws Exception
		{
			Log.startTestCase("2. Verify that user is able to login with Twitter credentails");
			System.out.println("2. Verify that user is able to login with Twitter credentails");
			LoginTW_Action.Execute(driver);	
		}
	
	@Test(description="TC003_Verify that user is able to login with Fontli credentails")
	public void TC003() throws Exception
		{
			Log.startTestCase("3. Verify that user is able to login with Fontli credentails");
			System.out.println("3. Verify that user is able to login with Fontli credentails");
			LoginFontli_Action.Execute(driver);	
		}
	

	@Test(description="TC004_Verify that user is able to see fontli collections under Discover tab")
	public void TC004() throws Exception
		{
			Log.startTestCase("4. Verify that user is able to see fontli collections under Discover tab");
			System.out.println("4. Verify that user is able to see fontli collections under Discover tab");
			DiscovertabAllCategoriesVerify_Action.Execute(driver);
		}
	
	@Test(description="TC005_verify the like and unlike count for a post in Discover tab")
	public void TC005() throws Exception
		{
			Log.startTestCase("5. Verifying Like and Un-Like counts for a post from Discover tab view");
			System.out.println("5. Verifying Like and Un-Like counts for a post from Discover tab view");
			DiscoverTabLikeCountVerify_Action.Execute(driver);
		}
	
	@Test(description="TC006_Verifying Comments counts for a post from Discover tab view")
	public void TC006() throws Exception
		{
			Log.startTestCase("6. Verifying Comments counts for a post from Discover tab view");
			System.out.println("6. Verifying Comments counts for a post from Discover tab view");
			DiscoverTabCommentCountVerify_Action.Execute(driver);
		}
		
	@Test(description="TC007_Verifying Following and unfollowing counts for the logged in user from Discover tab")
	public void TC007() throws Exception
		{
			Log.startTestCase("7. Verifying Following and unfollowing counts for the logged in user from Discover tab");
			System.out.println("7. Verifying Following and unfollowing counts for the logged in user from Discover tab");
			DiscovertabFollowVerify_Action.Execute(driver);
		}
	
	@Test(description="TC008_Verify the Like and Un-Like counts for a post from discover tab")
	public void TC008() throws Exception
		{
			Log.startTestCase("08. Verify the Like and Un-Like counts for a post from discover tab");
			System.out.println("08. Verify the Like and Un-Like counts for a post from discover tab");
			DiscoverTabProfileLikeCountVerify_Action.Execute(driver);
		}
		
	
	@Test(description="TC009_Verify the Following and UnFollowing counts for the logged in user from NewsFeed tab")
	public void TC009() throws Exception
		{
			Log.startTestCase("9. Verifying Following and UnFollowing counts for the logged in user from NewsFeed tab");
			System.out.println("9. Verifying Following and UnFollowing counts for the logged in user from NewsFeed tab");
			NFTabProfileFollowVerify_Action.Execute(driver);
		}
	
	@Test(description="TC010_Verify the Comments counts for the logged in user from NewsFeed tab")
	public void TC010() throws Exception
		{
			Log.startTestCase("10. Verifying Comments counts for the logged in user from NewsFeed tab");
			System.out.println("10. Verifying Comments counts for the logged in user from NewsFeed tab");
			NFTabProfileCommentsCountVerify_Action.Execute(driver);
		}
	
	@Test(description="TC011_Verify that user is able to do new user registration")
	public void TC011() throws Exception
		{
			Log.startTestCase("11. Verify that user is able to do new user registration");
			System.out.println("11. Verify that user is able to do new user registration");
			NewUserRegistration_Action.Execute(driver);
		}
	
/*	
 @Test(description="TC007_Verify the Like and Un-Like counts for a post from Newsfeed tab")
	public void TC007() throws Exception
		{
			//news feed like and unlike
			Log.startTestCase("7. Verify the Like and Un-Like counts for a post from Newsfeed tab");
			NewsFeed_Action.Execute(driver);
		}
		
	@Test(description="TC009_Verify the comment counts for a post in discover tab")
	public void TC009() throws Exception
		{
			Log.startTestCase("9. Verify the comment counts for a post in discover tab");
			DiscoverTabProfileCommentsCountVerify_Action.Execute(driver);
		}
	
	
	
	@Test(description="TC011_Verify the Following and unFollowing counts for the logged in user from Discover tab")
	public void TC011() throws Exception
		{
			Log.startTestCase("11. Verifying Following and unFollowing counts for the logged in user from Discover tab");
			DiscovertabProfileFollowVerify_Action.Execute(driver);
		}
		

	
*/
	
	@Test(description ="TC012_Verify whether a user can add a picture to NewsFeed and delete the same picture from NewsFeed tab")
	public void TC012() throws Exception
		{
			Log.startTestCase("12, Verifying whether a user can add a picture to NewsFeed and delete the same picture from NewsFeed tab");
			System.out.println("12, Verifying whether a user can add a picture to NewsFeed and delete the same picture from NewsFeed tab");
			NFaddPicture_Action.Execute(driver);
		}
		
	@Test(description ="TC013_Verify whether a user can add a picture to Colletions tab using More action and delete the same picture from Collections tab")
	public void TC013() throws Exception
		{
			Log.startTestCase("13. Verifying whether a user can add a picture to Colletions tab using More action and delete the same picture from Collections tab");
			System.out.println("13. Verifying whether a user can add a picture to Colletions tab using More action and delete the same picture from Collections tab");
			CameraPicAddtoTypeCollections_Action.Execute(driver);
		}	
	
	
	@Test(description="TC014_Verify whether a user is able to add a picture to his profile tab and delete the same from Profile tab")
	public void TC014() throws Exception
		{
			Log.startTestCase("14. Verifying whether a user is able to add a picture to his profile tab and delete the same from Profile tab");
			System.out.println("14. Verifying whether a user is able to add a picture to his profile tab and delete the same from Profile tab");
			CameraPicAddtoProfile_Action.Execute(driver);
		}
		
		
	@Test(description="TC015_Verify the search funtionality for people")
	public void TC015() throws Exception
		{
			Log.startTestCase("15.Verifying the result with the searched value");
			System.out.println("15.Verifying the result with the searched value");
			SearchforaUser_Action.Execute(driver);
		}
	
	@Test(description="TC016_Verify the search funtionality for HashTags")
	public void TC016() throws Exception
		{
			Log.startTestCase("16. Verify the search funtionality for HashTag");
			System.out.println("16. Verify the search funtionality for HashTag");
			SearchforHashtag_Action.Execute(driver);
		}
		
	@Test(description="TC017_Verify the search funtionality for Collections")
	public void TC017() throws Exception
		{
			Log.startTestCase("17. Verify the search funtionality for Collections");
			System.out.println("17. Verify the search funtionality for Collections");
			SearchforCollections_Action.Execute(driver);
		}
		
	@Test(description="TC018_Verify that user is able to update his User Name")
	public void TC018() throws Exception
		{
			Log.startTestCase("18. Verify that user is able to update his User Name");
			System.out.println("18. Verify that user is able to update his User Name");
			UpdateUserName_Action.Execute(driver);
		}
	
	@Test(description="TC019_Verify that user is able to update his EmailID")
	public void TC019() throws Exception
		{
			Log.startTestCase("19. Verify that user is able to update his EmailID");
			System.out.println("19. Verify that user is able to update his EmailID");
			UpdateEmailID_Action.Execute(driver);
		}

	@Test(description="TC020_Verify that user is able to update his Password")
	public void TC020() throws Exception
		{
			Log.startTestCase("20. Verify that user is able to update his Password");
			System.out.println("20. Verify that user is able to update his Password");
			UpdatePassword_Action.Execute(driver);
		}


	@Test(description="TC021_Verify app notification when someone follows the user")
	public void TC021() throws Exception
		{
			Log.startTestCase("21. Verify app notification when someone follow the user");
			System.out.println("21. Verify app notification when someone follow the user");
			AppNotificationFollow_Action.Execute(driver);
		}
	
	
	@Test(description="TC022_Verify app notification when someone liked the post of the user")
	public void TC022() throws Exception
		{
			Log.startTestCase("22. Verify app notification when someone liked the post of the user");
			System.out.println("22. Verify app notification when someone liked the post of the user");
			AppNotificationLike_Action.Execute(driver);
		}
	
	
	@Test(description="TC023_Verify app notification when someone commented on the post of the user")
	public void TC023() throws Exception
		{
			Log.startTestCase("23. Verify app notification when someone commented on the post of the user");
			System.out.println("23. Verify app notification when someone commented on the post of the user");
			AppNotificationComment_Action.Execute(driver);
		}
	
	@Test(description="TC024_Verify app notification when someone mentioned the name of the user in comment")
	public void TC024() throws Exception
		{
			Log.startTestCase("24. Verify app notification when someone mentioned the name of the user in comment");
			System.out.println("24. Verify app notification when someone mentioned the name of the user in comment");
			AppNotificationUserMentioned_Action.Execute(driver);
		}
	
	
	@Test(description="TC025_Verify app notification when someone tagged the post of the user with fonts")
	public void TC025() throws Exception
		{
			Log.startTestCase("25. Verify app notification when someone tagged the post of the user with fonts");
			System.out.println("25. Verify app notification when someone tagged the post of the user with fonts");
			AppNotificationTagged_Action.Execute(driver);
		}
	
	@Test(description="TC026_Verify that user is not able to upload spam images")
	public void TC026() throws Exception
		{
			Log.startTestCase("26_Verify that user is not able to upload spam images");
			System.out.println("26_Verify that user is not able to upload spam images");
			PostingSpam_Action.Execute(driver);
		}
		
	  @AfterTest
	  public void tearDown()
	  {
		  driver.quit();
	  }
	

}
