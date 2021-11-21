package com.example.salarieequitationapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private static final String TAG = "TaskAdapter";
    public TaskAdapter(@NonNull Context context, @NonNull List<Task> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = layoutInflater.inflate(R.layout.task_item, parent, false);
        TextView date = newItem.findViewById(R.id.taskDate);
        TextView time = newItem.findViewById(R.id.taskTime);
        TextView duration = newItem.findViewById(R.id.taskDuration);
        TextView title = newItem.findViewById(R.id.taskTitle);
        TextView detail = newItem.findViewById(R.id.taskDetail);
        date.setText(this.getItem(position).getDay() +"/"+ this.getItem(position).getMonthName());
        time.setText(this.getItem(position).getTime().substring(0,5));
        duration.setText(this.getItem(position).getDuration()+"min");
        title.setText(this.getItem(position).getTitle());
        detail.setText(this.getItem(position).getDetail());
        if(this.getItem(position).getIsDone() == 1)
            newItem.setBackgroundResource(R.drawable.rounded_corners_lightgreen);
        else
            newItem.setBackgroundResource(R.drawable.rounded_corners_lightred);
        return newItem;
    }
}
