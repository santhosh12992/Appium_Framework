package pageObjects;

public class NewsFeed_Screen {


	//post Feed like check box
	public static String feedLikeCheckBox(){
		String feedLikeCheckBox= "com.imaginea.fontli:id/feedLikeCheckBox";
		return feedLikeCheckBox;
	}

	//post feed likes count
	public static String feedLikesCount(){
		String feedLikesCount="com.imaginea.fontli:id/likeCountTextView";
		return feedLikesCount;
	}

	//post Comments view field
	public static String postComments(){	
		String postComments="com.imaginea.fontli:id/commentView";
		return postComments;
	}
	//post comment count field
	public static String postCommentsCount(){
		String postCommentsCount="com.imaginea.fontli:id/commentCount";
		return postCommentsCount;
	}

	//published post options like share
	public static String moreSharingIcons(){
		String moreSharingIcon="com.imaginea.fontli:id";
		return moreSharingIcon;

	}
	
	public static String idFeedUsername()
	{
		String idFeedUsername="com.imaginea.fontli:id/feed_user_name";
		return idFeedUsername;
	}

}
