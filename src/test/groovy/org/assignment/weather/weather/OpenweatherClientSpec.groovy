package org.assignment.weather.weather

import org.assignment.weather.WireMockSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static org.springframework.http.MediaType.*

@EnableFeignClients(clients = OpenweatherClient)
class OpenweatherClientSpec extends WireMockSpec {

    @Value('${weather.providers.weather.api-key}')
    String apiKey

    @Autowired
    OpenweatherClient client

    def 'should process response from openweather'() {
        given:
            BigDecimal lat = 56.9496G
            BigDecimal lon = 24.0978G
            String units = 'metric'

            wireMockRule.stubFor(get(urlPathEqualTo('/data/2.5/weather/'))
                    .withHeader('Accept', equalTo(APPLICATION_JSON_VALUE))
                    .withQueryParam('lat', equalTo(lat.toString()))
                    .withQueryParam('lon', equalTo(lon.toString()))
                    .withQueryParam('units', equalTo('metric'))
                    .willReturn(aResponse().withHeader('Content-Type', APPLICATION_JSON_VALUE)
                    .withBody(response('openweather_response.txt'))
                    .withStatus(200)))

            OpenweatherResponse expected = objectMapper.readValue(response('openweather_response.txt'), OpenweatherResponse)


        when:
            OpenweatherResponse actual = client.weatherBy(lat, lon, units, apiKey)

        then:
            actual == expected
    }
}
