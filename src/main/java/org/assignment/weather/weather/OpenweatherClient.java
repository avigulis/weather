package org.assignment.weather.weather;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "openweatherClient", url = "${weather.providers.weather.url}",
        fallbackFactory = OpenweatherClientFallBackFactory.class,
        configuration = OpenweatherClientConfiguration.class
)
public interface OpenweatherClient {

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    OpenweatherResponse weatherBy(
            @RequestParam("lat") BigDecimal latitude,
            @RequestParam("lon") BigDecimal longitude,
            @RequestParam("units") String units,
            @RequestParam("APPID") String apiKey);
}
