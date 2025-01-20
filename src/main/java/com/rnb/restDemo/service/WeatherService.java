package com.rnb.restDemo.service;

import com.rnb.restDemo.weather.WeatherModel;

import java.time.LocalDateTime;
import java.util.Optional;

public interface WeatherService
{
  Optional<WeatherModel> getWeather(double longtitude, double latitude, String city, String state, String country, LocalDateTime date);
}
