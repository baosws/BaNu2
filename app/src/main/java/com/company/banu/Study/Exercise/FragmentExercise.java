package com.company.banu.Study.Exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.company.banu.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentExercise extends Fragment {
    View mRootView;
    RecyclerView rvLevelExercise;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_study_exercise, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getViews();
        this.showLevelExercise();
    }

    void getViews()
    {
        rvLevelExercise = mRootView.findViewById(R.id.rv_levelExercises);
    }

    void showLevelExercise()
    {
        List<Level> levelData = new ArrayList<>();
        levelData.add(new Level("Beginner", getResources().getColor(R.color.beginner)));
        levelData.add(new Level("Intermediate", getResources().getColor(R.color.intermediate)));
        levelData.add(new Level("Advanced", getResources().getColor(R.color.advanced)));

        LevelAdapter levelAdapter = new LevelAdapter(levelData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLevelExercise.setLayoutManager(layoutManager);
        rvLevelExercise.setAdapter(levelAdapter);
    }
}
