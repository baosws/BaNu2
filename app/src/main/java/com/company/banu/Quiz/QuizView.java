package com.company.banu.Quiz;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.company.banu.R;
import com.nex3z.fingerpaintview.FingerPaintView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizView extends AppCompatActivity {

    @BindView(R.id.tv_score) TextView tvScore;
    @BindView(R.id.fpv_paint) FingerPaintView fpvPaint;
    @BindView(R.id.btn_pause)
    ImageButton btnPause;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_view);
        ButterKnife.bind(this);
        initPaintView();

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

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

    void showDialog()
    {
        dialog = new Dialog(QuizView.this);
        dialog.setContentView(R.layout.dialog_quiz);

        dialog.show();
    }
}
