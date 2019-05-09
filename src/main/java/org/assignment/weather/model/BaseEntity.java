package org.assignment.weather.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Version
    @Column(name = "entity_version", nullable = false)
    private Long version;

    @CreationTimestamp
    @Column(name = "entity_created", nullable = false, updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "entity_updated", nullable = false)
    private LocalDateTime updated;

}
