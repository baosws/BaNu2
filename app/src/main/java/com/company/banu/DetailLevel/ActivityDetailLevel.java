package com.company.banu.DetailLevel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.banu.CallBack;
import com.company.banu.DetailLevel.DiaryTopic.ListTopicAdapter;
import com.company.banu.DetailLevel.DiaryTopic.Topic;
import com.company.banu.R;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.company.banu.WatchLevels.ActivityWatchLevels;
import com.github.abdularis.civ.AvatarImageView;

import java.util.List;

public class ActivityDetailLevel extends AppCompatActivity implements ViewDetailLevel {
    TextView tvLevelName;
    TextView tvScore;
    GridView gridViewListTopic;
    AvatarImageView avatarImageView;
    PresenterDetailLevel presenterDetailLevel;
    ImageButton ibHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_level);
        presenterDetailLevel = new PresenterDetailLevel(this);
    }

    public void getViews()
    {
        ibHome = findViewById(R.id.ibHome);
        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityWatchLevels.class));
            }
        });
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
        final ListTopicAdapter listTopicAdapter = new ListTopicAdapter(this, topics);
        for (Topic topic: topics) {
            topic.addObserver(new CallBack<Topic>() {
                @Override
                public void call(Topic data) {
                    listTopicAdapter.notifyDataSetChanged();
                    gridViewListTopic.invalidate();
                }
            });
        }
        gridViewListTopic.setAdapter(listTopicAdapter);
    }

    public void loadAvatar(Bitmap bitmap) {
        avatarImageView.setImageBitmap(bitmap);
    }

    @Override
    public void invalidateTopic() {
        ((ListTopicAdapter)gridViewListTopic.getAdapter()).notifyDataSetChanged();
        gridViewListTopic.invalidate();
    }
}
