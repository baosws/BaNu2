package com.company.banu.Study.Theory;

import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.company.banu.WatchLevels.MediaResource;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import Universe.YoutubeUtils;

public class TheoryPresenter {
    TheoryView view;
    TheoryModel model;
    TheoryPresenter(TheoryView view, Lecture lecture) {
        this.view = view;
        model = new TheoryModel(lecture);
        init();
    }

    private void init() {
        view.getViews();
        model.getResource(new CallBack<MediaResource>() {
            @Override
            public void call(MediaResource data) {
                view.initVideo();
            }
        });
        model.getDescription(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.showSummarize(data);
            }
        });
    }

    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            model.getResource(new CallBack<MediaResource>() {
                @Override
                public void call(MediaResource data) {
                    youTubePlayer.cueVideo(YoutubeUtils.GetCueFromLink(data.getUrl()));
                }
            });

        }
    }

    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("btag", "Youtube InitializationFailure");
    }
}