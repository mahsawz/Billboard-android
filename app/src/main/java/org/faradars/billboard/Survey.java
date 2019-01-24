package org.billboard;

import com.google.gson.annotations.SerializedName;

public class Survey {

    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private int id;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

}
