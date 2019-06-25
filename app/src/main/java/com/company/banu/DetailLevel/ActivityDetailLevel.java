package com.company.banu.DetailLevel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.company.banu.R;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.github.abdularis.civ.AvatarImageView;

import java.util.ArrayList;
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

    public void loadGridViewListTopics()
    {
        final List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Counting", "counting", (float) 1.0));
        topics.add(new Topic("Comparison", "cmp", (float) 1.0));
        topics.add(new Topic("Adding", "ading", (float) 1.0));
        topics.add(new Topic("Counting", "counting", (float) 1.0));
        topics.add(new Topic("Comparison", "cmp", (float) 1.0));
        topics.add(new Topic("Adding", "ading", (float) 1.0));

        gridViewListTopic.setAdapter(new ListTopicAdapter(this, topics));
    }

    public void loadAvatar(Bitmap bitmap) {
        avatarImageView.setImageBitmap(bitmap);
    }
}
