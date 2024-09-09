package com.b21dccn216.pmanager.tabfragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.b21dccn216.pmanager.R;
import com.b21dccn216.pmanager.model.LoginUser;
import com.b21dccn216.pmanager.server.APIUtils;
import com.b21dccn216.pmanager.server.DataClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment {
    private static final int REQUEST_CODE_IMG = 10001;
    ImageView img;
    EditText edt_name, edt_emai, edt_role;
    Button btnEdit, btnSave, btnChange;
    private  View mView;
    private LoginUser user;

    private String realpath = "";
    private ImageView imgView;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        anhxa(mView);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(true);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(true);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_IMG);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(false);
                Log.e("DDD", "FIRST RUN " +  user.getImage());
                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        if (realpath!= ""){
                            final File[] file = {new File(realpath)};
                            String file_path = file[0].getAbsolutePath();
                            String[] nameFileArray = file_path.split("\\.");
                            file_path = nameFileArray[0] + System.currentTimeMillis() + '.' + nameFileArray[1];

                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file[0]);
                            MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file_path, requestBody);

                            DataClient dataClient = APIUtils.getData();
                            Call<String> callback = dataClient.UploadPhoto(body);
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    if(response != null)  publishProgress(response.body());
                                    Log.e("DDD", "Run First " + user.getImage());
                                }
                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Log.e("DDD", t.getMessage());
                                }
                            });
                            realpath = "";
                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Object[] values) {
                        super.onProgressUpdate(values);
                        user.setImage((String) values[0]);
                        Log.e("DDD","Run second " + user.getImage());
                        user.setFullname(edt_name.getText().toString());
                        user.setEmail(edt_emai.getText().toString());

                        DataClient dataClient1 = APIUtils.getData();
                        Call<String> callback = dataClient1.updateInformation(Integer.parseInt(user.getId()),
                                user.getFullname(),
                                user.getEmail(),
                                user.getImage());////////////// mes is name of image in /image server
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.e("DDD",response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.e("DDD","FAILE");
                            }
                        });
                    }
                };
                asyncTask.execute();
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new AlertDialog.Builder(getContext())
                        .setMessage("Log out?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getActivity().finish();
                            }
                        }).create();
                dialog.show();
            }
        });
        return mView;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMG && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            realpath = getRealPathFromUri(uri);
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setState(boolean canPressSave) {
        edt_name.setEnabled(canPressSave);
        edt_emai.setEnabled(canPressSave);
        edt_role.setEnabled(canPressSave);
        btnEdit.setEnabled(!canPressSave);
        btnSave.setEnabled(canPressSave);
    }

    private void anhxa(View mView){
        img = mView.findViewById(R.id.imageView1);
        edt_name = mView.findViewById(R.id.editText_fullname);
        edt_emai = mView.findViewById(R.id.editText_email);
        edt_role = mView.findViewById(R.id.editText_Role);
        btnEdit = mView.findViewById(R.id.btn_Edit);
        btnSave = mView.findViewById(R.id.btn_Save);
        btnChange = mView.findViewById(R.id.btnChangePassword);
        user = (LoginUser) getActivity().getIntent().getSerializableExtra("loginUser");
        setState(false);
        /// temporary
        DataClient dataClient = APIUtils.getData();
        Call<ResponseBody> callback = dataClient.getImage(APIUtils.Base_Url+ "image/" + user.getImage());
        callback.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream inputStream = response.body().byteStream();
                    // Convert InputStream to Bitmap and set it to ImageView
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    img.setImageBitmap(bitmap);
                }catch (NullPointerException e){
                    Log.e("DDD", e.toString() + user.getImage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        reset();
    }
    private void reset(){
        edt_name.setText(user.getFullname());
        edt_emai.setText(user.getEmail());
        edt_role.setText(user.getRole());
    }
    public String getRealPathFromUri(Uri uri){
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri,proj,null, null, null);
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}