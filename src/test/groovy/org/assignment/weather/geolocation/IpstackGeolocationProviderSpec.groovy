package org.assignment.weather.geolocation

import org.assignment.weather.application.GeolocationDto
import org.springframework.core.convert.ConversionService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject


class IpstackGeolocationProviderSpec extends Specification {

    @Shared String apiKey = 'any key'
    @Shared String ip = '1.2.3.4'

    IpstackClient client = Mock()

    ConversionService conversionService = Mock()

    @Subject
    IpstackGeolocationProvider provider = new IpstackGeolocationProvider(client, conversionService, apiKey)


    def 'should call ipstack and convert response to dto'() {
        setup:
            IpstackResponse emptyResponse = IpstackResponse.builder().build()

        when:
            provider.provide(ip)

        then:
            1 * client.geolocationByIp(ip, apiKey) >> emptyResponse
            1 * conversionService.convert(emptyResponse, GeolocationDto)
    }
}
