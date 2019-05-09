package org.assignment.weather.model.repository

import org.assignment.weather.DataJpaSpec
import org.assignment.weather.model.GeolocationQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

class GeolocationQueryRepositorySpec extends DataJpaSpec {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    GeolocationQueryRepository repository

    def 'should save entity'() {
        given:
            GeolocationQuery actual = repository.save(geolocationQuery())

        expect:
            actual
            GeolocationQuery expected = entityManager.find(GeolocationQuery, actual.getId())
            expected == actual

    }

    def 'should fetch saved entity'() {
        given:
            GeolocationQuery actual = entityManager.persistAndFlush(geolocationQuery())

        expect:
            repository.existsById(actual.getId())
    }

    GeolocationQuery geolocationQuery() {
        GeolocationQuery geolocationQuery = new GeolocationQuery()
        geolocationQuery.with {
            ipAddress = '1.2.3.4'
            latitude = 2.567844G
            longitude = -5.225541G
            response = 'any another text'
        }
        return geolocationQuery
    }
}
