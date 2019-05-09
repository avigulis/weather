package org.assignment.weather.model.repository

import org.assignment.weather.DataJpaSpec
import org.assignment.weather.model.GeolocationQuery
import org.assignment.weather.model.WeatherQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

class WeatherQueryRepositorySpec extends DataJpaSpec {

    @Autowired
    TestEntityManager entityManager

    @Autowired
    WeatherQueryRepository repository

    def 'should save entity'() {
        given:
            WeatherQuery actual = repository.save(weatherQuery())
            entityManager.flush()

        expect:
            actual
            WeatherQuery expected = entityManager.find(WeatherQuery, actual.getId())
            expected == actual

    }

    def 'should fetch saved entity'() {
        given:
            WeatherQuery actual = entityManager.persistAndFlush(weatherQuery())

        expect:
            repository.existsById(actual.getId())
    }

    def 'should save cascade'() {
        given:
            WeatherQuery actual = weatherQuery(geolocationQuery())
            repository.save(actual)
            entityManager.flush()

        expect:
            WeatherQuery expected = entityManager.find(WeatherQuery, actual.getId())
            expected == actual
    }

    WeatherQuery weatherQuery(GeolocationQuery geoQuery = null) {
        WeatherQuery weatherQuery = new WeatherQuery()
        weatherQuery.with {
            latitude = 12.456789G
            longitude = 3.123321G
            temperature = 12.88D
            units = 'metric'
            humidity = 89
            pressure = 1013
            response = "any text"
            geolocationQuery = geoQuery
        }
        return weatherQuery
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
