package org.assignment.weather.weather;

import org.assignment.weather.application.ServiceUnavailable;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpenweatherClientFallBackFactory implements FallbackFactory<OpenweatherClient> {

    @Override
    public OpenweatherClient create(Throwable cause) {
        return (latitude, longitude, units, apiKey) -> {
            log.error("openweather fallback reason: ", cause.getMessage());
            throw new ServiceUnavailable("openweather, service unavailable", cause);
        };
    }
}
