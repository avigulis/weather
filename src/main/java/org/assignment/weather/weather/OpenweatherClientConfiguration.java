package org.assignment.weather.weather;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

public class OpenweatherClientConfiguration {

    @Bean
    Retryer openweatherRetryer() {
        return new Retryer.Default(100 , SECONDS.toMillis(5) ,3);
    }

    @Bean
    OpenweatherClientFallBackFactory openweatherClientFallBackFactory() {
        return new OpenweatherClientFallBackFactory();
    }

}
