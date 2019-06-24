package com.company.banu.ShowProfileSetting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.company.banu.R;

public class ViewShowProfileSetting extends AppCompatActivity {
    static int layoutId = R.layout.activity_profile_setting;
    PresenterShowProfileSetting presenterShowProfileSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterShowProfileSetting = new PresenterShowProfileSetting(this);
    }
}
