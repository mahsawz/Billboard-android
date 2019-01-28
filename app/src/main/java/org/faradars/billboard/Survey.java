package org.faradars.billboard;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Survey implements Parcelable {

    @SerializedName("description")
    private String description;
    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private int id;
    @SerializedName("questions")
    private List<Question> questions;



    public List<Question> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(title);
        parcel.writeInt(id);
    }


    protected Survey(Parcel in) {
        description = in.readString();
        title = in.readString();
        id = in.readInt();
    }

    public static final Creator<Survey> CREATOR = new Creator<Survey>() {
        @Override
        public Survey createFromParcel(Parcel in) {
            return new Survey(in);
        }

        @Override
        public Survey[] newArray(int size) {
            return new Survey[size];
        }
    };
}
