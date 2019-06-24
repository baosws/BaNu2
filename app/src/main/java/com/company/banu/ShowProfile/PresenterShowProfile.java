package com.company.banu.ShowProfile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.ShowProfileSetting.ViewShowProfileSetting;
import com.github.abdularis.civ.AvatarImageView;

public class PresenterShowProfile {
    Activity activity;
    AvatarImageView avatarImageView;
    TextView textViewUserName;
    TextView textViewEmail;
    TextView textViewPassword;
    ImageView imageViewSetting;
    ImageView imageViewHome;
    ModelShowProfile modelShowProfile;

    public PresenterShowProfile(Activity activity) {
        this.activity = activity;
        modelShowProfile = new ModelShowProfile();
        bindViews();
    }

    private void bindViews() {
        avatarImageView = activity.findViewById(R.id.imv_avatar);
        imageViewHome = activity.findViewById(R.id.ivHome);
        imageViewSetting = activity.findViewById(R.id.ivSettings);
        textViewUserName = activity.findViewById(R.id.tvUserName);

        textViewUserName.setText(modelShowProfile.getUserName());
        modelShowProfile.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                avatarImageView.setImageBitmap(data);
            }
        });

        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, ViewShowProfileSetting.class));
            }
        });
    }
}
