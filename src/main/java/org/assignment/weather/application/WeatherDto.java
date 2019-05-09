package org.assignment.weather.application;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class WeatherDto {

    private final BigDecimal latitude;

    private final BigDecimal longitude;

    private final Double temperature;

    private final String units;

    private final Integer humidity;

    private final Integer pressure;

    private final String response;

}
