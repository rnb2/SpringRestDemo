package com.rnb.restDemo.weather;

import java.time.LocalDateTime;

public record WeatherRequest(double lat, double lon, String city, String state, String country, LocalDateTime dateTime)
{
}
