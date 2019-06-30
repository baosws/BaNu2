package com.company.banu.Study.Theory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.company.banu.Backend;
import com.company.banu.R;
import com.company.banu.Study.StudyFragment;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import Universe.YoutubeUtils;


public class FragmentTheory extends StudyFragment implements YouTubePlayer.OnInitializedListener, TheoryView {
    View mRootView;
    TextView tvContentLecture;
    TheoryPresenter presenter;
    public FragmentTheory() {
        super();
        name = "Theory";
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_study_theory, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        String lectureId = args.getString("lectureId");
        Lecture lecture = (Lecture)Backend.getCache(lectureId);
        presenter = new TheoryPresenter(this, lecture);
    }

    public void getViews()
    {
        tvContentLecture = mRootView.findViewById(R.id.tv_summarizeContent);
    }

    public void showSummarize(String summary)
    {
        tvContentLecture.setText(summary);
    }

    public void initVideo()
    {
        YouTubePlayerSupportFragment youtubePlayerFragment = new YouTubePlayerSupportFragment();
        youtubePlayerFragment.initialize(getString(R.string.api_key), this);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_video, youtubePlayerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        presenter.onInitializationSuccess(provider, youTubePlayer, b);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        presenter.onInitializationFailure(provider, youTubeInitializationResult);
    }
}