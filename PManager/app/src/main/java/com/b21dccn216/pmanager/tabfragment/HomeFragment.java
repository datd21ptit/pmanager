package com.b21dccn216.pmanager.tabfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.b21dccn216.pmanager.AddProjectActivity;
import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.adapter.AssignSpinnerAdapter;
import com.b21dccn216.pmanager.adapter.ListProjectAdapter;
import com.b21dccn216.pmanager.adapter.taskViewAdapter;
import com.b21dccn216.pmanager.model.LoginUser;
import com.b21dccn216.pmanager.model.Project;
import com.b21dccn216.pmanager.model.TaskAssign;
import com.b21dccn216.pmanager.server.APIUtils;
import com.b21dccn216.pmanager.server.DataClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static ArrayList<Project> listProject;
    private LoginUser user;
    private ArrayList<LoginUser> listUser;ArrayList<TaskAssign> listTask;
    ListView lsView;
    FloatingActionButton fab;
    AssignSpinnerAdapter adapter;
    SwipeRefreshLayout srl;
    Button btn_search;
    EditText edtSearch;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_home, container, false);
        anhXa(mView);


        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        srl.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
        lsView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                srl.setEnabled(listIsAtTop(lsView));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(getActivity(), AddProjectActivity.class);
               intent.putExtra("id_creator", Integer.parseInt(user.getId()));
               startActivity(intent);
               Log.e("DDD", "Back from intent");
           }
       });

        btn_search.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String toFind = edtSearch.getText().toString();
               if(toFind.length() == 0){
                   Log.e("DDD", "length to search = 0");
                   return;
               }
               ArrayList<Project> searchResult = new ArrayList<>();
               LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
               for(Project p : listProject){
                   if(levenshteinDistance.apply(p.getName(),toFind) <= 5 ){
                       searchResult.add(p);
                   }else if(levenshteinDistance.apply(p.getInfor(),toFind) <= 5 ){
                       searchResult.add(p);
                   }else if(levenshteinDistance.apply(p.getDeadline(),toFind) <=  5 ){
                       searchResult.add(p);
                   }
               }
               if(searchResult.size() > 0){
                   ListProjectAdapter listProjectAdapter = new ListProjectAdapter(searchResult, R.layout.layout_project_item);
                   lsView.setAdapter(listProjectAdapter);
                   Log.e("DDD", "Result" + searchResult.size());
               }else{
                   Log.e("DDD", "No search result");
               }
           }
       });



       lsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Dialog dialog = new Dialog(getActivity());
               dialog.setContentView(R.layout.dialog_project_item);
               dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
               dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
               EditText name = dialog.findViewById(R.id.project_name),
                       deadline = dialog.findViewById(R.id.project_deadline),
                       description = dialog.findViewById(R.id.project_description);
               name.setEnabled(false); description.setEnabled(false); deadline.setEnabled(false);
               ListView listAssign = dialog.findViewById(R.id.lsAssign);
               Button btn_edt = dialog.findViewById(R.id.btn_edit),
                       btn_assign = dialog.findViewById(R.id.btn_assign),
                       btn_back = dialog.findViewById(R.id.btn_back);

               setListAssign(position, listAssign);


               if(!user.getId().equals(listProject.get(position).getIdCreator())){
                   btn_edt.setEnabled(false);
                   btn_assign.setEnabled(false);
               }
               Project project = listProject.get(position);
               name.setText(project.getName());
               deadline.setText(project.getDeadline());
               description.setText(project.getInfor());


                btn_edt.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String st = (String) btn_edt.getText();
                       if(st.equals("Edit")){
                           btn_edt.setText("Save");
                           name.setEnabled(true); description.setEnabled(true); deadline.setClickable(true);deadline.setEnabled(true);
                       }else{
                           name.setEnabled(false); description.setEnabled(false); deadline.setClickable(false);deadline.setEnabled(false);
                           btn_edt.setText("Edit");
                            String pjName = name.getText().toString(),
                                    pjDeadline = deadline.getText().toString(),
                                    pjDescription = description.getText().toString();
                            DataClient dataClient = APIUtils.getData();
                            Call<String> callBack = dataClient.updateProject(listProject.get(position).getId(), pjName, pjDeadline, pjDescription);
                            callBack.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    Log.e("DDD","In" + response.body());
                                    refreshData();
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Log.e("DDD","Fail " + t.getMessage());
                                }
                            });
                            dialog.dismiss();
                            refreshData();
                       }
                   }
               });


                btn_assign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Dialog dialog1 = new Dialog(getActivity());
                        dialog1.setContentView(R.layout.dialog_assign);
                        dialog1.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        dialog1.getWindow().getAttributes().windowAnimations = R.style.animation;

                        EditText taskTitle = dialog1.findViewById(R.id.edt_task),
                                taskDeadline = dialog1.findViewById(R.id.edt_deadlineTask),
                                taskInfor = dialog1.findViewById(R.id.edt_infor);
                        Button btnAssign = dialog1.findViewById(R.id.btnAssign2),
                                btnCancel = dialog1.findViewById(R.id.btn_cancel);
                        Spinner spinnerUser = dialog1.findViewById(R.id.spinnerUser);

                        taskDeadline.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {chonNgay(taskDeadline);}
                        });



                        getAllUser(spinnerUser, getContext());
//                        spinnerUser
                        spinnerUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Log.e("DDD", listUser.get(position).getFullname());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

//                        spinnerUser.getId();

                        btnAssign.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataClient dataClient = APIUtils.getData();
                                String id_pj = listProject.get(position).getId(),
                                        id_do = listUser.get(spinnerUser.getSelectedItemPosition()).getId(),
                                        name = taskTitle.getText().toString(),
                                        infor =  taskInfor.getText().toString(),
                                        taskDeadL = taskDeadline.getText().toString();
                                Call<String> callback = dataClient.assignTask(id_pj,id_do,name, infor, taskDeadL);
                                callback.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {
                                        if(response !=null){
                                            Log.e("DDD", response.body());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                    }
                                });
                                dialog1.dismiss();
                                setListAssign(position,listAssign);
                            }
                        });
                        btnCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog1.dismiss();
                            }
                        });
                        dialog1.show();
                    }
                });


                btn_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                deadline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        chonNgay(deadline);
                    }
                });
               dialog.show();
           }
       });
        return mView;
    }




    private void refreshData() {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                listProject = new ArrayList<>();
                DataClient dataClient = APIUtils.getData();
                Call<ArrayList<Project>> callback = dataClient.getAllProject();
                callback.enqueue(new Callback<ArrayList<Project>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Project>> call, Response<ArrayList<Project>> response) {
                        listProject = response.body();
                        publishProgress(listProject);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Project>> call, Throwable t) {
                        Log.e("DDD", "onFailure"+ t.getMessage());
                    }
                });
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
                ListProjectAdapter listProjectAdapter = new ListProjectAdapter((ArrayList<Project>) values[0], R.layout.layout_project_item);
                lsView.setAdapter(listProjectAdapter);
                Log.e("DDD",  ((ArrayList<Project>) values[0]).size() + "");
                Log.e("DDD", "After get: " + listProject.size() + "");
            }
        };
        asyncTask.execute();
    }


    private void anhXa(View mView) {
        lsView = mView.findViewById(R.id.listview_Project);
        user = (LoginUser) getActivity().getIntent().getSerializableExtra("loginUser");
        fab = mView.findViewById(R.id.fabuton);
        srl = mView.findViewById(R.id.swiperefresh);
        fab.setEnabled(false); fab.hide();
        btn_search = mView.findViewById(R.id.buttonSearch);
        edtSearch = mView.findViewById(R.id.edtFindProject);
        if(user.getRole().toLowerCase().equals("manager")){
            fab.setEnabled(true);
            fab.show();
        }
        if (listProject == null){
            refreshData();
        }else{
            ListProjectAdapter listProjectAdapter = new ListProjectAdapter(listProject, R.layout.layout_project_item);
            lsView.setAdapter(listProjectAdapter);
        }
    }



    private void chonNgay(EditText edt_dead) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.set(year,month,dayOfMonth);
                Date date = cal.getTime();
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                String inActiveDate = format1.format(date);
                edt_dead.setText(inActiveDate);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
        datePickerDialog.show();
    }


    private void getAllUser(Spinner spinner, Context context){
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                DataClient dataClient = APIUtils.getData();
                Call<ArrayList<LoginUser>> callback = dataClient.getAllUser();
                callback.enqueue(new Callback<ArrayList<LoginUser>>() {
                    @Override
                    public void onResponse(Call<ArrayList<LoginUser>> call, Response<ArrayList<LoginUser>> response) {
                        publishProgress(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<LoginUser>> call, Throwable t) {

                    }
                });
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);
                listUser =  (ArrayList<LoginUser>) values[0];
                adapter = new AssignSpinnerAdapter(context, R.layout.layout_user_item, (List<LoginUser>) values[0]);
//                adapter.setDropDownViewResource();
                spinner.setAdapter(adapter);
            }
        };
        task.execute();
    }
    private boolean listIsAtTop(ListView listView)   {
        if(listView.getChildCount() == 0) return true;
        return listView.getChildAt(0).getTop() == 0;
    }




    private void setListAssign(int position, ListView listAssign) {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                DataClient dataClient = APIUtils.getData();
                Call<ArrayList<TaskAssign>> callback = dataClient.getTask(listProject.get(position).getId());
                callback.enqueue(new Callback<ArrayList<TaskAssign>>() {
                    @Override
                    public void onResponse(Call<ArrayList<TaskAssign>> call, Response<ArrayList<TaskAssign>> response) {
                        Log.d("DDD", "onResponse: lenth" + response.body().size());
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
                taskViewAdapter adapter1 = new taskViewAdapter(listTask, getContext());
                listAssign.setAdapter(adapter1);

            }
        };
        task.execute();
    }


}