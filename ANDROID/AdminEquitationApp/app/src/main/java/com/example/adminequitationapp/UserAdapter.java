package com.example.adminequitationapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private static final String TAG = "UserAdapter";
    public static final String EXTRA_USERID = "useridExtra";
    public static final String EXTRA_USERNAME = "usernameExtra";
    public static final String EXTRA_USER = "userExtra";

    public UserAdapter(@NonNull Context context, @NonNull ArrayList<User> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View newItem;
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        newItem = layoutInflater.inflate(R.layout.salariee_item, parent, false);
        Log.i(TAG, "client adapter name: " + position + " " + this.getItem(position).getId());
        TextView fullName = newItem.findViewById(R.id.userFullname);
        TextView email = newItem.findViewById(R.id.userEmail);
        //TextView type = newItem.findViewById(R.id.userType);
        ImageButton emploiBtn = newItem.findViewById(R.id.emploiButton);
        fullName.setText(this.getItem(position).getFullName());
        email.setText(this.getItem(position).getEmail());
        //type.setText(getItem(position).getType());
        emploiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iUserEmploi = new Intent(getContext(), SalarieeEmploiActivity.class).putExtra(EXTRA_USER, getItem(position));
                v.getContext().startActivity(iUserEmploi);
            }
        });
        return newItem;
    }
}
