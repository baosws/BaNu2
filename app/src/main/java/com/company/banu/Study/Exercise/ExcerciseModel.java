package com.company.banu.Study.Exercise;

import android.content.Context;
import android.content.res.Resources;

import com.company.banu.R;
import com.company.banu.Study.Exercise.LevelItem.ExcerciseLevel;
import com.company.banu.WatchLectures.LectureItem.Lecture;

import java.util.ArrayList;
import java.util.List;

public class ExcerciseModel {
    Lecture lecture;

    public ExcerciseModel(Lecture lecture)
    {
        this.lecture = lecture;
    }

    public Lecture getLecture() {
        return lecture;
    }
}
