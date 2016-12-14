package org.muferobotics.olympics.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel // TODO: if you will store data, you should add @Parcel annotaion top of class like that
public class Announcement {

    @SerializedName("title")
    String aTitle;
    @SerializedName("description")
    String aDescription;
    @SerializedName("image")
    String image;
    @SerializedName("content")
    String content;
    @SerializedName("type")
    String type;
    @SerializedName("date")
    String date;



    /**
     * If you make parcelable this class, you should add empty contstuctor
     */
    public Announcement() {
    }

    public String getTitle() {
        return aTitle;
    }

    public void setTitle(String title) {
        this.aTitle = title;
    }

    public String getDescription() {
        return aDescription;
    }

    public void setDescription(String description) {
        this.aDescription = description;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


