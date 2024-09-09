package com.b21dccn216.pmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.model.LoginUser;

import java.util.ArrayList;

public class ListUserAdapter extends BaseAdapter {
    private ArrayList<LoginUser> listUser;

    private int layoutForItem;

    public ListUserAdapter(ArrayList<LoginUser> listUser, int layoutForItem) {
        this.listUser = listUser;
        this.layoutForItem = layoutForItem;
    }

    @Override
    public int getCount() {
        return listUser.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView =  LayoutInflater.from(parent.getContext()).inflate(layoutForItem, parent, false);
        }
        LoginUser l = listUser.get(position);
        TextView txtName = convertView.findViewById(R.id.txt_fullName);
        TextView txtRole = convertView.findViewById(R.id.txt_roleName);
        txtName.setText(l.getFullname());
        txtRole.setText(l.getRole());
        return convertView;
    }
}
