package com.company.banu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.ShowProfile.ShowProfileActivity;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.company.banu.SignIn.ViewSignIn;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(new Intent(this, ViewSignIn.class), 12);
        }
        else {
            onActivityResult(0, 0, null);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        startActivity(new Intent(this, ShowProfileActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
