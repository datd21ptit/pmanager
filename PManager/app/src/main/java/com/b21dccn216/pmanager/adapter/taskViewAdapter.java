package com.b21dccn216.pmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.model.TaskAssign;

import java.util.ArrayList;

public class taskViewAdapter extends BaseAdapter {
    private ArrayList<TaskAssign> listTask;
    private Context context;

    public taskViewAdapter(ArrayList<TaskAssign> listTask, Context context) {
        this.listTask = listTask;
        this.context = context;
    }

    private int layout = R.layout.task_listview_item;
    @Override
    public int getCount() {
        return listTask.size();
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
        convertView = LayoutInflater.from(context).inflate(layout, parent, false);
        TextView name = convertView.findViewById(R.id.taskName),
                deadline = convertView.findViewById(R.id.taskDeadline),
                conduct = convertView.findViewById(R.id.txt_conduct),
                des = convertView.findViewById(R.id.description);
        CardView lo = convertView.findViewById(R.id.layoutId);

        TaskAssign tk = listTask.get(position);

        name.setText(tk.getName());
        deadline.setText(tk.getDdLine());
        conduct.setText(tk.getUserName());
        des.setText(tk.getInfor());
        if(tk.getIsFinish().equals("0")){
            lo.setCardBackgroundColor(context.getResources().getColor(R.color.notFinish));
            Log.d("DDD", "getView: falese");
        }else{
            lo.setCardBackgroundColor(context.getResources().getColor(R.color.ACGreen));
        }

        return convertView;
    }
}
