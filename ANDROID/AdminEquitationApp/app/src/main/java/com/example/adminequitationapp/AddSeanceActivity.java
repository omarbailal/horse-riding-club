package com.example.adminequitationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddSeanceActivity extends AppCompatActivity {
    private static final String TAG = "AddSeanceActivity";
    EditText time, duration, rep, min;
    TextView errorText;
    Spinner monitor;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seance);
        monitor = findViewById(R.id.addMonitor);
        time = findViewById(R.id.addTime);
        duration = findViewById(R.id.addDuration);
        errorText = findViewById(R.id.errorMsg);
        min = findViewById(R.id.min);
        min.setEnabled(false);
        rep = findViewById(R.id.addRep);
        rep.setText("1");
        getSalatieeList();

    }

    private void getSalatieeList() {
        ArrayList<User> users = new ArrayList<>();
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET,
                "http://" + login.ipAddress + "/equitationApp/public/api/user",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i(TAG, "response: " + response);
                for(int i = 0; i < response.length(); i++){
                    JSONObject c = null;
                    try {
                        User user = new User();
                        c = response.getJSONObject(i);
                        user.setId(c.getInt("userID"));
                        user.setFullName(c.getString("userFName").toLowerCase()+" " + c.getString("userLName").toLowerCase());
                        users.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "user name: " + users.get(i).getFullName());
                }
                Log.i(TAG, "user size: " + users.size());
                Log.i(TAG, "user type 0 : " + users.get(0).getType());
                ArrayAdapter<User> adapter = new ArrayAdapter<User>(getApplicationContext(), android.R.layout.simple_spinner_item, users);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                monitor.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addSeance(View view) {
        User selectedUser = (User) monitor.getSelectedItem();
        int userID = selectedUser.getId();
        int clientId = getIntent().getIntExtra(ClientEmploiActivity.EXTRA_CLIENTID, 0);
        LocalDate clickedDate = (LocalDate) getIntent().getSerializableExtra(ClientEmploiActivity.EXTRA_DATE);
        String timeV = String.valueOf(time.getText());
        int durationV = Integer.parseInt(String.valueOf(duration.getText()));
        int repV = Integer.parseInt(String.valueOf(rep.getText()));
        if(!String.valueOf(duration.getText()).equals("") && !String.valueOf(rep.getText()).equals("") && !String.valueOf(time.getText()).equals(""))
            for(int i=0; i<repV; i++){
                String startDate = clickedDate + " " + timeV;
                StringRequest request = new StringRequest(Request.Method.POST,
                        "http://" + login.ipAddress + "/equitationApp/public/api/addSeance",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i(TAG, response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("clientID", String.valueOf(clientId));
                        params.put("monitorID", String.valueOf(userID));
                        params.put("startDate", startDate);
                        params.put("duration", String.valueOf(durationV));
                        return params;
                    }
                };
                VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
                clickedDate = clickedDate.plusWeeks(1);
            }else  errorText.setText("all fields are required");


        finish();

    }

    public void closeActivity(View view) {
        finish();
    }


}