package com.company.banu.Quiz;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.company.banu.R;
import com.nex3z.fingerpaintview.FingerPaintView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizView extends AppCompatActivity {

    @BindView(R.id.tv_score) TextView tvScore;
    @BindView(R.id.fpv_paint) FingerPaintView fpvPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        ButterKnife.bind(this);
        initPaintView();
    }

    private void initPaintView() {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setDither(true);
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeJoin(Paint.Join.ROUND);
        p.setStrokeCap(Paint.Cap.ROUND);
        p.setStrokeWidth(21);
        fpvPaint.setPen(p);
    }
}
