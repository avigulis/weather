package org.assignment.weather.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class WeatherResponse {

    private final String temperature;

    private final String units;

    private final String humidity;

    private final String pressure;

}
