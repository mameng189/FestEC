package com.matt.latter.ec.sign;
import com.matt.latter.app.AccountManager;
import com.matt.latter.ec.database.DatabaseManager;
import com.matt.latter.ec.database.UserProfile;
import com.matt.latter.util.log.LatteLogger;

/**
 * Created by mamen on 2017/9/23.
 */

public class SignHandler {
    //注册
    public static void onSignUp(String response,ISignListener iSignListener){
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final  long userId = profileJson.getLong("userId");
//        final  String name = profileJson.getString("name");
//        final  String avatar = profileJson.getString("avatar");
//        final  String gender = profileJson.getString("gender");
//        final  String address = profileJson.getString("address");
//        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        final UserProfile profile = new UserProfile(123312456,"注册","mmmmsdf","男","郑州");
        //将解析到的数据插入到数据库里
        DatabaseManager.getInstance().getDao().insert(profile);
        LatteLogger.v("insertdb",profile.toString());
        //保存用户状态
        AccountManager.setSignState(true);
        iSignListener.onSignUpSuccess();
    }
    //登录
    public static void onSignIn(String response,ISignListener iSignListener){
        final UserProfile profile = new UserProfile(12121233,"登录","eee试试","男","郑州");
        //将解析到的数据插入到数据库里
        DatabaseManager.getInstance().getDao().insert(profile);
        LatteLogger.v("insertdb",profile.toString());
        //保存用户状态
        AccountManager.setSignState(true);
        iSignListener.onSignInSuccess();
    }
}
