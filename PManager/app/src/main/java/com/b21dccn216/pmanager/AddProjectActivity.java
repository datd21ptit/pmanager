package com.b21dccn216.pmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.b21dccn216.pmanager.server.APIUtils;
import com.b21dccn216.pmanager.server.DataClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProjectActivity extends AppCompatActivity {
    Button btn_add, btn_cancel;
    private int id_creator;
    EditText edt_name, edt_dead, edt_des;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        anhXa();

        edt_dead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonNgay();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pj_name = edt_name.getText().toString(),
                        pj_deadline = edt_dead.getText().toString(),
                        pj_description = edt_des.getText().toString();
                if(pj_name.length() == 0 || pj_deadline.length() == 0){
                    Log.e("DDD", "Length = 0");
                    return;
                }else{
                    DataClient dataClient = APIUtils.getData();
                    Call<String> callback = dataClient.addProject(pj_name, String.valueOf(id_creator),pj_deadline,pj_description);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String mess = response.body();
                            Log.e("DDD", "onResponse: " + mess);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("DDD", "onFailuer: Fail");
                            Toast.makeText(getApplicationContext(), "Fail to add", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void anhXa() {
        btn_add = findViewById(R.id.buttonAdd);
        btn_cancel = findViewById(R.id.buttonCancel);
        edt_dead = findViewById(R.id.editText_Deadline);
        edt_name = findViewById(R.id.edt_ProjectName);
        edt_des = findViewById(R.id.edt_projectDescription);
        id_creator = getIntent().getIntExtra("id_creator", -1);
        Log.e("DDD", String.valueOf(id_creator));

        if(id_creator == -1){
            finish();
        }
    }
    private void chonNgay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
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

}