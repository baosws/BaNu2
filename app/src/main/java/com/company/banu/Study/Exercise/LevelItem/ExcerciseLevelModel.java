package com.company.banu.Study.Exercise.LevelItem;


import android.util.Pair;

import com.company.banu.CallBack;
import com.company.banu.Quiz.Excercise;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;

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

    public void getExcerciseCount(final CallBack<Pair<Integer, Integer>> cb) {
        excerciseLevel.getExcercises(new CallBack<ArrayList<Excercise>>() {
            @Override
            public void call(ArrayList<Excercise> data) {
                final int[] passed = {0};
                for (Excercise excercise: data) {
                    excercise.getPassed(new CallBack<Boolean>() {
                        @Override
                        public void call(Boolean data) {
                            if (data) {
                                passed[0]++;
                            }
                        }
                    });
                }
                int total = data.size();
                cb.call(new Pair<Integer, Integer>(passed[0], total));
            }
        });
    }
}
