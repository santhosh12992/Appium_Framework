package Utilities;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

public  class Screenshots
{
	
	Dimension size;
	static String destDir;
	static DateFormat dateFormat;
	
	public static String takeScreenshots(AppiumDriver driver, String Scenario) throws Exception
	{	  
		Path path = null;
		destDir = Constant.directoryPath+Constant.FolderDirectory;
		new File(destDir).mkdirs();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File targetFile=new File(destDir +"//" +Scenario +".jpeg");
		try 
		  {
		   path = Files.copy(scrFile.toPath() , targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		   System.out.println(path.toString());
		  } 
		catch (IOException e) 
		  {
		   e.printStackTrace();
		  }
	 
		return path.toString();
	}
	

	

}
