package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class Gift {

    @SerializedName("name")
    private String name;
    @SerializedName("cost")
    private int cost;
    @SerializedName("id")
    private int id;
    @SerializedName("code")
    private String code;
    @SerializedName("description")
    private String description;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


}
