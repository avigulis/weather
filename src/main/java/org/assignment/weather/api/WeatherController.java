package org.assignment.weather.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping(value = "/weather", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/{ipAddress}")
    public WeatherResponse weather(@PathVariable @IpAddress String ipAddress) {
        return weatherService.weather(ipAddress);
    }

    @GetMapping
    public WeatherResponse weather(HttpServletRequest request) {
        return weatherService.weather(request.getRemoteAddr());
    }
}
