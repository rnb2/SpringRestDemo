package com.rnb.restDemo.rest;

import com.rnb.restDemo.service.WeatherServiceImpl;
import com.rnb.restDemo.weather.WeatherRequest;
import com.rnb.restDemo.weather.WeatherRespone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * run example:
 * POST http://localhost:8082/rnb/api/weather
 * Content-Type: application/json
 *
 * {
 *   "lat": 46.45877,
 *   "lon": 30.74128,
 *   "city": "Odessa",
 *   "state": "Oda",
 *   "country": "Ukrain",
 *   "dateTime": "2025-01-17T14:56:58.276"
 * }
 */

@RestController
@RequestMapping("/rnb/api")
public class WeatherController
{
  private final Logger log = LoggerFactory.getLogger(WeatherController.class);

  private final WeatherServiceImpl weatherFacade;

  public WeatherController(WeatherServiceImpl weatherFacade)
  {
    this.weatherFacade = weatherFacade;
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addCustomFormatter(new DateFormatter("dd/mm/yyyy HH:mm"));
  }

  @PostMapping("/weather")
  public WeatherRespone getWeather(@RequestBody WeatherRequest weatherRequest)
  {
    return weatherFacade.getWeather(weatherRequest.lon(), weatherRequest.lat(),
        weatherRequest.city(),
        weatherRequest.state(),
        weatherRequest.country(),
        weatherRequest.dateTime())
        .map(weatherModel ->
            new WeatherRespone(weatherModel.dateTime(), weatherModel.temperature(), weatherModel.humidity(), weatherModel.windSpeed(), weatherModel.windDirection()))
        .orElse(WeatherRespone.empty());
  }
}
