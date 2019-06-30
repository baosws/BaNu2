package com.company.banu.Quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.nex3z.fingerpaintview.FingerPaintView;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityQuiz extends AppCompatActivity implements QuizView {

    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.fpv_paint)
    FingerPaintView fpvPaint;
    @BindView(R.id.btn_pause)
    ImageButton btnPause;
    @BindView(R.id.img_question)
    ImageView imgQuestion;

    QuizPresenter presenter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        presenter = new QuizPresenter(this);
        presenter.init();
    }

    public void getData() {
        Intent intent = getIntent();
        Lecture lecture = (Lecture)Backend.getCache(intent.getStringExtra("lectureId"));
        final String level = (String)intent.getStringExtra("level");
        lecture.getExcercises(new CallBack<ArrayList<Excercise>>() {
            @Override
            public void call(ArrayList<Excercise> data) {
                final ArrayList<Excercise> excercises = new ArrayList<>();
                final Semaphore semaphore = new Semaphore(1);
                for (final Excercise excercise: excercises) {
                    excercise.getLevel(new CallBack<QuizLevel>() {
                        @Override
                        public void call(QuizLevel data) {
                            Log.d("btag", String.format("ActivityQuiz:call: %s", data.toString()));
                            if (data.toString().equalsIgnoreCase(level)) {
                                try {
                                    semaphore.acquire();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                excercises.add(excercise);
                                semaphore.release();
                            }
                        }
                    });
                }
                presenter.setExcercises(excercises);
            }
        });
    }

    public void getViews() {
        ButterKnife.bind(this);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPause();
            }
        });

        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setDither(true);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(21);
        fpvPaint.setPen(p);
    }

    @Override
    public void showDialog() {
        dialog = new Dialog(this);
        dialog.setTitle("Hehe");
        dialog.setContentView(R.layout.dialog_quiz);
        dialog.show();
    }

    @Override
    public void setImage(Bitmap bitmap) {
        imgQuestion.setImageBitmap(bitmap);
    }
}