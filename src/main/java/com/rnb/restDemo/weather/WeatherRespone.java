package com.rnb.restDemo.weather;

import java.time.LocalDateTime;

public record WeatherRespone(LocalDateTime date, String temperature, String humidity, String windSpeed, String windDirection)
{

  private static final String NOT_FOUND = "not found";

  public static WeatherRespone empty()
  {
    return new WeatherRespone(LocalDateTime.now(), NOT_FOUND, NOT_FOUND, NOT_FOUND, NOT_FOUND);
  }
}
