package org.assignment.weather.geolocation;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "ipstackClient", url = "${weather.providers.geolocation.url}",
        fallbackFactory = IpstackClientFallBackFactory.class,
        configuration = IpstackClientConfiguration.class
)
public interface IpstackClient {

    @GetMapping(value = "{ipAddress}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    IpstackResponse geolocationByIp(@PathVariable("ipAddress") String ipAddress, @RequestParam("access_key") String apiKey);
}
