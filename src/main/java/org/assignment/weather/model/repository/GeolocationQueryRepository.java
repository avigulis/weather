package org.assignment.weather.model.repository;

import org.assignment.weather.model.GeolocationQuery;
import org.springframework.data.repository.CrudRepository;

public interface GeolocationQueryRepository extends CrudRepository<GeolocationQuery, Long> {
}
