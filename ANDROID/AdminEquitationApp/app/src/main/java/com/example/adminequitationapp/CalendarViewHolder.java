package com.example.adminequitationapp;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayOfWeek;
    public final TextView seancesNumber;
    public final TextView tasksNumber;
    //public final TextView borderBottom;
    public final CalendarAdapter.OnItemListener onItemListener;
    LinearLayout cell;
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemLisener) {
        super(itemView);
        dayOfWeek = itemView.findViewById(R.id.cellDayText);
        seancesNumber = itemView.findViewById(R.id.seancesNumber);
        tasksNumber = itemView.findViewById(R.id.tasksNumber);
        //borderBottom = itemView.findViewById(R.id.borderBottom);
        this.onItemListener = onItemLisener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemclick(getAdapterPosition(), (String) dayOfWeek.getText());
    }
}
