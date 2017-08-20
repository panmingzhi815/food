package org.wei.food.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.wei.food.domain.OrderInfo;
import org.wei.food.domain.UserInfo;

import java.util.List;

@Repository
public interface OrderInfoRepository extends PagingAndSortingRepository<OrderInfo,Long> {

    List<OrderInfo> findByOrderPay(boolean orderPay);

    @Query("select sum(oi.orderPrice) from OrderInfo oi where oi.userOrderInfo is null")
    Double sumOrderPrice();

    List<OrderInfo> findByUserOrderInfoIsNull();

}
