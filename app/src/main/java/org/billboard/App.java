package org.billboard;

import java.io.Serializable;

public class App implements Serializable {

    private int count;
    private int credit;
    private String download_link;
    private String icon;
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
