package org.wei.food.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Data
public class FootInfo extends DomainObject {
    @Column
    private String footName;
    @ManyToOne
    private FootType footType;
    @Column
    private Double price;
    @Column
    private Boolean visible;
}
