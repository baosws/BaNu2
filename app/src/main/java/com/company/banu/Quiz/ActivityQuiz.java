package com.company.banu.Quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.util.ByteBufferUtil;
import com.company.banu.Backend;
import com.company.banu.CallBack;
import com.company.banu.Classifier;
import com.company.banu.R;
import com.company.banu.WatchLectures.LectureItem.Lecture;
import com.nex3z.fingerpaintview.FingerPaintView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    @BindView(R.id.btn_done)
    Button btnDone;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.tv_numQuiz)
    TextView tvNumQuiz;

    QuizPresenter presenter;
    Dialog dialogPause, dialogDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        try {
            presenter = new QuizPresenter(this, new Classifier(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getData();
    }

    public void getData() {
        Intent intent = getIntent();
        Lecture lecture = (Lecture)Backend.getCache(intent.getStringExtra("lectureId"));
        final String level = intent.getStringExtra("level");
        lecture.getExcercises(new CallBack<HashMap<QuizLevel, ArrayList<Excercise>>>() {
            @Override
            public void call(HashMap<QuizLevel, ArrayList<Excercise>> data) {
                presenter.init();
                QuizLevel key = QuizLevel.valueOf(level);
                presenter.setExcercises(data.containsKey(key) ? data.get(key) : new ArrayList<Excercise>());
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

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btag", String.format("ActivityQuiz:onClick: ecec"));
                long startTime = SystemClock.uptimeMillis();
                Bitmap bitmap = fpvPaint.exportToBitmap(Classifier.IMG_WIDTH, Classifier.IMG_HEIGHT);
                long timeCost = SystemClock.uptimeMillis() - startTime;
                Log.d("btag", String.format("ActivityQuiz:onClick: time: %d", timeCost));
                presenter.check(bitmap);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("btag", String.format("ActivityQuiz:onClick: clicked"));
                fpvPaint.clear();
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

    private void setDialogListener()
    {
        Button btnHome = dialogPause.findViewById(R.id.btn_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnContinue = dialogPause.findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                dialogPause.dismiss();
            }
        });

        Button btnRestart = dialogPause.findViewById(R.id.btn_restart);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPause.dismiss();
                presenter.restart();
            }
        });
    }

    @Override
    public void showDialogPause() {
        dialogPause = new Dialog(this);
        dialogPause.setCanceledOnTouchOutside(false);
        dialogPause.setContentView(R.layout.dialog_quiz);
        setDialogListener();
        dialogPause.show();
    }

    private void bindResultToDialog()
    {
        TextView tvResult = dialogDone.findViewById(R.id.tv_result);
        tvResult.setText(tvScore.getText().toString() + tvNumQuiz.getText());

        Button btnDoAgain = dialogDone.findViewById(R.id.btn_doAgain);
        btnDoAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        Button btnDoneResult = dialogDone.findViewById(R.id.btn_done);
        btnDoneResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void showDialogDone() {
        dialogDone = new Dialog(this);
        dialogDone.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogDone.setCanceledOnTouchOutside(false);
        dialogDone.setContentView(R.layout.dialog_result_exercise);
        bindResultToDialog();
        dialogDone.show();
    }

    public void updateScore() {
        int score = Integer.valueOf(tvScore.getText().toString());
        score++;
        tvScore.setText(Integer.toString(score));
    }

    @Override
    public void resetScore() {
        tvScore.setText(Integer.toString(0));
    }

    @Override
    public void setImage(Bitmap bitmap) {
        imgQuestion.setImageBitmap(bitmap);
    }

    @Override
    public void clearPainter() {
        fpvPaint.clear();
    }

    @Override
    public void setNumQuiz(int size) {
        tvNumQuiz.setText("/" + size);
    }

}