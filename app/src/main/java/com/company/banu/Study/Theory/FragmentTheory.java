package com.company.banu.Study.Theory;

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

import com.company.banu.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import Universe.YoutubeUtils;


public class FragmentTheory extends Fragment implements YouTubePlayer.OnInitializedListener {
    View mRootView;
    TextView tvContentLecture;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_study_theory, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViews();
        showSummarize();
        loadVideo();
    }

    void getViews()
    {
        tvContentLecture = mRootView.findViewById(R.id.tv_summarizeContent);
    }

    void showSummarize()
    {
        tvContentLecture.setText("Hello!\nThis is Summarize Content" );
    }

    void loadVideo()
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
        if(!b){
            youTubePlayer.cueVideo(YoutubeUtils.GetCueFromLink("https://youtu.be/Z14lqZRf2ZM"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(getActivity(),"fail",Toast.LENGTH_SHORT).show();
    }
}
