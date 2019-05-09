package org.assignment.weather.model;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.NONE;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "weather_queries")
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@ToString(of = {"id", "latitude", "longitude"})
@NoArgsConstructor
public class WeatherQuery extends BaseEntity {

    @Id
    @GeneratedValue(generator = "weather_queries_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "weather_queries_seq", sequenceName = "weather_queries_seq", allocationSize = 1)
    @Column(name = "weather_query_id", nullable = false)
    @Setter(NONE)
    private Long id;

    @Column(name = "latitude", nullable = false, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, scale = 6)
    private BigDecimal longitude;

    @Column(name = "temperature", nullable = false, scale = 2)
    private Double temperature;

    @Column(name = "units", nullable = false)
    private String units;

    @Column(name = "humidity", scale = 2)
    private Integer humidity;

    @Column(name = "pressure", scale = 2)
    private Integer pressure;

    @OneToOne(cascade = {PERSIST, MERGE, REFRESH}, fetch = LAZY)
    @JoinColumn(name = "geolocation_query_id")
    private GeolocationQuery geolocationQuery;

    @Column(name = "response", nullable = false)
    private String response;
}
