package com.example.adminequitationapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {
    private static final String TAG = "ClientAdapter";
    public static final String EXTRA_CLIENTID = "clientidExtra";
    public static final String EXTRA_CLIENTNAME = "clientnameExtra";

    public ClientAdapter(@NonNull Context context, @NonNull List<Client> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = layoutInflater.inflate(R.layout.client_item, parent, false);
        Log.i(TAG, "client adapter name: " + position + " " + this.getItem(position).getId());

        TextView fullName = newItem.findViewById(R.id.clientFullname);
        TextView email = newItem.findViewById(R.id.clientEmail);
        //TextView phone = newItem.findViewById(R.id.clientPhone);
        ImageButton emploiBtn = newItem.findViewById(R.id.emploiButton);
        fullName.setText(this.getItem(position).getFullName());
        email.setText(this.getItem(position).getEmail());
        //phone.setText(getItem(position).getPhone());
        emploiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iClientEmploi = new Intent(getContext(), ClientEmploiActivity.class).putExtra(EXTRA_CLIENTID, getItem(position).getId());
                iClientEmploi.putExtra(EXTRA_CLIENTNAME, getItem(position).getFullName());
                v.getContext().startActivity(iClientEmploi);
            }
        });
        return newItem;
    }
}
