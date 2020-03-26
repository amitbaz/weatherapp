package com.example.weatherapp.model;

import android.util.Log;

import com.example.weatherapp.BR;
import com.example.weatherapp.net.Api;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Forecast extends BaseObservable {
  String timeZone;
  double temperature;

  public Forecast(){
    timeZone = "";
    temperature = 0;
  }

  @Bindable
  public String getTimeZone() {
    return timeZone;
  }

  @Bindable
  public void setTimeZone(String timeZone) {
    this.timeZone = timeZone;
    notifyPropertyChanged(BR.timeZone);
  }

  @Bindable
  public double getTemperature() {
    return temperature;
  }

  @Bindable
  public void setTemperature(double temperature) {
    this.temperature = temperature;
    notifyPropertyChanged(BR.temperature);
  }

  public void fetchForecast(String key, double latitude, double longtitude, String unit, final Forecast f) {
    Log.d("Fetch process", "fetching forecast from server");
    Callback<Forecast> callback = new Callback<Forecast>() {
      @Override
      public void onResponse(Call<Forecast> call, Response<Forecast> response) {
        Forecast body = response.body();
        Log.d("body content", body.toString());
//        forecast.setTemperature(body.getTemperature());
//        forecast.setTimeZone(body.getTimeZone());
        f.setTimeZone(body.getTimeZone());
        f.setTemperature(body.getTemperature());
      }

      @Override
      public void onFailure(Call<Forecast> call, Throwable t) {
            Log.d("Fetch process error", call.request().url().encodedPath());
      }
    };
    Api.getApi().getForecast(key,latitude, longtitude,unit).enqueue(callback);
  }
}
