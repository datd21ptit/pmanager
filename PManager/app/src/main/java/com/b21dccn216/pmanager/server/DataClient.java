package com.b21dccn216.pmanager.server;


import com.b21dccn216.pmanager.model.LoginUser;
import com.b21dccn216.pmanager.model.Project;
import com.b21dccn216.pmanager.model.TaskAssign;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;


public interface DataClient {

//
//    USER
//
    @Multipart
    @POST("UploadImage.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part photo);
    @FormUrlEncoded
    @POST("signup.php")
    Call<String> signup(@Field("name") String name,
                        @Field("username") String username,
                        @Field("password") String password,
                        @Field("email") String email,
                        @Field("role") String role);
    @FormUrlEncoded
    @POST("login.php")
    Call<ArrayList<LoginUser>> login(@Field("username") String username,
                                     @Field("password") String password);

    @GET
    Call<ResponseBody> getImage(@Url String url);

    @FormUrlEncoded
    @POST("updateInfor.php")
    Call<String> updateInformation(@Field("id") int id,
                                   @Field("name") String name,
                                   @Field("email") String email,
                                   @Field("image") String image);
    @GET("allUsers.php")
    Call<ArrayList<LoginUser>> getAllUser();


//
//    PROJECT
//
    @GET("allProject.php")
    Call<ArrayList<Project>> getAllProject();
    @POST("addProject.php")
    @FormUrlEncoded
    Call<String> addProject(@Field("name") String projectName,
                            @Field("id_creator") String id_creator,
                            @Field("deadline") String deadline,
                            @Field("des") String description);

    @POST("updateProject.php")
    @FormUrlEncoded
    Call<String> updateProject(@Field("id") String id,
                               @Field("name") String name,
                               @Field("deadline") String deadline,
                               @Field("description") String description);


//
//    TASK
//
    @POST("assignTask.php")
    @FormUrlEncoded
    Call<String> assignTask(@Field("id_pj") String idProject,
                            @Field("id_do") String id_user,
                            @Field("name") String name,
                            @Field("infor") String infor,
                            @Field("deadline") String deadline);
    @POST("getTask.php")
    @FormUrlEncoded
    Call<ArrayList<TaskAssign>> getTask(@Field("id_pj") String pj);

    @POST("getUserTask.php")
    @FormUrlEncoded
    Call<ArrayList<TaskAssign>> getUserTask(@Field("id") String id);

    @POST("setTaskStatus.php")
    @FormUrlEncoded
    Call<String> setTaskStatus(@Field("id") String idTask);
}
