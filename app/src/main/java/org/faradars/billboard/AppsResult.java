package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppsResult {

    @SerializedName("apps")
    private List<App> apps;

    @SerializedName("status")
    private String status;

    public List<App> getApp() {
        return apps;
    }

    public String getStatus() {
        return status;
    }
}
