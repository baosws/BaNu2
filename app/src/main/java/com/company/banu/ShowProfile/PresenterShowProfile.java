package com.company.banu.ShowProfile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Display;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.ShowProfileSetting.ViewShowProfileSetting;
import com.company.banu.WatchLevels.ActivityWatchLevels;
import com.github.abdularis.civ.AvatarImageView;

public class PresenterShowProfile {
    ModelShowProfile modelShowProfile;
    ViewShowProfile viewShowProfile;

    public PresenterShowProfile(ViewShowProfile view) {
        this.viewShowProfile = view;
        modelShowProfile = new ModelShowProfile(this);
        init();
    }

    public void init()
    {
        modelShowProfile.getAvatar(new CallBack() {
            @Override
            public void call(Object data) {
                viewShowProfile.setAvatar((Bitmap) data);
            }
        });

        viewShowProfile.setUserName(modelShowProfile.getUserName());
    }

}
