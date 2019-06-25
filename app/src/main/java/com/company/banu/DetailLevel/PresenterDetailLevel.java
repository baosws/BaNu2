package com.company.banu.DetailLevel;

import android.app.Activity;
import android.graphics.Bitmap;

import com.company.banu.CallBack;

public class PresenterDetailLevel {
    private final ViewDetailLevel activity;
    ModelDetailLevel modelDetailLevel;

    PresenterDetailLevel(final ViewDetailLevel activity) {
        this.activity = activity;
        modelDetailLevel = new ModelDetailLevel();

        activity.getViews();
        activity.loadGridViewListTopics();
        modelDetailLevel.getAvatar(new CallBack<Bitmap>() {
            @Override
            public void call(Bitmap data) {
                activity.loadAvatar(data);
            }
        });
    }
}
