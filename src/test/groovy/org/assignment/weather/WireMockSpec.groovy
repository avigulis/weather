package org.assignment.weather

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import static org.springframework.util.SocketUtils.findAvailableTcpPort

@SpringBootTest
@ContextConfiguration(initializers = WireMockInitializer, classes = FeignTestConfiguration)
abstract class WireMockSpec extends IntegrationSpec {

    public static final int WIRE_MOCK_PORT = findAvailableTcpPort()

    @Shared ObjectMapper objectMapper = new ObjectMapper()

    @Rule
    WireMockRule wireMockRule = new WireMockRule(
            wireMockConfig().port(WIRE_MOCK_PORT),
            false
    )

    static class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of("wiremock.server.port=${WIRE_MOCK_PORT}")
                    .applyTo(context)
        }
    }

    @Configuration
    @ImportAutoConfiguration([
            FeignAutoConfiguration,
            HttpMessageConvertersAutoConfiguration
            ])
    static class FeignTestConfiguration {
    }

    String response(String fileName) {
        return getClass().getResource("/stubs/$fileName").text
    }
}
