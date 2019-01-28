package org.billboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {

    @SerializedName("context")
    private String context;
    @SerializedName("items")
    private List <Item> items;
    @SerializedName("id")
    private int id;

    public String getContext() {
        return context;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }
}
