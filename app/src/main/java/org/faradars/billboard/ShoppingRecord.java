package org.faradars.billboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ShoppingRecord implements Parcelable {
    @SerializedName("gift_id")
    private int gift_id;
    @SerializedName("date")
    private String date;
    @SerializedName("code")
    private String code;
    @SerializedName("description")
    private String description;

    public String getCode() {
        return code;
    }

    public int getGift_id() {
        return gift_id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gift_id);
        dest.writeString(date);
        dest.writeString(code);
        dest.writeString(description);
    }

    protected ShoppingRecord(Parcel in) {
        gift_id = in.readInt();
        date = in.readString();
        code = in.readString();
        description = in.readString();
    }

    public static final Creator<ShoppingRecord> CREATOR = new Creator<ShoppingRecord>() {
        @Override
        public ShoppingRecord createFromParcel(Parcel in) {
            return new ShoppingRecord(in);
        }

        @Override
        public ShoppingRecord[] newArray(int size) {
            return new ShoppingRecord[size];
        }
    };
}
