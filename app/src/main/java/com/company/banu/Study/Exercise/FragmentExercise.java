package com.company.banu.Study.Exercise;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.banu.Backend;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.R;
import com.company.banu.Study.Exercise.LevelItem.ExcerciseLevel;
import com.company.banu.Study.Exercise.LevelItem.ExcerciseLevelAdapter;
import com.company.banu.Study.StudyFragment;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;
import java.util.List;

public class FragmentExercise extends StudyFragment implements ExcerciseView {
    View mRootView;
    RecyclerView rvLevelExercise;
    ExercisePresenter presenter;

    public FragmentExercise() {
        super();
        name = "Excercise";
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_study_exercise, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle args = getArguments();
        String lectureId = args.getString("lectureId");
        Lecture lecture = (Lecture)Backend.getCache(lectureId);
        presenter = new ExercisePresenter(this, lecture);
    }

    public void getViews()
    {
        rvLevelExercise = mRootView.findViewById(R.id.rv_levelExercises);
    }

    public void showExcerciseLevels(Lecture lecture) {
        List<ExcerciseLevel> excerciseLevelData = new ArrayList<>();
        excerciseLevelData.add(new ExcerciseLevel(lecture, QuizLevel.Beginner, getResources().getColor(R.color.beginner)));
        excerciseLevelData.add(new ExcerciseLevel(lecture, QuizLevel.Intermediate, getResources().getColor(R.color.intermediate)));
        excerciseLevelData.add(new ExcerciseLevel(lecture, QuizLevel.Advanced, getResources().getColor(R.color.advanced)));

        ExcerciseLevelAdapter excerciseLevelAdapter = new ExcerciseLevelAdapter(excerciseLevelData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRootView.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLevelExercise.setLayoutManager(layoutManager);
        rvLevelExercise.setAdapter(excerciseLevelAdapter);
    }
}
