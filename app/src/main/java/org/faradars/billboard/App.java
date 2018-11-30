package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class App implements Serializable {

    @SerializedName("count")
    private int count;
    @SerializedName("credit")
    private int credit;
    @SerializedName("download_link")
    private String download_link;
    @SerializedName("icon")
    private String icon;
    @SerializedName("name")
    private String name;


    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getIcon() {
        return icon;
    }

    public String getDownload_link() {
        return download_link;
    }

    public int getCredit() {
        return credit;
    }

    public App(String name, int count, int credit, String icon, String download_link) {

        this.name = name;
        this.download_link = download_link;
        this.icon = icon;
        this.count = count;
        this.credit = credit;
    }
}
