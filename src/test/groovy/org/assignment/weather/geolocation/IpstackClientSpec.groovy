package org.assignment.weather.geolocation

import org.assignment.weather.WireMockSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.openfeign.EnableFeignClients

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static org.springframework.http.MediaType.*

@EnableFeignClients(clients = IpstackClient)
class IpstackClientSpec extends WireMockSpec {

    @Value('${weather.providers.geolocation.api-key}')
    String apiKey

    @Autowired
    IpstackClient client

    def 'should process response from ipstack'() {
        given:
            String expectedIP = '1.2.3.4'
            wireMockRule.stubFor(get(urlPathEqualTo("/$expectedIP"))
                    .withHeader('Accept', equalTo(APPLICATION_JSON_VALUE))
                    .withQueryParam('access_key', equalTo(apiKey))
                    .willReturn(aResponse().withHeader('Content-Type', APPLICATION_JSON_VALUE)
                    .withBody(response('ipstack_response.txt'))
                    .withStatus(200)))

            IpstackResponse expected = objectMapper.readValue(response('ipstack_response.txt'), IpstackResponse)

        when:
            IpstackResponse actual = client.geolocationByIp(expectedIP, apiKey)

        then:
            actual == expected
    }
}
