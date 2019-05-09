package org.assignment.weather

import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles('test')
abstract class IntegrationSpec extends Specification {
}
