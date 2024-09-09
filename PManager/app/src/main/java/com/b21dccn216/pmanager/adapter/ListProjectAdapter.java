package com.b21dccn216.pmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.model.Project;

import java.util.ArrayList;

public class ListProjectAdapter extends BaseAdapter {
    private ArrayList<Project> listProject;
    private int layoutForItem;

    public ListProjectAdapter(ArrayList<Project> listProject, int layoutForItem) {
        this.listProject = listProject;
        this.layoutForItem = layoutForItem;
    }

    @Override
    public int getCount() {
        return listProject.size();
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
        Project i = listProject.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(layoutForItem, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.project_name);
        TextView txtDeadline = convertView.findViewById(R.id.project_deadline);
        TextView txtDescription = convertView.findViewById(R.id.project_description);
        ProgressBar pgbProject = convertView.findViewById(R.id.project_progress_bar);

        txtName.setText(i.getName());
        txtDeadline.setText(i.getDeadline());
        txtDescription.setText(i.getInfor());
        pgbProject.setProgress((int)(i.getProgress()));

        return convertView;
    }
}
