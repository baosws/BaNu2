package com.company.banu.Study.Exercise.LevelItem;


import com.company.banu.CallBack;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class ExcerciseLevelModel {

    ExcerciseLevelPresenter presenter;
    ExcerciseLevel excerciseLevel;

    public ExcerciseLevelModel(ExcerciseLevelPresenter excerciseLevelPresenter)
    {
        this.presenter = excerciseLevelPresenter;
    }

    public void setExcerciseLevel(ExcerciseLevel level){
        this.excerciseLevel = level;
    }

    public String getName() {
        return excerciseLevel.getLevel().toString();
    }

    public int getColor()
    {
        return excerciseLevel.getColor();
    }

    public void getLectureId(CallBack<String> cb) {
        excerciseLevel.getLecture().getId(cb);
    }

    public QuizLevel getLevel() {
        return excerciseLevel.getLevel();
    }

}
