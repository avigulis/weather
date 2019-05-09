package org.assignment.weather.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
class ApiError {

    @Singular
    private List<String> errors;

}
