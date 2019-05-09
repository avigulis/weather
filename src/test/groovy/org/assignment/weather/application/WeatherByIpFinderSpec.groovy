package org.assignment.weather.application

import org.assignment.weather.model.GeolocationQuery
import org.assignment.weather.model.WeatherQuery
import org.assignment.weather.model.repository.GeolocationQueryRepository
import org.assignment.weather.model.repository.WeatherQueryRepository
import org.springframework.core.convert.ConversionService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

class WeatherByIpFinderSpec extends Specification {

    @Shared String ip = '1.2.3.4'
    @Shared BigDecimal lon = 10.678876G
    @Shared BigDecimal lat = -3.566556G

    GeolocationProvider geolocationProvider = Mock()
    WeatherProvider weatherProvider = Mock()
    GeolocationQueryRepository geolocationQueryRepository = Mock()
    WeatherQueryRepository weatherQueryRepository = Mock()
    ConversionService conversionService = Mock()

    @Subject
    WeatherByIpFinder finder = new WeatherByIpFinder(
            geolocationProvider,
            weatherProvider,
            geolocationQueryRepository,
            weatherQueryRepository,
            conversionService
    )

    def 'should call each provider and save results'() {
        given:
            GeolocationDto geolocationDto = geolocationDto()
            GeolocationQuery geolocationQuery = new GeolocationQuery()
            WeatherDto weatherDto = WeatherDto.builder().build()
            WeatherQuery weatherQuery = new WeatherQuery()

        when:
            finder.find(ip)

        then:
            1 * geolocationProvider.provide(ip) >> geolocationDto
            1 * conversionService.convert(geolocationDto, GeolocationQuery) >> geolocationQuery
            1 * weatherProvider.provide(lat, lon) >> weatherDto
            1 * conversionService.convert(weatherDto, WeatherQuery) >> weatherQuery
            1 * weatherQueryRepository.save(weatherQuery)
    }

    @Unroll
    def 'should throw exception when ip is empty'() {
        when:
            finder.find(ipAddress)

        then:
            Exception ex = thrown(IllegalArgumentException)
            ex.message == 'ip address can not be empty'

        where:
            ipAddress << [null, '']
    }

    GeolocationDto geolocationDto() {
        return GeolocationDto.builder()
                .latitude(lat)
                .longitude(lon)
                .build()
    }
}

