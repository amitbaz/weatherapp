package com.example.weatherapp;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.viewmodel.ForecastViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
  private ForecastViewModel viewModel;
  private Forecast forecast;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    forecast = new Forecast();
    setupBinding(savedInstanceState);
  }

  private void setupBinding(Bundle savedInstanceState) {
    final ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    viewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
    if (savedInstanceState == null) {
      viewModel.init(forecast);
    }
    activityMainBinding.setLifecycleOwner(this);
    activityMainBinding.setForecast(forecast);
//    viewModel.getForecast().observe(this, new Observer<Forecast>() {
//      @Override
//      public void onChanged(Forecast forecast) {
//        Log.d("Forecast changed", Double.toString(forecast.getTemperature()));
//        //activityMainBinding.temperatureTv.setText(Double.toString(forecast.getTemperature()));
//      }
//    });
    //TODO get location from user
    viewModel.fetchLocationBasedForecast(52.451025561337474,13.338504036418149, "si");
  }
}
