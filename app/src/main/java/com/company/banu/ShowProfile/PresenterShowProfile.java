package com.company.banu.ShowProfile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.company.banu.CallBack;
import com.company.banu.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.github.abdularis.civ.AvatarImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class PresenterShowProfile {
    Activity activity;
    AvatarImageView avatarImageView;
    TextView textViewUserName;
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
        textViewUserName = activity.findViewById(R.id.tvUserName);
        imageViewSetting = activity.findViewById(R.id.ivSettings);
        imageViewHome = activity.findViewById(R.id.ivHome);

        modelShowProfile.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                avatarImageView.setImageBitmap(bitmap);
            }
        });
        textViewUserName.setText(modelShowProfile.getUserName());
    }
}
