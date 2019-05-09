package org.assignment.weather.application;

public interface GeolocationProvider {

    GeolocationDto provide(String ipAddress);

}
