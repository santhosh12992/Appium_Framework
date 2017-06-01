package pageObjects;

// Objects in Login screen are defined as Strings

public class Login_Screen {
	// Initializing all the WebElements in Home Screen

	public static String skipButton() 
	{
		String skipButton = "SKIP";
		return skipButton;
	}
	
	// CONFIGURING ELEMENTS FOR FB LOGIN
	
	public static String fbButton() 
	{
		String fbButton = "Connect with Facebook";
		return fbButton;
	}
	
	public static String fbuserName() 
	{
		String fbuserName = "//android.widget.EditText[@index='2']";
		//String fbuserName = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[3]/android.view.View[3]/android.widget.EditText[1]";
		return fbuserName;
	}
	
	public static String fbpassWord() 
	{	
		String fbpassWord = "//android.widget.EditText[@index='3']";
		//String fbpassWord = "//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/android.view.View[3]/android.view.View[3]/android.widget.EditText[2]";
		return fbpassWord;
	}

	public static String fbloginButton() 
	{
		String fbloginButton = "//android.widget.Button[@content-desc ='Log In']";
		return fbloginButton;
	}
	
	public static String fbloginConfirmButton() 
	{
		String fbloginConfirmButton = "//android.widget.Button[@content-desc ='Continue']";
		return fbloginConfirmButton;
	}

	// CONFIGURING ELEMENTS FOR TWITTER LOGIN
	
	public static String twButton() 
	{
		String twButton = "Connect with Twitter";
		return twButton;
	}
	
	public static String twuserName() 
	{
		String twuserName = "//android.widget.EditText[@index='0']";
		return twuserName;
	}
	
	public static String twpassWord() 
	{
		String twpassWord = "//android.view.View[@index='3']//child::android.widget.EditText";
		return twpassWord;
	}

	public static String twloginButton() 
	{
		String fbloginButton = "//android.widget.Button[@index='0']";
		return fbloginButton;
	}

	public static String twloginCancel() 
	{
		String twloginCancel = "//android.widget.Button[@index='1']";
		return twloginCancel;
	}
	
	// CONFIGURING ELEMENTS FOR EMAIL LOGIN
	
	public static String emailButton() 
	{
		String mailButton = "Connect with Mail";
		return mailButton;
	}
	
	public static String existingUserCheckBox(){
		String existingUserCheckBox = "com.imaginea.fontli:id/login_check_box";
		return existingUserCheckBox;
	}
	
	public static String emailuserName() 
	{
		String emailuserName = "com.imaginea.fontli:id/username_editText";
		return emailuserName;
	}
	
	public static String emailpassWord() 
	{
		String emailpassWord = "com.imaginea.fontli:id/password_editText";
		return emailpassWord;
	}
	
	public static String emailloginButton() 
	{
		String emailloginButton = "com.imaginea.fontli:id/email_sign_in_button";
		return emailloginButton;
	}
}
