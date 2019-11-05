package com.mcs.luel.myweather.PojoClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList ;
import java.util.List;

public class PeriodPojo  implements Parcelable {


    List<PeriodDetailPojo> periods;

    protected PeriodPojo(Parcel in) {
        periods = in.createTypedArrayList(PeriodDetailPojo.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(periods);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PeriodPojo> CREATOR = new Creator<PeriodPojo>() {
        @Override
        public PeriodPojo createFromParcel(Parcel in) {
            return new PeriodPojo(in);
        }

        @Override
        public PeriodPojo[] newArray(int size) {
            return new PeriodPojo[size];
        }
    };

    public List<PeriodDetailPojo> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodDetailPojo> periods) {
        this.periods = periods;
    }
}
