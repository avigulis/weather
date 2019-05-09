package org.assignment.weather.geolocation

import com.fasterxml.jackson.databind.ObjectMapper
import org.assignment.weather.application.GeolocationDto
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

class IpstackToGeolocationDtoConverterSpec extends Specification {

    @Shared ObjectMapper objectMapper = new ObjectMapper()

    @Subject
    IpstackToGeolocationDtoConverter converter = new IpstackToGeolocationDtoConverter(objectMapper)

    def 'should convert ipstack response to geolocation dto'() {
        given:
            IpstackResponse expected = IpstackResponse.builder()
                                            .ip('1.2.3.4')
                                            .latitude(10.3456G)
                                            .longitude(-4.678945G)
                                            .build()

        when:
            GeolocationDto actual = converter.convert(expected)

        then:
            with(actual) {
                ipAddress == expected.ip
                latitude == expected.latitude
                longitude == expected.longitude
                response
            }
    }
}
