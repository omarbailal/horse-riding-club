package com.example.adminequitationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {
    private static final String TAG = "AddTaskActivity";
    EditText time, duration, rep, title, details, min;
    TextView errorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        time = findViewById(R.id.addTime);
        duration = findViewById(R.id.addDuration);
        errorText = findViewById(R.id.errorMsg);
        min = findViewById(R.id.min);
        min.setEnabled(false);
        rep = findViewById(R.id.addRep);
        title = findViewById(R.id.addTitle);
        details = findViewById(R.id.addDetails);
        rep.setText("1");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addTask(View view) {
        int userID = getIntent().getIntExtra(SalarieeEmploiActivity.EXTRA_USERID, 1);
        LocalDate clickedDate = (LocalDate) getIntent().getSerializableExtra(ClientEmploiActivity.EXTRA_DATE);
        if (!String.valueOf(duration.getText()).equals("") && !String.valueOf(rep.getText()).equals("") && !String.valueOf(time.getText()).equals("") && !String.valueOf(title.getText()).equals("") && !String.valueOf(details.getText()).equals("")){
            String timeV = String.valueOf(time.getText());
            int durationV = Integer.parseInt(String.valueOf(duration.getText()));
            int repV = Integer.parseInt(String.valueOf(rep.getText()));
            String titleV = String.valueOf(title.getText());
            String detailsV = String.valueOf(details.getText());
            for (int i = 0; i < repV; i++) {
                String startDate = clickedDate + " " + timeV;
                StringRequest request = new StringRequest(Request.Method.POST,
                        "http://" + login.ipAddress + "/equitationApp/public/api/addTask",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i(TAG, response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(TAG, String.valueOf(error));
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("startDate", startDate);
                        params.put("durationMinut", String.valueOf(durationV));
                        params.put("title", titleV);
                        params.put("detail", detailsV);
                        params.put("user_Fk", String.valueOf(userID));
                        return params;
                    }
                };
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
                clickedDate = clickedDate.plusWeeks(1);
            }
            finish();
        }else  errorText.setText("all fields are required");




    }

    public void closeActivity(View view) {
        finish();
    }
}