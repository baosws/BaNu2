package com.company.banu.DetailLevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.company.banu.R;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailLevel extends AppCompatActivity {
    TextView tvLevelName;
    TextView tvScore;
    GridView gridViewListTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_level);
        getView();
        loadGridViewListTopics();
    }

    void getView()
    {
        tvLevelName = findViewById(R.id.tv_levelName);
        tvScore = findViewById(R.id.tv_score);
        gridViewListTopic = findViewById(R.id.gv_listTopic);
    }

    void loadGridViewListTopics()
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
}
