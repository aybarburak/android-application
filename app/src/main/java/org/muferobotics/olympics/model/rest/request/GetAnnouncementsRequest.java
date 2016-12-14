package org.muferobotics.olympics.model.rest.request;

import com.google.gson.annotations.SerializedName;

public class GetAnnouncementsRequest {
    @SerializedName("apikey")
    String apikey;
    @SerializedName("lang")
    boolean isTurkish;

    public GetAnnouncementsRequest(String apikey, boolean isTurkish) {
        this.apikey = apikey;
        this.isTurkish = isTurkish;
    }
}
