package pageObjects;

public class Profile_Settings 
{

	public static String saveButton() 
	{
		String saveButton = "com.imaginea.fontli:id/action_save";
		return saveButton;
	}
	
	public static String xsaveButton() 
	{
		String xsaveButton = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.TextView[1]";
		return xsaveButton;
	}
	
	public static String accountnameText() 
	{
		String accountName = "com.imaginea.fontli:id/edt_full_name";
		return accountName;
	}
	
	
	public static String accountemailText() 
	{
		String accountemail = "com.imaginea.fontli:id/edt_email";
		return accountemail;
	}
	
	public static String aboutOption() 
	{
		String aboutOption = "com.imaginea.fontli:id/txt_about";
		return aboutOption;
	}
	
		
	
	public static String followingCount() 
	{
		String followingCount = "com.imaginea.fontli:id/followingCount";
		return followingCount;
	}	
	
	public static String followersCount() 
	{
		String followersCount = "com.imaginea.fontli:id/followerCount";
		return followersCount;
	}	

	//	CHANGE PASSWORD DETAILS
	
	public static String changePassword(){
		String changePassword="com.imaginea.fontli:id/txt_change_password";
		return changePassword;
	}
	
	public static String currentPassword(){
		String currentPassword ="com.imaginea.fontli:id/edt_current_password";
		return currentPassword;
	}
	
	public static String newPassword(){
		String newPassword = "com.imaginea.fontli:id/edt_new_password";
		return newPassword;
	}
	
	public static String reTypePassword(){
		String reTypePassword = "com.imaginea.fontli:id/edt_retype_password";
		return reTypePassword;
	}
}
