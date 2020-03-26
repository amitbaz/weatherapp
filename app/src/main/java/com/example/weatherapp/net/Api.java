package com.example.weatherapp.net;

import com.example.weatherapp.model.Forecast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Api {
  public static ForecastApiInterface api;
  public static final String BASE_URL="https://api.darksky.net";

  public static ForecastApiInterface getApi(){
    if(api == null){
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(logging)
        .build();
      Gson gson = new GsonBuilder()
        .registerTypeAdapter(
          Forecast.class,
          new JsonForcastDeserializer()
        ).create();

      Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();

      api = retrofit.create(ForecastApiInterface.class);
    }
    return api;
  }

  public interface ForecastApiInterface {

    @GET("forecast/{key}/{lat},{long}")
    Call<Forecast> getForecast(@Path("key") String key,
                               @Path("lat") double latitude, @Path("long") double longtitude,
                               @Query("units") String units);
  }
}
