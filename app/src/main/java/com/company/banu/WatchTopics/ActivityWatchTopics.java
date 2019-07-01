package com.company.banu.WatchTopics;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.ShowProfile.ShowProfileActivity;
import com.company.banu.WatchLevels.LevelItem.Level;
import com.company.banu.WatchTopics.TopicItem.ListTopicAdapter;
import com.company.banu.WatchTopics.TopicItem.Topic;
import com.company.banu.R;
import com.company.banu.ShowProfile.ViewShowProfile;
import com.company.banu.WatchLevels.ActivityWatchLevels;
import com.company.banu.WatchTopics.TopicItem.TopicEvent;
import com.github.abdularis.civ.AvatarImageView;

import java.util.List;

public class ActivityWatchTopics extends AppCompatActivity implements WatchTopicsView {
    TextView tvLevelName;
    TextView tvScore;
    GridView gridViewListTopic;
    AvatarImageView avatarImageView;
    WatchTopicsPresenter watchTopicsPresenter;
    ImageButton ibHome;
    ProgressBar determinateBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_level);
        Intent intent = getIntent();
        Level level = (Level) Backend.getCache(intent.getStringExtra("levelId"));
        watchTopicsPresenter = new WatchTopicsPresenter(this, level);
    }

    public void getViews()
    {
        determinateBar = findViewById(R.id.determinateBar);
        ibHome = findViewById(R.id.ib_home);
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
                startActivity(new Intent(getApplicationContext(), ShowProfileActivity.class));
            }
        });
    }

    public void loadGridViewListTopics(List<Topic> topics)
    {
        Log.d("btag", "loadGridViewListTopics: len = " + topics.size());
        final ListTopicAdapter listTopicAdapter = new ListTopicAdapter(this, topics);
        for (Topic topic: topics) {
            topic.getAny(new CallBack<Notifier>() {
                @Override
                public void call(Notifier data) {
                    listTopicAdapter.notifyDataSetChanged();
                    gridViewListTopic.invalidate();
                }
            });
        }
        gridViewListTopic.setAdapter(listTopicAdapter);
    }

    public void setPercent(Float percent) {
        determinateBar.setProgress((int)(percent * 100));
    }
    public void loadAvatar(Bitmap bitmap) {
        avatarImageView.setImageBitmap(bitmap);
    }

    public void setLevelName(String name) {
        tvLevelName.setText(name);
    }
}
