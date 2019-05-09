package org.assignment.weather.weather;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(onConstructor = @__(@JsonCreator()))
@Builder
public class OpenweatherResponse {

    private final String name;

    private final String cod;

    private final String id;

    private final Coord coord;

    private final Main main;

    @Value
    static class Coord {
        private final BigDecimal lon;

        private final BigDecimal lat;
    }

    @Value
    static class Main {
        private final Double temp;

        private final Integer humidity;

        private final Integer pressure;

        @JsonProperty("temp_min")
        private final Double tempMin;

        @JsonProperty("temp_max")
        private final Double tempMax;
    }
}
