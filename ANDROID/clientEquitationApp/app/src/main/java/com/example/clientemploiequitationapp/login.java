package com.example.clientemploiequitationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private static final String TAG = "login";

    public static String ipAddress = "192.168.1.12";
    public static final String EXTRA_CLIENT = "clientObj";
    private static final String PREF_EMAIL = "prefEmail";
    private static final String PREF_PASSWORD = "prefPassword";
    private static final String PREF_AUTOLOG = "prefAutolog";
    TextInputEditText inputEmail, inputPassword;
    TextView errorText;
    private static final Client client = new Client();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(preferences.getBoolean(PREF_AUTOLOG, false)){
            Log.i(TAG, "pref email: " + preferences.getString(PREF_EMAIL,""));
            Log.i(TAG, "pref password: " + preferences.getString(PREF_PASSWORD,""));
            authenticate(preferences.getString(PREF_EMAIL,""), preferences.getString(PREF_PASSWORD,""));
        }
        inputEmail = findViewById(R.id.emailInput);
        inputPassword = findViewById(R.id.passwordInput);
        errorText = findViewById(R.id.errorMsg);
    }

    public void loginClicked(View view) {
        String email, password;
        email = String.valueOf(inputEmail.getText());
        password = String.valueOf(inputPassword.getText());
        if(!email.equals("") && !password.equals("")){
            authenticate(email, password);
        }else errorText.setText("all fields are required");
    }

    public void authenticate(String email, String password){
        StringRequest loginReq = new StringRequest(Request.Method.POST,
                "http://" + ipAddress + "/equitationApp/public/api/clientLogin",
                new Response.Listener<String>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            String login = json.getString("login");
                            if(login.equals("success")){
                                JSONObject clientJson = json.getJSONObject("client");
                                client.setId(clientJson.getInt("clientID"));
                                client.setfName(clientJson.getString("fName"));
                                client.setlName(clientJson.getString("lName"));
                                client.setEmail(clientJson.getString("email"));
                                client.setPhone(clientJson.getString("clientPhone"));
                                client.setPhoto(clientJson.getString("photo"));
                                //client.setBirthDate((Date) clientJson.get("birthDate"));
                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString(PREF_EMAIL, email);
                                editor.putString(PREF_PASSWORD, password);
                                editor.putBoolean(PREF_AUTOLOG, true);

                                editor.apply();
                                Intent Ilogin = new Intent(getApplicationContext(), MainActivity.class).putExtra(EXTRA_CLIENT, client);
                                startActivity(Ilogin);
                                finish();
                                //Toast.makeText(login.this, "success login, welcom " + client.getString("fName"), Toast.LENGTH_LONG).show();
                            }else errorText.setText("email or password incorrect");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("loginClass", "error" + e.toString());
                        }
                        Log.i("test",response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorText.setText("Connection error");
                Log.e(TAG, error.toString());
            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(loginReq);

    }
}