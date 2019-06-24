package com.company.banu.ShowProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.company.banu.Backend;
import com.company.banu.R;
import com.company.banu.WatchLevels.ViewWatchLevels;
import com.google.firebase.auth.FirebaseAuth;

import Universe.User;

public class ViewShowProfile extends AppCompatActivity {
    static int layoutId = R.layout.activity_profile;
    PresenterShowProfile signInPresenterShowProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);

        signInPresenterShowProfile = new PresenterShowProfile(this);
        startActivity(new Intent(this, ViewWatchLevels.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}
