package org.assignment.weather.geolocation;

import org.assignment.weather.application.GeolocationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@Component
@AllArgsConstructor
public class IpstackToGeolocationDtoConverter implements Converter<IpstackResponse, GeolocationDto> {

    private final ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public GeolocationDto convert(IpstackResponse source) {
        Assert.notNull(source, "Target type to convert to cannot be null");
        return GeolocationDto.builder()
                .ipAddress(source.getIp())
                .latitude(source.getLatitude())
                .longitude(source.getLongitude())
                .response(objectMapper.writeValueAsString(source))
                .build();
    }
}
