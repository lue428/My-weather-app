package com.mcs.luel.myweather.PojoClasses;


import android.os.Parcel;
import android.os.Parcelable;

public class PeriodDetailPojo  implements Parcelable {

    int number;

    String name;

    String startTime;

    String endTime;

    boolean isDaytime;

    int temperature;

    char temperatureUnit;

    String windSpeed;

    String windDirection;

    String icon;

    String shortForecast;

    String detailedForecast;

    protected PeriodDetailPojo(Parcel in) {
        number = in.readInt();
        name = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        isDaytime = in.readByte() != 0;
        temperature = in.readInt();
        temperatureUnit = (char) in.readInt();
        windSpeed = in.readString();
        windDirection = in.readString();
        icon = in.readString();
        shortForecast = in.readString();
        detailedForecast = in.readString();
    }

    public static final Creator<PeriodDetailPojo> CREATOR = new Creator<PeriodDetailPojo>() {
        @Override
        public PeriodDetailPojo createFromParcel(Parcel in) {
            return new PeriodDetailPojo(in);
        }

        @Override
        public PeriodDetailPojo[] newArray(int size) {
            return new PeriodDetailPojo[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isDaytime() {
        return isDaytime;
    }

    public void setDaytime(boolean daytime) {
        isDaytime = daytime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public char getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(char temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeByte((byte) (isDaytime ? 1 : 0));
        dest.writeInt(temperature);
        dest.writeInt((int) temperatureUnit);
        dest.writeString(windSpeed);
        dest.writeString(windDirection);
        dest.writeString(icon);
        dest.writeString(shortForecast);
        dest.writeString(detailedForecast);
    }
}
