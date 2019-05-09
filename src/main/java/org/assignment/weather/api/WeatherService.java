package org.assignment.weather.api;

import org.assignment.weather.application.WeatherByIpFinder;
import org.assignment.weather.application.WeatherDto;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WeatherService {

    private final WeatherByIpFinder weatherByIpFinder;

    public WeatherResponse weather(String ipAddress) {
        WeatherDto weatherDto = weatherByIpFinder.find(ipAddress);
        return WeatherResponse.builder()
                .temperature(weatherDto.getTemperature().toString())
                .units(weatherDto.getUnits())
                .humidity(weatherDto.getHumidity().toString())
                .pressure(weatherDto.getPressure().toString())
                .build();
    }
}
