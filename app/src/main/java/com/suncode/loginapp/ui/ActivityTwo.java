package com.suncode.loginapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.protobuf.StringValue;
import com.suncode.loginapp.R;
import com.suncode.loginapp.ui.base.BaseActivity;

public class ActivityTwo extends BaseActivity {


    private TextView mName;
    private Button mButtonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mName = findViewById(R.id.user);
        mButtonLogout = findViewById(R.id.logout);

        mName.setText(getUserName() + " " + getFirebaseUserId());

        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutApps();
            }
        });
    }
}
