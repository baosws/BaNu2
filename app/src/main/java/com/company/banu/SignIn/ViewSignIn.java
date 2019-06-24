package com.company.banu.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.company.banu.R;

public class ViewSignIn extends AppCompatActivity {
    static int layoutId = R.layout.activity_signin;
    PresenterSingIn signInPresenterSingIn;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        signInPresenterSingIn = new PresenterSingIn(this);
        bindViews();
    }

    void bindViews() {
        signInButton = findViewById(R.id.btn_signin);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInPresenterSingIn.signIn();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        signInPresenterSingIn.signInReturn(requestCode, resultCode, data);
    }
}
