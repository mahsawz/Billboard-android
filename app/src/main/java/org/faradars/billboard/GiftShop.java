package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GiftShop {
    public List<Gift> getGifts() {
        return gifts;
    }

    @SerializedName("gifts")
    private List<Gift> gifts;



}
