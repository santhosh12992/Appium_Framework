package pageObjects;

public class Message_Prompts 
{

	public static String logOutmsgOK() 
	{
		String logOutmsgOK = "//android.widget.Button[@index='1']";
		return logOutmsgOK;
	}

	public static String logOutmsgCancel() 
	{
		String logOutmsgCancel = "android:id/button2";
		return logOutmsgCancel;
	}
	
	public static String idMessageOK() 
	{
		String idMessageOK = "android:id/button1";
		return idMessageOK;
	}
	
	public static String idAllowPermission() 
	{
		String idAllowPermission = "com.android.packageinstaller:id/permission_allow_button";
		return idAllowPermission;
	}
	
	public static String idDenyPermission() 
	{
		String idDenyPermission = "com.android.packageinstaller:id/permission_deny_button";
		return idDenyPermission;
	}
	
	
}
