package com.example.weatherapp.viewmodel;

import android.app.Application;
import android.app.TaskStackBuilder;
import android.util.Log;

import com.example.weatherapp.R;
import com.example.weatherapp.model.Forecast;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ForecastViewModel extends AndroidViewModel {

  private Forecast forecast;

  public ForecastViewModel(@NonNull Application application) {
    super(application);
  }

  public void init(Forecast forecast){
    this.forecast = forecast;
    forecast.setTimeZone("kaki");
  }

  public void fetchLocationBasedForecast(double latitude, double longtitude, String unit){
    forecast.fetchForecast(getApplication().getResources().getString(R.string.api_key), latitude, longtitude, unit, forecast);

  }

  public Double getForecastTemperature(){
    return forecast.getTemperature();
  }

  public String getForecastTimeZone() { return forecast.getTimeZone(); }
}
