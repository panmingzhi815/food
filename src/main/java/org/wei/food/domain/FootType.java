package org.wei.food.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class FootType extends DomainObject{

    @Column
    private String footTypeName;

}
