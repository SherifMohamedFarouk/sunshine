package com.veirn.weatherapp;

import com.veirn.weatherapp.model.WeatherListmodel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface weatherface {
    @GET("/weather")
    Call<WeatherListmodel> getWeatherListmodelCall();
}
