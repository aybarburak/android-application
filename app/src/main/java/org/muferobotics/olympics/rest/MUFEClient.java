package org.muferobotics.olympics.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class MUFEClient {
    private String BASE_URL = "http://api.muferobotics.org";

    private AppService appService;

    public MUFEClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(getGsonConvertor()))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        //request.addHeader("apikey", "key");
                    }
                })
                .build();

        appService = restAdapter.create(AppService.class);
    }

    public AppService getAppService() {
        return appService;
    }

    private Gson getGsonConvertor() {
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
