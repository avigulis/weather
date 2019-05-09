package org.assignment.weather

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(initializers = WireMockSpec.WireMockInitializer)
abstract class SpringBootSpec extends IntegrationSpec {

    @LocalServerPort
    int localPort

}
