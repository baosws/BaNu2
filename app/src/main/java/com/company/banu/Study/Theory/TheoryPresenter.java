package com.company.banu.Study.Theory;

import android.util.Log;

import com.company.banu.WatchLectures.LectureItem.Lecture;
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
        view.showSummarize("Hello!\nThis is Summarize Content");
        view.loadVideo();
    }

    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(YoutubeUtils.GetCueFromLink("https://youtu.be/Z14lqZRf2ZM"));
        }
    }

    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("btag", "Youtube InitializationFailure");
    }
}
