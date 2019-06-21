package com.company.banu.ShowProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.company.banu.R;

public class ViewShowProfile extends AppCompatActivity {
    static int layoutId = R.layout.activity_main;
    PresenterShowProfile signInPresenterShowProfile;
    Button signInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        bindViews();
        signInPresenterShowProfile = new PresenterShowProfile(this);
    }

    void bindViews() {

    }

    public void signIn() {
        signInPresenterShowProfile.signIn();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInPresenterShowProfile.signInReturn(requestCode, resultCode, data);
    }
}
