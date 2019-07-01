package com.company.banu.Study.Exercise.LevelItem;

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
