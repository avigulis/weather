package org.assignment.weather.application

import org.assignment.weather.model.GeolocationQuery
import spock.lang.Specification
import spock.lang.Subject


class GeolocationDtoToGeolocationQueryConverterSpec extends Specification {

    @Subject
    GeolocationDtoToGeolocationQueryConverter converter = new GeolocationDtoToGeolocationQueryConverter()

    def 'should convert one type to anather'() {
        given:
            GeolocationDto expected = GeolocationDto.builder()
                    .ipAddress('1.2.3.4')
                    .latitude(10.3456G)
                    .longitude(-4.678945G)
                    .build()

        when:
            GeolocationQuery actual = converter.convert(expected)

        then:
            with(actual) {
                ipAddress == expected.ipAddress
                latitude == expected.latitude
                longitude == expected.longitude
            }
    }
}
