package com.mcs.luel.myweather.PojoClasses;


import android.os.Parcel;
import android.os.Parcelable;

public class ForecastPojo implements Parcelable {


    PeriodPojo properties;

    protected ForecastPojo(Parcel in) {
    }

    public static final Creator<ForecastPojo> CREATOR = new Creator<ForecastPojo>() {
        @Override
        public ForecastPojo createFromParcel(Parcel in) {
            return new ForecastPojo(in);
        }

        @Override
        public ForecastPojo[] newArray(int size) {
            return new ForecastPojo[size];
        }
    };

    public PeriodPojo getPeriodPojo() {
        return properties;
    }

    public void setPeriodPojo(PeriodPojo periodPojo) {
        this.properties = periodPojo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(properties, flags);
    }
}


