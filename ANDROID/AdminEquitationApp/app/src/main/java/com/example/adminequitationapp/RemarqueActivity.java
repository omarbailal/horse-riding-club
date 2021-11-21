package com.example.adminequitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RemarqueActivity extends AppCompatActivity {
    private static final String TAG = "RemarqueActivity";
    TextView dateText, timeText, clientText;
    EditText remarqueEditeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarque);
        dateText = findViewById(R.id.dateText);
        timeText = findViewById(R.id.timeText);
        clientText = findViewById(R.id.clientName);
        Seance seance = (Seance) getIntent().getSerializableExtra(SeanceAdapter.EXSTRA_SEANCE);
        String clientName = (String) getIntent().getStringExtra(SeanceAdapter.EXSTRA_CLIENT);
        dateText.setText(seance.getDay() + " " + seance.getMonthName() + ", " + seance.getYear());
        timeText.setText(seance.getTime().substring(0,5) + "    " + seance.getDuration() + "min");
        clientText.setText("Client: " + clientName);
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
        Seance seance = (Seance) getIntent().getSerializableExtra(SeanceAdapter.EXSTRA_SEANCE);
        String text = String.valueOf(remarque.getText());
        MyDB db = new MyDB(this);
        db.insertRemarque(seance.getId(), text);
        finish();
    }
}