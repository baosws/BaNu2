package com.company.banu.Quiz;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.util.Log;

import com.company.banu.CallBack;
import com.company.banu.Classifier;
import com.company.banu.Notifier.Notifier;
import com.company.banu.R;
import com.company.banu.Result;

import java.util.ArrayList;

public class QuizPresenter {
    QuizView view;
    QuizModel model;
    int current = 0;

    public QuizPresenter(QuizView view, Classifier classifier) {
        this.view = view;
        model = new QuizModel(classifier);
    }

    public void init() {
        view.getViews();
        view.getData();
    }

    public void onPause() {
        view.showDialog();
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        model.setExcercises(excercises);
        nextQuiz();
    }

    private void nextQuiz() {
        model.getExcercise(current, new CallBack<Excercise>() {
            @Override
            public void call(Excercise data) {
                data.getImage(new CallBack<Bitmap>() {
                    @Override
                    public void call(Bitmap data) {
                        view.setImage(data);
                    }
                });
            }
        });
    }

    public void check(Bitmap bitmap) {
        Result result = model.check(bitmap);
        final String out = String.valueOf(result.getNumber());
        Log.d("btag", String.format("QuizPresenter:check: out = %s", out));
        model.getExcercise(current, new CallBack<Excercise>() {
            @Override
            public void call(Excercise data) {
                model.check(out, data);
            }
        });
    }
}