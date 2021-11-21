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

public class AddClientActivity extends AppCompatActivity {
    private static final String TAG = "AddClientActivity";
    EditText fname, lname, phone, email, password;
    String fnameV, lnameV, phoneV, emailV, passwordV;
    TextView errorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        fname = findViewById(R.id.fName);
        lname = findViewById(R.id.lName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        errorText = findViewById(R.id.errorMsg);
    }
    
    public void addClient(View view) {
        if(!String.valueOf(fname.getText()).equals("") && !String.valueOf(lname.getText()).equals("") && !String.valueOf(phone.getText()).equals("")&& !String.valueOf(email.getText()).equals("")&& !String.valueOf(password.getText()).equals("")) {
            fnameV = String.valueOf(fname.getText());
            lnameV = String.valueOf(lname.getText());
            phoneV = String.valueOf(phone.getText());
            emailV = String.valueOf(email.getText());
            passwordV = String.valueOf(password.getText());
            StringRequest request = new StringRequest(Request.Method.POST,
                    "http://" + login.ipAddress + "/equitationApp/public/api/addClient",
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
                    params.put("fName", fnameV);
                    params.put("lName", lnameV);
                    params.put("email", emailV);
                    params.put("password", passwordV);
                    params.put("clientPhone", phoneV);
                    return params;
                }
            };
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
            finish();
        }
        else errorText.setText("all fields are required");

    }

    public void closeActivity(View view) {
        finish();
    }
}