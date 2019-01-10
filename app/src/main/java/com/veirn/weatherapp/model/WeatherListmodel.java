package com.veirn.weatherapp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherListmodel implements Parcelable {

    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<com.veirn.weatherapp.model.List> list = null;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<com.veirn.weatherapp.model.List> getList() {
        return list;
    }

    public void setList(java.util.List<com.veirn.weatherapp.model.List> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeString(this.cod);
        dest.writeValue(this.message);
        dest.writeValue(this.cnt);
        dest.writeTypedList(this.list);
    }

    public WeatherListmodel() {
    }

    protected WeatherListmodel(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.cod = in.readString();
        this.message = (Double) in.readValue(Double.class.getClassLoader());
        this.cnt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.list = in.createTypedArrayList(List.CREATOR);
    }

    public static final Parcelable.Creator<WeatherListmodel> CREATOR = new Parcelable.Creator<WeatherListmodel>() {
        @Override
        public WeatherListmodel createFromParcel(Parcel source) {
            return new WeatherListmodel(source);
        }

        @Override
        public WeatherListmodel[] newArray(int size) {
            return new WeatherListmodel[size];
        }
    };
}
