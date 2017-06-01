package pageObjects;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class Camera_Screen 
{

	public static String idCameraButton() 
	{
		String idCameraButton = "com.imaginea.fontli:id/camera_action_button";
		return idCameraButton;
	}

	public static String idCameraClickButton() 
	{
		String idCameraClickButton = "com.imaginea.fontli:id/img_camera";
		return idCameraClickButton;
	}

	public static String idPublishOK() 
	{
		String idPublishOK = "com.imaginea.fontli:id/action_publish";
		return idPublishOK;
	}
	public static String xPublishCancel() 
	{
		String xPublishCancel = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.view.ViewGroup[1]/android.widget.ImageButton[1]";
		return xPublishCancel;
	}	

	public static String namePublished() 
	{
		String namePublished = "Published";
		return namePublished;
	}
	
	public static String idAddtoCollection() 
	{
		String idAddtoCollection = "com.imaginea.fontli:id/addCollection";
		return idAddtoCollection;
	}
	
	public static String idActionSelectOK() 
	{
		String idActionSelectOK = "com.imaginea.fontli:id/action_select_font";
		return idActionSelectOK;
	}
	
	public static String idNeedSOSCheckbox() 
	{
		String idNeedSOSCheckbox = "com.imaginea.fontli:id/sosCheckBoxText";
		return idNeedSOSCheckbox;
	}	
	public static String idFrontCamera() 
	{
		String idFrontCamera = "com.imaginea.fontli:id/img_switch_camera";
		return idFrontCamera;
	}
	
	public static String idOpenGallery() 
	{
		String idOpenCamera = "com.imaginea.fontli:id/img_open_gallery";
		return idOpenCamera;
	}
	
	public static String idCroped() 
	{
		String idOpenCamera = "com.imaginea.fontli:id/btn_done";
		return idOpenCamera;
	}
	
	private static String idImages()
	{
		String idImages = "com.android.documentsui:id/icon_thumb";
		return idImages;
	}
	
	public static WebElement non_SpamImage(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.id(Camera_Screen.idImages()));
		boolean flag = new Random().nextBoolean();
		if(flag) return list.get(2);
		else return list.get(3);
	}
	
	public static WebElement spamImage(AppiumDriver driver) 
	{
		List<WebElement> list = driver.findElements(By.id(Camera_Screen.idImages()));
		boolean flag = new Random().nextBoolean();
		if(flag) return list.get(1);
		else return list.get(0);
	}
	
	public static String idImageFlash() 
	{
		String idImageFlash = "com.imaginea.fontli:id/img_flash_mode_button";
		return idImageFlash;
	}

	public static String idCaption() {
		String idCaption = "com.imaginea.fontli:id/caption_text";
		return idCaption;
	}
	
}
