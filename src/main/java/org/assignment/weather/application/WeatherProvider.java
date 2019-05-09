package org.assignment.weather.application;

import java.math.BigDecimal;

public interface WeatherProvider {

    WeatherDto provide(BigDecimal latitude, BigDecimal longitude);
}
