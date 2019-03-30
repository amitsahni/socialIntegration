package fbconnect.controller;

/**
 * Created by amit on 4/2/17.
 */

public class WebEndPoint {
    public static final String USER_ID_KEY = "user_id";
    public static final String ALBUM_ID_KEY = "album_id";


    public static String ACCESS_TOKEN_KEY = "access_token";
    public static String FIELD = "fields";
    public static final String ID = "id";
    public static String BASE = "https://graph.facebook.com/v1.0/";
    public static final String PROFILE = "{" + ID + "}";
    public static final String FRIEND = "{" + ID + "}/friends";
    public static final String ALBUM = "{" + ID + "}/albums";
    public static final String PHOTO = "{" + ID + "}/photos";

    public static int PROFILE_TASK_ID = 1;
    public static int FRIEND_TASK_ID = 2;
    public static int ALBUM_TASK_ID = 3;
    public static int PHOTO_TASK_ID = 4;
}
