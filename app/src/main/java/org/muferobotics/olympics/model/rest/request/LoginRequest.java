package org.muferobotics.olympics.model.rest.request;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    String email;

    @SerializedName("password")
    String password;

    @SerializedName("apikey")
    String apikey;


    public LoginRequest(String email, String password, String apikey) {
        this.email = email;
        this.password = password;
        this.apikey = apikey;
    }
}
