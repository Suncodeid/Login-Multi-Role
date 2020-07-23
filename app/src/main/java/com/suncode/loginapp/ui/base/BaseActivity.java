package com.suncode.loginapp.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.suncode.loginapp.LoginActivity;
import com.suncode.loginapp.util.Constant;
import com.suncode.loginapp.util.Session;

public class BaseActivity extends AppCompatActivity {
    protected BaseActivity mActivity;
    protected Session mSession;
    protected FirebaseAuth firebaseAuth;
    protected FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mSession = new Session(this);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    protected void logoutApps() {
        mSession.removeSession();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    protected String getFirebaseUserId() {
        return mSession.getPreferences().getString(Constant.KEY_UID, "");
    }


    protected String getUserName() {
        return mSession.getPreferences().getString(Constant.KEY_SESSION_USER_NAME, "");
    }

    protected String getUserNameAdmin() {
        return mSession.getPreferences().getString(Constant.KEY_SESSION_ADMIN_NAME, "");
    }
}
