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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeanceAdapter extends ArrayAdapter<Seance> {
    private static final String TAG = "SeanceAdapter";
    public static final String EXSTRA_SEANCE = "extraSeance";
    public static final String EXSTRA_CLIENT = "extraClient";
    int item;
    ArrayList<Client> clients;
    public SeanceAdapter(@NonNull Context context, @NonNull List<Seance> objects, ArrayList<Client> clients, int item) {
        super(context, 0, objects);
        this.clients = clients;
        this.item = item;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem, newItem0;
        String fullName = "";
        Client clientX = new Client();
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(item == 1){
            newItem = layoutInflater.inflate(R.layout.seance_item, parent, false);
            TextView date = newItem.findViewById(R.id.seanceDate);
            TextView time = newItem.findViewById(R.id.seanceTime);
            TextView duration = newItem.findViewById(R.id.seanceDuration);
            TextView clientName = newItem.findViewById(R.id.clientName);
            CheckBox isDone = newItem.findViewById(R.id.isDone);
            TextView remarque = newItem.findViewById(R.id.remarque);
            if(this.getItem(position).getIsDone() == 1)
                isDone.setChecked(true);
            else
                isDone.setChecked(false);
            date.setText(this.getItem(position).getDay() +"/"+ this.getItem(position).getMonthName());
            time.setText(this.getItem(position).getTime().substring(0,5));
            duration.setText(this.getItem(position).getDuration()+"min");
            for(Client client:clients)
                if(client.getId() == this.getItem(position).getClientID())
                    fullName = client.getFullName();
            clientName.setText(fullName);
            clientX.setFullName(fullName);

            remarque.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iSeance = new Intent(getContext(), RemarqueActivity.class).putExtra(EXSTRA_SEANCE, getItem(position));
                    iSeance.putExtra(EXSTRA_CLIENT, clientX.getFullName());
                    v.getContext().startActivity(iSeance);
                }
            });
            isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    StringRequest request = new StringRequest(Request.Method.PUT,
                            "http://" + login.ipAddress + "/equitationApp/public/api/updateSeance/" + getItem(position).getId(),
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.i(TAG, "response: " + response + " seance ID: " + getItem(position).getId());
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Log.i(TAG, "error: " + error + " seance ID: " + getItem(position).getId());
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
            return newItem;
        }else
        {
            newItem0 = layoutInflater.inflate(R.layout.seance_item0, parent, false);
            TextView date = newItem0.findViewById(R.id.seanceDate);
            TextView time = newItem0.findViewById(R.id.seanceTime);
            TextView duration = newItem0.findViewById(R.id.seanceDuration);
            TextView clientName = newItem0.findViewById(R.id.clientName);
            date.setText(this.getItem(position).getDay() +"/"+ this.getItem(position).getMonthName());
            time.setText(this.getItem(position).getTime().substring(0,5));
            duration.setText(this.getItem(position).getDuration()+"min");
            for(Client client:clients)
                if(client.getId() == this.getItem(position).getClientID())
                    fullName = client.getFullName();
            clientName.setText(fullName);
            clientX.setFullName(fullName);
            return newItem0;
        }

    }

}
