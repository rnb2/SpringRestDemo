package com.rnb.restDemo.repository;

import com.rnb.restDemo.weather.WeatherModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class WeatherRepository
{
  public Optional<WeatherModel> getWeather(double lat, double lng, LocalDateTime dateTime)
  {
    return Optional.of(new WeatherModel(LocalDateTime.now(), "+5", "veter", "20 m//s", "South"));
  }
}
