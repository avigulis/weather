package org.assignment.weather.geolocation;

import org.assignment.weather.application.ServiceUnavailable;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IpstackClientFallBackFactory implements FallbackFactory<IpstackClient> {

    @Override
    public IpstackClient create(Throwable cause) {
        return (ipAddress, apiKey) -> {
            log.error("ipstack fallback reason: ", cause.getMessage());
            throw new ServiceUnavailable("ipstack, service unavailable", cause);
        };
    }
}
