package org.wei.food.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserOrderInfo extends DomainObject {
    @Column
    private String userOrderName;
    @Column
    private Integer userOrderSize;
    @Column
    private Double userOrderMoney;
    @OneToMany
    private List<OrderInfo> orderInfoList = new ArrayList<>();
}
