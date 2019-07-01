package com.company.banu.Study.Exercise.LevelItem;

import android.app.AlertDialog;
import android.util.Pair;

import com.company.banu.CallBack;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ExcerciseLevelPresenter {
    ExcerciseLevelModel model;
    ExcerciseLevelView view;

    public ExcerciseLevelPresenter(ExcerciseLevelView excerciseLevelView)
    {
        this.view = excerciseLevelView;
        this.model = new ExcerciseLevelModel(this);
        init();
    }

    private void init() {
        view.getViews();
    }

    public void setLevel(ExcerciseLevel excerciseLevel)
    {
        model.setExcerciseLevel(excerciseLevel);
        view.setName(model.getName());
        view.setColor(model.getColor());
        model.getExcerciseCount(new CallBack<Pair<Integer, Integer>>() {
            @Override
            public void call(Pair<Integer, Integer> data) {
                view.updateScore(data.first, data.second);
                // Check result to update state
                if (data.first * 1.0 / data.second >= 0.7)
                {
                    view.updateState(true);
                }
                else {
                    view.updateState(false);
                }
                // Check if there are some exercise
                view.setOnClick(data.second != 0);
            }
        });
    }

    public void onClick(final boolean haveData) {
        model.getLectureId(new CallBack<String>() {
            @Override
            public void call(String data) {
                if (haveData)
                    view.startQuiz(data, model.getLevel());
                else
                    view.showNoExercise();
            }
        });
    }
}
