package com.example.adminequitationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;

public class ClientsActivity extends AppCompatActivity {
    private static final String TAG = "ClientsActivity";
    private ListView clientList;
    private SearchView searchfilter;
    ClientAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);
        clientList = findViewById(R.id.clientList);
        searchfilter = findViewById(R.id.searchClient);
        getClientsList();
    }

    private void getClientsList() {
        ArrayList<Client> clients = new ArrayList<>();
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET,
                "http://" + login.ipAddress + "/equitationApp/public/api/client",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    JSONObject c = null;
                    try {
                        Client client = new Client();
                        c = response.getJSONObject(i);
                        client.setId(c.getInt("clientID"));
                        client.setFullName(c.getString("fName").toLowerCase()+" "+ c.getString("lName").toLowerCase());
                        client.setEmail(c.getString("email"));
                        client.setPhone(c.getString("clientPhone"));
                        clients.add(client);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "client name: " + clients.get(i).getFullName());
                }
                Log.i(TAG, "clients size: " + clients.size());
                Log.i(TAG, "client name 0 : " + clients.get(0).getFullName());
                adapter = new ClientAdapter(ClientsActivity.this, clients);
                clientList.setAdapter(adapter);
                searchFilter(clients);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error: " + error);
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void searchFilter(ArrayList<Client> clients) {
        searchfilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Client> newClients = new ArrayList<>();
                for(Client client:clients)
                    if(client.getFullName().toLowerCase().contains(newText.toLowerCase()))
                        newClients.add(client);
                adapter = new ClientAdapter(ClientsActivity.this, newClients);
                clientList.setAdapter(adapter);
                return false;
            }
        });
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
    public void salarieeList(View view) {
        Intent iUser = new Intent(getApplicationContext(), SalarieeActivity.class);
        startActivity(iUser);
        finish();
    }

    public void addClient(View view) {
        Intent iAddClient = new Intent(getApplicationContext(), AddClientActivity.class);
        startActivity(iAddClient);
    }
}