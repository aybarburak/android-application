package org.muferobotics.olympics.model.rest.response;

import com.google.gson.annotations.SerializedName;

import org.muferobotics.olympics.model.User;

public class UserResponse {
    @SerializedName("key")
    String apiSecret;
    @SerializedName("user_data")
    User user;

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public User getUser() {
        return user;
    }
}
