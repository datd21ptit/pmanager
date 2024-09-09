package com.b21dccn216.pmanager.tabfragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.adapter.taskViewAdapter;
import com.b21dccn216.pmanager.model.LoginUser;
import com.b21dccn216.pmanager.model.TaskAssign;
import com.b21dccn216.pmanager.server.APIUtils;
import com.b21dccn216.pmanager.server.DataClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {
    ListView lvTask;
    LoginUser user;
    private ArrayList<TaskAssign> listTask;
    SwipeRefreshLayout srl;

    public TodoFragment() {
    }


    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_todo, container, false);
        anhXa(mView);
        setListAssign(user.getId());
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setListAssign(user.getId());
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        srl.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
        lvTask.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(listIsAtTop(lvTask)){
                    srl.setEnabled(true);
                }else{
                    srl.setEnabled(false);
                }
            }
        });
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new AlertDialog.Builder(getContext())
                        .setMessage("Have you finish?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("DDD", "onClick: Clickerdd");
                                AsyncTask tk = new AsyncTask() {
                                    @Override
                                    protected Object doInBackground(Object[] objects) {
                                        DataClient dataClient = APIUtils.getData();
                                        Call<String> callback = dataClient.setTaskStatus(listTask.get(position).getId());
                                        callback.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {

                                                publishProgress(null);
                                                Log.i("DDD", "onResponse: truuu" );
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {
                                                Log.d("DDD", "onFailure: " + t.getMessage());
                                            }
                                        });
                                        return null;
                                    }

                                    @Override
                                    protected void onProgressUpdate(Object[] values) {
                                        super.onProgressUpdate(values);
                                        setListAssign(user.getId());
                                        Log.d("DDD", "onProgressUpdate: updating");
                                    }
                                };
                                tk.execute();


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                dialog.show();
            }
        });



        return mView;
    }

    private void anhXa(View view) {
        lvTask = view.findViewById(R.id.listview_Task);
        user = (LoginUser) getActivity().getIntent().getSerializableExtra("loginUser");
        srl = view.findViewById(R.id.swiperefresh_todoLayout);
//        Toast.makeText(getContext(), user.getId(), Toast.LENGTH_SHORT).show();
    }
    private void setListAssign(String id) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                DataClient dataClient = APIUtils.getData();
                Call<ArrayList<TaskAssign>> callback = dataClient.getUserTask(id);
                callback.enqueue(new Callback<ArrayList<TaskAssign>>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    public void onResponse(Call<ArrayList<TaskAssign>> call, Response<ArrayList<TaskAssign>> response) {
                        Log.d("DDD", "onResponse: lenth " + response.body().size());
                        listTask = response.body();
                        publishProgress(response.body());
                    }
                    @Override
                    public void onFailure(Call<ArrayList<TaskAssign>> call, Throwable t) {
                        Log.e("DDD","onFailuer"+  t.getMessage());
                    }
                });

                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
                taskViewAdapter adapter1 = new taskViewAdapter((ArrayList<TaskAssign>) values[0], getContext());
                lvTask.setAdapter(adapter1);
                Log.d("DDD", "onProgressUpdate: wwtf " );
            }
        };
        task.execute();
    }
    private boolean listIsAtTop(ListView listView)   {
        if(listView.getChildCount() == 0) return true;
        return listView.getChildAt(0).getTop() == 0;
    }

}