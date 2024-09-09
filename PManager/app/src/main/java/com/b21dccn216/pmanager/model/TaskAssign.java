package com.b21dccn216.pmanager.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TaskAssign implements Serializable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("infor")
    @Expose
    private String infor;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("ddLine")
    @Expose
    private String ddLine;
    @SerializedName("isFinish")
    @Expose
    private String isFinish;
    private final static long serialVersionUID = 1350839459593423359L;

    /**
     * No args constructor for use in serialization
     *
     */
    public TaskAssign() {
    }

    /**
     *
     * @param name
     * @param id
     * @param isFinish
     * @param userName
     * @param ddLine
     * @param infor
     */
    public TaskAssign(String id, String name, String infor, String userName, String ddLine, String isFinish) {
        super();
        this.id = id;
        this.name = name;
        this.infor = infor;
        this.userName = userName;
        this.ddLine = ddLine;
        this.isFinish = isFinish;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDdLine() {
        return ddLine;
    }

    public void setDdLine(String ddLine) {
        this.ddLine = ddLine;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

}