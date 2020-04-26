package com.wust.mymusic.util;

public class ConstantUtils {

    public final static int PHONE_NUMBER_LENGTH = 11; // China phone number length is 11

    /**
     * HTTP Response
     */
    public final static String LOGIN_RESPONSE_KEY = "com.wust.neteasemusic_login_response_key";


    /**
     * NetEase Music API URL
     */
    public static final String BASE_URL = "http://192.168.0.108:3000";
    public final static String CELLPHONE_API = "/login/cellphone";
    public final static String USER_DETAIL_API = "/user/detail";
    public final static String SEARCH_API = "/search";
    public final static String COMMENT_API = "/comment/music";
    public final static String SEARCH_HOT_API = "/search/hot";
    public final static String SONG_URL_API = "/song/url";
    public final static String SONG_DETAIL_API = "/song/detail";
    public final static String BANNER_API = "/banner";
    public final static String RECOMMEND_SONG_LIST_API = "/recommend/resource";
    public final static String PLAYLIST_DETAIL_API = "/playlist/detail";


    /**
     * HTTP STATUS CODE
     */
    public final static int SUCCESS = 200;
    public final static int ACCOUNT_NOT_EXISTS = 501;
    public final static int INCORRECT_PASSWORD = 502;
    public final static int TRY_PASSWORD_LIMIT = 509; //密码错误超过限制 Password error exceeds limit

    /**
     * HTTP Response
     */
    //public final static String LOGIN_RESPONSE_KEY = "shellhub.github.neteasemusic_login_response_key";

    /**
     * SharedPreferences
     */
    public final static String SP_LOGIN_USER = "com.wust.neteasemusic_login";
    public final static String SP_LOGIN_USER_UID_KEY = "uid";
    public final static String SP_LOGIN_USER_USERNAME_KEY = "username";
    public final static String SP_LOGIN_USER_PASSWORD_KEY = "password";
    public final static String SP_NET_EASE_MUSIC_SETTING = "com.wust.neteasemusic_sp_net_ease_music_setting";
    public final static String SP_NET_EASE_MUSIC_STATUS = "com.wust.neteasemusic_sp_net_ease_music_status";
    public final static String SP_PLAY_TYPE_KEY = "play_type";
    public final static String SP_CURRENT_SONG_ID_KEY = "song_id";
    public final static String SP_CURRENT_SONG_URL_KEY = "song_url";
    public final static String SP_CURRENT_SONG_NAME_KEY = "song_name";
    public final static String SP_CURRENT_SONG_ARTIST_AND_ALBUM_KEY = "song_artist_and_album";
    public final static String SP_CURRENT_SONG_ALBUM_URL_KEY = "song_album";
    public final static String SP_CURRENT_IS_PLAYING_STATUS_KEY = "song_play_status";
    public final static String SP_CURRENT_PLAYLIST_INDEX_KEY = "play_list_index";
    public final static String SP_CURRENT_SEARCH_KEYWORD_KEY = "search_keyword";
    public final static String SP_CURRENT_SEARCH_OFFSET_KEY = "search_offset";
}
