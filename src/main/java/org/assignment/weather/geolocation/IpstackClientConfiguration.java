package org.assignment.weather.geolocation;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.context.annotation.Bean;

import feign.Retryer;

class IpstackClientConfiguration {

    @Bean
    Retryer ipstackRetryer() {
        return new Retryer.Default(100 , SECONDS.toMillis(5) ,3);
    }

    @Bean
    IpstackClientFallBackFactory ipstackClientFallBackFactory() {
        return new IpstackClientFallBackFactory();
    }
}
