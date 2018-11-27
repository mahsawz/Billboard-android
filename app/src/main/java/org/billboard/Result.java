package org.billboard;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String  email;

    @SerializedName("password")
    private String  password;

    @SerializedName("credit")
    private int credit;

    public String getPassword() {
        return password;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public int getCredit(){
        return credit;
    }


}
