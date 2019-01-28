package org.billboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyResult {

    @SerializedName("survey")
    private List<Survey> survey;

    @SerializedName("status")
    private String status;

    public List<Survey> getSurvey() {
        return survey;
    }

    public String getStatus() {
        return status;
    }

}
