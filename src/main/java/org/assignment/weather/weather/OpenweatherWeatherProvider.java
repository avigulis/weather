package org.assignment.weather.weather;

import java.math.BigDecimal;

import org.assignment.weather.application.WeatherDto;
import org.assignment.weather.application.WeatherProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class OpenweatherWeatherProvider implements WeatherProvider {

    private final OpenweatherClient client;

    private final ConversionService conversionService;

    private final String apiKey;

    public OpenweatherWeatherProvider(OpenweatherClient client,
                                      ConversionService conversionService,
                                      @Value("${weather.providers.weather.api-key}") String apiKey) {
        this.client = client;
        this.conversionService = conversionService;
        this.apiKey = apiKey;
    }

    @Cacheable("openweather")
    @Override
    public WeatherDto provide(BigDecimal latitude, BigDecimal longitude) {
        OpenweatherResponse response = client.weatherBy(latitude, longitude, "metric", apiKey);
        return conversionService.convert(response, WeatherDto.class);
    }
}
