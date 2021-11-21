package com.example.adminequitationapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskAdapter extends ArrayAdapter<Task> {
    private static final String TAG = "TaskAdapter";
    public static final String EXTRA_TASKID = "taskidExtra";
    int item;
    public TaskAdapter(@NonNull Context context, @NonNull List<Task> objects, int item) {
        super(context, 0, objects);
        this.item = item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem, newItem0;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(item == 1){
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
        }else{
            newItem0 = layoutInflater.inflate(R.layout.task_item0, parent, false);
            TextView date = newItem0.findViewById(R.id.taskDate);
            TextView time = newItem0.findViewById(R.id.taskTime);
            TextView duration = newItem0.findViewById(R.id.taskDuration);
            TextView title = newItem0.findViewById(R.id.taskTitle);
            TextView detail = newItem0.findViewById(R.id.taskDetail);
            ImageButton updateBtn = newItem0.findViewById(R.id.updateBtn);
            ImageButton deleteBtn = newItem0.findViewById(R.id.deleteBtn);
            CheckBox isDone = newItem0.findViewById(R.id.isDone);
            if(this.getItem(position).getIsDone() == 1)
                isDone.setChecked(true);
            else
                isDone.setChecked(false);
            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iUpdate = new Intent(getContext(), UpdateTaskActivity.class).putExtra(EXTRA_TASKID, getItem(position).getId());
                    v.getContext().startActivity(iUpdate);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StringRequest request = new StringRequest(Request.Method.POST,
                            "http://" + login.ipAddress + "/equitationApp/public/api/deleteTask/" + getItem(position).getId(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
                }
            });
            isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    StringRequest request = new StringRequest(Request.Method.PUT,
                            "http://" + login.ipAddress + "/equitationApp/public/api/updateTask/" + getItem(position).getId(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i(TAG, "response: " + response + " task ID: " + getItem(position).getId());
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Log.i(TAG, "error: " + error + " task ID: " + getItem(position).getId());
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            if(isChecked==true)
                                params.put("isDone", String.valueOf(1));
                            else
                                params.put("isDone", String.valueOf(0));
                            return params;
                        }
                    };
                    VolleySingleton.getInstance(getContext()).addToRequestQueue(request);
                }
            });
            date.setText(this.getItem(position).getDay() +"/"+ this.getItem(position).getMonthName());
            time.setText(this.getItem(position).getTime().substring(0,5));
            duration.setText(this.getItem(position).getDuration()+"min");
            title.setText(this.getItem(position).getTitle());
            detail.setText(this.getItem(position).getDetail());
            return newItem0;
        }
    }
}
