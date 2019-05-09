package org.assignment.weather.api

import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import javax.validation.ConstraintValidatorContext

class IpAddressValidatorSpec extends Specification {

    ConstraintValidatorContext context = Stub()

    @Subject
    IpAddressValidator validator = new IpAddressValidator()

    @Unroll
    def "should be isValid=#isValid when ip is '#ip'"() {
        expect:
            validator.isValid(ip, context) == isValid

        where:
            ip                || isValid
            ''                || false
            'a.b.c.x'         || false
            'n.0.0.0'         || false
            '0.0.0'           || false
            '0.0.0.0'         || true
            '255.255.255.255' || true
            '254.1.2.3'       || true
            '125.65.1.255'    || true
    }
}
