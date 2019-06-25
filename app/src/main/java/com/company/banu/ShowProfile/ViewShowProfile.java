package com.company.banu.ShowProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.company.banu.R;

public class ViewShowProfile extends AppCompatActivity {
    static int layoutId = R.layout.activity_profile;
    PresenterShowProfile presenterShowProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterShowProfile = new PresenterShowProfile(this);
    }
}
