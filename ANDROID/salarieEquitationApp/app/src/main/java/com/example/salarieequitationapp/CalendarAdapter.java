package com.example.salarieequitationapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;


public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>{
    private static final String TAG = "CalendarAdapter";
    private final ArrayList<String> daysOffWeek;
    private final OnItemListener onItemLisener;
    private LocalDate currentDate;
    private Context context;
    private User user;
    private static int colIndex = -1;

    public CalendarAdapter(ArrayList<String> daysOffWeek, OnItemListener onItemLisener, LocalDate currentDate, Context context, User user) {
        this.daysOffWeek = daysOffWeek;
        this.onItemLisener = onItemLisener;
        this.currentDate = currentDate;
        this.context = context;
        this.user = user;
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) ((parent.getWidth()) * 0.142857);
        return new CalendarViewHolder(view, onItemLisener);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder,final int position) {
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.with(DayOfWeek.MONDAY).getMonthValue();
        int currentDay = Integer.valueOf(daysOffWeek.get(position));
        LocalDate firstOfWeek = currentDate.with(DayOfWeek.MONDAY);
        int daysInMonth = firstOfWeek.lengthOfMonth();
        if(currentDay > daysInMonth){
            currentDay = currentDay - daysInMonth;
            if(currentMonth == 12){
                currentMonth = 1;
                currentYear++;
            }else
                currentMonth ++;

        }
        Log.i(TAG , "current date: " + currentDay + "-" + currentMonth + "-" + currentYear);
        int seancesNum = 0, taskNum = 0;
        //String dayItem = currentDay > daysInMonth ? (currentDay - daysInMonth)+"" : currentDay+"";
        holder.dayOfWeek.setText(currentDay+"");
        if(LocalDate.now().getYear() == currentYear && LocalDate.now().getMonthValue() == currentMonth && LocalDate.now().getDayOfMonth() == currentDay){
            holder.dayOfWeek.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if(user.getSeances() != null){
            for(int i = 0; i < user.getSeances().size(); i++){
                int year = user.getSeances().get(i).getYear();
                int month = user.getSeances().get(i).getMonth();
                int day = user.getSeances().get(i).getDay();
                if(year == currentYear && month == currentMonth && day == currentDay){
                    seancesNum++;
                }
            }
            if(seancesNum > 0)
                holder.seancesNumber.setText("s : " + seancesNum);
        }
        if(user.getTasks() != null){
            for(int i = 0; i < user.getTasks().size(); i++){
                int year = user.getTasks().get(i).getYear();
                int month = user.getTasks().get(i).getMonth();
                int day = user.getTasks().get(i).getDay();
                if(year == currentYear && month == currentMonth && day == currentDay){
                    taskNum++;
                }
            }
            if(taskNum > 0)
                holder.tasksNumber.setText("t : " + taskNum);
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                colIndex=position;
//                notifyDataSetChanged();
//            }
//        });
//        if(colIndex==position){
//            holder.dayOfWeek.setTextColor(Color.parseColor("#1E90FF"));
//        }
//        else
//        {
//            holder.dayOfWeek.setTextColor(Color.parseColor("#000000"));
//        }


    }

    @Override
    public int getItemCount() {
        return daysOffWeek.size();
    }

    public  interface OnItemListener {
        void onItemclick(int position, String dayText);
    }


}
