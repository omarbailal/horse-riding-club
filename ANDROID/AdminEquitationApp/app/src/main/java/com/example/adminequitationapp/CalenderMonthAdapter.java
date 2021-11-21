package com.example.adminequitationapp;

import android.annotation.SuppressLint;
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

import java.time.LocalDate;
import java.util.ArrayList;


public class CalenderMonthAdapter extends RecyclerView.Adapter<CalenderViewHolder> {
    private static final String TAG = "CalenderAdapter";
    private final ArrayList<String> daysOfMounth;
    private final OnItemLitener onItemLitener;
    private final ArrayList<Seance> seances;
    private LocalDate selectedDate;

    public CalenderMonthAdapter(ArrayList<String> daysOfMounth, OnItemLitener onItemLitener, ArrayList<Seance> seances, LocalDate selectedDate) {
        this.daysOfMounth = daysOfMounth;
        this.onItemLitener = onItemLitener;
        this.seances = seances;
        this.selectedDate = selectedDate;
    }

    @NonNull
    @Override
    public CalenderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.15);
        return new CalenderViewHolder(view, onItemLitener);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CalenderViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        holder.dayOfMounth.setText(daysOfMounth.get(position));
        int selectedYear = selectedDate.getYear();
        int selectedMonth = selectedDate.getMonthValue();
        int selectedDay = 0;
        if(daysOfMounth.get(position) != "") {
            selectedDay = Integer.parseInt(daysOfMounth.get(position));
        }

        for(int i = 0; i < seances.size(); i++){
            int year = seances.get(i).getYear();
            int month = seances.get(i).getMonth();
            int day = seances.get(i).getDay();
            String time = (String) seances.get(i).getTime().substring(0,5);
            int duration = seances.get(i).getDuration();
            int isDone = seances.get(i).getIsDone();
            if(LocalDate.now().getYear() == selectedYear && LocalDate.now().getMonthValue() == selectedMonth && LocalDate.now().getDayOfMonth() == selectedDay){
                holder.dayOfMounth.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if(year == selectedYear && month == selectedMonth && day == selectedDay){
                //holder.dayOfMounth.setTypeface(Typeface.DEFAULT_BOLD);
                holder.itemView.setBackgroundResource(R.drawable.cell_corners);
                holder.time.setText(time);
                holder.duration.setText(duration+"min");
                holder.seance = seances.get(i);
                if(isDone == 0)
                    holder.dayOfMounth.setTextColor(Color.parseColor("#C61C5A"));
                else
                    holder.dayOfMounth.setTextColor(Color.parseColor("#228c22"));
            }
        }

    }


    @Override
    public int getItemCount() {
        return daysOfMounth.size();
    }



    public interface OnItemLitener{
        @RequiresApi(api = Build.VERSION_CODES.O)
        void onItemClick(int position, String dayText, Seance seance);
    }

}
