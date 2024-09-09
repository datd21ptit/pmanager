package com.b21dccn216.pmanager.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Project implements Serializable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Id_creator")
    @Expose
    private String idCreator;
    @SerializedName("Infor")
    @Expose
    private String infor;
    @SerializedName("Deadline")
    @Expose
    private String deadline;
    @SerializedName("Progress")
    @Expose
    private Integer progress;
    private final static long serialVersionUID = -5763156493627814779L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Project() {
    }

    /**
     *
     * @param name
     * @param progress
     * @param id
     * @param deadline
     * @param idCreator
     * @param infor
     */
    public Project(String id, String name, String idCreator, String infor, String deadline, Integer progress) {
        super();
        this.id = id;
        this.name = name;
        this.idCreator = idCreator;
        this.infor = infor;
        this.deadline = deadline;
        this.progress = progress;
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

    public String getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(String idCreator) {
        this.idCreator = idCreator;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

}