package org.muferobotics.olympics.rest;

import org.json.JSONArray;
import org.muferobotics.olympics.model.Announcement;
import org.muferobotics.olympics.model.Result;
import org.muferobotics.olympics.model.Robot;
import org.muferobotics.olympics.model.rest.request.GetAnnouncementsRequest;
import org.muferobotics.olympics.model.rest.request.ListRobotsRequest;
import org.muferobotics.olympics.model.rest.request.LoginRequest;
import org.muferobotics.olympics.model.rest.request.ModifyUserRequest;
import org.muferobotics.olympics.model.rest.request.ResultsRequest;
import org.muferobotics.olympics.model.rest.response.UserResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface AppService {

    @POST("/user/login")
    void login(
            @Body LoginRequest loginRequest,
            Callback<UserResponse> callback);

    @POST("/user/get_announces")
    void getAnnouncements(
            @Body GetAnnouncementsRequest getAnnouncementsRequest,
            Callback<List<Announcement>> callback);

    @POST("/user/list_robots")
    void listRobots(
            @Body ListRobotsRequest listRobotsRequest,
            Callback<List<Robot>> callback);

    @POST("/user/modify_user")
    void modifyUser(
            @Body ModifyUserRequest modifyUserRequest,
            Callback<List<JSONArray>> callback);

    @POST("/user/results")
    void results(
            @Body ResultsRequest resultsRequest,
            Callback<List<Result>> callback);
}
