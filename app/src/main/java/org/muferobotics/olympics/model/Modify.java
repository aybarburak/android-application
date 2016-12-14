package org.muferobotics.olympics.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Modify {
    @SerializedName("p_name")
    String p_name;
    @SerializedName("p_val")
    String p_value;

    public Modify() {
    }

    public Modify(String p_name, String p_value) {
        this.p_name = p_name;
        this.p_value = p_value;
    }
}
