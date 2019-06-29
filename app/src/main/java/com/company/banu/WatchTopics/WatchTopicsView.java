package com.company.banu.WatchTopics;

import android.graphics.Bitmap;

import com.company.banu.WatchTopics.TopicItem.Topic;

import java.util.List;

public interface WatchTopicsView {
    void getViews();
    void loadGridViewListTopics(List<Topic> topics);
    void loadAvatar(Bitmap bitmap);
    void setPercent(Float percent);
    void setLevelName(String name);
}
