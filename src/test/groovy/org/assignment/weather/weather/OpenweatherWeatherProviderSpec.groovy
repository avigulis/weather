package org.assignment.weather.weather

import org.assignment.weather.application.WeatherDto
import org.springframework.core.convert.ConversionService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class OpenweatherWeatherProviderSpec extends Specification {

    @Shared String apiKey = 'any key'
    @Shared BigDecimal lon = 10.455678G
    @Shared BigDecimal lat = -3.655689G

    OpenweatherClient client = Mock()

    ConversionService conversionService = Mock()

    @Subject
    OpenweatherWeatherProvider provider = new OpenweatherWeatherProvider(client, conversionService, apiKey)

    def 'should call openweather and convert response to dto'() {
        given:
            OpenweatherResponse emptyResponse = OpenweatherResponse.builder().build()

        when:
            provider.provide(lat, lon)

        then:
            1 * client.weatherBy(lat, lon, 'metric', apiKey) >> emptyResponse
            1 * conversionService.convert(emptyResponse, WeatherDto)
    }
}
