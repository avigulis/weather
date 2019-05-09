package org.assignment.weather.model.repository;

import org.assignment.weather.model.WeatherQuery;
import org.springframework.data.repository.CrudRepository;

public interface WeatherQueryRepository extends CrudRepository<WeatherQuery, Long> {

}
