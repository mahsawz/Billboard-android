package org.faradars.billboard;

import com.google.gson.annotations.SerializedName;

public class GiftShopResult {

    @SerializedName("record")
    private ShoppingRecord record;

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }
    public ShoppingRecord getRecord() {
        return record;
    }

}