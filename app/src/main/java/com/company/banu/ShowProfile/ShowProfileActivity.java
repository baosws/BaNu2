package com.company.banu.ShowProfile;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.company.banu.R;
import com.company.banu.ShowProfileSetting.ViewShowProfileSetting;
import com.company.banu.WatchLevels.ActivityWatchLevels;
import com.company.banu.WatchLevels.WatchLevelView;
import com.github.abdularis.civ.AvatarImageView;


import Universe.ImagePicker;
import Universe.ImageUtils;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ShowProfileActivity extends AppCompatActivity implements ViewShowProfile {
    static int layoutId = R.layout.activity_profile;
    private static final int PICK_IMAGE_ID = 234;

    PresenterShowProfile presenterShowProfile;
    @BindView(R.id.imv_avatar)
    AvatarImageView avatarImageView;
    @BindView(R.id.tvUserName)
    TextView textViewUserName;
    @BindView(R.id.btn_setting)
    ImageButton imageViewSetting;
    @BindView(R.id.btn_home)
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        ButterKnife.bind(this);
        presenterShowProfile = new PresenterShowProfile(this);
        setOnClickListener();
    }

    private void setOnClickListener() {
        imageViewSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewShowProfileSetting.class));
            }
        });

        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPickImage(v);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(), ActivityWatchLevels.class));
            }
        });
    }

    private void onPickImage(View v) {
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:
                Log.d("Nunu", data.getData().toString());
                Bitmap bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                Glide.with(this)
                        .load(bitmap)
                        .into(avatarImageView);
                presenterShowProfile.uploadAvatar(bitmap);
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    @Override
    public void setAvatar(Bitmap img) {
        avatarImageView.setImageBitmap(img);
    }

    @Override
    public void setUserName(String userName) {
        textViewUserName.setText(userName);
    }


}
