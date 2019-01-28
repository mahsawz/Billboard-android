package org.billboard;

import com.google.gson.annotations.SerializedName;


public class ClickedSurveyResult {

    @SerializedName("survey")
    private Survey survey;

    @SerializedName("status")
    private String status;

    public Survey getSurvey() {
        return survey;
    }

    public String getStatus() {
        return status;
    }
}
