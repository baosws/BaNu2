package com.company.banu.Study.Exercise.LevelItem;

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
            }
        });
    }

    public void onClick() {
        model.getLectureId(new CallBack<String>() {
            @Override
            public void call(String data) {
                view.startQuiz(data, model.getLevel());
            }
        });
    }
}
