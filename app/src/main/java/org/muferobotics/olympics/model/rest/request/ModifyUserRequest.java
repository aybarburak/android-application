package org.muferobotics.olympics.model.rest.request;

import com.google.gson.annotations.SerializedName;

import org.muferobotics.olympics.model.Modify;

import java.util.ArrayList;

public class ModifyUserRequest {
    @SerializedName("email")
    String email;

    @SerializedName("key")
    String key;

    @SerializedName("modifications")
    ArrayList<Modify> modify;

    @SerializedName("apikey")
    String apikey;

    public ModifyUserRequest(String email, String key, ArrayList<Modify> modify, String apikey) {
        this.email = email;
        this.key = key;
        this.modify = modify;
        this.apikey = apikey;
    }

}
