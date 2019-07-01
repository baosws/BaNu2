package com.company.banu.Quiz;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.company.banu.CallBack;
import com.company.banu.Classifier;
import com.company.banu.Notifier.Notifier;
import com.company.banu.R;
import com.company.banu.Result;

import java.util.ArrayList;
import java.util.Random;

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
    }

    public void onPause() {
        view.showDialog();
    }

    public void setExcercises(ArrayList<Excercise> excercises) {
        model.setExcercises(excercises);
        nextQuiz();
    }

    private void nextQuiz() {
        Log.d("btag", String.format("QuizPresenter:nextQuiz: "));
        model.getExcercise(current, new CallBack<Excercise>() {
            @Override
            public void call(Excercise data) {
                if (data != null) {
                    data.getImage(new CallBack<Bitmap>() {
                        @Override
                        public void call(Bitmap data) {
                            view.setImage(data);
                        }
                    });
                }
            }
        });
    }

    public void check(Bitmap bitmap) {
        Result result = model.check(bitmap);
        final String out = String.valueOf(result.getNumber());
        Log.d("btag", String.format("QuizPresenter:check: out = %s, conf = %f, %d", out, result.getProbability(), result.getTimeCost()));
        Toast.makeText((AppCompatActivity)view, (new Random()).nextBoolean() ? "Correct!!" : "Incorrect!!", Toast.LENGTH_SHORT).show();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                model.getExcercises(new CallBack<ArrayList<Excercise>>() {
//                    @Override
//                    public void call(ArrayList<Excercise> data) {
//                        model.check(out, data.get(current), new CallBack<Boolean>() {
//                            @Override
//                            public void call(Boolean data) {
//                                Log.d("btag", String.format("QuizPresenter:call: " + data));
//                                Toast.makeText((AppCompatActivity)view, data ? "Correct!!!" : "Incorrect!!!", Toast.LENGTH_SHORT).show();
//                                nextQuiz();
//                            }
//                        });
//                    }
//                });
//            }
//        }).start();
    }
}

