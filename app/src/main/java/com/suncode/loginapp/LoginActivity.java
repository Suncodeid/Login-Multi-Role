package com.suncode.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.suncode.loginapp.callback.LoginViewCallback;
import com.suncode.loginapp.model.User;
import com.suncode.loginapp.ui.ActivityOne;
import com.suncode.loginapp.ui.ActivityTwo;
import com.suncode.loginapp.ui.base.BaseActivity;
import com.suncode.loginapp.util.Constant;

public class LoginActivity extends BaseActivity implements LoginViewCallback {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login);
        mProgressbar = findViewById(R.id.progresLogin);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowProgress();
                String userEmail = mEmail.getText().toString();
                String userPassword = mPassword.getText().toString();

                if (TextUtils.isEmpty(mEmail.getText()) && TextUtils.isEmpty(mPassword.getText())) {
                    showToast("Username / Password Kosong");
                    onHideProgress();
                } else if (TextUtils.isEmpty(mEmail.getText())) {
                    showToast("Username Kosong");
                    onHideProgress();
                } else if (TextUtils.isEmpty(mPassword.getText())) {
                    showToast("Password Kosong");
                    onHideProgress();
                } else {
                    getAuthFirebase(userEmail, userPassword);
                }
            }
        });
        checkUserLogin(mSession.getPreferences().getInt(Constant.KEY_IS_LOGIN, 0));
    }

    private void getFireStoreData(String idUser) {
        DocumentReference docRef = firebaseFirestore.collection("user").document(idUser);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    long userTag = document.getLong("tag").intValue();
                    saveSession(idUser, (int) userTag, document);
                } else {
                    Log.d("gagal", "Documment tidak ada");
                }
            } else {
                Log.d("TAG", "gagal", task.getException());
            }
        });
    }

    // Save Session
    private void saveSession(String uid, int userTag, DocumentSnapshot documentSnapshot) {
        if (userTag == 1) {
            User admin = new User();
            admin.setName(documentSnapshot.getString("name"));
            admin.setTag(documentSnapshot.getLong("tag").intValue());
            admin.setuId(uid);
            mSession.setupSessionAdmin(admin);

        } else if (userTag == 2) {
            User user = new User();
            user.setName(documentSnapshot.getString("name"));
            user.setTag(documentSnapshot.getLong("tag").intValue());
            user.setuId(uid);
            mSession.setupSessionUser(user);
        }
        checkUserLogin(mSession.getPreferences().getInt(Constant.KEY_IS_LOGIN, 0));
    }

    private void getAuthFirebase(String userEmail, String userPassword) {
        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
            task.addOnFailureListener(LoginActivity.this, e -> onFailedAuthFirebase());
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                onSuccessAuthFirebase(firebaseUser.getUid());
            } else {
                onFailedAuthFirebase();
            }
        });
    }

    private void checkUserLogin(int cekPengguna) {
        onHideProgress();
        if (cekPengguna != 0){
            if (cekPengguna == 1) {
                Intent j = new Intent(LoginActivity.this, ActivityOne.class);
                startActivity(j);
                finish();
            } else if (cekPengguna == 2) {
                Intent i = new Intent(LoginActivity.this, ActivityTwo.class);
                startActivity(i);
                finish();
            }
        }
    }

    @Override
    public void onSuccessAuthFirebase(String uid) {
        getFireStoreData(uid);
    }

    @Override
    public void onFailedAuthFirebase() {
        showToast("Gagal Login");
    }

    @Override
    public void onShowProgress() {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideProgress() {
        mProgressbar.setVisibility(View.GONE);
    }
}
