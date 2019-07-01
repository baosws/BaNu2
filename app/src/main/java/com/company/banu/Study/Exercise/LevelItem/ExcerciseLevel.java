package com.company.banu.Study.Exercise.LevelItem;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.Quiz.Excercise;
import com.company.banu.Quiz.ExcerciseEvent;
import com.company.banu.Quiz.QuizLevel;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;
import java.util.HashMap;

public class ExcerciseLevel extends Notifier<ExcerciseLevelEvent> {
    private QuizLevel name;
    private ArrayList<Excercise> excercises;
    private int color;
    private Lecture lecture;

    public ExcerciseLevel(Lecture lecture, final QuizLevel name, int c)
    {
        this.name = name;
        this.color = c;
        this.lecture = lecture;
        lecture.getExcercises(new CallBack<HashMap<QuizLevel, ArrayList<Excercise>>>() {
            @Override
            public void call(HashMap<QuizLevel, ArrayList<Excercise>> data) {
                setExcercises(data.get(name));
            }
        });
    }

    public QuizLevel getLevel() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        this.excercises = excercises;
        notify(ExcerciseLevelEvent.HadExcercises);
    }

    public void getExcercises(final CallBack<ArrayList<Excercise>> cb) {
        addEvent(ExcerciseLevelEvent.HadExcercises, new CallBack<Notifier>() {
                @Override
                public void call(Notifier data) {
                    cb.call(excercises);
                }
            }
        );
    }

    public Lecture getLecture() {
        return lecture;
    }
}
