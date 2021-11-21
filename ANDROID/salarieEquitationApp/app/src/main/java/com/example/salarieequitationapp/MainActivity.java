package com.example.salarieequitationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private static final String TAG = "MainActivity";
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private User user;
    private ListView seancesList, tasksList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (User) getIntent().getSerializableExtra(login.EXTRA_USER);
        Log.i(TAG, "user name: " + user.fName);
        calendarRecyclerView = findViewById(R.id.weekDays);
        monthYearText = findViewById(R.id.monthYearText);
        selectedDate = LocalDate.now();
        seancesList = findViewById(R.id.seances);
        tasksList = findViewById(R.id.tasks);
        //if(user.getSeances() == null && user.getTasks() == null)
        getSeancesAndTasks();

    }

    public void getSeancesAndTasks(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                "http://" + login.ipAddress + "/equitationApp/public/api/seancesAndTasks/" + user.getId(),
                null,
                new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG,"getSeancesAndTasks" + response);
                        if(user.getSeances() == null){
                            try {
                                for(int i = 0; i < response.getJSONArray("seances").length(); i++){
                                    JSONObject s = response.getJSONArray("seances").getJSONObject(i);
                                    Seance seance = new Seance(s.getString("startDate"), s.getInt("durationMinut"), s.getInt("isDone"), s.getInt("id"), s.getInt("clientID"));
                                    Log.i(TAG,"adding seance to monitor " + s);
                                    user.addSeance(seance);

                                }
                            } catch (JSONException e) {
                                Log.e(TAG,"adding seance to monitor failed" + e);
                                e.printStackTrace();
                            }
                        }
                        if(user.getTasks() == null) {
                            try {
                                for (int i = 0; i < response.getJSONArray("tasks").length(); i++) {
                                    JSONObject t = response.getJSONArray("tasks").getJSONObject(i);
                                    Task task = new Task(t.getString("startDate"), t.getString("title"), t.getString("detail"), t.getInt("durationMinut"), t.getInt("isDone"), t.getInt("id"));
                                    user.addTask(task);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i(TAG, "user get s and t: " + user);
                        setWeekView();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(selectedDate.with(DayOfWeek.MONDAY)));
        Log.i("TAG", "days of week array: " + daysInWeekArray(selectedDate));
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInWeekArray(selectedDate), this, selectedDate, getApplicationContext(), user);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        calendarRecyclerView.setLayoutManager(linearLayoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setSeances();
        setTasks();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setSeances(){
        List<Seance> seances= user.getSeances();
        List<Seance> seancesInWeek = new ArrayList<>();
        for(Seance seance:seances){
            LocalDate date =  LocalDate.of(seance.getYear(), seance.getMonth(), seance.getDay());
            if(inSameWeek(selectedDate, date))
                seancesInWeek.add(seance);
        }
        if(seancesInWeek != null) {
            getAllClients(seancesInWeek);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setTasks(){
        List<Task> tasks = user.getTasks();
        List<Task> tasksInWeek = new ArrayList<>();
        for(Task task:tasks){
            LocalDate date =  LocalDate.of(task.getYear(), task.getMonth(), task.getDay());
            Log.i(TAG, "date compare " + selectedDate + " " + date );
            if(inSameWeek(selectedDate, date))
                tasksInWeek.add(task);
        }
        if(tasksInWeek != null)
            tasksList.setAdapter(new TaskAdapter(MainActivity.this, tasksInWeek));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    boolean inSameWeek(LocalDate date1, LocalDate date2){
        date1 = date1.with(DayOfWeek.MONDAY);
        int lun = date1.with(DayOfWeek.MONDAY).getDayOfMonth();
        int dim = date1.with(DayOfWeek.SUNDAY).getDayOfMonth();
        if(lun > dim){
            date1 = date1.plusMonths(1);
            dim = dim + date1.lengthOfMonth();
        }
        if(date1.getYear() == date2.getYear() ){
            int monthChanged = 0;
            for(int i = lun; i <= dim; i++) {
                int day = i;
                if(day > date1.lengthOfMonth()) {
                    day = day - date1.lengthOfMonth();
                    if(monthChanged == -1){
                        date1 = date1.plusMonths(1);
                        monthChanged = 0;
                    }
                }else if(dim > date1.lengthOfMonth() && i == lun){
                    date1 = date1.minusMonths(1);
                    monthChanged = -1;
                }
                Log.i(TAG , "current date : " + day + "-" + date1.getMonthValue() + "-" + date1.getYear());
                if (date2.getDayOfMonth() == day && date1.getMonthValue() == date2.getMonthValue())
                    return true;
            }
        }
        return false;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    boolean inSameDay(LocalDate date1, LocalDate date2){
        int year1, month1, day1;
        year1 = date1.getYear();
        month1 = date1.getMonthValue();
        day1 = date1.getDayOfMonth();
        int lun = date1.with(DayOfWeek.MONDAY).getDayOfMonth();
        int dim = date1.with(DayOfWeek.SUNDAY).getDayOfMonth();
        if(lun > dim && day1 < 7){
            if(month1 == 12){
                month1 = 1;
                year1 ++;
            }else
                month1 ++;
        }
        Log.i(TAG, "in same day date1: " + year1 + "-" + month1 + "-" + day1);
        if(year1 == date2.getYear() && month1 == date2.getMonthValue() && day1 == date2.getDayOfMonth())
            return true;
        else
            return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInWeekArray(LocalDate date) {
        ArrayList<String> daysInWeekArray = new ArrayList<>();
        //YearMonth yearMonth = YearMonth.from(date);
        LocalDate firstOfWeek = date.with(DayOfWeek.MONDAY);
        int firstDayOfWeek = firstOfWeek.getDayOfMonth();
        int daysInMonth = firstOfWeek.lengthOfMonth();
        for(int i = 0; i < 7; i++){
//            if(firstDayOfWeek + i > daysInMonth){
//                firstDayOfWeek = firstDayOfWeek - daysInMonth;
//
//            }
            daysInWeekArray.add(String.valueOf(firstDayOfWeek + i));
        }
        return daysInWeekArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void prevWeekAction(View view) {
        selectedDate = selectedDate.minusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextWeekAction(View view) {
        selectedDate = selectedDate.plusWeeks(1);
        setWeekView();
    }

    public void logoutClicked(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
        Intent Ilogout = new Intent(getApplicationContext(), login.class);
        startActivity(Ilogout);
        finish();
    }
    public void getAllClients(List<Seance> seances){
        ArrayList<Client> clients = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, "http://" + login.ipAddress + "/equitationApp/public/api/client/",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            JSONObject c = null;
                            try {
                                c = response.getJSONObject(i);
                                Client client = new Client(c.getInt("clientID"), c.getString("fName")+"  " + c.getString("lName"));
                                clients.add(client);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        seancesList.setAdapter(new SeanceAdapter(MainActivity.this, seances, clients));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemclick(int position, String dayText) {
        //set seances in day clicked
        Log.d(TAG, "onItemClick: clicked.");
        LocalDate clickedDate = LocalDate.of(selectedDate.getYear(), selectedDate.getMonthValue(), Integer.parseInt(dayText));
        List<Seance> seances= user.getSeances();
        List<Seance> seancesInDay = new ArrayList<>();
        for(Seance seance:seances){
            LocalDate date =  LocalDate.of(seance.getYear(), seance.getMonth(), seance.getDay());
            Log.i(TAG, "date compare ::seance " + clickedDate + " " + date );
            if(inSameDay(clickedDate, date)){
                seancesInDay.add(seance);
                Log.i(TAG, "seance added");
            }
        }
        if(seancesInDay != null) {
            getAllClients(seancesInDay);
        }

        //set tasks in day clicked
        List<Task> tasks = user.getTasks();
        List<Task> tasksInDay = new ArrayList<>();
        for(Task task:tasks){
            LocalDate date =  LocalDate.of(task.getYear(), task.getMonth(), task.getDay());
            Log.i(TAG, "date compare ::task " + clickedDate + " " + date );
            if(inSameDay(clickedDate, date)){
                tasksInDay.add(task);
                Log.i(TAG, "task added");
            }
        }
        if(tasksInDay != null){
            tasksList.setAdapter(new TaskAdapter(MainActivity.this, tasksInDay));
        }

    }


}