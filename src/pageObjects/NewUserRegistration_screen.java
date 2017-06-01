package pageObjects;

public class NewUserRegistration_screen {
	
	public static String backViewButton()
	{
		String backViewButton="com.imaginea.fontli:id/backView";
		return backViewButton;
	}
	
	
	//title text
	public static String titleText(){
		String titleText="com.imaginea.fontli:id/txt_title";
		return titleText;
	}
	
	//user name 
	public static String userName(){
		String userName="com.imaginea.fontli:id/username_editText";
		return userName;
	}
	
	//user mail id
	public static String userMailID(){
		String userMailID="com.imaginea.fontli:id/email_editText";
		return userMailID;
	}
	
	//user password change
	public static String userPassword(){
		String userPassword="com.imaginea.fontli:id/password_editText";
		return userPassword;
	}
	
	public static String confirmPassword(){
		String confirmPassword="com.imaginea.fontli:id/password_confirm_editText";
		return confirmPassword;
	}
	
	
	//alrerady registered user chec box
	public static String loginCheckboxButton(){
		String loginCheckboxButton="com.imaginea.fontli:id/login_check_box";
		return loginCheckboxButton;
	}
	
	//register button
	public static String registerButton(){
		String registerButton="com.imaginea.fontli:id/email_sign_in_button";
		return registerButton;
	}
	
	//terms and conditions text
	public static String termsconditions(){
		String termsconditions="com.imaginea.fontli:id/txt_terms_conditions";
		return termsconditions;
	}

}
