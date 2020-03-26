package com.example.weatherapp.net;

import android.util.Log;

import com.example.weatherapp.model.Forecast;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;

class JsonForcastDeserializer implements JsonDeserializer<Forecast> {
  @Override
  public Forecast deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    Forecast forecast = new Forecast();
    if(json.isJsonObject()){
      for(Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()){
        if(entry.getKey().equals("timezone")){
          Log.d("Test", "Object: key: " + entry.getKey() + " = " + entry.getValue());
          forecast.setTimeZone(entry.getValue().getAsString());
        }else if(entry.getKey().equals("currently")){
          JsonObject jsonObject = entry.getValue().getAsJsonObject();
          for(Map.Entry<String, JsonElement> e : jsonObject.entrySet()){
            if(e.getKey().equals("temperature")){
              forecast.setTemperature(e.getValue().getAsDouble());
              break;
            }
          }
        }
      }
    }
    return forecast;
  }
}
