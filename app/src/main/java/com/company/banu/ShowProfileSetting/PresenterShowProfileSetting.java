package com.company.banu.ShowProfileSetting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.CallBack;
import com.company.banu.MainActivity;
import com.company.banu.R;
import com.github.abdularis.civ.AvatarImageView;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class PresenterShowProfileSetting {
    Activity activity;
    AvatarImageView avatarImageView;
    TextView textViewUserName;
    TextView textViewEmail;
    TextView textViewPassword;
    TextView textViewSignOut;
    ImageView imageViewSetting;
    ImageView imageViewHome;
    ModelShowProfileSetting modelShowProfileSetting;

    public PresenterShowProfileSetting(Activity activity) {
        this.activity = activity;
        modelShowProfileSetting = new ModelShowProfileSetting();
        bindViews();
    }

    private void bindViews() {
        textViewUserName = activity.findViewById(R.id.tv_profileName);
        textViewEmail = activity.findViewById(R.id.tv_profileEmail);
        textViewPassword = activity.findViewById(R.id.tv_profilePassword);
        textViewSignOut = activity.findViewById(R.id.tv_SignOut);

        textViewUserName.setText(modelShowProfileSetting.getUserName());
        textViewEmail.setText(modelShowProfileSetting.getEmail());
        textViewPassword.setText("123456");
        textViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(activity, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                activity.startActivity(intent);
            }
        });
    }
}
