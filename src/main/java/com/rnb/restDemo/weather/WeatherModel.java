package com.rnb.restDemo.weather;

import java.time.LocalDateTime;

public record WeatherModel(LocalDateTime dateTime, String temperature, String humidity, String windSpeed, String windDirection)
{
}
