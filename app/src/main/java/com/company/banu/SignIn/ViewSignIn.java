package com.company.banu.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.company.banu.R;

public class ViewSignIn extends AppCompatActivity {
    static int layoutId = R.layout.activity_main;
    PresenterSingIn signInPresenterSingIn;
    Button signInButton;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        bindViews();
        signInPresenterSingIn = new PresenterSingIn(this);
        signIn();
    }

    void bindViews() {

    }

    public void signIn() {
        signInPresenterSingIn.signIn();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInPresenterSingIn.signInReturn(requestCode, resultCode, data);
    }
}
