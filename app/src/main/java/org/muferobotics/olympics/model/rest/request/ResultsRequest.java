package org.muferobotics.olympics.model.rest.request;

import com.google.gson.annotations.SerializedName;


public class ResultsRequest {

    @SerializedName("apikey")
    String apikey;

    @SerializedName("category_id")
    String category_id;


    public ResultsRequest(String apikey, String category_id) {
        this.apikey = apikey;
        this.category_id = category_id;
    }
}
