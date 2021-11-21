package com.example.clientemploiequitationapp;

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


public class CalenderAdapter extends RecyclerView.Adapter<CalenderViewHolder> {
    private static final String TAG = "CalenderAdapter";
    private final ArrayList<String> daysOfMounth;
    private final OnItemLitener onItemLitener;
    private final Client client;
    private LocalDate selectedDate;

    public CalenderAdapter(ArrayList<String> daysOfMounth, OnItemLitener onItemLitener, Client client, LocalDate selectedDate) {
        this.daysOfMounth = daysOfMounth;
        this.onItemLitener = onItemLitener;
        this.client = client;
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

        for(int i = 0; i < client.getSeances().size(); i++){
            int year = client.getSeances().get(i).getYear();
            int month = client.getSeances().get(i).getMonth();
            int day = client.getSeances().get(i).getDay();
            String time = (String) client.getSeances().get(i).getTime().substring(0,5);
            int duration = client.getSeances().get(i).getDuration();
            int isDone = client.getSeances().get(i).isDone();
            if(LocalDate.now().getYear() == selectedYear && LocalDate.now().getMonthValue() == selectedMonth && LocalDate.now().getDayOfMonth() == selectedDay){
                holder.dayOfMounth.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if(year == selectedYear && month == selectedMonth && day == selectedDay){
                //holder.dayOfMounth.setTypeface(Typeface.DEFAULT_BOLD);
                holder.itemView.setBackgroundResource(R.drawable.cell_corners);
                holder.time.setText(time);
                holder.duration.setText(duration+"min");
                holder.seance = client.getSeances().get(i);
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
