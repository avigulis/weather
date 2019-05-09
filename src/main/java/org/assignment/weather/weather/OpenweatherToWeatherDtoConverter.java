package org.assignment.weather.weather;

import org.assignment.weather.application.WeatherDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Component
@AllArgsConstructor
public class OpenweatherToWeatherDtoConverter implements Converter<OpenweatherResponse, WeatherDto> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public WeatherDto convert(OpenweatherResponse source) {
        Assert.notNull(source, "Target type to convert to cannot be null");
        return WeatherDto.builder()
                .latitude(source.getCoord().getLat())
                .longitude(source.getCoord().getLon())
                .temperature(source.getMain().getTemp())
                .units("metric")
                .humidity(source.getMain().getHumidity())
                .pressure(source.getMain().getPressure())
                .response(objectMapper.writeValueAsString(source))
                .build();
    }
}
