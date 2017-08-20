package org.wei.food.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class UserInfo extends DomainObject{
    @Column
    private String username;
    @Column
    private String password;
}
