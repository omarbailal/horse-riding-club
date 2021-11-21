package com.example.clientemploiequitationapp;

import androidx.annotation.Nullable;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements CalenderAdapter.OnItemLitener{
    private static final String TAG = "MainActivity";
    public static final String EXSTRA_SEANCE = "extraSeance";
    private static final int ACT_SEANCE = 100;
    private TextView mainTitle;
    private static TextView monthYearText;
    private static RecyclerView calenderRecyclerView;
    private static LocalDate selectedDate;
    private static Client client = new Client();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = (Client) getIntent().getSerializableExtra(login.EXTRA_CLIENT);
        calenderRecyclerView = findViewById(R.id.calenderRecyclerView);
        monthYearText = findViewById(R.id.monthYearText);
        selectedDate = LocalDate.now();
        JsonArrayRequest seanceReq = new JsonArrayRequest(Request.Method.GET,
                "http://" + login.ipAddress + "/equitationApp/public/api/seanceClient/" + client.getId(),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject s = response.getJSONObject(i);
                                Seance seance = new Seance(s.getString("startDate"), s.getInt("durationMinut"), s.getInt("isDone"), s.getInt("id"));
                                client.addSeance(seance);
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

    @Override
    protected void onResume() {
        super.onResume();

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);
        CalenderAdapter calenderAdapter = new CalenderAdapter(daysInMonth, this, client, selectedDate);
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



    public void logoutClicked(View view) {
        client = new Client();
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
        if(seance != null){
            Log.d(TAG, seance.getStartDate());
            Intent iSeance = new Intent(getApplicationContext(), SeanceActivity.class).putExtra(EXSTRA_SEANCE, seance);
            startActivity(iSeance);
        }
        Log.d(TAG, "onItemClick: clicked.");
    }

}