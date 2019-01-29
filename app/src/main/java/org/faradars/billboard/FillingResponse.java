package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class FillingResponse {
    public String getStatus() {
        return status;
    }

    @SerializedName("status")
    private String status;
}
