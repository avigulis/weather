package org.assignment.weather.application;

import static lombok.AccessLevel.PRIVATE;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class GeolocationDto {

    private final String ipAddress;

    private final BigDecimal latitude;

    private final BigDecimal longitude;

    private final String response;
}
