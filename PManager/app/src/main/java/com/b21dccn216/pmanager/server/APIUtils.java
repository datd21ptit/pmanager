package com.b21dccn216.pmanager.server;

public class APIUtils {

    // Day la URL dan den thu muc server
    // Thay dia chi IP bang dia chi IPv4 cua may (dung lenh ipconfig de tim)
    public static final String Base_Url = "http://10.20.152.58/serverTTCS/";

    public static DataClient dataClient = RetrofitClient.getClient(Base_Url).create(DataClient.class);


    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }

}