package com.company.banu.DetailLevel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.company.banu.DetailLevel.DiaryTopic.Topic;
import com.company.banu.R;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.github.abdularis.civ.AvatarImageView;

import java.util.List;

public class ActivityDetailLevel extends AppCompatActivity implements ViewDetailLevel {
    TextView tvLevelName;
    TextView tvScore;
    GridView gridViewListTopic;
    AvatarImageView avatarImageView;
    PresenterDetailLevel presenterDetailLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_level);
        presenterDetailLevel = new PresenterDetailLevel(this);
    }

    public void getViews()
    {
        tvLevelName = findViewById(R.id.tv_levelName);
        tvScore = findViewById(R.id.tv_score);
        gridViewListTopic = findViewById(R.id.gv_listTopic);
        avatarImageView = findViewById(R.id.imv_avatar);
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewShowProfile.class));
            }
        });
    }

    public void loadGridViewListTopics(List<Topic> topics)
    {
        gridViewListTopic.setAdapter(new ListTopicAdapter(this, topics));
    }

    public void loadAvatar(Bitmap bitmap) {
        avatarImageView.setImageBitmap(bitmap);
    }

    @Override
    public void invalidateTopic() {
        gridViewListTopic.invalidate();
    }
}
