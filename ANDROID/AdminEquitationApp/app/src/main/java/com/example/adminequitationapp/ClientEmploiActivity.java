package com.example.adminequitationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ClientEmploiActivity extends AppCompatActivity implements CalenderMonthAdapter.OnItemLitener {
    private static final String TAG = "ClientEmploiActivity";
    public static final String EXTRA_SEANCE = "seanceExtra";
    public static final String EXTRA_DATE = "dateExtra";
    public static final String EXTRA_CLIENTID = "clientidExtra";
    String clientName;
    int clientId;
    TextView clientNametext;
    private static TextView monthYearText;
    private static RecyclerView calenderRecyclerView;
    private static LocalDate selectedDate;
    ArrayList<Seance> seances = new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_emploi);
        clientId = getIntent().getIntExtra(ClientAdapter.EXTRA_CLIENTID, 0);
        clientName = getIntent().getStringExtra(ClientAdapter.EXTRA_CLIENTNAME);
        clientNametext = findViewById(R.id.clientNameText);
        clientNametext.setText("Client: " + clientName);
        calenderRecyclerView = findViewById(R.id.calenderRecyclerView);
        monthYearText = findViewById(R.id.monthYearText);
        selectedDate = LocalDate.now();
        getSeances();
    }

    private void getSeances() {
        JsonArrayRequest seanceReq = new JsonArrayRequest(Request.Method.GET,
                "http://" + login.ipAddress + "/equitationApp/public/api/seanceClient/" + clientId,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                Seance seance = new Seance();
                                JSONObject s = response.getJSONObject(i);
                                seance.setStartDate(s.getString("startDate"));
                                seance.setDuration(s.getInt("durationMinut"));
                                seance.setIsDone(s.getInt("isDone"));
                                seance.setId(s.getInt("id"));
                                seances.add(seance);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        setMonthView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, String.valueOf(error));
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(seanceReq);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalenderMonthAdapter calenderAdapter = new CalenderMonthAdapter(daysInMonth, this, seances, selectedDate);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calenderAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
                daysInMonthArray.add("");
            else
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prevMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }


    public void reloadData(View view) {
        recreate();
    }

    public void back(View view) {
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText, Seance seance) {
        if(!dayText.equals("")){
            LocalDate clickedDate = LocalDate.of(selectedDate.getYear(), selectedDate.getMonthValue(), Integer.parseInt(dayText));
            Log.i(TAG, "date clicked :" + clickedDate.getYear()+"-"+clickedDate.getMonthValue()+"-"+clickedDate.getDayOfMonth());
            if(seance != null){
                if(seance.getIsDone() == 0){
                    Intent iUpdateSeance = new Intent(getApplicationContext(), UpdateSeanceActivity.class).putExtra(EXTRA_SEANCE, seance);
                    startActivity(iUpdateSeance);
                }
            }else{
                if(clickedDate.isAfter(LocalDate.now())){
                    Intent iAddSeance = new Intent(getApplicationContext(), AddSeanceActivity.class).putExtra(EXTRA_DATE, clickedDate);
                    iAddSeance.putExtra(EXTRA_CLIENTID, clientId);
                    startActivity(iAddSeance);
                }
            }
        }
    }

}