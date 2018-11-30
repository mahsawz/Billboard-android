package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class UserResult {
    @SerializedName("user")
    private User user;
    @SerializedName("status")
    private String status;

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }


}
