package org.assignment.weather.model;

import static lombok.AccessLevel.NONE;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "geolocation_queries")
@NoArgsConstructor
@ToString(of = {"id", "ipAddress"})
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class GeolocationQuery extends BaseEntity {

    @Id
    @GeneratedValue(generator = "geolocation_queries_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "geolocation_queries_seq", sequenceName = "geolocation_queries_seq", allocationSize = 1)
    @Column(name = "geolocation_query_id", nullable = false)
    @Setter(NONE)
    private Long id;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "latitude", nullable = false, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, scale = 6)
    private BigDecimal longitude;

    @Column(name = "response", nullable = false)
    private String response;

}
