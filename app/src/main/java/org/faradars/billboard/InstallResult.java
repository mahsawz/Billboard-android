package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class InstallResult {
    @SerializedName("app")
    private App app;

    @SerializedName("status")
    private String status;

    public App getApp() {
        return app;
    }

    public String getStatus() {
        return status;
    }
}
