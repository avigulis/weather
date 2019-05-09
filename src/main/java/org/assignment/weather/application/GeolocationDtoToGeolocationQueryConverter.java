package org.assignment.weather.application;

import org.assignment.weather.model.GeolocationQuery;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class GeolocationDtoToGeolocationQueryConverter implements Converter<GeolocationDto, GeolocationQuery> {

    @Override
    public GeolocationQuery convert(GeolocationDto source) {
        Assert.notNull(source, "Target type to convert to cannot be null");
        GeolocationQuery geolocationQuery = new GeolocationQuery();
        geolocationQuery.setIpAddress(source.getIpAddress());
        geolocationQuery.setLatitude(source.getLatitude());
        geolocationQuery.setLongitude(source.getLongitude());
        geolocationQuery.setResponse(source.getResponse());
        return geolocationQuery;
    }
}
