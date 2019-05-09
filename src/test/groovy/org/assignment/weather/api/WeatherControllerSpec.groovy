package org.assignment.weather.api

import org.assignment.weather.WebMvcSpec
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.RequestPostProcessor
import spock.lang.Shared

import static groovy.json.JsonOutput.*
import static org.springframework.http.MediaType.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class WeatherControllerSpec extends WebMvcSpec {

    @Shared WeatherResponse response = WeatherResponse.builder()
            .temperature("20.12")
            .units("metric")
            .humidity("89")
            .pressure("1013")
            .build()

    @SpringBean
    WeatherService service = Stub() {
        weather(_ as String) >> response
    }

    @Autowired
    MockMvc mockMvc

    @Autowired
    WeatherController controller

    def 'should return api error when invalid ip address'() {
        given:
            String ip = '123.45.2.300'

        expect:
            mockMvc.perform(get("/weather/{ip}", ip).accept(APPLICATION_JSON))
                    .andDo(log())
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(jsonPath('$.errors').exists())
                    .andExpect(jsonPath('$.errors.length()').value(1))
    }

    def 'should return valid response as json when valid ip address sent'() {
        given:
            String ip = '1.2.3.4'
            String expectedResponse =  toJson(response)

        expect:
            mockMvc.perform(get('/weather/{ip}', ip).accept(APPLICATION_JSON))
                .andDo(log())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(content().json(expectedResponse))
    }

    def 'should return valid response as json when ip address determined from request'() {
        given:
            String ip = '1.2.3.4'
            String expectedResponse =  toJson(response)

        expect:
            mockMvc.perform(get('/weather').accept(APPLICATION_JSON)
                    .with(remoteAddress(ip)))
                    .andDo(log())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                    .andExpect(content().json(expectedResponse))
    }

    private RequestPostProcessor remoteAddress(String ip) {
        return { MockHttpServletRequest request ->
            request.setRemoteAddr(ip)
            return request
        }
    }
}
