package org.assignment.weather.geolocation;

import org.assignment.weather.application.GeolocationDto;
import org.assignment.weather.application.GeolocationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class IpstackGeolocationProvider implements GeolocationProvider {

    private final IpstackClient ipstackClient;

    private final ConversionService conversionService;

    private final String apiKey;

    public IpstackGeolocationProvider(IpstackClient ipstackClient,
                                      ConversionService conversionService,
                                      @Value("${weather.providers.geolocation.api-key}") String apiKey) {
        this.ipstackClient = ipstackClient;
        this.conversionService = conversionService;
        this.apiKey = apiKey;
    }

    @Cacheable("ipstack")
    @Override
    public GeolocationDto provide(String ipAddress) {
        IpstackResponse response = ipstackClient.geolocationByIp(ipAddress, apiKey);
        return conversionService.convert(response, GeolocationDto.class);
    }
}
