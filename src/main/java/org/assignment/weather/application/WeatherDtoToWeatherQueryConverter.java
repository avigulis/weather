package org.assignment.weather.application;

import org.assignment.weather.model.WeatherQuery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class WeatherDtoToWeatherQueryConverter implements Converter<WeatherDto, WeatherQuery> {

    @Override
    public WeatherQuery convert(WeatherDto source) {
        Assert.notNull(source, "Target type to convert to cannot be null");
        WeatherQuery weatherQuery = new WeatherQuery();
        weatherQuery.setLatitude(source.getLatitude());
        weatherQuery.setLongitude(source.getLongitude());
        weatherQuery.setTemperature(source.getTemperature());
        weatherQuery.setUnits(source.getUnits());
        weatherQuery.setHumidity(source.getHumidity());
        weatherQuery.setPressure(source.getPressure());
        weatherQuery.setResponse(source.getResponse());
        return weatherQuery;
    }
}
