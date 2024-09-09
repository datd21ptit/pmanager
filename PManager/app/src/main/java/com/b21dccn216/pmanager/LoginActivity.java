package com.b21dccn216.pmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.b21dccn216.pmanager.model.LoginUser;
import com.b21dccn216.pmanager.server.APIUtils;
import com.b21dccn216.pmanager.server.DataClient;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;
    RelativeLayout loginBT;
    TextView signupBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();

//        //for debug only
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        LoginUser user = new LoginUser();
//        user.setId("29");
//        user.setFullname("Nguyen Tran Dat");
//        user.setEmail("emailForDebug");
//        user.setRole("Debug");
//        intent.putExtra("loginUser", (Serializable) user);
//        startActivity(intent);
//        //


        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString(), password = passwordET.getText().toString();


                if(username.length() > 0 && password.length() > 0){
                    DataClient dataClient = APIUtils.getData();
                    // goi file login.php
                    Call<ArrayList<LoginUser>> callback = dataClient.login(username, password);
                    callback.enqueue(new Callback<ArrayList<LoginUser>>() {
                        @Override
                        public void onResponse(Call<ArrayList<LoginUser>> call, Response<ArrayList<LoginUser>> response) {
                            ArrayList<LoginUser> res = (ArrayList<LoginUser>) response.body();
                            Log.e("PMANAGER", "GOT IT");
                            if(res.size() == 0){
                                Toast.makeText(getApplicationContext(), "Wrong username or password!!!", Toast.LENGTH_SHORT).show();
                            }else{
                                SharedPreferences sharedPreferences = getSharedPreferences("thongtindangnhap",MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("Username", username);
                                editor.putString("Password", password);
                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("loginUser", (Serializable) res.get(0));
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<LoginUser>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Fail to connect to server", Toast.LENGTH_LONG).show();
//                            Log.e("PMANAGER", "LOG FROM ON FAILURE EUNQUE:" + t.getMessage());
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(), "Không được để trống username hoặc password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signupBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterDialog();
            }
        });

    }

    private void anhxa() {
        usernameET = findViewById(R.id.editText_username);
        passwordET = findViewById(R.id.editText_password);
        loginBT = findViewById(R.id.group_signin_button);
        signupBT = findViewById(R.id.SignUpText);
    }
// Trong Activity hoặc Fragment của bạn

    public void openRegisterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_register, null);
        builder.setView(dialogView);


        EditText editTextUsername = dialogView.findViewById(R.id.editTextUsername);
        EditText editTextPassword = dialogView.findViewById(R.id.editTextPassword);
        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        EditText editTextRole = dialogView.findViewById(R.id.editTextRole);




        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String role = editTextRole.getText().toString();
                DataClient dataClient = APIUtils.getData();
                Call<String> callback = dataClient.signup(name,username, password, email, role);

                Log.e("DDD", "DATS BY SOMETHING");
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String message = response.body();
                        Log.e("DDD", message);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("DDD", "FAIL BY SOMETHING");
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onResume(){
        super.onResume();

        SharedPreferences sharedPreferences = getSharedPreferences("thongtindangnhap", MODE_PRIVATE);
        String usernameLUU = sharedPreferences.getString("Username", "");
        String passwordLUU = sharedPreferences.getString("Password", "");
        usernameET.setText(usernameLUU);
        passwordET.setText(passwordLUU);
    }

}