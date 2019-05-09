package org.assignment.weather

import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.assignment.weather.api.WeatherResponse
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.web.client.TestRestTemplate

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class HappyPathE2eSpec extends SpringBootSpec {

    @Rule
    WireMockRule wireMockRule = new WireMockRule(
            wireMockConfig().port(WireMockSpec.WIRE_MOCK_PORT),
            false
    )

    @Value('${weather.providers.geolocation.api-key}')
    String geolocationApiKey

    @Value('${weather.providers.weather.api-key}')
    String weatherApiKey

    @Autowired
    TestRestTemplate restTemplate

    def 'should send ip and get weather conditions'() {
        given:
            String ip = '195.13.230.100'
            String url = "http://localhost:$localPort/weather"

            BigDecimal lat = 56.9496G
            BigDecimal lon = 24.0978G

            wireMockRule.stubFor(get(urlPathEqualTo("/$ip"))
                    .withHeader('Accept', equalTo(APPLICATION_JSON_VALUE))
                    .withQueryParam('access_key', equalTo(geolocationApiKey))
                    .willReturn(aResponse().withHeader('Content-Type', APPLICATION_JSON_VALUE)
                    .withBody(response('ipstack_response.txt'))
                    .withStatus(200)))

            wireMockRule.stubFor(get(urlPathEqualTo('/data/2.5/weather/'))
                    .withHeader('Accept', equalTo(APPLICATION_JSON_VALUE))
                    .withQueryParam('lat', equalTo(lat.toString()))
                    .withQueryParam('lon', equalTo(lon.toString()))
                    .withQueryParam('units', equalTo('metric'))
                    .willReturn(aResponse().withHeader('Content-Type', APPLICATION_JSON_VALUE)
                    .withBody(response('openweather_response.txt'))
                    .withStatus(200)))

        when:
            WeatherResponse actual = restTemplate.getForObject("$url/$ip", WeatherResponse)

        then:
            with(actual) {
                temperature == '8.71'
                humidity == '66'
                pressure == '1015'
                units == 'metric'
            }
    }

    String response(String fileName) {
        return getClass().getResource("/stubs/$fileName").text
    }
}

