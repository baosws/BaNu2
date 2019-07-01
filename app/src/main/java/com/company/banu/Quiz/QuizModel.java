package com.company.banu.Quiz;

import android.graphics.Bitmap;
import android.widget.Toast;

import com.company.banu.CallBack;
import com.company.banu.Classifier;
import com.company.banu.Notifier.Notifier;
import com.company.banu.Result;

import java.util.ArrayList;

public class QuizModel extends Notifier<QuizEvent> {
    private ArrayList<Excercise> excercises;
    private Classifier classifier;
    public QuizModel(Classifier classifier) {
        this.classifier = classifier;
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        this.excercises = excercises;
        notify(QuizEvent.HadExcercises);
    }

    public void getExcercises(final CallBack<ArrayList<Excercise>> cb) {
        addEvent(QuizEvent.HadExcercises, new CallBack<Notifier>() {
            @Override
            public void call(Notifier data) {
                cb.call(excercises);
            }
        });
    }

    public void getExcercise(final int i, final CallBack<Excercise> cb) {
        getExcercises(new CallBack<ArrayList<Excercise>>() {
            @Override
            public void call(ArrayList<Excercise> data) {
                if (i < data.size()) {
                    cb.call(data.get(i));
                }
                else {
                    cb.call(null);
                }
            }
        });
    }

    public Result check(Bitmap bitmap) {
        return classifier.classify(bitmap);
    }

    public void check(final String out, final Excercise excercise, final CallBack<Boolean> cb) {
        excercise.getAnswer(new CallBack<String>() {
            @Override
            public void call(String data) {
                Boolean res = data.equals(out);
                excercise.setPassed(res);
                cb.call(res);
            }
        });
    }
}
