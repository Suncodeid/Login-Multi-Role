package com.suncode.loginapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.suncode.loginapp.R;
import com.suncode.loginapp.ui.base.BaseActivity;

public class ActivityOne extends BaseActivity {

    private TextView mName;
    private Button mButtonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        mName = findViewById(R.id.user);
        mButtonLogout = findViewById(R.id.logout);

        mName.setText(getUserName() + getFirebaseUserId());
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutApps();
            }
        });
    }
}
