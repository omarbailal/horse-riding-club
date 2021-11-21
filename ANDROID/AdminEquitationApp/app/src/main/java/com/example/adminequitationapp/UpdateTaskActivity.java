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

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UpdateTaskActivity extends AppCompatActivity {
    private static final String TAG = "UpdateTaskActivity";
    EditText year, month, day, time;
    TextView errorMsg;
    int yearV, monthV, dayV, taskID;
    String timeV;
    private static String dateUpdated = "";
    Timestamp startDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        year = findViewById(R.id.updateYear);
        month = findViewById(R.id.updateMonth);
        day = findViewById(R.id.updateDay);
        time = findViewById(R.id.updateTime);
        errorMsg = findViewById(R.id.errorMsg);
        taskID = getIntent().getIntExtra(TaskAdapter.EXTRA_TASKID, 0);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateTask(View view) {
        if(!String.valueOf(year.getText()).equals("") && !String.valueOf(month.getText()).equals("") && !String.valueOf(day.getText()).equals("")){
            yearV = Integer.parseInt(String.valueOf(year.getText()));
            monthV = Integer.parseInt(String.valueOf(month.getText()));
            dayV = Integer.parseInt(String.valueOf(day.getText()));
            timeV = String.valueOf(time.getText());
            try {
                LocalDate date = LocalDate.of(yearV, monthV, dayV);
                if(date.isBefore(LocalDate.now()))
                    errorMsg.setText("the date must be after current date");
                else{
                    dateUpdated = yearV + "-" + monthV + "-" + dayV + " " + timeV;
                    startDate = Timestamp.valueOf(dateUpdated);

                    Log.i(TAG, "dateUpdated : " + dateUpdated);
                    Log.i(TAG, "time stamp : " + startDate);
                    updateDate();
                }

            }catch(Exception e){
                errorMsg.setText("invalide date");
            }
        }
        else  errorMsg.setText("all fields are required");
    }
    private void updateDate() {
        StringRequest request = new StringRequest(Request.Method.PUT,
                "http://" + login.ipAddress + "/equitationApp/public/api/updateTaskDate/" + taskID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject r = new JSONObject(response);
                            Log.i(TAG, "dateUpdated onresponse: " + dateUpdated);
                            Log.i(TAG, "update task response :" + r);
                            if(r.getString("msg") != "success")
                                errorMsg.setText(r.getString("msg"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "update task response :" + error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Log.i(TAG, "startDate map: " + startDate);
                Map<String, String> params = new HashMap<>();
                params.put("startDate", String.valueOf(startDate));
                //params.put("startDate", "2021-12-01 10:00:00");
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        finish();
    }

    public void closeActivity(View view) {
        finish();
    }
}