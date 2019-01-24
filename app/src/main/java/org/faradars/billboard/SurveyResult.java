package org.billboard;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyResult {

    @SerializedName("apps")
    private List<Survey> surveys;

    @SerializedName("status")
    private String status;

    public List<Survey> getSurvey() {
        return surveys;
    }

    public String getStatus() {
        return status;
    }
}
