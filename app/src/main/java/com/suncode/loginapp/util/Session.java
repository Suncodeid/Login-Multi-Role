package com.suncode.loginapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.suncode.loginapp.model.User;

public class Session {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Session(Context context) {
        preferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setupSessionAdmin(User admin) {
        editor.putString(Constant.KEY_SESSION_ADMIN_NAME, admin.getName());
        editor.putInt(Constant.KEY_SESSION_ADMIN_TAG, admin.getTag());
        editor.putInt(Constant.KEY_IS_LOGIN,admin.getTag());
        editor.putString(Constant.KEY_UID, admin.getuId());
        editor.commit();
    }

    public void setupSessionUser(User user) {
        editor.putString(Constant.KEY_SESSION_USER_NAME, user.getName());
        editor.putInt(Constant.KEY_SESSION_USER_TAG, user.getTag());
        editor.putInt(Constant.KEY_IS_LOGIN, user.getTag());
        editor.putString(Constant.KEY_UID, user.getuId());
        editor.commit();
    }

    public void removeSession(){
        editor.clear();
        editor.commit();
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
