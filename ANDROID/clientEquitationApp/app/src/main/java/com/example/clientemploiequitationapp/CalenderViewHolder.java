package com.example.clientemploiequitationapp;

import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalenderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView dayOfMounth;
    public final TextView time;
    public final TextView duration;
    private final CalenderAdapter.OnItemLitener onItemLitener;
    public Seance seance;

    public CalenderViewHolder(@NonNull View itemView, CalenderAdapter.OnItemLitener onItemLitener) {
        super(itemView);
        this.dayOfMounth = itemView.findViewById(R.id.cellDayText);
        this.time = itemView.findViewById(R.id.cellTimeText);
        this.duration = itemView.findViewById(R.id.cellDurationText);
        this.onItemLitener = onItemLitener;
        this.seance = null;
        itemView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        onItemLitener.onItemClick(getAdapterPosition(), (String) dayOfMounth.getText(), seance);
    }
}
