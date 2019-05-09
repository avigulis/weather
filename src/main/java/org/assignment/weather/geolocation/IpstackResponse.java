package org.assignment.weather.geolocation;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(onConstructor = @__(@JsonCreator()))
@Builder
public class IpstackResponse {

    private final String ip;

    private final String type;

    @JsonProperty("continent_name")
    private final String continentName;

    @JsonProperty("continent_code")
    private final String continentCode;

    @JsonProperty("region_code")
    private final String regionCode;

    @JsonProperty("region_name")
    private final String regionName;

    private final String city;

    private final String zip;

    private final BigDecimal latitude;

    private final BigDecimal longitude;

}
