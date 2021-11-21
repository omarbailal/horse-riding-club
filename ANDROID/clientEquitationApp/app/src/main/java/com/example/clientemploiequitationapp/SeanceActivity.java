package com.example.clientemploiequitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SeanceActivity extends AppCompatActivity {
    private static final String TAG = "SeanceActivity";
    TextView dateText, timeText, isDoneText;
    EditText remarqueEditeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance);
        isDoneText = findViewById(R.id.textIsDone);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        Seance seance = (Seance) getIntent().getSerializableExtra(MainActivity.EXSTRA_SEANCE);
        if(seance.isDone() == 1){
            isDoneText.setText("done");
            isDoneText.setTextColor(Color.parseColor("#228c22"));
        }else{
            isDoneText.setText("not done");
            isDoneText.setTextColor(Color.parseColor("#C61C5A"));
        }
        dateText.setText(seance.getDay() + " " + seance.getMonthName() + ", " + seance.getYear());
        timeText.setText(seance.getTime().substring(0,5) + "    " + seance.getDuration() + "min");
        MyDB db = new MyDB(this);
        String remarque = db.readRemarque(seance.getId());
        remarqueEditeText = findViewById(R.id.remarqueEditeText);
        remarqueEditeText.setText(remarque);

    }

    public void closeActivity(View view) {
        finish();
    }

    public void saveRemarque(View view) {
        EditText remarque = findViewById(R.id.remarqueEditeText);
        Seance seance = (Seance) getIntent().getSerializableExtra(MainActivity.EXSTRA_SEANCE);
        String text = String.valueOf(remarque.getText());
        MyDB db = new MyDB(this);
        db.insertRemarque(seance.getId(), text);
        finish();
    }
}