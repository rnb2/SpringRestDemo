package com.rnb.restDemo.service;

import com.rnb.restDemo.repository.WeatherRepository;
import com.rnb.restDemo.weather.WeatherModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WeatherServiceImpl implements WeatherService
{
  private final WeatherRepository weatherRepository;

  public WeatherServiceImpl(WeatherRepository weatherRepository)
  {
    this.weatherRepository = weatherRepository;
  }

  public Optional<WeatherModel> getWeather(double longtitude, double latitude, String city, String state, String country, LocalDateTime date)
  {
    return weatherRepository.getWeather(longtitude, latitude, date);
  }
}
