package com.example.adminequitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SalarieeActivity extends AppCompatActivity {
    private static final String TAG = "SalarieeActivity";
    private ListView userList;
    private SearchView searchfilter;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salariee);
        Log.i(TAG, "on create");
        userList = findViewById(R.id.salarieeList);
        searchfilter = findViewById(R.id.searchUser);
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
                        user.setEmail(c.getString("email"));
                        user.setPhone(c.getString("userPhone"));
                        user.setType(c.getString("userType"));
                        users.add(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "user name: " + users.get(i).getFullName());
                }
                Log.i(TAG, "user size: " + users.size());
                Log.i(TAG, "user type 0 : " + users.get(0).getType());
                adapter = new UserAdapter(SalarieeActivity.this, users);
                userList.setAdapter(adapter);
                searchuserfilter(users);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void searchuserfilter(ArrayList<User> users) {
        searchfilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<User> newUsers = new ArrayList<>();
                for(User user:users)
                    if(user.getFullName().toLowerCase().contains(newText.toLowerCase()))
                        newUsers.add(user);
                adapter = new UserAdapter(SalarieeActivity.this, newUsers);
                userList.setAdapter(adapter);
                return false;
            }
        });
    }

    public void clientsList(View view) {
        Intent iClient = new Intent(getApplicationContext(), ClientsActivity.class);
        startActivity(iClient);
        finish();
    }

    public void home(View view) {
        finish();
    }

    public void logoutClicked(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
        Intent Ilogout = new Intent(getApplicationContext(), login.class);
        startActivity(Ilogout);
        finish();
    }
}