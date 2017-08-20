package org.wei.food.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class OrderFoot extends DomainObject {
    @Column
    private String footName;
    @Column
    private String footLa;
    @Column
    private Double price;
}
