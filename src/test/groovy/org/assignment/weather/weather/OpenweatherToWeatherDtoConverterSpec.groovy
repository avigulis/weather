package org.assignment.weather.weather

import com.fasterxml.jackson.databind.ObjectMapper
import org.assignment.weather.application.WeatherDto
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class OpenweatherToWeatherDtoConverterSpec extends Specification {

    @Shared ObjectMapper objectMapper = new ObjectMapper()

    @Subject
    OpenweatherToWeatherDtoConverter converter = new OpenweatherToWeatherDtoConverter(objectMapper)

    def 'should convert openweather response to weather dto'() {
        given:
            OpenweatherResponse expected = OpenweatherResponse.builder()
                    .name('Riga')
                    .cod('200')
                    .id('456172')
                    .coord(new OpenweatherResponse.Coord(24.1G, 56.95G))
                    .main(new OpenweatherResponse.Main(8.71D, 66, 1015, 8.33D, 9D))
                    .build()

        when:
            WeatherDto actual = converter.convert(expected)

        then:
            with(actual) {
                latitude == expected.coord.lat
                longitude == expected.coord.lon
                temperature == expected.main.temp
                units == 'metric'
                humidity == expected.main.humidity
                pressure == expected.main.pressure
                response
            }
    }
}
