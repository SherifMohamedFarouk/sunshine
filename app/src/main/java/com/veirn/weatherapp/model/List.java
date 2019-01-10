package com.veirn.weatherapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class List implements Parcelable {

    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Temp temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Integer deg;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.dt);
        dest.writeParcelable(this.temp, flags);
        dest.writeValue(this.pressure);
        dest.writeValue(this.humidity);
        dest.writeList(this.weather);
        dest.writeValue(this.speed);
        dest.writeValue(this.deg);
        dest.writeValue(this.clouds);
    }

    public List() {
    }

    protected List(Parcel in) {
        this.dt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.temp = in.readParcelable(Temp.class.getClassLoader());
        this.pressure = (Double) in.readValue(Double.class.getClassLoader());
        this.humidity = (Double) in.readValue(Double.class.getClassLoader());
        this.weather = new ArrayList<Weather>();
        in.readList(this.weather, Weather.class.getClassLoader());
        this.speed = (Double) in.readValue(Double.class.getClassLoader());
        this.deg = (Integer) in.readValue(Integer.class.getClassLoader());
        this.clouds = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<List> CREATOR = new Parcelable.Creator<List>() {
        @Override
        public List createFromParcel(Parcel source) {
            return new List(source);
        }

        @Override
        public List[] newArray(int size) {
            return new List[size];
        }
    };
}