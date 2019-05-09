package org.assignment.weather.application;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.emptyToNull;
import static java.util.Objects.nonNull;

import org.assignment.weather.model.GeolocationQuery;
import org.assignment.weather.model.WeatherQuery;
import org.assignment.weather.model.repository.GeolocationQueryRepository;
import org.assignment.weather.model.repository.WeatherQueryRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class WeatherByIpFinder {

    private final GeolocationProvider geolocationProvider;

    private final WeatherProvider weatherProvider;

    private final GeolocationQueryRepository geolocationQueryRepository;

    private final WeatherQueryRepository weatherQueryRepository;

    private final ConversionService conversionService;

    public WeatherDto find(String ipAddress) {
        checkArgument(nonNull(emptyToNull(ipAddress)), "ip address can not be empty");

        GeolocationDto geolocation = geolocationProvider.provide(ipAddress);
        GeolocationQuery geolocationQuery = conversionService.convert(geolocation, GeolocationQuery.class);
        geolocationQueryRepository.save(geolocationQuery);

        WeatherDto weather = weatherProvider.provide(geolocation.getLatitude(), geolocation.getLongitude());
        WeatherQuery weatherQuery = conversionService.convert(weather, WeatherQuery.class);
        weatherQuery.setGeolocationQuery(geolocationQuery);
        weatherQueryRepository.save(weatherQuery);

        return weather;
    }
}


