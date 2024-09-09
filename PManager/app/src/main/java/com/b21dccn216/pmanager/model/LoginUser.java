
package com.b21dccn216.pmanager.model;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class LoginUser implements Serializable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Fullname")
    @Expose
    private String fullname;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Role")
    @Expose
    private String role;
    @SerializedName("Image")
    @Expose
    private String image;
    private final static long serialVersionUID = -8597260176484364415L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginUser() {
    }

    /**
     *
     * @param image
     * @param role
     * @param id
     * @param fullname
     * @param email
     */
    public LoginUser(String id, String fullname, String email, String role, String image) {
        super();
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.role = role;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}