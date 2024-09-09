package com.b21dccn216.pmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.model.LoginUser;
import java.util.List;

public class AssignSpinnerAdapter extends ArrayAdapter<LoginUser> {

    public AssignSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<LoginUser> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_selected_user_item, parent, false);
        TextView txtName = convertView.findViewById(R.id.txt_selected_fullName);
        LoginUser us = this.getItem(position);
        if(us != null){
            txtName.setText(us.getFullname());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user_item, parent, false);
        TextView txtName = convertView.findViewById(R.id.txt_fullName),
                txtRole = convertView.findViewById(R.id.txt_roleName);
        LoginUser us = this.getItem(position);
        if(us != null){
            txtName.setText(us.getFullname());
            txtRole.setText(us.getRole());
        }
        return convertView;
    }
}
