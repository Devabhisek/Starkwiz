package com.starkwiz.starkwiz.LinkingClass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.starkwiz.starkwiz.Activities.UserSelection_Activity;
import com.starkwiz.starkwiz.ModelClass.Login_ModelClass;


public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_Message = "keyMessage";
    private static final String KEY_access_token = "keyaccess_token";
    private static final String KEY_id = "keyid";
    private static final String KEY_first_name = "keyfirst_name";
    private static final String KEY_last_name = "keylast_name";
    private static final String KEY_mobile_number = "keymobile_number";
    private static final String KEY_cls = "keycls";
    private static final String KEY_school_board = "keyschool_board";
    private static final String KEY_role = "keyrole";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user register
    //this method will store the user data in shared preferences
    public void userLogin(Login_ModelClass login_modelClass) {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_access_token,      login_modelClass.getAccess_token());
        editor.putString(KEY_id,                login_modelClass.getId());
        editor.putString(KEY_first_name,        login_modelClass.getFirst_name());
        editor.putString(KEY_last_name,         login_modelClass.getLast_name());
        editor.putString(KEY_mobile_number,     login_modelClass.getMobile_number());
        editor.putString(KEY_cls,               login_modelClass.getCls());
        editor.putString(KEY_school_board,      login_modelClass.getSchool_board());
        editor.putString(KEY_role,              login_modelClass.getRole());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_access_token, null) != null;
    }

    //this method will give the logged in user
   public Login_ModelClass getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
           return new Login_ModelClass(


               sharedPreferences.getString(KEY_access_token, null),
               sharedPreferences.getString(KEY_id, null),
               sharedPreferences.getString(KEY_first_name, null),
               sharedPreferences.getString(KEY_last_name, null),
               sharedPreferences.getString(KEY_mobile_number, null),
               sharedPreferences.getString(KEY_cls, null),
               sharedPreferences.getString(KEY_school_board, null),
               sharedPreferences.getString(KEY_role, null)

                   );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, UserSelection_Activity.class));
    }




}
