package org.wei.food.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class OrderInfo extends DomainObject {
    @Column
    private String orderName;
    @Column
    private Double orderPrice;
    @Column
    private Boolean orderPay;
    @Column
    private Date createTime;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderFoot> orderFootList;
    @ManyToOne
    private UserOrderInfo userOrderInfo;

}
