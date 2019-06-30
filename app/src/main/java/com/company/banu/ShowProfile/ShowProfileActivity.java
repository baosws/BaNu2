package com.company.banu.ShowProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.R;
import com.github.abdularis.civ.AvatarImageView;

import butterknife.BindView;

public class ShowProfileActivity extends AppCompatActivity {
    static int layoutId = R.layout.activity_profile;
    PresenterShowProfile presenterShowProfile;
    @BindView(R.id.imv_avatar)
    AvatarImageView avatarImageView;
    @BindView(R.id.tv_profileName)
    TextView textViewUserName;
    @BindView(R.id.tv_profileEmail) TextView textViewEmail;
    @BindView(R.id.tv_profilePassword) TextView textViewPassword;
    @BindView(R.id.ivSettings)
    ImageView imageViewSetting;
    @BindView(R.id.btn_home)
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        presenterShowProfile = new PresenterShowProfile(this);
    }
}
