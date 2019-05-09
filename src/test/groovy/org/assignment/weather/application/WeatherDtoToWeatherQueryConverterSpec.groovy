package org.assignment.weather.application

import org.assignment.weather.model.WeatherQuery
import spock.lang.Specification
import spock.lang.Subject


class WeatherDtoToWeatherQueryConverterSpec extends Specification {

    @Subject
    WeatherDtoToWeatherQueryConverter converter = new WeatherDtoToWeatherQueryConverter()

    def 'should convert from one type to another'() {
        given:
            WeatherDto expected = WeatherDto.builder()
                    .longitude(24.1G)
                    .latitude(56.95g)
                    .temperature(8.71D)
                    .units('metric')
                    .humidity(66)
                    .pressure(1015)
                    .response('any text')
                    .build()

        when:
            WeatherQuery actual = converter.convert(expected)

        then:
            with(actual) {
                latitude == expected.latitude
                longitude == expected.longitude
                temperature == expected.temperature
                units == expected.units
                humidity == expected.humidity
                pressure == expected.pressure
                response == expected.response
            }
    }
}
