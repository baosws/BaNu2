package com.company.banu.Study.Exercise;

import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ExercisePresenter {
    FragmentExercise view;
    ExcerciseModel model;
    public ExercisePresenter(FragmentExercise view, Lecture lecture)
    {
        this.view = view;
        model = new ExcerciseModel(lecture);
        init();
    }

    private void init() {
        view.getViews();
        view.showLevelExercise();
    }
}
