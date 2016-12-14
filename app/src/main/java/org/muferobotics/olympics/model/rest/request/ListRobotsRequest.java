package org.muferobotics.olympics.model.rest.request;

import com.google.gson.annotations.SerializedName;

public class ListRobotsRequest {
    @SerializedName("email")
    String email;

    @SerializedName("key")
    String key;

    @SerializedName("apikey")
    String apikey;


    public ListRobotsRequest(String email, String key, String apikey) {
        this.email = email;
        this.key = key;
        this.apikey = apikey;
    }
}
