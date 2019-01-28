package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("context")
    private String context;
    @SerializedName("vote_count")
    private int vote_count;
    @SerializedName("id")
    private int id;

    public String getContext() {
        return context;
    }

    public int getVote_count() {
        return vote_count;
    }

    public int getId() {
        return id;
    }
}
