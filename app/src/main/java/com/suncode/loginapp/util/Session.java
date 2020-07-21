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

    public void setupSessionAdmin(User Admin) {
        editor.putString(Constant.KEY_SESSION_ADMIN_NAME, Admin.getName());
        editor.putInt(Constant.KEY_SESSION_ADMIN_TAG, Admin.getTag());
        editor.putInt(Constant.KEY_IS_LOGIN, Admin.getTag());
        editor.commit();
    }

    public void setupSessionUser(User User) {
        editor.putString(Constant.KEY_SESSION_USER_NAME, User.getName());
        editor.putInt(Constant.KEY_SESSION_USER_TAG, User.getTag());
        editor.putInt(Constant.KEY_IS_LOGIN, User.getTag());
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
