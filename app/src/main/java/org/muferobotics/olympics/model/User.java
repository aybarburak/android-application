package org.muferobotics.olympics.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class User {
    @SerializedName("username")
    String userName;
    @SerializedName("email")
    String email;
    @SerializedName("user_id")
    Integer userId;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    @SerializedName("birthdate")
    String birthDate;
    @SerializedName("academy_information")
    String academyInformation;
    @SerializedName("phone_number")
    String phoneNumber;
    @SerializedName("city")
    String city;
    @SerializedName("country")
    String country;
    @SerializedName("image")
    String image;


    /**
     * If you make parclable this class, you should add empty contstuctor
     */
    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAcademyInformation() {
        return academyInformation;
    }

    public void setAcademyInformation(String academyInformation) {
        this.academyInformation = academyInformation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
