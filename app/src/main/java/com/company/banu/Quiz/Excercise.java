package com.company.banu.Quiz;

import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.Notifier.Notifier;
import com.company.banu.WatchLectures.LectureItem.Lecture;

public class Excercise extends Notifier<ExcerciseEvent> {
    private String id;
    private Bitmap image;
    private String answer;
    private Boolean passed;
    private Lecture lecture;
    public Excercise(Lecture lecture) {
        this.lecture = lecture;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        notify(ExcerciseEvent.HadAnswer);
    }

    public void setImage(Bitmap image) {
        this.image = image;
        notify(ExcerciseEvent.HadImage);
    }

    public void getAnswer(final CallBack<String> cb) {
        addEvent(ExcerciseEvent.HadAnswer, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(answer);
            }
        });
    }

    public void getImage(final CallBack<Bitmap> cb) {
        addEvent(ExcerciseEvent.HadImage, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(image);
            }
        });
    }

    public void setId(String id) {
        this.id = id;
        notify(ExcerciseEvent.HadId);
    }

    public void getId(final CallBack<String> cb) {
        addEvent(ExcerciseEvent.HadId, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(id);
            }
        });
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
        notify(ExcerciseEvent.HadPassed);
    }

    public void getPassed(final CallBack<Boolean> cb) {
        addEvent(ExcerciseEvent.HadPassed, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(passed);
            }
        });
    }
}
