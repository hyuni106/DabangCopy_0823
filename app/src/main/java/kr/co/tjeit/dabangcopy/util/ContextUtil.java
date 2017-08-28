package kr.co.tjeit.dabangcopy.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.dabangcopy.datas.User;

/**
 * Created by user on 2017-08-22.
 */

public class ContextUtil {
    //    메모될 파일의 이름 생성
    private final static String prefName = "dabangPref";
//    자동로그인 여부를 저장할때 사용할 태그
    private final static String AUTO_LOGIN = "AUTO_LOGIN";
    private final static String LOGIN_USER_ID = "LOGIN_USER_ID";
    private final static String LOGIN_USER_PW = "LOGIN_USER_PW";
    private final static String LOGIN_USER_NAME = "LOGIN_USER_NAME";
    private final static String LOGIN_USER_PHONE = "LOGIN_USER_PHONE";
    private final static String LOGIN_USER_URL = "LOGIN_USER_URL";

//    getter / setter 는 public으로 열어줌

    public static User user = null;


    public static void setLoginUserInfo(String loginIdStr) {
        user = new User();
        user.setLoginId(loginIdStr);
    }

    public static void setAutoLogin(Context context, boolean isAutoLogin) {
//        1, 메모할 파일을 열어야함
//        메모장을 열어주려면, 반드시 Context 객체를 통해서 열어야함, 화면 필요.
//        메모장 파일 : SharedPreferences
//        접근을 위한 변수 : pref
//        불러오는 방법 : context.getSharedPreferences > 화면을 통해 메모장에 접근
//        불러오는 파일 : prefName
//        공개 여부(모드) : 비공개 > Context.MODE_PRIVATE
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

//        불러낸 메모장에서 저장기능을 불러옴 : pref.edit()
//        어떤 항목을 저장할지 : AUTO_LOGIN (자동로그인 여부)
//        실제로 저장될 데이터 : isAutoLogin
//        저장 확정 : commit()
        pref.edit().putBoolean(AUTO_LOGIN, isAutoLogin).commit();
    }

    public static boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Activity.MODE_PRIVATE);

//        확인한 boolean값을 저장할 변수 : autoLogin
//        메모장에서 boolean값을 빼내는 기능 : pref.getBoolean
//        재료 1 : 메모한 항목의 이름
//        재료 2 : 만약 메모된 기록이 없을 경우 설정값 : 기본세팅 > false
        boolean autoLogin = pref.getBoolean(AUTO_LOGIN, false);
        return autoLogin;
    }

    public static void setLoginUserId(Context context, String loginIdStr) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(LOGIN_USER_ID, loginIdStr).commit();
    }

    public static String getLoginUserId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String id = pref.getString(LOGIN_USER_ID, null);
        return id;
    }

    public static void setLoginUserName(Context context, String loginUserName) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_NAME, loginUserName).commit();
//        .apply()도 가능
    }

    public static String getLoginUserName(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String name = pref.getString(LOGIN_USER_NAME, null);
        return name;
    }

    public static void setLoginUserPhoneNum(Context context, String loginUserPhoneNum) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_PHONE, loginUserPhoneNum).commit();
    }

    public static String getLoginUserPhoneNum(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String name = pref.getString(LOGIN_USER_PHONE, null);
        return name;
    }

//    public static void setLoginUserNickName(Context context, String loginUserNick) {
//        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
//        pref.edit().putString(LOGIN_USER_NICKNAME, loginUserNick).commit();
//    }
//
//    public static String getLoginUserNickName(Context context) {
//        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
//        String name = pref.getString(LOGIN_USER_NICKNAME, null);
//        return name;
//    }

    public static void setLoginUser(Context context, String name, String phoneNum, String id, String profileURL) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_NAME, name).commit();
        pref.edit().putString(LOGIN_USER_PHONE, phoneNum).commit();
        pref.edit().putString(LOGIN_USER_ID, id).commit();
        pref.edit().putString(LOGIN_USER_URL, id).commit();

        user = new User();
    }

    public static User getLoginUser(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if (user != null) {
            user.setName(pref.getString(LOGIN_USER_NAME, ""));
            user.setPhoneNum(pref.getString(LOGIN_USER_PHONE, ""));
            user.setLoginId(pref.getString(LOGIN_USER_ID, ""));
            user.setProfileImageURL(pref.getString(LOGIN_USER_URL, ""));
        }

        return user;
    }

    public static void logoutProcess(Context context) {
        user.setLoginId(null);
    }
}
