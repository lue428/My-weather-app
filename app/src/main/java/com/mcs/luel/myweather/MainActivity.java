package com.mcs.luel.myweather;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import com.mcs.luel.myweather.PojoClasses.ForecastPojo;
import com.mcs.luel.myweather.PojoClasses.PeriodDetailPojo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NetTest {

    NetworkApi networkApi;
    double lon, lat;
    ForecastPojo DailyForecast, HourlyForecast;
    String TAG = "tag";
    DailyCustomAdapter adapter;
    RecyclerView dailyrv;
    RecyclerView hourlyrv;
    AppBarLayout appBarLayout;
    NestedScrollView scrollView;
    CollapsingToolbarLayout toolbarLayout;

    //Define details card items
    TextView tempDetailTv, windspeedDetailTv, windDirectionDetailTv, forecastDetailTv;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Location location = getLoaction();

        toolbarLayout = findViewById(R.id.toolbar_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        scrollView = findViewById(R.id.sv_container);
        appBarLayout = findViewById(R.id.appbar);
        dailyrv = findViewById(R.id.rv_daily );
        hourlyrv = findViewById(R.id.rv_hourly );
        lon = location.getLongitude();
        lat = location.getLatitude();
        tempDetailTv = findViewById(R.id.tv_temp_detail);
        windspeedDetailTv = findViewById(R.id.tv_windspeed_detail);
        windDirectionDetailTv = findViewById(R.id.wind_direction_detail);
        forecastDetailTv = findViewById(R.id.tv_forecast_detail);
        initNetworkCall();

    }


    @SuppressLint("MissingPermission") Location location;
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLoaction(){
        this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            final Snackbar snack = Snackbar.make(new View(MainActivity.this),
                    "location is required to display current weather",
                    Snackbar.LENGTH_INDEFINITE);
            snack.show();
            snack.setAction("Dismiss", new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    snack.dismiss();
                }
            });
        }

        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        return location;
    }

    private void initNetworkCall(){
        networkApi = retrofit.create(NetworkApi.class);
        networkApi.getDailyForecast(String.valueOf(lat),String.valueOf(lon))
                .enqueue(firstNetworkCall);
        networkApi.getHourlyForecast(String.valueOf(lat),String.valueOf(lon))
         .enqueue(hourlyNetworkCall);
    }

    public Callback<ForecastPojo> firstNetworkCall = new Callback<ForecastPojo>() {
        @Override
        public void onResponse(Call<ForecastPojo> call,
                               Response<ForecastPojo> response) {
            DailyForecast = response.body();
            Log.d(TAG, "response successful, current temp = " +
                    response.body().getPeriodPojo().getPeriods().get(0).getTemperature());

            adapter = new DailyCustomAdapter(response.body().getPeriodPojo().getPeriods());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                    LinearLayoutManager.HORIZONTAL,
                    false);
            dailyrv.setLayoutManager(linearLayoutManager);
            dailyrv.setAdapter(adapter);
        }

        @Override
        public void onFailure(Call<ForecastPojo> call, Throwable t) {
            Log.i(TAG, "=========onFailure: " + t.getMessage());
            Log.i(TAG, "=========onFailure: detail = " + t.getCause());
        }

    };

    public Callback<ForecastPojo> hourlyNetworkCall = new Callback<ForecastPojo>() {
        @Override
        public void onResponse(Call<ForecastPojo> call,
                               Response<ForecastPojo> response) {
            HourlyForecast = response.body();
            Log.d(TAG, "response successful, current temp = " +
                    response.body().getPeriodPojo().getPeriods().get(0).getTemperature());

            adapter = new DailyCustomAdapter(response.body().getPeriodPojo().getPeriods());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                    LinearLayoutManager.HORIZONTAL,
                    false);
            hourlyrv.setLayoutManager(linearLayoutManager);
            hourlyrv.setAdapter(adapter);
            toolbarLayout.setTitle(HourlyForecast.getPeriodPojo().getPeriods().get(0).getTemperature() +"\u2109"+
                    " " + getAddress());

            defineDetailsCardView(HourlyForecast);


        }

        @Override
        public void onFailure(Call<ForecastPojo> call, Throwable t) {
            Log.i(TAG, "=========onFailure: " + t.getMessage());
            Log.i(TAG, "=========onFailure: detail = " + t.getCause());
        }
    };

    public String getAddress() {
        String finalAddress= "";
        String addressStr ="";
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault()); //it is Geocoder
        StringBuilder builder = new StringBuilder();
        try {
            List<Address> address = geoCoder.getFromLocation(lat, lon, 1);
            addressStr = address.get(0).getLocality() +
            ", " + address.get(0).getCountryCode();

        } catch (IOException e) {}
        catch (NullPointerException e) {}

        return addressStr;
    }

    public void defineDetailsCardView(ForecastPojo hourlyForecast){
        PeriodDetailPojo period = hourlyForecast.getPeriodPojo().getPeriods().get(0);
        tempDetailTv.setText(period.getTemperature() + "\u2109");
        windDirectionDetailTv.setText(period.getWindDirection());
        windspeedDetailTv.setText(period.getWindSpeed());
        forecastDetailTv.setText(period.getShortForecast());

    }

}
