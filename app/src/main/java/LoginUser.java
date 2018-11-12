package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class LoginUser {

    @SerializedName("email")
    private String  email;

    @SerializedName("password")
    private String  password;
    public LoginUser(String email,String password){

        this.email = email;
        this.password=password;

    }
    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }



}
